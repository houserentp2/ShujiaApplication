package example.com.shujiaapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bilibili.boxing.impl.Base64Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import example.com.shujiaapplication.R;


public class LandlordActivity extends BaseActivity {

    private TabLayout tabLayout;
    private final static String TAG="LandlordActivity";
    public static final String[] mTabTitle = new String[]{"长租", "短租"};
    private static String responseData = "";
    private ListView mListView;
    static ArrayList<BuildingListData> buildingListDatas = new ArrayList<>();
    private static MyPageFragmentAdapter listAdapter;
    private boolean isRefreshData = false;

    //private static boolean havegotresponsedata = false;
    //private static List<BuildingListData>buildingListDatas = new ArrayList<>();
    private static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("requestData", Context.MODE_PRIVATE);
                responseData = preferences.getString("requestGetData","");
                if(!(responseData.equals("")||responseData.equals("Invalid Token") ||responseData == "null\n")){
                    Gson gson = new Gson();
                    buildingListDatas = gson.fromJson(responseData,new TypeToken<List<BuildingListData>>(){}.getType());
                    //havegotresponsedata = true;
                    listAdapter.notifyDataSetChanged();
                    Toast.makeText(MyApplication.getContext(),"获得房屋成功!",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(MyApplication.getContext(), ShowBuildListActivity.class);
//                    intent.putExtra("houseInformation",responseData);
//                    MyApplication.getContext().startActivity(intent);

                }else{
                    Toast.makeText(MyApplication.getContext(),responseData,Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlord);
        initView();
        getBuildingListDatas();

    }

    private void initView() {

        ListView listView = (ListView)findViewById(R.id.id_listview);
        listAdapter = new MyPageFragmentAdapter();
        listView.setAdapter(listAdapter);

        //TODO 点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BuildingListData buildingListData = buildingListDatas.get(i);
                buildingListData.getHouseid();
                //房屋详情
                /////////
            }
        });
        ImageButton button_add = (ImageButton)findViewById(R.id.id_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //添加房屋
                Intent intent = new Intent(LandlordActivity.this, AddHouseActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivityForResult(intent,1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && data != null){
            String result = data.getExtras().getString("refresh");//得到新Activity 关闭后返回的数据
            if (result == "true"){
                isRefreshData = true;
                onResume();
//                listAdapter.notifyDataSetChanged();
            }
        }

        //Log.i(TAG, result);
    }
    public void getBuildingListDatas() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                DiscountData data = new DiscountData(AuthInfo.userid,AuthInfo.token);
                RequsetData.requestData(data,"getmyputs");
                Message message = new Message();
                message.what = 0;
                handler.sendMessage(message);
            }
        }).start();
    }

    @Override
    public void onResume() {
        if(buildingListDatas == null)
        {
            buildingListDatas = new ArrayList<>();
        }
        if(isRefreshData)
        {
            Log.d("PageFragment","RefreshData");

            getBuildingListDatas();       //从服务器中获取数据
            isRefreshData = false;
            listAdapter.notifyDataSetChanged();
        }
        //adapter.notifyDataSetChanged();
        super.onResume();
    }
    class MyPageFragmentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return buildingListDatas.size();
        }

        @Override
        public void notifyDataSetChanged()
        {
            if(buildingListDatas != null){
                super.notifyDataSetChanged();
            }
        }
        @Override
        public Object getItem(int i) {
            return buildingListDatas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //文字显示
            BuildingListData data = (BuildingListData) getItem(position);

            if(convertView == null)
            {
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.fragment_landlord_item,parent,false);

                //convertView = getLayoutInflater().inflate(R.layout.layout_student_item,parent,false);
            }

            TextView textView = (TextView)convertView.findViewById(R.id.id_title);
            textView.setText(data.getTitle());

            textView = (TextView)convertView.findViewById(R.id.id_price);
            textView.setText("价钱：" + data.getPrice());

            textView = (TextView)convertView.findViewById(R.id.id_struct);
            textView.setText("大小：" + data.getSquare() + "m^2");

            //指定格式化格式
            //"2019-06-26T21:56:02.455+08:00"
            SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            String timeStr = data.getTime();
            timeStr = timeStr.substring(0, timeStr.length()-3)+timeStr.substring(timeStr.length()-2,timeStr.length());
            Date d = null;
            try {
                d = f.parse(timeStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            f = new SimpleDateFormat("yyyy-MM-dd");

            textView = (TextView)convertView.findViewById(R.id.id_time);
            textView.setText("时间：" + f.format(d));


            // 设置图标显示
            ImageView iconView = (ImageView)convertView.findViewById(R.id.id_picture);

            iconView.setImageDrawable(Base64Util.Base64ToDrawable(LandlordActivity.this,data.getPicture()));

            ImageButton button = (ImageButton)convertView.findViewById(R.id.id_edithouse);

            //View.OnClickListener

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO 修改房屋界面...
//                    Building building = new Building("",
//                            "",
//                            "",
//                            "",
//                            "",
//                            "",
//                            1,
//                            2,
//                            "",
//                            "",
//                            "",
//                            "",
//                            "",
//                            "",
//                            new String[]{""});
                    BuildingListData buildingListData = buildingListDatas.get(position);

                    Intent intent = new Intent(MyApplication.getContext(), EditHouseActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                    intent.putExtra("houseid",buildingListData.getHouseid());
//                    intent.putExtra("price",buildingListData.getPrice());
//                    intent.putExtra("square",buildingListData.getSquare());
//                    intent.putExtra("shi",buildingListData.getShiting().getShi());
//                    intent.putExtra("ting",buildingListData.getShiting().getTing());
//                    intent.putExtra("title",buildingListData.getTitle());
//                    intent.putExtra("discription",buildingListData.getDescription());
//                    intent.putExtra("province",buildingListData.getLocation().getProvince());
//                    intent.putExtra("city",buildingListData.getLocation().getCity());
//                    intent.putExtra("zone",buildingListData.getLocation().getZone());
//                    intent.putExtra("path",buildingListData.getLocation().getPath());
//                    intent.putExtra("pictures",buildingListData.getPictures());

                    startActivity(intent);
                    isRefreshData = true;
                }
            });
            return convertView;
        }
    }

//    public static class PageFragment extends Fragment {
//        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//        private static final String ARG_PARAM1 = "ARG_PAGE";
//        private boolean isRefreshData = false;
//        private static MyPageFragmentAdapter adapter;
//        private int mPage;
//
//
//        public PageFragment() {
//            // Required empty public constructor
//        }
//        @Override
//        public void onResume() {
//            if(isRefreshData)
//            {
//                Log.d("PageFragment","RefreshData");
//
//                getBuildingListDatas();       //从服务器中获取数据
//                isRefreshData = false;
//            }
//            //adapter.notifyDataSetChanged();
//            super.onResume();
//        }
//        public static PageFragment newInstance(int page) {
//            PageFragment fragment = new PageFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_PARAM1, page);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            if (getArguments() != null) {
//                mPage = getArguments().getInt(ARG_PARAM1);
//            }
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            // Inflate the layout for this fragment
//            View view = inflater.inflate(R.layout.fragment_landlord_page, container, false);
//
////            TextView textView = (TextView) view.findViewById(R.id.id_hello);
////            textView.setText("Fragment #" + mPage);
//
//            getBuildingListDatas();
//
//            return view;
//        }
//
//        @Override
//        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//            super.onActivityCreated(savedInstanceState);
//            //while (!havegotresponsedata){
////                try {
////                    Thread.sleep(500);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//            //}
//            ListView listView = (ListView) getActivity().findViewById(R.id.id_listview);
//            MyPageFragmentAdapter listAdapter = new MyPageFragmentAdapter();
//            listView.setAdapter(listAdapter);
//
//            //TODO 点击事件
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    BuildingListData buildingListData = buildingListDatas.get(i);
//                    /////
//                    /////////
//                }
//            });
//        }
//
//
//    }
}
