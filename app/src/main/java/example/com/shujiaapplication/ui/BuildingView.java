package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import example.com.shujiaapplication.R;

public class BuildingView extends AppCompatActivity {
    private BuildingListData mbuild;
    private NewBuilding nbuild;
    private ArrayList<String> a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] newString=new String[5];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_view);
        Intent intent=getIntent();
        nbuild=(NewBuilding)intent.getSerializableExtra("nbuild");
        mbuild=getBuildingInformation(nbuild.getUserid(),nbuild.getToken(),nbuild.getHouseid());
        ImageView imageView=findViewById(R.id.building_order_image);
        imageView.setImageBitmap(mbuild.getPictureByBitmap());
        TextView buildingDetail=findViewById(R.id.building_details);
        buildingDetail.setText(mbuild.getTitle());
        TextView buildingShitin=findViewById(R.id.building_shitin);
        buildingShitin.setText(mbuild.getShiting().getShi()+"室"+mbuild.getShiting().getShi()+"厅");
        TextView buildingLivingPeople=findViewById(R.id.building_living_people);
        buildingLivingPeople.setText(mbuild.getPrice());
        Button button =findViewById(R.id.pay);
        for(String view:newString){
            String b="匿名用户："+view;
            a.add(b);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,a);
        ListView listView=(ListView)findViewById(R.id.building_pay_view);
        listView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BuildingView.this,BuildingWritng.class);
                intent.putExtra("nbuild1",nbuild);
                startActivity(intent);
            }
        });
    }
    public BuildingListData getBuildingInformation(String Userid, String Token, String Houseid){
        GetHouseInfo a=new GetHouseInfo(Userid,Token);
        String s= RequsetData.requestData(a,"gethouselist");
        ArrayList<BuildingListData> buildings = new ArrayList<BuildingListData>();
        Gson gson = new Gson();
        buildings = gson.fromJson(s,new TypeToken<List<BuildingListData>>(){}.getType());
        BuildingListData building=new BuildingListData();
        for(BuildingListData build:buildings){
            if(build.getHouseid().equals(Houseid)){
                building=build;
            }
        }
        return building;
    }
}
