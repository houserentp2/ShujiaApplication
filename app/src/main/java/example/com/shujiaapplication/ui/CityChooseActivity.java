package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import example.com.shujiaapplication.R;

public class CityChooseActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private ImageView backView;
    private TextView myPosition;
    private String city;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_choose);
       initView();

    }

    private void initView(){
        List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
        String[] name = new String[]{"北京","重庆","成都","广州","杭州","上海","深圳","武汉","厦门"};
        int[] cityImage = new int[]{R.drawable.beijing,R.drawable.shanghai,R.drawable.guangzhou,R.drawable.hangzhou,R.drawable.nanjing,R.drawable.tianjin,R.drawable.xian,R.drawable.zhuhai,R.drawable.aomen};
        for(int i=0;i<9;i++){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("imageItem",cityImage[i]);//添加图像资源的ID
            item.put("textItem", name[i]);//按序号添加ItemText
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(CityChooseActivity.this,items,R.layout.my_grid_item,new String[]{"imageItem", "textItem"},new int[]{R.id.image_item, R.id.text_item});
        gridView = (GridView)findViewById(R.id.city_gridview);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);

        backView = (ImageView) findViewById(R.id.back_image);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        myPosition = (TextView) findViewById(R.id.choose_position_button);
        myPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                city = "武汉";
                switchActivity();
            }
        });


        mSearchView = (SearchView) findViewById(R.id.city_search_view);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                CityDataGenerator cd = new CityDataGenerator();
                try {
                    if(s != null &&cd.findCity(s)){
                        city = s;
                        switchActivity();
                    }
                    else{
                        Toast.makeText(CityChooseActivity.this,"请输入正确的城市名",Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.equals("")==true)
                    s="'fjjsdlfj";
                initAdapter(s);
                return true;
            }
        });

//        CityDataGenerator cd = new CityDataGenerator();
//        ArrayList<String> ans = cd.getList("");
//        for(String str: ans){
//            Log.e("CityChooseActivity",str);
//        }
    }

    public void initAdapter(String s){
        CityDataGenerator cd = new CityDataGenerator();
        ArrayList<String> ans = cd.getList(s);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CityChooseActivity.this,android.R.layout.simple_list_item_1,ans);
       ListView  listView  = (ListView)findViewById(R.id.show_city);
        listView.bringToFront();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 city = ans.get(position);
                switchActivity();
            }
        });
    }

    //返回主页
    private void switchActivity(){
        Intent intent = new Intent(CityChooseActivity.this,HomePageActivity.class);
        intent.putExtra("city",city);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:{
                city = "北京";
                switchActivity();
                break;
            }
            case 1:{
                city = "重庆";
                switchActivity();
                break;
            }
            case 2:{
                city = "成都";
                switchActivity();
                break;
            }
            case 3:{
                city = "广州";
                switchActivity();
                break;
            }
            case 4:{
                city = "杭州";
                switchActivity();
                break;
            }
            case 5:{
                city = "上海";
                switchActivity();
                break;
            }
            case 6:{
                city = "深圳";
                switchActivity();
                break;
            }
            case 7:{
                city = "武汉";
                switchActivity();
                break;
            }
            case 8:{
                city = "厦门";
                switchActivity();
                break;
            }
        }
    }
}
