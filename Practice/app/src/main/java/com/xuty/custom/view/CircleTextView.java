package com.xuty.custom.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleTextView extends View {

    public int defaultWidthSize = 200;
    public int defaultHeightSize = 200;
    private Paint paint;
    private String text;


    public CircleTextView(Context context) {
        super(context);
        init();
    }

    public CircleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public CircleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getMode(heightMeasureSpec);


        if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode ==MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidthSize,defaultHeightSize);
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidthSize,heightSpecSize);
        }else if(heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,defaultHeightSize);

        }


    }

    private void init(){

        paint = new Paint();
        
    }

}
