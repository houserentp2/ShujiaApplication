package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lljjcoder.citypickerview.widget.CityPicker;

import example.com.shujiaapplication.R;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class PersonalInfoActivity extends BaseActivity {
    private Button return_myfragment;
    private Button PIsave;
    private EditText PIHome;
    private EditText PICity;
    private EditText PIeditName;
    private EditText PIIDNum;
    private CityPicker cityPicker;
    private static final int SETINFO = 0;
    private static final int GETINFO = 1;
    private static String responseData = "";
    public static final MediaType JSON=MediaType.get("application/json; charset=utf-8");
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SETINFO:{
                    SharedPreferences preferences = getSharedPreferences("requestData",MODE_PRIVATE);
                    responseData = preferences.getString("requestGetData","");
                    Gson gson = new Gson();
                    PersonInfoData p = gson.fromJson(responseData,PersonInfoData.class);
                    if(p!=null&&p.getResident()!=null){
                        PIeditName.setText(p.getNickname());
                        PIIDNum.setText(p.getId());
                        PICity.setText(p.getResident().getProvince()+p.getResident().getCity()+p.getResident().getZone());
                        PIHome.setText(p.getResident().getPath());
                    }
                    break;
                }

                case GETINFO:{
                    SharedPreferences preferences = getSharedPreferences("requestData",MODE_PRIVATE);
                    responseData = preferences.getString("requestGetData","");
                    if(responseData.equals("Update Success")){
                        Toast.makeText(PersonalInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(PersonalInfoActivity.this,responseData,Toast.LENGTH_SHORT).show();
                    }
                    break;
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
        setContentView(R.layout.activity_personal_info);
        initControl();
        setOldInfo();
        notKorad(PICity);
        PIsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String  detailHome = PIHome.getText().toString();
                        String realName = PIeditName.getText().toString();
                        String IDnum = PIIDNum.getText().toString();
                        Resident resident = new Resident(PICity.getText().toString().split("\t")[0],PICity.getText().toString().split("\t")[1],PICity.getText().toString().split("\t")[2],detailHome);
                        PersonInfoData personInfoData=new PersonInfoData(AuthInfo.userid,AuthInfo.token,realName,IDnum,resident);
                        RequsetData.requestData(personInfoData,"userinfo");
                        Message message = new Message();
                        message.what =GETINFO;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });

        return_myfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PersonalInfoActivity.this,HomePageActivity.class);
                startActivity(intent1);
            }
        });
    }

    public void setOldInfo(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DiscountData discountData=new DiscountData(AuthInfo.userid,AuthInfo.token);
                RequsetData.requestData(discountData,"getuserinfo");
                Message message = new Message();
                message.what = SETINFO;
                handler.sendMessage(message);
            }
        }).start();
    }

    public void initControl(){
        return_myfragment = (Button)findViewById(R.id.return_myfragment);
        PIsave = (Button)findViewById(R.id.PIsave);
        PIHome = (EditText)findViewById(R.id.PIHome);
        PICity = (EditText)findViewById(R.id.PICity);
        PIeditName = (EditText)findViewById(R.id.PIeditName);
        PIIDNum = (EditText)findViewById(R.id.PIIDNum);
    }

    public void notKorad(EditText editText){
        editText.setInputType(InputType.TYPE_NULL);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    initCityPicker();
                    cityPicker.show();
                }
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCityPicker();
                cityPicker.show();
            }
        });
    }

    public void initCityPicker() {
        cityPicker = new CityPicker.Builder(PersonalInfoActivity.this)
                .textSize(20)//滚轮文字的大小
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#0CB6CA")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                .textColor(Color.parseColor("#000000"))//滚轮文字的颜色
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();

        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                String province = citySelected[0];
                String city = citySelected[1];
                String district = citySelected[2];
                String code = citySelected[3];
                PICity.setText(province +"\t" +city +"\t"+ district);
            }

            @Override
            public void onCancel() {
            }
        });
    }
}