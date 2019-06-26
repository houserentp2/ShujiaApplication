package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import example.com.shujiaapplication.R;

public class BuildingWritng extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;
    private Building mbuild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_writng);
        Intent intent=getIntent();
        mbuild=(Building)intent.getSerializableExtra("mbuild1");
        Button button =findViewById(R.id.view_button);
        editText=(EditText)findViewById(R.id.text_view);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.view_button:
                Intent intent=new Intent(BuildingWritng.this,OrderSuccess.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
