package example.com.shujiaapplication.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import example.com.shujiaapplication.R;

public class MyImageView extends LinearLayout {

    private ImageView iv;
    private TextView tv;
    public MyImageView(Context context,AttributeSet attrs) {
        super(context, attrs);
        int resourceId = -1;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyImageView);
        iv = new ImageView(context);
        tv = new TextView(context);
        int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.MyImageView_Oriental:
                    resourceId = typedArray.getInt(R.styleable.MyImageView_Oriental, 0);
                    this.setOrientation(resourceId == 1 ? LinearLayout.HORIZONTAL : LinearLayout.VERTICAL);
                    break;
                case R.styleable.MyImageView_Text:
                    resourceId = typedArray.getResourceId(R.styleable.MyImageView_Text, 0);
                    tv.setText(resourceId > 0 ? typedArray.getResources().getText(resourceId) : typedArray.getString(R.styleable.MyImageView_Text));
                    tv.setTextSize(13);
                    tv.setGravity(Gravity.CENTER);
                    break;
                case R.styleable.MyImageView_Src:
                    resourceId = typedArray.getResourceId(R.styleable.MyImageView_Src, 0);
                    iv.setImageResource(resourceId > 0 ?resourceId:R.drawable.collect_black);
                    iv.setAdjustViewBounds(true);
                    iv.setMaxHeight(70);
                    break;
            }
        }
        addView(iv);
        addView(tv);
        typedArray.recycle();
    }

    public void setImage(int picture) {
        iv.setImageResource(picture);
    }
}
