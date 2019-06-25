package example.com.shujiaapplication.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;

import example.com.shujiaapplication.R;
import example.com.shujiaapplication.ui.myDatePicker.CustomDatePicker;
import example.com.shujiaapplication.ui.myDatePicker.DateCalculate;
import example.com.shujiaapplication.ui.myDatePicker.DateFormatUtils;

public class DateChooseActivity extends BaseActivity {

    private TextView nightText;
    private CardView inDateCard;
    private CardView outDateCard;
    private TextView inDate;
    private TextView outDate;
    private CustomDatePicker inDatePicker,outDatePicker;
    private ImageView backImage;
    private Button confirmButton;
    private Button resetButton;

    private Boolean hasInDate;
    private Boolean hasOutDate;
    private String inDateStr;
    private String outDateStr;
    private int nightLong;
    private String maxDateStr = "2025-05-01";

    private int chooseType;
    public static final int SHORT_CHOOSE = 0;
    public static final int LONG_CHOOSE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_choose);

        chooseType = getChooseType();

        initView();
        initDateInPicker();
        initDateOutPicker();
    }


    private int getChooseType(){
        Intent intent = getIntent();
        return intent.getIntExtra("ChooseType",0);
    }

    private void initView(){
        //设定返回按钮
        backImage = (ImageView) findViewById(R.id.back_image_date);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
                initDataShow();
            }
        });
        confirmButton = (Button) findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()){
                    backActivity();
                }
                else{
                    Toast.makeText(DateChooseActivity.this,"请选择入住日期和离开日期",Toast.LENGTH_SHORT).show();
                }
            }
        });

        initData();
        nightText = (TextView) findViewById(R.id.night_count);
        inDate = (TextView) findViewById(R.id.in_data_textview);
        outDate = (TextView) findViewById(R.id.out_data_textview);
        inDateCard = (CardView) findViewById(R.id.in_date_cardview);
        outDateCard = (CardView) findViewById(R.id.out_date_cardview);
        inDateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inDatePicker.show(inDate.getText().toString());
            }
        });
        outDateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outDatePicker.show(outDate.getText().toString());
            }
        });
        initDataShow();
    }

    private void initData(){
        hasInDate = false;
        hasOutDate = false;
        inDateStr = "入住日期";
        outDateStr = "离开日期";
        nightLong = 0;
    }

    private void initDataShow(){
        inDate.setText(inDateStr);
        outDate.setText(outDateStr);
        nightText.setText("共"+nightLong+"晚");
    }

    private void initDateInPicker() {
        long endTimestamp= DateFormatUtils.str2Long(maxDateStr, false);
        long beginTimestamp = System.currentTimeMillis();

        inDatePicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) throws ParseException {
                inDateStr = DateFormatUtils.long2Str(timestamp, false);
                inDate.setText(inDateStr);
                hasInDate = true;
                nightLongListener();
            }
        }, beginTimestamp, endTimestamp);

        selectPicker(inDatePicker);
    }

    private void initDateOutPicker(){
        long endTimestamp = DateFormatUtils.str2Long(maxDateStr, false);
        long beginTimestamp= System.currentTimeMillis();
        outDatePicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) throws ParseException {
                outDateStr = DateFormatUtils.long2Str(timestamp, false);
                outDate.setText(outDateStr);
                hasOutDate = true;
                nightLongListener();
            }
        }, beginTimestamp, endTimestamp);
        selectPicker(outDatePicker);
    }

    //入住日期和离开日期是否都选择了
    private Boolean check(){
        return (hasInDate && hasOutDate);
    }

    //返回主页的数据传输
    private void backActivity(){
        String[] strs = new String[3];
        strs[0] = inDateStr;
        strs[1] = inDateStr;
        strs[2] = ""+nightLong;
        Intent intent = new Intent(DateChooseActivity.this,HomePageActivity.class);
        intent.putExtra("dateData",strs);
        startActivity(intent);
    }

    private void nightLongListener() throws ParseException {
        if(check()){
            if(chooseType == SHORT_CHOOSE){
                nightLong = DateCalculate.calculateDays(inDateStr,outDateStr);
                nightText.setText("共"+nightLong+"晚");
            }
            else{
                nightLong = DateCalculate.calculateMonths(inDateStr,outDateStr);
                nightText.setText("共"+nightLong+"月");
            }
        }
    }

    private void selectPicker(CustomDatePicker mDatePicker){
        // 不允许点击屏幕或物理返回键关闭
        mDatePicker.setCancelable(false);
        // 不显示时和分
        mDatePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mDatePicker.setCanShowAnim(false);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        inDatePicker.onDestroy();
        outDatePicker.onDestroy();
    }

}
