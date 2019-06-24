package example.com.shujiaapplication.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import example.com.shujiaapplication.R;

public class ShowBuildListActivity extends BaseActivity implements View.OnClickListener {

    private List<Building> buildingList = new ArrayList<>();
    private Button choosePrice;
    private Button chooseDate;
    private Button chooseOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_build_list);
        initControl();
        initBuildList();
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

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.building_recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        ShowBuildAdapter adapter = new ShowBuildAdapter(buildingList);
        recyclerView.setAdapter(adapter);
    }

    public void initBuildList(){                   //从数据库获取信息
        for(int i=0;i<10;i++){
            Building building = new Building(i,228,50,1,1,"梦幻一号","湖北省","武汉市","洪山区","华中科技大学",0,0,R.drawable.background,2);
            buildingList.add(building);
        }

    }
}
