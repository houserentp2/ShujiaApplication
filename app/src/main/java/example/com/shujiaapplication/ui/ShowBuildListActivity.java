package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import example.com.shujiaapplication.R;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShowBuildListActivity extends BaseActivity implements View.OnClickListener {

    private List<BuildingListData> buildingList = new ArrayList<>();
    private DoubleSlideSeekBar mDoubleslideWithoutrule;
    private Button choosePrice;
    private Button chooseDate;
    private Button chooseOrder;
    private Button resetPrice;
    private Button surePrice;
    private LinearLayout pricePicker;
    private int order_mode = 0;
    private static int min = 0;
    private static int max = Integer.MAX_VALUE;
    private String responseStr = "";
    public static final MediaType JSON=MediaType.get("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_build_list);
        Intent intent = getIntent();
        String responses = intent.getStringExtra("getHouseList");
        Log.e("ShowBuildingLIst","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!案说法伽"+responses);
        responseStr = responses;
        initBuildList(1,0,Integer.MAX_VALUE);
        initControl();
        getMaxandMin();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chooseDate:{
                break;
            }

            case R.id.choosePrice:{
                pricePicker.setVisibility(View.VISIBLE);
                pricePicker.bringToFront();
                break;
            }
            case  R.id.chooseOrder:{
                order_mode++;
                if(order_mode%2==1){
                    chooseOrder.setText("价格降序");
                    initBuildList(1,min,max);
                }else{
                    chooseOrder.setText("价格升序");
                    initBuildList(2,min,max);
                }
                initAdapter();
                Toast.makeText(ShowBuildListActivity.this,"you click it",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.reset_button:{
                initBuildList(1,0,Integer.MAX_VALUE);
                initAdapter();
                choosePrice.setText("价格筛选");
                pricePicker.setVisibility(View.GONE);
                break;
            }

            case R.id.confirm_button:{                                               //get  min and max
                getMaxandMin();
                initBuildList(1,min,max);
                initAdapter();
                pricePicker.setVisibility(View.GONE);
                break;
            }
        }
    }

    public void getMaxandMin(){
        mDoubleslideWithoutrule.setOnRangeListener(new DoubleSlideSeekBar.onRangeListener() {
            @Override
            public void onRange(float low, float big) {
                choosePrice.setText((int)low+"--"+(int)(big-40));
                min = Integer.parseInt(String.format("%.0f" , low));
                max = Integer.parseInt(String.format("%.0f" , big-40));
                Log.e("ShowBuildListActivity","最低："+min+"最高："+max);
            }
        });
    }

    public void initControl(){
        mDoubleslideWithoutrule = (DoubleSlideSeekBar)findViewById(R.id.doubleslide_withoutrule);
        pricePicker = (LinearLayout)findViewById(R.id.choose_price_picker) ;
        chooseDate = (Button)findViewById(R.id.chooseDate);
        chooseOrder = (Button)findViewById(R.id.chooseOrder);
        choosePrice = (Button)findViewById(R.id.choosePrice);
        resetPrice = (Button)findViewById(R.id.confirm_button);
        surePrice = (Button)findViewById(R.id.reset_button);
        chooseDate.setOnClickListener(this);
        chooseOrder.setOnClickListener(this);
        choosePrice.setOnClickListener(this);
        resetPrice.setOnClickListener(this);
        surePrice.setOnClickListener(this);
        initAdapter();
    }

    public void initAdapter(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.building_recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        ShowBuildAdapter adapter = new ShowBuildAdapter(buildingList);
        recyclerView.setAdapter(adapter);
    }

    public void initBuildList(int m,int low,int max){                   //从数据库获取信息

        List<Integer> pictures = new ArrayList<>();
        pictures.add(R.drawable.user);
        pictures.add(R.drawable.imgv_slide);
        pictures.add(R.drawable.background);
        pictures.add(R.drawable.collect);
        pictures.add(R.drawable.mybackground);
        pictures.add(R.drawable.unseen);
        List<BuildingListData> buildings = new ArrayList<>();


        Gson gson = new Gson();
        buildings = gson.fromJson(responseStr,new TypeToken<List<BuildingListData>>(){}.getType());


        if(m==1){
            if(buildingList.size()!=0){
                for(int i=buildingList.size()-1;i>=0;i--){
                    buildingList.remove(i);
                }
            }
            screenPrice(buildings,low,max);
            buildingList = buildings;
        }

        if(m==2){
            if(buildingList.size()!=0){
                for(int i=buildingList.size()-1;i>=0;i--){
                    buildingList.remove(i);
                }
            }

            screenPrice(buildings,low,max);
            for(int i=buildings.size()-1;i>=0;i--){
                buildingList.add(buildings.get(i));
            }
        }
    }



    public void screenPrice(List<BuildingListData> buildings,int low,int max){
        for(int i=buildings.size()-1;i>=0;i--){
            if(!((buildings.get(i).getPriceByInt()>=low)&&(buildings.get(i).getPriceByInt())<=max)){
                buildings.remove(i);
            }
        }
    }
}
