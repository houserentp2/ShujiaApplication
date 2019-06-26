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

import java.util.ArrayList;
import java.util.List;

import example.com.shujiaapplication.R;

public class BuildingView extends AppCompatActivity {
    private Building mbuild;
    private ArrayList<String> a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_view);
        Intent intent=getIntent();
        mbuild=(Building)intent.getSerializableExtra("mbuild");
        ImageView imageView=findViewById(R.id.building_order_image);
        imageView.setImageResource(mbuild.getPicture_id().get(0));
        TextView buildingDetail=findViewById(R.id.building_details);
        buildingDetail.setText(mbuild.getTitle());
        TextView buildingShitin=findViewById(R.id.building_shitin);
        buildingShitin.setText(String.valueOf(mbuild.getShi()));
        TextView buildingLivingPeople=findViewById(R.id.building_living_people);
        buildingLivingPeople.setText(String.valueOf(mbuild.getLiving_people()));
        Button button =findViewById(R.id.pay);
        for(String view:mbuild.getBuildingView()){
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
                intent.putExtra("mbuild1",mbuild);
                startActivity(intent);
            }
        });
    }
}
