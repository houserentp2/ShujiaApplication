package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.com.shujiaapplication.R;

public class ResetPasswordActivity extends BaseActivity {
    private EditText resetNewPassward;
    private EditText reset_sureNewPassword;
    private Button reSetPassword;
    private static final int RESET = 0;
    private static String responseData = "";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==RESET){
                SharedPreferences preferences = getSharedPreferences("requestData",MODE_PRIVATE);
                responseData = preferences.getString("requestGetData","");
                if(responseData.equals("Update Password Success")){
                    Intent intent1 = new Intent(ResetPasswordActivity.this,SuccessAccountActivity.class);
                    intent1.putExtra("newOrReset","reset");
                    startActivity(intent1);
                }else{
                    Toast.makeText(ResetPasswordActivity.this,responseData,Toast.LENGTH_SHORT).show();
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

        setContentView(R.layout.activity_reset_password);
        initControl();

        reSetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = getIntent();
                        String phone = intent.getStringExtra("reset_phonenum");
                        String newPassword = resetNewPassward.getText().toString();
                        NewAccountData newAccountData = new NewAccountData(phone,"1234",null,newPassword);
                        RequsetData.requestData(newAccountData,"resetpassword");
                        Message message = new Message();
                        message.what = RESET;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }

    public void initControl(){
        reset_sureNewPassword = (EditText)findViewById(R.id.reset_sureNewPassword);
        resetNewPassward = (EditText)findViewById(R.id.resetNewPassward);
        reSetPassword = (Button)findViewById(R.id.reSetPassword);
    }
}
