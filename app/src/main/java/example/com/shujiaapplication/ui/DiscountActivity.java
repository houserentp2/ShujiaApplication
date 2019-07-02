package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private static final int DISCOUNT = 0;
    private static String responseData = "";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==DISCOUNT){
                SharedPreferences preferences = getSharedPreferences("requestData",MODE_PRIVATE);
                responseData = preferences.getString("requestGetData","");
                Gson gson = new Gson();
                discountLists = gson.fromJson(responseData,new TypeToken<List<DiscountListData>>(){}.getType());
                selectDiscountList = (RecyclerView)findViewById(R.id.discount_list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
                selectDiscountList.setLayoutManager(layoutManager);
                selectDiscountList.setItemAnimator(new DefaultItemAnimator());
                adapter = new DiscountListAdapter(discountLists,DiscountActivity.this);
                selectDiscountList.setAdapter(adapter);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        initDiscount();
    }

    public void initDiscount(){    //从服务器获取优惠券列表
        new Thread(new Runnable() {
            @Override
            public void run() {
                DiscountData discountData=new DiscountData(AuthInfo.userid,AuthInfo.token);
                RequsetData.requestData(discountData,"getdiscountlist");
                Message message = new Message();
                message.what =DISCOUNT;
                handler.sendMessage(message);
            }
        }).start();
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
        Intent intent1 = getIntent();
        String activty = intent1.getStringExtra("fromActivity");
        if(activty.equals("MyFragment")){
        }else{
            Toast.makeText(DiscountActivity.this,"你选择了第"+(position+1)+"优惠券",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DiscountActivity.this,OrderSecurity.class);
            intent.putExtra("discountID",discountLists.get(position).getDiscountid());
            intent.putExtra("discountMoney",discountLists.get(position).getReduce());
            startActivity(intent);
        }
    }
    @Override
    public void onItemLongClick(View view, int position) {

    }
}
