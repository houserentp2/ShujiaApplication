package example.com.shujiaapplication.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
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
    private EditText PIeditPhone;
    private CityPicker cityPicker;
    public static final MediaType JSON=MediaType.get("application/json; charset=utf-8");

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
        notKorad(PICity);
        PIsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  detailHome = PIHome.getText().toString();
                String City = PICity.getText().toString();
                String realName = PIeditName.getText().toString();
                String IDnum = PIIDNum.getText().toString();
                String Phonenum = PIeditPhone.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            OkHttpClient client = new OkHttpClient();
                            Gson gson=new Gson();
                            Resident resident = new Resident(PICity.getText().toString().split("\t")[0],PICity.getText().toString().split("\t")[1],PICity.getText().toString().split("\t")[2],detailHome);
                            PersonInfoData personInfoData=new PersonInfoData(AuthInfo.userid,AuthInfo.token,realName,IDnum,resident);
                            RequestBody requestBody=RequestBody.create(JSON,gson.toJson(personInfoData));
                            Request request=new Request.Builder()
                                    .url("http://192.168.43.57:1323/userinfo")
                                    .post(requestBody)
                                    .build();
                            Response response=client.newCall(request).execute();
                            String responseData=response.body().string();
                            if(responseData.equals("Update Success")){
                                Toast.makeText(PersonalInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
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

    public void initControl(){
        return_myfragment = (Button)findViewById(R.id.return_myfragment);
        PIsave = (Button)findViewById(R.id.PIsave);
        PIHome = (EditText)findViewById(R.id.PIHome);
        PICity = (EditText)findViewById(R.id.PICity);
        PIeditName = (EditText)findViewById(R.id.PIeditName);
        PIIDNum = (EditText)findViewById(R.id.PIIDNum);
        PIeditPhone = (EditText)findViewById(R.id.PIeditPhone);
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
