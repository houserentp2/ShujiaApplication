package example.com.shujiaapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import example.com.shujiaapplication.R;
import okhttp3.MediaType;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button setSeen;
    private EditText editAccount;
    private EditText editPassword;
    private TextView forgetPassward;
    private TextView newAccount;
    private static String responseData = "";
    private static LoginData loginData;
    private static  final int LOGIN = 0;
    public static final MediaType JSON=MediaType.get("application/json; charset=utf-8");
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==LOGIN){
                SharedPreferences preferences = getSharedPreferences("requestData",MODE_PRIVATE);
                responseData = preferences.getString("requestGetData","");
                if(responseData.contains("userid")){
                    Toast.makeText(MainActivity.this,"登录成功!",Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("autoLogin", Context.MODE_PRIVATE).edit();
                    editor.putString("autoLoginPhone",loginData.getNewAccountName());
                    editor.putString("autoLoginPassword",loginData.getNewAccountPassword());
                    editor.apply();
                    Intent intent1 = new Intent(MainActivity.this,HomePageActivity.class);
                    startActivity(intent1);
                }else{
                    Toast.makeText(MainActivity.this,responseData,Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        initControl();

        SharedPreferences preferences = getSharedPreferences("autoLogin",MODE_PRIVATE);
        String phone = preferences.getString("autoLoginPhone","");
        String password = preferences.getString("autoLoginPhone","");
        if(phone.equals("")==false&&password.equals("")==false){                                    //自动登录
            loginData=new LoginData(phone,null,null,password);
            RequsetData.requestData(loginData,"login");
            Message message = new Message();
            message.what = LOGIN;
            handler.sendMessage(message);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:{                                                                       //if登录条件
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"登录中...",Toast.LENGTH_SHORT).show();
                         loginData=new LoginData(editAccount.getText().toString(),null,null,editPassword.getText().toString());
                        RequsetData.requestData(loginData,"login");
                        Message message = new Message();
                        message.what = LOGIN;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            }
            case R.id.forgetPassword:{
                Intent intent1 = new Intent(MainActivity.this,ForgetPasswordActivity.class);
                startActivity(intent1);
                break;
            }
            case R.id.newAccount:{
                Intent intent1 = new Intent(MainActivity.this,NewAccountActivity.class);
                startActivity(intent1);
                break;
            }
            case R.id.setseen:{
                if(editPassword.getInputType()==128){
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.unseen,null);
                    setSeen.setBackground(drawable);
                    editPassword.setInputType(129);
                }else {
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.seen,null);
                    setSeen.setBackground(drawable);
                    editPassword.setInputType(128);
                }
            }
        }
    }

    public void initControl(){
        Button login = (Button)findViewById(R.id.login);
        setSeen = (Button)findViewById(R.id.setseen);
        editAccount = (EditText)findViewById(R.id.editaccount);
        editPassword = (EditText)findViewById(R.id.editpassword);
        forgetPassward = (TextView)findViewById(R.id.forgetPassword);
        newAccount = (TextView)findViewById(R.id.newAccount);
        login.setOnClickListener(this);
        forgetPassward.setOnClickListener(this);
        newAccount.setOnClickListener(this);
        setSeen.setOnClickListener(this);
    }

    }