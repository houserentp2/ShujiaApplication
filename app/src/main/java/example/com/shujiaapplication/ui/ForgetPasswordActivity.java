package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import example.com.shujiaapplication.R;

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {

    private EditText forget_editgetPhone;
    private EditText forget_editgetCode;
    private Button forget_getCode;
    private Button reGetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_forget_password);
        initControl();


    }

    public void initControl(){
        forget_editgetPhone = (EditText)findViewById(R.id.forget_editgetPhone);
        forget_editgetCode = (EditText)findViewById(R.id.foret_editgetCode);
        forget_getCode = (Button)findViewById(R.id.forget_getCode);
        reGetPassword = (Button)findViewById(R.id.reGetPassword);
        forget_getCode.setOnClickListener(this);
        reGetPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forget_getCode:{
                Code.daotime(forget_getCode);
                break;
            }
            case R.id.reGetPassword:{
                Intent intent1 = new Intent(ForgetPasswordActivity.this,ResetPasswordActivity.class);
                startActivity(intent1);
                break;
            }
        }
    }
}
