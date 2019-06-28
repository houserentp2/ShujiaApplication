package example.com.shujiaapplication.ui;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import example.com.shujiaapplication.R;

public class DiscountActivity extends BaseActivity implements View.OnClickListener, DiscountListAdapter.IonSlidingViewClickListener{

    private List<DiscountListData> discountLists = new ArrayList<>();
    private RecyclerView selectDiscountList;
    private DiscountListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_discount);

        selectDiscountList = (RecyclerView)findViewById(R.id.discount_list);
        initDiscount();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        selectDiscountList.setLayoutManager(layoutManager);
        selectDiscountList.setItemAnimator(new DefaultItemAnimator());
        adapter = new DiscountListAdapter(discountLists,DiscountActivity.this);
        selectDiscountList.setAdapter(adapter);
    }

    public void initDiscount(){    //从服务器获取优惠券列表
        Person p = new Person();
        String responseStr = RequsetData.requestData(p,"getdiscountlist");
        Gson gson = new Gson();
        discountLists = gson.fromJson(responseStr,new TypeToken<List<DiscountActivity>>(){}.getType());
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDeleteBtnCilck(View view, int position) {
        Toast.makeText(DiscountActivity.this,"you click delect is",Toast.LENGTH_SHORT).show();
        adapter.removeData(position);

    }


    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
