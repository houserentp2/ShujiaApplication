package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import example.com.shujiaapplication.R;

public class OrderSecurity extends AppCompatActivity{
    private EditText editText;
    private NewBuilding nbuild;
    private String a;
    private String discount;
    private String money;
    private static  final int SETPAY = 0;
    private static String responseData = "";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==SETPAY){
                SharedPreferences preferences = getSharedPreferences("requestData",MODE_PRIVATE);
                responseData = preferences.getString("requestGetData","");
                Gson gson = new Gson();
                PayResult payResult=new PayResult();
                payResult = gson.fromJson(responseData,PayResult.class);
                Toast.makeText(OrderSecurity.this,payResult.getPayresult(),Toast.LENGTH_SHORT);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_security);
        a="aaaaaa";
        Intent intent=getIntent();
        nbuild=(NewBuilding)intent.getSerializableExtra("nbuild1");
        Button button =(Button)findViewById(R.id.pay_button);
        Button button1 =(Button)findViewById(R.id.pay_button0);
        EditText editText=(EditText)findViewById(R.id.text_pay);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(OrderSecurity.this,DiscountActivity.class);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input =editText.getText().toString();
                if (input.equals(a)){
                    sendResult();
                    Intent intent=new Intent(OrderSecurity.this,OrderSuccess.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(OrderSecurity.this,"wrong number",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void sendResult(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Pay pay=new Pay();
                BigDecimal p = new BigDecimal("0");
                pay.setBalance(p);
                pay.setAlipay(p);
                pay.setWechatpaypay(p);
                Intent intent2=getIntent();
                String[] newbuilding=intent2.getStringArrayExtra("getHouseData");
                newbuilding[4]=newbuilding[4]+"T00:00：00+8：00";
                newbuilding[5]=newbuilding[5]+"T00:00：00+8：00";
                newbuilding[6]=newbuilding[6]+"T00:00：00+8：00";
                discount=intent2.getStringExtra("discountID");
                money=intent2.getStringExtra("discountMoney");
                NewBuilding a=new NewBuilding(newbuilding[0],newbuilding[1],newbuilding[2],newbuilding[3],"",discount,newbuilding[4],newbuilding[5],newbuilding[6],"1",pay);
                RequsetData.requestData(a,"pay");
                Message message = new Message();
                message.what = SETPAY;
                handler.sendMessage(message);
            }
        }).start();
    }
}
