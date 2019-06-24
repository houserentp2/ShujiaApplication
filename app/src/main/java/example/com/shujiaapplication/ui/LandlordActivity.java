package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import example.com.shujiaapplication.R;
import example.com.shujiaapplication.model.HouseInfo;

public class LandlordActivity extends BaseActivity {

    private TabLayout tabLayout;
    public static final String[] mTabTitle = new String[]{"长租", "短租"};
    private Fragment[] mFragmensts;

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

//                //改变Tab 状态
//                for(int i=0;i< tabLayout.getTabCount();i++){
//                    //TODO
//                }
//                tabLayout.addTab(tabLayout.newTab().setText(mTabTitle[0]));
//                tabLayout.addTab(tabLayout.newTab().setText(mTabTitle[1]));

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

        // TODO: Rename and change types of parameters
        private int mPage;

        ArrayList<HouseInfo> houseInfos;

        public PageFragment() {
            // Required empty public constructor
        }

        // TODO: Rename and change types and number of parameters
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

            houseInfos = getHouseInfos();       //从服务器中获取数据，假设都是一样的

            return view;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            ListView listView = (ListView) getActivity().findViewById(R.id.id_listview);
            MyPageFragmentAdapter listAdapter = new MyPageFragmentAdapter();
            listView.setAdapter(listAdapter);
        }

        public ArrayList<HouseInfo> getHouseInfos() {
            ArrayList<HouseInfo> houseInfos = new ArrayList<>(3);

            houseInfos.add(new HouseInfo(getActivity().getDrawable(R.drawable.house_1), "房子1",
                    1400, 40, "临近地铁"));
            houseInfos.add(new HouseInfo(getActivity().getDrawable(R.drawable.house_2), "房子2",
                    1330, 42, "有电梯"));
            houseInfos.add(new HouseInfo(getActivity().getDrawable(R.drawable.house_1), "房子3",
                    1260, 35, "有网"));
            return houseInfos;
        }

        class MyPageFragmentAdapter extends BaseAdapter {


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
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null)
                {
                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    convertView = inflater.inflate(R.layout.fragment_landlord_item,parent,false);

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

                return convertView;
            }
        }
    }




}
