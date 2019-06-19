package example.com.shujiaapplication.ui.MainFragment;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import example.com.shujiaapplication.R;
import example.com.shujiaapplication.ui.DataGenerator;

public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private TabLayout mTabLayout;
    private Fragment[]mFragmensts;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mFragmensts = DataGenerator.getHomeFragments("TabLayout Tab");
        initView();
    }

    private void initView() {
        mTabLayout = (TabLayout) getActivity().findViewById(R.id.top_tab_layout);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());

//                //改变Tab 状态
//                for(int i=0;i< mTabLayout.getTabCount();i++){
//                    if(i == tab.getPosition()){
//                        mTabLayout.getTabAt(i).;
//                    }else{
//                        mTabLayout.getTabAt(i).setText("aaa");
//                    }
//                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.addTab(mTabLayout.newTab().setText(DataGenerator.mTabTitleHome[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(DataGenerator.mTabTitleHome[1]));
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
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,fragment).commit();
            }
        }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
