package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;

import example.com.shujiaapplication.R;

import static example.com.shujiaapplication.ui.DateChooseActivity.LONG_CHOOSE;
import static example.com.shujiaapplication.ui.DateChooseActivity.SHORT_CHOOSE;

public class HouseInfomationActivity extends BaseActivity {

    private ViewPager viewPager;
    private PagerAdapter adapter;
    private List<View> viewPages = new ArrayList<>();
    private GridView gridView;

    private TextView priceText;
    private TextView locationText;
    private MyImageView callView;
    private MyImageView collectView;
    private Button reserveButton;

    private Building house;

    private int housePeople;
    private int houseShi;
    private int houseTing;
    private int houseSquare;
    private List<Integer> picture_id;


    // 定义是否开启自动滚动，默认开启
    private boolean isAutoPlay = true;
    // 默认自动滚动任务延时两秒执行
    private int delayTime = 2500;
    // 定义处理自动滚动的handler
    private Handler handler = new Handler();
    // 当前ViewPager展示页
    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_infomation);
        getBuilding();
        initView();
        initPageAdapter();
        initEvent();
        startAutoPlay();

        List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
        String[] name = new String[]{"整套出租",houseShi+"室"+houseTing+"厅","宜居"+housePeople+"人",houseSquare+"平方米"};
        for(int i=0;i<4;i++){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("imageItem", R.drawable.tab_collect_selector);//添加图像资源的ID
            item.put("textItem", name[i]);//按序号添加ItemText
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(HouseInfomationActivity.this,items,R.layout.my_grid_item,new String[]{"imageItem", "textItem"},new int[]{R.id.image_item, R.id.text_item});
        gridView = (GridView)findViewById(R.id.house_information_grid);
        gridView.setAdapter(adapter);
    }

    private void getBuilding(){
        Intent intent = getIntent();
        house = (Building) intent.getSerializableExtra("selectedHouse");
        housePeople = house.getLiving_people();
        houseShi = house.getShi();
        houseTing = house.getTing();
        houseSquare = house.getSquare();
        picture_id = house.getPicture_id();
    }

    //绑定控件
    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.page_view);

        priceText = (TextView) findViewById(R.id.price_text);
        priceText.setText(house.getPrice()+"元/晚");

        locationText = (TextView) findViewById(R.id.location_text);
        locationText.setText(house.getTitle()+house.getProvince()+house.getCity()+house.getZone()+house.getPath());

        callView = (MyImageView) findViewById(R.id.call_view);
        collectView = (MyImageView) findViewById(R.id.collect_view);
        reserveButton = (Button) findViewById(R.id.reserve_button);
        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dateIntent = new Intent(HouseInfomationActivity.this,DateChooseActivity.class);
                dateIntent.putExtra("ChooseType",LONG_CHOOSE);
                startActivity(dateIntent);
            }
        });
    }

    //为控件绑定事件,绑定适配器
    private void initEvent() {
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new GuidePageChangeListener());
    }

    //初始化ViewPager
    private void initPageAdapter() {
        /**
         * 对于这几个想要动态载入的page页面，使用LayoutInflater.inflate()来找到其布局文件，并实例化为View对象
         */
        LayoutInflater inflater = LayoutInflater.from(this);
        for(int picture : picture_id){
            viewPages.add(buildLayout(picture));
        }

        adapter = new PagerAdapter() {
            //获取当前界面个数
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            //判断是否由对象生成页面
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewPages.get(position % viewPages.size()));
            }

            //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = viewPages.get(position % viewPages.size());
                container.addView(view);
                return view;
            }
        };
    }

    private LinearLayout buildLayout(int picture){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,300);
        LinearLayout layout = new LinearLayout(HouseInfomationActivity.this);
        layout.setLayoutParams(params);
        addView(layout,picture);
        return layout;
    }

    private void addView(final LinearLayout lineLayout ,int picture){
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        final ImageView iv = new ImageView(this);
        iv.setImageResource(picture);
//        iv.setImageDrawable(getResources().getDrawable(R.drawable.background));
        iv.setLayoutParams(vlp);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        lineLayout.addView(iv);
    }


    public class GuidePageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //页面滑动完成后执行
        @Override
        public void onPageSelected(int position) {
            //判断当前是在那个page，就把对应下标的ImageView原点设置为选中状态的图片
//            for (int i = 0; i < imageViews.length; i++) {
//                imageViews[position].setBackgroundResource(R.mipmap.page_indicator_focused);
//                if (position != i) {
//                    imageViews[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
//                }
//            }
        }

        //监听页面的状态，0--静止  1--滑动   2--滑动完成
        @Override
        public void onPageScrollStateChanged(int state) {
            if(state == 0){
                startAutoPlay();
            }
            else{
                pauseAutoPlay();
            }
        }
    }
    /**
     *   开启自动滚动的方法
     */
    private void startAutoPlay() {
        isAutoPlay = true;
        handler.removeCallbacks(task);
        handler.postDelayed(task, delayTime);
    }

    /**
     * 暂停自动滚动
     */
    public void pauseAutoPlay() {
        isAutoPlay = false;
        handler.removeCallbacks(task);
    }
    /**
     * 自动滚动核心代码
     */
    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            if (isAutoPlay) {
                // 自动滚动逻辑处理
                currentItem = viewPager.getCurrentItem();
                currentItem++;
                if(currentItem == adapter.getCount() -1) {
                    currentItem = 0;
                    viewPager.setCurrentItem(currentItem);
                    handler.postDelayed(this, delayTime);
                } else {
                    viewPager.setCurrentItem(currentItem);
                    handler.postDelayed(this, delayTime);
                }
            } else {
                handler.postDelayed(this, delayTime);
            }
        }
    };
}
