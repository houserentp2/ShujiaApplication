package example.com.shujiaapplication.ui;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_pay);
        List<Integer> pictures = new ArrayList<>();
        pictures.add(R.drawable.imgv_slide);
        pictures.add(R.drawable.background);
        pictures.add(R.drawable.collect);
        pictures.add(R.drawable.mybackground);
        pictures.add(R.drawable.user);
        pictures.add(R.drawable.unseen);
        Building mbuild=new Building(1,1,1,1,1,"fuck","fuck","fuck","fuck","fuck",1,0,pictures,1,0,0);
        ImageView imageView=findViewById(R.id.building_order_image);
        imageView.setImageResource(mbuild.getPicture_id().get(0));
        TextView buildingDetail=findViewById(R.id.building_details);
        buildingDetail.setText(mbuild.getTitle());
        TextView buildingShitin=findViewById(R.id.building_shitin);
        buildingShitin.setText(String.valueOf(mbuild.getShi()));
        TextView buildingLivingPeople=findViewById(R.id.building_living_people);
        buildingLivingPeople.setText(String.valueOf(mbuild.getLiving_people()));
        Button button =findViewById(R.id.pay);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mbuild.getBuildingView());
        ListView listView=(ListView)findViewById(R.id.building_pay_view);
        listView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
