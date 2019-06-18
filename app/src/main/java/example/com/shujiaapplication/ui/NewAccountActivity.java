package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import example.com.shujiaapplication.R;

public class NewAccountActivity extends BaseActivity {
    private EditText editAccount;
    private EditText editPassword;
    private EditText surePassword;
    private Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        initControl();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(NewAccountActivity.this,SuccessAccountActivity.class);
                startActivity(intent1);
            }
        });
    }

    public void initControl(){
        editAccount = (EditText)findViewById(R.id.editaccount);
        editPassword = (EditText)findViewById(R.id.editpassword);
        surePassword = (EditText)findViewById(R.id.editSurePassword);
        register = (Button)findViewById(R.id.register);
    }
}
