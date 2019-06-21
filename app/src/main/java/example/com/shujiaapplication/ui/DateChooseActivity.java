package example.com.shujiaapplication.ui;


import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import example.com.shujiaapplication.R;
import example.com.shujiaapplication.ui.myDatePicker.CustomDatePicker;
import example.com.shujiaapplication.ui.myDatePicker.DateFormatUtils;

public class DateChooseActivity extends BaseActivity {

    private String now;
    private TextView tv;
    private CardView inDateCard;
    private CardView outDateCard;
    private TextView inDate;
    private TextView outDate;
    private CustomDatePicker mDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_choose);

        tv = (TextView) findViewById(R.id.night_count);
        inDate = (TextView) findViewById(R.id.in_data_textview);
        outDate = (TextView) findViewById(R.id.out_data_textview);
        inDateCard = (CardView) findViewById(R.id.in_date_cardview);
        outDateCard = (CardView) findViewById(R.id.out_date_cardview);
        inDateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePicker.show(inDate.getText().toString());
            }
        });
        outDateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePicker.show(outDate.getText().toString());
            }
        });
        initDatePicker();
    }

    private void initDatePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("2019-05-01", false);
        long endTimestamp = System.currentTimeMillis();

        inDate.setText(DateFormatUtils.long2Str(endTimestamp, false));

        // 通过时间戳初始化日期，毫秒级别
        mDatePicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                inDate.setText(DateFormatUtils.long2Str(timestamp, false));
            }
        }, beginTimestamp, endTimestamp);
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
        mDatePicker.onDestroy();
    }

}
