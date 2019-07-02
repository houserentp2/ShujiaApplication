package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private String money;
    private String discountid;
    private static  final int SETPAY = 0;
    private static String responseData = "";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==SETPAY){
                SharedPreferences preferences = getSharedPreferences("requestData",MODE_PRIVATE);
                responseData = preferences.getString("requestGetData","");
                Log.e("OrderSec","---------------------------------"+responseData);
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
        String discount="2561563176";
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
                String order="OrderSecurity";
                intent1.putExtra("fromActivity",order);
                startActivityForResult(intent1,1);
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
                BigDecimal p = new BigDecimal("1000000");
                BigDecimal q = new BigDecimal("0");
                pay.setBalance(q);
                pay.setAlipay(p);
                pay.setWechatpaypay(p);
                Intent intent2=getIntent();
                String[] newbuilding=intent2.getStringArrayExtra("getHouseData");
                System.out.print(newbuilding[4]);
                newbuilding[5]=newbuilding[5]+"T00:00：00+8：00";
                System.out.print(newbuilding[4]);
                newbuilding[6]=newbuilding[6]+"T00:00：00+8：00";
                int result=1;
                NewBuilding a=new NewBuilding(AuthInfo.userid,AuthInfo.token,newbuilding[2],newbuilding[3],"","","2019-06-26T21:56:02+08:00","2019-06-29T23:43:42+08:00", "2019-07-26T23:43:42+08:00",result,pay);
                RequsetData.requestData(a,"pay");
                Message message = new Message();
                message.what = SETPAY;
                handler.sendMessage(message);
            }
        }).start();
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        switch(requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    discountid=data.getStringExtra("discountID");
                }
                break;
            default:
        }
    }
}
