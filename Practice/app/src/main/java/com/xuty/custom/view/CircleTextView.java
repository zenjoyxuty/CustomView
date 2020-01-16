package com.xuty.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.xuty.custom.R;

public class CircleTextView extends View {

    public int defaultWidthSize = 200;
    public int defaultHeightSize = 200;
    private Paint paint;
    private String text = "跳过";
    private Context context;
    private  int innerColor;
    private int strokeColor;
    private int currentProgress;
    private long totalTime;


    public CircleTextView(Context context) {
       this(context,null);
    }

    public CircleTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }

    public CircleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;


        init(attrs);

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

    private void init(AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleTextView);
        if(typedArray!=null){
           innerColor = typedArray.getColor(R.styleable.CircleTextView_inner_color, Color.RED);
           strokeColor =  typedArray.getColor(R.styleable.CircleTextView_stroke_color, Color.BLACK);
           typedArray.recycle();
        }

        paint = new Paint();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       int width = getWidth();
       int height = getHeight();
       if(width != height){
           width = height;
       }

        paint.setColor(Color.BLUE);
        canvas.drawCircle(width/2,width/2,300,paint);
        paint.setColor(Color.GREEN);

        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();

        int  textWidth = (int) paint.measureText(text);
        int textBaseX = width/2 - textWidth/2;
        int centerHeight = getHeight()/2;
        int textBaseY = centerHeight+(fontMetricsInt.bottom-fontMetricsInt.top)/2 -fontMetricsInt.bottom;
        canvas.drawText(text,textBaseX,textBaseY,paint);

       // canvas.drawArc();


    }

    public void startDrawProgress(){
        currentProgress = 0;
        post(drawProgress);
    }

    Runnable drawProgress = new Runnable() {
        @Override
        public void run() {
           currentProgress++;
           invalidate();
           postDelayed(drawProgress,totalTime/100);
        }
    };




}
