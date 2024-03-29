package example.com.shujiaapplication.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import example.com.shujiaapplication.R;

public class VerifyActivity extends BaseActivity {

    private ViewPager viewPager;
    private PagerAdapter adapter;
    private List<View> viewPages = new ArrayList<>();
    private GridView gridView;

    private TextView priceText;
    private TextView locationText;

    private Button verifyButton;
    private Button cancelButton;

    private Building house;

    private List<String> picture_id;


    private int result;
    // 定义是否开启自动滚动，默认开启
    private boolean isAutoPlay = true;
    // 默认自动滚动任务延时两秒执行
    private int delayTime = 2500;
    // 定义处理自动滚动的handler
    private Handler handler = new Handler();
    // 当前ViewPager展示页
    private int currentItem;
    private  String responseData = "";
    private static String responseStr = "";

    private Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.e("VerifyActivity","inMessage----------------");
            if(msg.what == 0){
                SharedPreferences preferences = getSharedPreferences("requestData", Context.MODE_PRIVATE);
                responseStr = preferences.getString("requestGetData","");
                Log.e("VerifyActivity","inMessage----------------"+responseStr);
                if(responseStr.contains("Success")){
                    Log.e("VerifyActivity","success!!!!!!!!"+responseStr);

                }
                else{
                    Log.e("VerifyActivity","success!!!!!!!!"+responseStr);
                    Toast.makeText(VerifyActivity.this,responseData,Toast.LENGTH_SHORT).show();
                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        getBuilding();
    }

    private void switchToResult(){
        Intent intent = new Intent(VerifyActivity.this,VerifyResultActivity.class);
        intent.putExtra("result",""+result);
        startActivity(intent);
    }

    private void init(){
        initView();
        initPageAdapter();
        initEvent();
        startAutoPlay();
        List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
        String[] name = new String[]{"整套出租",house.getShiting().getShi()+"室"+house.getShiting().getTing()+"厅","宜居"+house.getOthers().getStatus().getLiving()+"人",house.getSquare()+"平方米"};
        int[] pictures = new int[]{R.drawable.house_rent,R.drawable.shi_ting,R.drawable.living_people,R.drawable.square};
        for(int i=0;i<4;i++){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("imageItem", pictures[i]);//添加图像资源的ID
            item.put("textItem", name[i]);//按序号添加ItemText
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(VerifyActivity.this,items,R.layout.my_grid_item,new String[]{"imageItem", "textItem"},new int[]{R.id.image_item, R.id.text_item});
        gridView = (GridView)findViewById(R.id.house_information_grid);
        gridView.setAdapter(adapter);
    }

    private void getBuilding(){                    //得到数据库信息
        Intent intent = getIntent();
        responseData = intent.getStringExtra("checkerHouse");
        Gson gson = new Gson();
        house = gson.fromJson(responseData,Building.class);

        picture_id = Arrays.asList(house.getPictures());
        init();
    }
    //绑定控件
    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.page_view);

        priceText = (TextView) findViewById(R.id.price_text);
        priceText.setText(house.getPriceByInt()+"元/晚");

        locationText = (TextView) findViewById(R.id.location_text);
        locationText.setText(house.getTitle()+house.getLocation().getProvince()+house.getLocation().getCity()+house.getLocation().getZone()+house.getLocation().getPath());

        cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = 0;
                Log.e("VerifyActivity","cancelButton"+result);
                sendResult();
            }
        });

        verifyButton = (Button) findViewById(R.id.verify_button);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = 1;
                sendResult();
            }
        });
    }

    private void sendResult(){
        CheckerResultData check = new CheckerResultData(AuthInfo.userid,AuthInfo.token,house.getHouseid(),result);
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequsetData.requestData(check,"putcheckresult");
                Message message = new Message();
                message.what = 0;
                handler.sendMessage(message);
            }
        }).start();

        switchToResult();
    }

    //为控件绑定事件,绑定适配器
    private void initEvent() {
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new VerifyActivity.GuidePageChangeListener());
    }

    //初始化ViewPager
    private void initPageAdapter() {
        /**
         * 对于这几个想要动态载入的page页面，使用LayoutInflater.inflate()来找到其布局文件，并实例化为View对象
         */
        LayoutInflater inflater = LayoutInflater.from(this);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int newWidth = dm.widthPixels;
        int newHeight = 1000;
//        for(Bitmap picture : picture_id){
//            int bitwidth = picture.getWidth();
//            int bitheight = picture.getHeight();
//            float scaleWidth = ((float) newWidth) / bitwidth;
//            float scaleHeight = ((float) newHeight) / bitheight;
//            Matrix matrix = new Matrix();
//            matrix.postScale(scaleWidth, scaleHeight);
//            picture = Bitmap.createBitmap(picture, 0, 0, bitwidth, bitheight, matrix, true);
//            Bitmap bitmap = Bitmap.createBitmap(picture, 0,0, dm.widthPixels, newHeight);
//            viewPages.add(buildLayout(bitmap));
//        }
        for(String url : picture_id){
            viewPages.add(buildLayout(url));
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

    private LinearLayout buildLayout(String url){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,300);
        LinearLayout layout = new LinearLayout(VerifyActivity.this);
        layout.setLayoutParams(params);
        addView(layout,url);
        return layout;
    }

    private void addView(final LinearLayout lineLayout ,String url){
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        final ImageView iv = new ImageView(this);
        Glide.with(MyApplication.getContext())
                .load(url)
                .centerCrop()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(iv);

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

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}
