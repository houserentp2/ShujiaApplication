package example.com.shujiaapplication.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import example.com.shujiaapplication.R;

public class NewAccountActivity extends BaseActivity {
    private Handler mHandler = new Handler();
    private static String code = "";
    private EditText editAccount;
    private EditText editPassword;
    private EditText surePassword;
    private EditText editCode;
    private int daoTime = 61;
    private Button getCode;                                                                        //得到验证码按钮
    private Button register;                                                                       //注册按钮


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_new_account);
        initControl();

        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoTime = daoTime-1;
                getFourCode();
                getCode.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (daoTime<=60){
                            daoTime = daoTime-1;
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if(daoTime!=0){
                                        getCode.setText(daoTime+"秒后重新获取");
                                    } else{
                                        getCode.setEnabled(true);
                                        getCode.setText("获取验证码");
                                        daoTime = 61;
                                    }
                                }
                            });
                            try{
                                Thread.sleep(1000);
                            }catch (Exception e){
                                e.fillInStackTrace();
                            }
                        }
                    }
                }).start();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {                                   //注册按钮
            @Override
            public void onClick(View v) {
                int accountLen = editAccount.getText().toString().length();
                int passwordLen = editPassword.getText().toString().length();
                String editCode1 = editCode.getText().toString();

                SharedPreferences preferences = getSharedPreferences("setCode",MODE_PRIVATE);
                String getCode = preferences.getString("code","null");

                if(accountLen<3||accountLen>10){
                    Toast.makeText(NewAccountActivity.this,"请输入3-10位的用户名",Toast.LENGTH_SHORT).show();
                }else if(passwordLen<6||passwordLen>20){
                    Toast.makeText(NewAccountActivity.this,"请输入6-20位的密码",Toast.LENGTH_SHORT).show();
                }else if(editPassword.getText().toString().equals(surePassword.getText().toString())==false){
                    Toast.makeText(NewAccountActivity.this,"两次密码输入不一致,请重新输入",Toast.LENGTH_SHORT).show();
                }else if(editCode1.equals(getCode)==false){
                    Toast.makeText(NewAccountActivity.this,"验证码有误，请重新验证",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent1 = new Intent(NewAccountActivity.this,SuccessAccountActivity.class);
                    startActivity(intent1);
                }
            }
        });
    }

    public void getFourCode(){

        Random random = new Random();
        if(code.length()!=0){
            code = "";
        }
        for(int i=0;i<=3;i++){
            code = code+random.nextInt(10);
        }

        SharedPreferences.Editor editor = getSharedPreferences("setCode",MODE_PRIVATE).edit();
        editor.putString("code",code);
        editor.apply();

        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        String Channel_id = "my_channel_id_01";

        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(Channel_id,"My Notification",NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Channel description");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            channel.enableVibration(true);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder notification = new NotificationCompat.Builder(NewAccountActivity.this,Channel_id);
        notification .setContentTitle("您好，您注册住多多的验证码为：")
                .setContentText(code)
                .setWhen(System.currentTimeMillis())
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        manager.notify(1,notification.build());
    }

    public void initControl(){
        getCode = (Button)findViewById(R.id.getCode);
        editCode = (EditText)findViewById(R.id.editCode);
        editAccount = (EditText)findViewById(R.id.editaccount);
        editPassword = (EditText)findViewById(R.id.editpassword);
        surePassword = (EditText)findViewById(R.id.editSurePassword);
        register = (Button)findViewById(R.id.register);
    }
}
