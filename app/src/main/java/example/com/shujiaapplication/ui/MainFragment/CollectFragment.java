package example.com.shujiaapplication.ui.MainFragment;

import android.os.Bundle;
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

import java.util.ArrayList;

import example.com.shujiaapplication.R;
import example.com.shujiaapplication.model.HouseInfo;

public class CollectFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


//    List<String> mTitle;
//    List<Fragment> mFragment;

    private TabLayout tabLayout;
    public static final String []mTabTitle = new String[]{"长租","短租"};
    private Fragment[] mFragmensts;

    public CollectFragment() {
        // Required empty public constructor
    }

    public static CollectFragment newInstance(String param1, String param2) {
        CollectFragment fragment = new CollectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获得两个界面的实例
        mFragmensts = getFragments();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collect, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }

    private void initView(){
        tabLayout =  getActivity().findViewById(R.id.id_tablayout);
        //Log.d("CollectFragment","找tablayout");
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
    }

    private void onTabItemSelected(int position){
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = mFragmensts[0];
                break;
            case 1:
                fragment = mFragmensts[1];
                break;

        }
        if(fragment!=null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.id_house_container,fragment).commit();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private Fragment[] getFragments(){
        Fragment[] fragments = new Fragment[2];


        fragments[0] = PageFragment.newInstance(1);
        fragments[1] = PageFragment.newInstance(2);

        return fragments;
    }

    public static class PageFragment extends Fragment {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "ARG_PAGE";

        private int mPage;
        private boolean isRefreshData = false;
        ArrayList<HouseInfo> houseInfos;

        public PageFragment() {
            // Required empty public constructor
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
            View view = inflater.inflate(R.layout.fragment_collect_page, container, false);

//            TextView textView = (TextView) view.findViewById(R.id.id_hello);
//            textView.setText("Fragment #" + mPage);

            Log.d("PageFragment","onCreateView");
            houseInfos = getHouseInfos();       //从服务器中获取数据
            return view;
        }
        @Override
        public void onResume() {
            if(isRefreshData)
            {
                Log.d("PageFragment","RefreshData");

                houseInfos = getHouseInfos();       //从服务器中获取数据
                isRefreshData = false;
            }
            super.onResume();
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            ListView listView = (ListView)getActivity().findViewById(R.id.id_listview);
            MyPageFragmentAdapter listAdapter = new MyPageFragmentAdapter();
            listView.setAdapter(listAdapter);

            //TODO 点击事件
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    HouseInfo houseInfo = houseInfos.get(i);
                    /////////
                }
            });
        }

        //TODO 获取信息
        public ArrayList<HouseInfo> getHouseInfos(){
            ArrayList<HouseInfo> houseInfos = new ArrayList<>(3);

            houseInfos.add(new HouseInfo(getActivity().getDrawable(R.drawable.house_1),"房子1",
                                        1400, 40,"临近地铁"));
            houseInfos.add(new HouseInfo(getActivity().getDrawable(R.drawable.house_2),"房子2",
                    1330, 42,"有电梯"));
            houseInfos.add(new HouseInfo(getActivity().getDrawable(R.drawable.house_1),"房子3",
                    1260, 35,"有网"));
            return houseInfos;
        }

        class MyPageFragmentAdapter extends BaseAdapter{


            @Override
            public int getCount() {
                return houseInfos.size();
            }

            @Override
            public Object getItem(int i) {
                return houseInfos.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                if(convertView == null)
                {
                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    convertView = inflater.inflate(R.layout.fragment_collect_item,parent,false);

                    //convertView = getLayoutInflater().inflate(R.layout.layout_student_item,parent,false);
                }

                //文字显示
                HouseInfo data = (HouseInfo)getItem(position);
                TextView textView = (TextView)convertView.findViewById(R.id.id_title);
                textView.setText(data.getTitle());

                textView = (TextView)convertView.findViewById(R.id.id_price);
                textView.setText("价钱：" + data.getPrice());

                textView = (TextView)convertView.findViewById(R.id.id_struct);
                textView.setText(data.getSquare() + "m^2");

                textView = (TextView)convertView.findViewById(R.id.id_discription);
                textView.setText("价钱：" + data.getDiscription());


                // 设置图标显示
                ImageView iconView = (ImageView)convertView.findViewById(R.id.id_picture);

                iconView.setImageDrawable(data.getPicture());

                ImageButton button = (ImageButton)convertView.findViewById(R.id.id_edithouse);

                //View.OnClickListener

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        houseInfos.remove(position);

                        //Fragment fragment = PageFragment.newInstance(mPage);
                        PageFragment.this.isRefreshData = true;
                        PageFragment.this.onResume();
                    }
                });


                return convertView;
            }
        }

    }
}
