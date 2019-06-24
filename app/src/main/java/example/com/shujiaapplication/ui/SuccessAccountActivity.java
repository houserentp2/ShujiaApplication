package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import example.com.shujiaapplication.R;

public class SuccessAccountActivity extends BaseActivity {
    private TextView showTime;
    private Handler mHandler = new Handler();
    private int i=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_success_account);
        showTime = (TextView)findViewById(R.id.showTime);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent1 = getIntent();
                            String data = intent1.getStringExtra("newOrReset");
                            if(data.equals("new")==true){
                                showTime.setText("注册成功，"+i+"秒后自动跳转到登录界面");
                            }else{
                                showTime.setText("重置密码成功，"+i+"秒后自动跳转到登录界面");
                            }


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
