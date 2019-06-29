package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import example.com.shujiaapplication.R;

public class VerifyResultActivity extends BaseActivity {


    private Button button;
    private TextView text;
    private int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_result);
    }

    private void initView(){
        text = (TextView) findViewById(R.id.show_result);
        button = (Button) findViewById(R.id.yes_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VerifyResultActivity.this,VerifyActivity.class);
                startActivity(i);
            }
        });
    }

    private void getResult(){
        Intent intent = getIntent();
        result = Integer.parseInt(intent.getStringExtra("result"));
        if(result > 0){
            text.setText("审核通过成功");
        }
        else
            text.setText("禁止审核成功");
    }
}
