package example.com.shujiaapplication.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import example.com.shujiaapplication.R;

public class ShowBuildListActivity extends BaseActivity implements View.OnClickListener {

    private List<Building> buildingList = new ArrayList<>();
    private Button choosePrice;
    private Button chooseDate;
    private Button chooseOrder;
    private int order_mode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_build_list);

        initBuildList(1);
        initControl();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chooseDate:{
                break;
            }

            case R.id.choosePrice:{
                break;
            }
            case  R.id.chooseOrder:{
                order_mode++;
                if(order_mode%2==1){
                    chooseOrder.setText("价格降序");
                    initBuildList(1);
                }else{
                    chooseOrder.setText("价格升序");
                    initBuildList(2);
                }

                initAdapter();
                Toast.makeText(ShowBuildListActivity.this,"you click it",Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    public void initControl(){
        chooseDate = (Button)findViewById(R.id.chooseDate);
        chooseOrder = (Button)findViewById(R.id.chooseOrder);
        choosePrice = (Button)findViewById(R.id.choosePrice);
        chooseDate.setOnClickListener(this);
        chooseOrder.setOnClickListener(this);
        choosePrice.setOnClickListener(this);
        initAdapter();
    }

    public void initAdapter(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.building_recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        ShowBuildAdapter adapter = new ShowBuildAdapter(buildingList);
        recyclerView.setAdapter(adapter);
    }

    public void initBuildList(int m){                   //从数据库获取信息

        List<Building> buildings = new ArrayList<>();
        for(int i=0;i<10;i++){
            Building building1 = new Building(i,(228-i*i),50,1,1,"梦幻一号","湖北省","武汉市","洪山区","华中科技大学",0,0,R.drawable.background,R.drawable.user,R.drawable.collect,2);
            Building building = new Building(i,(228+i),50,1,1,"梦幻一号","湖北省","武汉市","洪山区","华中科技大学",0,0,R.drawable.background,R.drawable.user,R.drawable.collect,2);
            buildings.add(building);
            buildings.add(building1);
        }

        Collections.sort(buildings);
        if(m==1){
            if(buildingList.size()!=0){
                for(int i=buildingList.size()-1;i>=0;i--){
                    buildingList.remove(i);
                }
            }
            buildingList = buildings;
        }

        if(m==2){
            if(buildingList.size()!=0){
                for(int i=buildingList.size()-1;i>=0;i--){
                    buildingList.remove(i);
                }
            }
            for(int i=buildings.size()-1;i>=0;i--){
                buildingList.add(buildings.get(i));
            }

        }
    }

}
