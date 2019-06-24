package example.com.shujiaapplication.ui;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import example.com.shujiaapplication.R;

public class IntroduceAppActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_introduce_app);

        TextView function = (TextView)findViewById(R.id.function);
        function.setText("1、【直联房东】"+"\n线上预约高效，直呼房东方便快捷。租客、房东直接联系、看房、签约。"+"\n2、【轻松找房 方便快捷】\n"+"地图找房、条件筛选、关键词搜索，专场推荐、一键发现附近房等。"
                +"\n3、【安全省心】\n"+"实现在线预约、在线签约、在线支付、分期付款、芝麻信用免押等便利的租房功能，租房体验安全便捷。");
    }
}
