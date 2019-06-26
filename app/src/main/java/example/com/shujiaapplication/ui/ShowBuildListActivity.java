package example.com.shujiaapplication.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import example.com.shujiaapplication.R;

public class ShowBuildListActivity extends BaseActivity implements View.OnClickListener {

    private List<Building> buildingList = new ArrayList<>();
    private DoubleSlideSeekBar mDoubleslideWithoutrule;
    private Button choosePrice;
    private Button chooseDate;
    private Button chooseOrder;
    private Button resetPrice;
    private Button surePrice;
    private LinearLayout pricePicker;
    private int order_mode = 0;
    private static int min = 0;
    private static int max = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_build_list);
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
                    initBuildList(1,0,Integer.MAX_VALUE);
                }else{
                    chooseOrder.setText("价格升序");
                    initBuildList(2,0,Integer.MAX_VALUE);
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
        List<Building> buildings = new ArrayList<>();
        for(int i=0;i<10;i++){
            Building building2 = new Building(i,(228+i*i),50,1,1,"梦幻一号","湖北省","武汉市","洪山区","华中科技大学",0,0,pictures,R.drawable.user,R.drawable.collect,2);
            Building building1 = new Building(i,(228-i*i),50,1,1,"梦幻一号","湖北省","武汉市","洪山区","华中科技大学",0,0,pictures,R.drawable.user,R.drawable.collect,2);
            Building building = new Building(i,(228+i),50,1,1,"梦幻一号","湖北省","武汉市","洪山区","华中科技大学",0,0,pictures,R.drawable.user,R.drawable.collect,2);
            buildings.add(building);
            buildings.add(building1);
            buildings.add(building2);
        }

        Collections.sort(buildings);
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

    public void screenPrice(List<Building> buildings,int low,int max){

        for(int i=buildings.size()-1;i>=0;i--){
            if(!((buildings.get(i).getPrice()>=low)&&(buildings.get(i).getPrice()<=max))){
                buildings.remove(i);

            }
        }
    }
}
