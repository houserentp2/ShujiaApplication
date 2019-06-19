package example.com.shujiaapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SuccessAccountActivity extends BaseActivity {
    private TextView showTime;
    private Handler mHandler = new Handler();
    private int i=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_account);
        showTime = (TextView)findViewById(R.id.showTime);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            showTime.setText("注册成功，"+i+"秒后自动跳转到登录界面");
                        }
                    });
                    try{
                        Thread.sleep(1000);
                        i--;
                    }catch (Exception e){
                        e.fillInStackTrace();
                    }
                    if(i==0){
                        Intent intent1 = new Intent(SuccessAccountActivity.this,MainActivity.class);
                        startActivity(intent1);
                    }
                }
            }
        }).start();
    }
    }
