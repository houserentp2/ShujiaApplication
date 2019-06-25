package example.com.shujiaapplication.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import example.com.shujiaapplication.R;

public class DoubleSlideSeekBar extends View {

    private int lineWidth;
    private int lineLength = 400;
    private int textHeight;
    private int imageWidth;
    private int imageHeight;
    private boolean hasRule;
    private boolean isLowerMoving;
    private boolean isUpperMoving;
    private int textSize;
    private int textColor;
    private int inColor = Color.BLUE;
    private int outColor = Color.BLUE;
    private int ruleColor = Color.BLUE;
    private int ruleTextColor = Color.BLUE;
    private Bitmap bitmapLow;
    private Bitmap bitmapBig;
    private int slideLowX;
    private int slideBigX;
    private int bitmapHeight;
    private int bitmapWidth;
    private int paddingLeft = 100;
    private int paddingRight = 100;
    private int paddingTop = 50;
    private int paddingBottom = 10;
    private int lineStart = paddingLeft;
    private int lineY;
    private int lineEnd = lineLength + paddingLeft;
    private int bigValue = 100;
    private int smallValue = 0;
    private float smallRange;
    private float bigRange;
    private String unit = " ";
    private int equal = 20;
    private String ruleUnit = " ";
    private int ruleTextSize = 20;
    private int ruleLineHeight = 20;
    private Paint linePaint;
    private Paint bitmapPaint;
    private Paint textPaint;
    private Paint paintRule;

    public DoubleSlideSeekBar(Context context) {
        this(context, null);
    }

    public DoubleSlideSeekBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoubleSlideSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DoubleSlideSeekBar, defStyleAttr, 0);
        int size = typedArray.getIndexCount();
        for (int i = 0; i < size; i++) {
            int type = typedArray.getIndex(i);
            switch (type) {
                case R.styleable.DoubleSlideSeekBar_inColor:
                    inColor = typedArray.getColor(type, Color.BLACK);
                    break;
                case R.styleable.DoubleSlideSeekBar_lineHeight:
                    lineWidth = (int) typedArray.getDimension(type, dip2px(getContext(), 10));
                    break;
                case R.styleable.DoubleSlideSeekBar_outColor:
                    outColor = typedArray.getColor(type, Color.YELLOW);
                    break;
                case R.styleable.DoubleSlideSeekBar_textColor:
                    textColor = typedArray.getColor(type, Color.BLUE);
                    break;
                case R.styleable.DoubleSlideSeekBar_textSize:
                    textSize = typedArray.getDimensionPixelSize(type, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.DoubleSlideSeekBar_imageLow:
                    bitmapLow = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(type, 0));
                    break;
                case R.styleable.DoubleSlideSeekBar_imageBig:
                    bitmapBig = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(type, 0));
                    break;
                case R.styleable.DoubleSlideSeekBar_imageheight:
                    imageHeight = (int) typedArray.getDimension(type, dip2px(getContext(), 20));
                    break;
                case R.styleable.DoubleSlideSeekBar_imagewidth:
                    imageWidth = (int) typedArray.getDimension(type, dip2px(getContext(), 20));
                    break;
                case R.styleable.DoubleSlideSeekBar_hasRule:
                    hasRule = typedArray.getBoolean(type, false);
                    break;
                case R.styleable.DoubleSlideSeekBar_ruleColor:
                    ruleColor = typedArray.getColor(type, Color.BLUE);
                    break;
                case R.styleable.DoubleSlideSeekBar_ruleTextColor:
                    ruleTextColor = typedArray.getColor(type, Color.BLUE);
                    break;
                case R.styleable.DoubleSlideSeekBar_unit:
                    unit = typedArray.getString(type);
                    break;
                case R.styleable.DoubleSlideSeekBar_equal:
                    equal = typedArray.getInt(type, 10);
                    break;
                case R.styleable.DoubleSlideSeekBar_ruleUnit:
                    ruleUnit = typedArray.getString(type);
                    break;
                case R.styleable.DoubleSlideSeekBar_ruleTextSize:
                    ruleTextSize = typedArray.getDimensionPixelSize(type, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.DoubleSlideSeekBar_ruleLineHeight:
                    ruleLineHeight = (int) typedArray.getDimension(type, dip2px(getContext(), 10));
                    break;
                case R.styleable.DoubleSlideSeekBar_bigValue:
                    bigValue = typedArray.getInteger(type, 100);
                    break;
                case R.styleable.DoubleSlideSeekBar_smallValue:
                    smallValue = typedArray.getInteger(type, 100);
                    break;


                default:
                    break;
            }
        }
        typedArray.recycle();
        init();
    }

    private void init() {
        if (bitmapLow == null) {
            bitmapLow = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }
        if (bitmapBig == null) {
            bitmapBig = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }
        bitmapHeight = bitmapLow.getHeight();
        bitmapWidth = bitmapLow.getWidth();
        int newWidth = imageWidth;
        int newHeight = imageHeight;
        float scaleWidth = ((float) newWidth) / bitmapWidth;
        float scaleHeight = ((float) newHeight) / bitmapHeight;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        bitmapLow = Bitmap.createBitmap(bitmapLow, 0, 0, bitmapWidth, bitmapHeight, matrix, true);
        bitmapBig = Bitmap.createBitmap(bitmapBig, 0, 0, bitmapWidth, bitmapHeight, matrix, true);
        bitmapHeight = bitmapLow.getHeight();
        bitmapWidth = bitmapLow.getWidth();
        slideLowX = lineStart;
        slideBigX = lineEnd;
        smallRange = smallValue;
        bigRange = bigValue-40;
        if (hasRule) {
            paddingTop = paddingTop + Math.max(textSize, ruleLineHeight + ruleTextSize);
        } else {
            paddingTop = paddingTop + textSize;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getMyMeasureWidth(widthMeasureSpec);
        int height = getMyMeasureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int getMyMeasureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            size = Math.max(size, paddingBottom + paddingTop + bitmapHeight + 10);
        } else {
            int height = paddingBottom + paddingTop + bitmapHeight + 10;
            size = Math.min(size, height);
        }
        return size;
    }

    private int getMyMeasureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            size = Math.max(size, paddingLeft + paddingRight + bitmapWidth * 2);
        } else {
            int width = paddingLeft + paddingRight + bitmapWidth * 2;
            size = Math.min(size, width);
        }
        lineLength = size - paddingLeft - paddingRight - bitmapWidth;
        lineEnd = lineLength + paddingLeft + bitmapWidth / 2;
        lineStart = paddingLeft + bitmapWidth / 2;
        slideBigX = lineEnd;
        slideLowX = lineStart;
        return size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        lineY = getHeight() - paddingBottom - bitmapHeight / 2;
        textHeight = lineY - bitmapHeight / 2 - 10;
        if (hasRule) {
            drawRule(canvas);
        }
        if (linePaint == null) {
            linePaint = new Paint();
        }
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(lineWidth);
        linePaint.setColor(inColor);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(slideLowX, lineY, slideBigX, lineY, linePaint);
        linePaint.setColor(outColor);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(lineStart, lineY, slideLowX, lineY, linePaint);
        canvas.drawLine(slideBigX, lineY, lineEnd, lineY, linePaint);
        if (bitmapPaint == null) {
            bitmapPaint = new Paint();
        }
        canvas.drawBitmap(bitmapLow, slideLowX - bitmapWidth / 2, lineY - bitmapHeight / 2, bitmapPaint);
        canvas.drawBitmap(bitmapBig, slideBigX - bitmapWidth / 2, lineY - bitmapHeight / 2, bitmapPaint);
        if (textPaint == null) {
            textPaint = new Paint();
        }
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setAntiAlias(true);
        canvas.drawText(String.format("%.0f" + unit, smallRange), slideLowX - bitmapWidth / 2, textHeight, textPaint);
        canvas.drawText(String.format("%.0f" + unit, bigRange-40), slideBigX - bitmapWidth / 2, textHeight, textPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        float nowX = event.getX();
        float nowY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                boolean rightY = Math.abs(nowY - lineY) < bitmapHeight / 2;
                boolean lowSlide = Math.abs(nowX - slideLowX) < bitmapWidth / 2;
                boolean bigSlide = Math.abs(nowX - slideBigX) < bitmapWidth / 2;
                if (rightY && lowSlide) {
                    isLowerMoving = true;
                } else if (rightY && bigSlide) {
                    isUpperMoving = true;
                } else if (nowX >= lineStart && nowX <= slideLowX - bitmapWidth / 2 && rightY) {
                    slideLowX = (int) nowX;
                    updateRange();
                    postInvalidate();
                } else if (nowX <= lineEnd && nowX >= slideBigX + bitmapWidth / 2 && rightY) {
                    slideBigX = (int) nowX;
                    updateRange();
                    postInvalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isLowerMoving) {
                    if (nowX <= slideBigX - bitmapWidth && nowX >= lineStart - bitmapWidth / 2) {
                        slideLowX = (int) nowX;
                        if (slideLowX < lineStart) {
                            slideLowX = lineStart;
                        }
                        updateRange();
                        postInvalidate();
                    }

                } else if (isUpperMoving) {
                    if (nowX >= slideLowX + bitmapWidth && nowX <= lineEnd + bitmapWidth / 2) {
                        slideBigX = (int) nowX;
                        if (slideBigX > lineEnd) {
                            slideBigX = lineEnd;
                        }
                        updateRange();
                        postInvalidate();

                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isUpperMoving = false;
                isLowerMoving = false;
                break;
            default:
                break;
        }

        return true;
    }

    private void updateRange() {
        smallRange = computRange(slideLowX);
        bigRange = computRange(slideBigX)-40;
        if (onRangeListener != null) {
            onRangeListener.onRange(smallRange, bigRange);
        }
    }

    private float computRange(float range) {
        return (range - lineStart) * (bigValue - smallValue) / lineLength + smallValue;
    }

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    protected void drawRule(Canvas canvas) {
        if (paintRule == null) {
            paintRule = new Paint();
        }
        paintRule.setStrokeWidth(1);
        paintRule.setTextSize(ruleTextSize);
        paintRule.setTextAlign(Paint.Align.CENTER);
        paintRule.setAntiAlias(true);
        for (int i = smallValue; i <= bigValue; i += (bigValue - smallValue) / equal) {
            float degX = lineStart + i * lineLength / (bigValue - smallValue);
            int degY = lineY - ruleLineHeight;
            paintRule.setColor(ruleColor);
            canvas.drawLine(degX, lineY, degX, degY, paintRule);
            paintRule.setColor(ruleTextColor);
            canvas.drawText(String.valueOf(i) + ruleUnit, degX, degY, paintRule);
        }
    }
    public interface onRangeListener {
        void onRange(float low, float big);
    }

    private onRangeListener onRangeListener;

    public void setOnRangeListener(DoubleSlideSeekBar.onRangeListener onRangeListener) {
        this.onRangeListener = onRangeListener;
    }
}
