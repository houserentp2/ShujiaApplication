package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import example.com.shujiaapplication.R;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private EditText editAccount;
    private EditText editPassword;
    private TextView forgetPassward;
    private TextView newAccount;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:{                                                                      //if登录条件
                Intent intent1 = new Intent(MainActivity.this,HomePageActivity.class);
                startActivity(intent1);
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
        }
    }

    public void initControl(){
        Button login = (Button)findViewById(R.id.login);
        editAccount = (EditText)findViewById(R.id.editaccount);
        editPassword = (EditText)findViewById(R.id.editpassword);
        forgetPassward = (TextView)findViewById(R.id.forgetPassword);
        newAccount = (TextView)findViewById(R.id.newAccount);
        login.setOnClickListener(this);
        forgetPassward.setOnClickListener(this);
        newAccount.setOnClickListener(this);
    }
}
