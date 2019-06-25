package example.com.shujiaapplication.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Random;

import example.com.shujiaapplication.R;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NewAccountActivity extends BaseActivity implements View.OnClickListener {
    public static final MediaType JSON=MediaType.get("application/json; charset=utf-8");
    private EditText editAccount;
    private EditText editPassword;
    private EditText surePassword;
    private EditText editCode;
    private Button setSeen1;
    private Button setSeen2;
    private Button getCode;                                                                        //得到验证码按钮
    private Button register;                                                                       //注册按钮
    private Button testLink;
    private TextView testResult;


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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getCode:{
                Code.daotime(getCode);
                break;
            }
            case R.id.register:{
                int accountLen = editAccount.getText().toString().length();
                int passwordLen = editPassword.getText().toString().length();
                String editCode1 = editCode.getText().toString();

                SharedPreferences preferences = getSharedPreferences("setCode",MODE_PRIVATE);
                String getCode = preferences.getString("code","null");

                if(accountLen!=11){
                    Toast.makeText(NewAccountActivity.this,"请输入11位手机号",Toast.LENGTH_SHORT).show();
                }else if(passwordLen<6||passwordLen>20){
                    Toast.makeText(NewAccountActivity.this,"请输入6-20位的密码",Toast.LENGTH_SHORT).show();
                }else if(editPassword.getText().toString().equals(surePassword.getText().toString())==false){
                    Toast.makeText(NewAccountActivity.this,"两次密码输入不一致,请重新输入",Toast.LENGTH_SHORT).show();
                }else if(editCode1.equals(getCode)==false){
                    Toast.makeText(NewAccountActivity.this,"验证码有误，请重新验证",Toast.LENGTH_SHORT).show();
                }else{
                    register();

//                    Intent intent1 = new Intent(NewAccountActivity.this,SuccessAccountActivity.class);
//                    intent1.putExtra("newOrReset","new");
//                    startActivity(intent1);
                }
                break;
            }
            case R.id.setseen1:{
                if(editPassword.getInputType()==128){
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.unseen,null);
                    setSeen1.setBackground(drawable);
                    editPassword.setInputType(129);
                }else {
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.seen,null);
                    setSeen1.setBackground(drawable);
                    editPassword.setInputType(128);
                }
                break;
            }
            case R.id.setseen2:{
                if(surePassword.getInputType()==128){
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.unseen,null);
                    setSeen2.setBackground(drawable);
                    surePassword.setInputType(129);
                }else {
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.seen,null);
                    setSeen2.setBackground(drawable);
                    surePassword.setInputType(128);
                }
                break;
            }
            case R.id.testLink:{
                SendMessage();
            }
        }
    }

    public void SendMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url("http://192.168.43.57:1323/testconnection")
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    showResponse(responseData);

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public void register(){
        Toast.makeText(NewAccountActivity.this,"fsdf3",Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Gson gson=new Gson();
                    NewAccountData newAccount=new NewAccountData(editAccount.getText().toString(),editPassword.getText().toString(),"1111");
                    RequestBody requestBody=RequestBody.create(JSON,gson.toJson(newAccount));
                    Request request=new Request.Builder()
                            .url("http://192.168.43.57:1323/register")
                            .post(requestBody)
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    showResponse(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                testResult.setText(response);
            }
        });
    }

    public void initControl(){
        getCode = (Button)findViewById(R.id.getCode);
        register = (Button)findViewById(R.id.register);
        setSeen1 = (Button)findViewById(R.id.setseen1);
        setSeen2 = (Button)findViewById(R.id.setseen2);
        editCode = (EditText)findViewById(R.id.editCode);
        editAccount = (EditText)findViewById(R.id.editaccount);
        editPassword = (EditText)findViewById(R.id.editpassword);
        surePassword = (EditText)findViewById(R.id.editSurePassword);
        testLink = (Button)findViewById(R.id.testLink);
        testResult = (TextView)findViewById(R.id.testResult);
        getCode.setOnClickListener(this);
        register.setOnClickListener(this);
        setSeen1.setOnClickListener(this);
        setSeen2.setOnClickListener(this);
        testLink.setOnClickListener(this);
    }
}
