package example.com.shujiaapplication.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.lljjcoder.citypickerview.widget.CityPicker;

import example.com.shujiaapplication.R;


public class PersonalInfoActivity extends BaseActivity {
    private EditText PICity;
    private EditText PIeditAccount;
    private EditText PIeditName;
    private EditText PIIDNum;
    private CityPicker cityPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        initControl();
        notKorad(PICity);
    }

    public void initControl(){
        PICity = (EditText)findViewById(R.id.PICity);
        PIeditAccount = (EditText)findViewById(R.id.PIeditAccount);
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
                PICity.setText(province + city + district);
            }

            @Override
            public void onCancel() {
            }
        });
    }
}
