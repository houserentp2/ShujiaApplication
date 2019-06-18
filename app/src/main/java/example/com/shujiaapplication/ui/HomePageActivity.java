package example.com.shujiaapplication.ui;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;

import example.com.shujiaapplication.R;

public class HomePageActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private Fragment[]mFragmensts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_home_page);
        mFragmensts = DataGenerator.getFragments("TabLayout Tab");
        initView();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.bottom_tab_layout);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());

                //改变Tab 状态
                for(int i=0;i< mTabLayout.getTabCount();i++){
                    if(i == tab.getPosition()){
                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabResPressed[i]));
                    }else{
                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabRes[i]));
                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_home_selector)).setText(DataGenerator.mTabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_collect_selector)).setText(DataGenerator.mTabTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_order_selector)).setText(DataGenerator.mTabTitle[2]));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_my_selector)).setText(DataGenerator.mTabTitle[3]));

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

            case 2:
                fragment = mFragmensts[2];
                break;
            case 3:
                fragment = mFragmensts[3];
                break;
        }
        if(fragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
        }
    }
}
