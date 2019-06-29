package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
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
    private String houseid;
    private BuildingListData buildinglistdata;
    private static  final int GETHOUSELIST = 0;
    private static String responseData = "";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==GETHOUSELIST){
                SharedPreferences preferences = getSharedPreferences("requestData",MODE_PRIVATE);
                responseData = preferences.getString("requestGetData","");
                ArrayList<BuildingListData> buildings = new ArrayList<BuildingListData>();
                Gson gson = new Gson();
                buildings = gson.fromJson(responseData,new TypeToken<List<BuildingListData>>(){}.getType());
                BuildingListData building=new BuildingListData();
                for(BuildingListData build:buildings){
                    if(build.getHouseid().equals(houseid)){
                        building=build;
                        buildinglistdata=building;
                    }
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] newString=new String[5];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_view);
        Intent intent=getIntent();
        nbuild=(NewBuilding)intent.getSerializableExtra("nbuild");
        getBuildingInformation(nbuild.getUserid(),nbuild.getToken(),nbuild.getHouseid());
        mbuild=buildinglistdata;
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
    public void getBuildingInformation(String Userid, String Token,String Houseid){
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetHouseInfo a=new GetHouseInfo(Userid,Token);
                RequsetData.requestData(a,"gethouselist");
                Message message = new Message();
                message.what = GETHOUSELIST;
                handler.sendMessage(message);
            }
        }).start();
    }
}
