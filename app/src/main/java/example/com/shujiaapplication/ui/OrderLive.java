package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.com.shujiaapplication.R;

public class OrderLive extends AppCompatActivity {
    private String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_live);
        a="aaaaaa";
        Button button =(Button)findViewById(R.id.live_button);
        EditText editText=(EditText)findViewById(R.id.text_live);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input =editText.getText().toString();
                if (input.equals(a)){
                    Intent intent=new Intent(OrderLive.this,OrderSuccess.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(OrderLive.this,"wrong number",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
