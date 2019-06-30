package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import example.com.shujiaapplication.R;

public class BuildingWritng extends BaseActivity implements View.OnClickListener{
    private EditText editText;
    private NewBuilding nbuild;
    private static  final int SETCOMMENT = 0;
    private static String responseData = "";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==SETCOMMENT){
                SharedPreferences preferences = getSharedPreferences("requestData",MODE_PRIVATE);
                responseData = preferences.getString("requestGetData","");
                Gson gson = new Gson();
                String isfinished = gson.fromJson(responseData,String.class);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_writng);
        Intent intent=getIntent();
        nbuild=(NewBuilding)intent.getSerializableExtra("nbuild1");
        Button button =findViewById(R.id.view_button);
        editText=(EditText)findViewById(R.id.text_view);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.view_button:
                String commen =editText.getText().toString();
                setcomment(commen);
                Intent intent=new Intent(BuildingWritng.this,OrderSuccess.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void setcomment(String comment){
        new Thread(new Runnable() {
            @Override
            public void run() {
                PutComment a=new PutComment(AuthInfo.userid,AuthInfo.token,nbuild.getHouseid(),comment);
                RequsetData.requestData(a,"putcomment");
                Message message = new Message();
                message.what = SETCOMMENT;
                handler.sendMessage(message);
            }
        }).start();
    }
}
