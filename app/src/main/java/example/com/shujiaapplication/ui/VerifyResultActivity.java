package example.com.shujiaapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import example.com.shujiaapplication.R;

public class VerifyResultActivity extends BaseActivity {


    private Button button;
    private TextView text;
    private int result = 0;
    private static String responseSwitch = "";

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){                        //如果审核页面有数据则进入审核页面。
                SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("requestData", Context.MODE_PRIVATE);
                responseSwitch = preferences.getString("requestGetData","");
                Log.e("VerfityActivity","resonseData!!!"+responseSwitch);
                if(responseSwitch.contains("userid")){
                    Intent intent = new Intent(MyApplication.getContext(), VerifyActivity.class);
                    intent.putExtra("checkerHouse",responseSwitch);
                    startActivity(intent);
                }else{
                    Toast.makeText(VerifyResultActivity.this,"暂时没有需要审核的房屋",Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(VerifyResultActivity.this,HomePageActivity.class);
                    startActivity(in);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_result);
    }

    private void initView(){
        text = (TextView) findViewById(R.id.show_result);
        button = (Button) findViewById(R.id.yes_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiscountData discount = new DiscountData(AuthInfo.userid,AuthInfo.token);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RequsetData.requestData(discount,"gettocheckhouse");
                        Message message = new Message();
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }

    private void getResult(){
        Intent intent = getIntent();
        result = Integer.parseInt(intent.getStringExtra("result"));
        if(result > 0){
            text.setText("审核通过成功");
        }
        else
            text.setText("禁止通过成功");
    }
}
