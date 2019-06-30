package example.com.shujiaapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

import example.com.shujiaapplication.R;

public class LandlordActivity extends BaseActivity {

    private TabLayout tabLayout;
    public static final String[] mTabTitle = new String[]{"长租", "短租"};
    private Fragment[] mFragmensts;
    private static String responseData = "";
    private static List<BuildingListData>buildingListDatas = new ArrayList<>();
    private static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("requestData", Context.MODE_PRIVATE);
                responseData = preferences.getString("requestGetData","");
                Gson gson = new Gson();
                buildingListDatas = gson.fromJson(responseData,new TypeToken<List<Building>>(){}.getType());

                if(!responseData.equals("")){
                    //Toast.makeText(MyApplication.getContext(),"成功!",Toast.LENGTH_SHORT).show();
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
        mFragmensts = getFragments();
        initView();
    }

    private void initView() {
        tabLayout = (TabLayout)findViewById(R.id.id_tablayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.addTab(tabLayout.newTab().setText(mTabTitle[0]));
        tabLayout.addTab(tabLayout.newTab().setText(mTabTitle[1]));


        ImageButton button_add = (ImageButton)findViewById(R.id.id_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //添加房屋
                Intent intent = new Intent(MyApplication.getContext(), AddHouseActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity(intent);
            }
        });
    }

    private void onTabItemSelected(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = mFragmensts[0];
                break;
            case 1:
                fragment = mFragmensts[1];
                break;

        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.id_house_container, fragment).commit();
        }
    }

    private Fragment[] getFragments() {
        Fragment[] fragments = new Fragment[2];


        fragments[0] = PageFragment.newInstance(1);
        fragments[1] = PageFragment.newInstance(2);

        return fragments;
    }


    public static class PageFragment extends Fragment {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "ARG_PAGE";
        private boolean isRefreshData = false;

        private int mPage;

        ArrayList<BuildingListData> buildingListDatas = new ArrayList<>();

        public PageFragment() {
            // Required empty public constructor
        }
        @Override
        public void onResume() {
            if(isRefreshData)
            {
                Log.d("PageFragment","RefreshData");

                getBuildingListDatas();       //从服务器中获取数据
                isRefreshData = false;
            }
            super.onResume();
        }
        public static PageFragment newInstance(int page) {
            PageFragment fragment = new PageFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_PARAM1, page);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mPage = getArguments().getInt(ARG_PARAM1);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_landlord_page, container, false);

//            TextView textView = (TextView) view.findViewById(R.id.id_hello);
//            textView.setText("Fragment #" + mPage);

            getBuildingListDatas();

            return view;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            ListView listView = (ListView) getActivity().findViewById(R.id.id_listview);
            MyPageFragmentAdapter listAdapter = new MyPageFragmentAdapter();
            listView.setAdapter(listAdapter);

            //TODO 点击事件
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    BuildingListData buildingListData = buildingListDatas.get(i);
                    /////
                    /////////
                }
            });
        }

        public void getBuildingListDatas() {
            //TODO 修改为Building
            //ArrayList<HouseInfo> houseInfos = new ArrayList<>(3);

//            houseInfos.add(new HouseInfo(getActivity().getDrawable(R.drawable.house_1), "房子1",
//                    1400, 40, "临近地铁"));
//            houseInfos.add(new HouseInfo(getActivity().getDrawable(R.drawable.house_2), "房子2",
//                    1330, 42, "有电梯"));
//            houseInfos.add(new HouseInfo(getActivity().getDrawable(R.drawable.house_1), "房子3",
//                    1260, 35, "有网"));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DiscountData p = new DiscountData(AuthInfo.userid,AuthInfo.token);
                    RequsetData.requestData(p,"getmyputs");
                    Message message = new Message();
                    message.what = 0;
                    handler.sendMessage(message);
                }
            }).start();
        }

        class MyPageFragmentAdapter extends BaseAdapter {


            @Override
            public int getCount() {
                return buildingListDatas.size();
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
                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    convertView = inflater.inflate(R.layout.fragment_landlord_item,parent,false);

                    //convertView = getLayoutInflater().inflate(R.layout.layout_student_item,parent,false);
                }

                TextView textView = (TextView)convertView.findViewById(R.id.id_title);
                textView.setText(data.getTitle());

                textView = (TextView)convertView.findViewById(R.id.id_price);
                textView.setText("价钱：" + data.getPrice());

                textView = (TextView)convertView.findViewById(R.id.id_struct);
                textView.setText(data.getSquare() + "m^2");

                textView = (TextView)convertView.findViewById(R.id.id_time);
                textView.setText("时间：" + data.getTime());


                // 设置图标显示
                ImageView iconView = (ImageView)convertView.findViewById(R.id.id_picture);

                iconView.setImageDrawable(Base64Util.Base64ToDrawable(getActivity(),data.getPicture()));

                ImageButton button = (ImageButton)convertView.findViewById(R.id.id_edithouse);

                //View.OnClickListener

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //TODO 修改房屋界面...
                        Building building = new Building("",
                                "",
                                "",
                                "",
                                "",
                                "",
                                1,
                                2,
                                "",
                                "",
                                "",
                                "",
                                "",
                                "",
                                new String[]{""});
                        Intent intent = new Intent(MyApplication.getContext(), EditHouseActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                        intent.putExtra("houseid",building.getHouseid());
                        intent.putExtra("price",building.getPrice());
                        intent.putExtra("square",building.getSquare());
                        intent.putExtra("shi",building.getShiting().getShi());
                        intent.putExtra("ting",building.getShiting().getTing());
                        intent.putExtra("title",building.getTitle());
                        intent.putExtra("discription",building.getDescription());
                        intent.putExtra("province",building.getLocation().getProvince());
                        intent.putExtra("city",building.getLocation().getCity());
                        intent.putExtra("zone",building.getLocation().getZone());
                        intent.putExtra("path",building.getLocation().getPath());
                        intent.putExtra("pictures",building.getPictures());

                        startActivity(intent);
                    }
                });
                return convertView;
            }
        }
    }
}
