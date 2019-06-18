package example.com.shujiaapplication.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import example.com.shujiaapplication.R;
import example.com.shujiaapplication.ui.MainFragment.CollectFragment;
import example.com.shujiaapplication.ui.MainFragment.HomeFragment;
import example.com.shujiaapplication.ui.MainFragment.MyFragment;
import example.com.shujiaapplication.ui.MainFragment.OrderFragment;

public class DataGenerator {

    public static final int []mTabRes = new int[]{R.drawable.tab_home_selector,R.drawable.tab_collect_selector,R.drawable.tab_order_selector,R.drawable.tab_my_selector};
    public static final int []mTabResPressed = new int[]{R.drawable.tab_my_selected,R.drawable.tab_collect_selected,R.drawable.tab_order_selected,R.drawable.tab_order_selected};
    public static final String []mTabTitle = new String[]{"首页","收藏","订单","我的"};

    public static Fragment[] getFragments(String from){
        Fragment fragments[] = new Fragment[4];
        fragments[0] = HomeFragment.newInstance(from,from);
        fragments[1] = CollectFragment.newInstance(from,from);
        fragments[2] = OrderFragment.newInstance(from,from);
        fragments[3] = MyFragment.newInstance(from,from);
        return fragments;
    }

    /**
     * 获取Tab 显示的内容
     * @param context
     * @param position
     * @return
     */
    public static View getTabView(Context context, int position){
        View view = LayoutInflater.from(context).inflate(R.layout.home_tab_content,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}