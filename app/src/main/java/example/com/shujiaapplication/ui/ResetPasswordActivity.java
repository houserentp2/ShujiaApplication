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

public class ResetPasswordActivity extends BaseActivity {

    private EditText resetNewPassward;
    private EditText reset_sureNewPassword;
    private Button reSetPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_reset_password);
        initControl();

        reSetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ResetPasswordActivity.this,SuccessAccountActivity.class);
                intent1.putExtra("newOrReset","reset");
                startActivity(intent1);
            }
        });
    }

    public void initControl(){
        reset_sureNewPassword = (EditText)findViewById(R.id.reset_sureNewPassword);
        resetNewPassward = (EditText)findViewById(R.id.resetNewPassward);
        reSetPassword = (Button)findViewById(R.id.reSetPassword);
    }
}
