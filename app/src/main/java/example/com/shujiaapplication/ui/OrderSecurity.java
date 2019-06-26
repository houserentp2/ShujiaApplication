package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.com.shujiaapplication.R;

public class OrderSecurity extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;
    private Building mbuild;
    private String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_writng);
        a="a";
        Intent intent=getIntent();
        mbuild=(Building)intent.getSerializableExtra("mbuild1");
        Button button =findViewById(R.id.view_button);
        editText=(EditText)findViewById(R.id.text_view);
        button.setText("确认");
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.view_button:
                String input =editText.getText().toString();
                if (input.equals(a)){
                    Intent intent=new Intent(OrderSecurity.this,OrderSuccess.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(OrderSecurity.this,"wrong number",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
