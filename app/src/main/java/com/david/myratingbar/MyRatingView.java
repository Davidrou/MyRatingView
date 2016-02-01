package com.david.myratingbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MyRatingView extends LinearLayout implements View.OnClickListener {

    private int starNum = 5;
    private int markNum = 3;
    private Drawable lightDrawable,blackDrawable;
    private ImageView[] imageViews;

    public MyRatingView(Context context) {
        this(context,null);
    }

    public MyRatingView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MyRatingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.MyRatingView);
        starNum=typedArray.getInt(R.styleable.MyRatingView_totalStarNum,5);
        markNum=typedArray.getInt(R.styleable.MyRatingView_markNum,3);
        lightDrawable=typedArray.getDrawable(R.styleable.MyRatingView_lightDrawable);
        blackDrawable=typedArray.getDrawable(R.styleable.MyRatingView_blackDrawable);
        typedArray.recycle();
        if(starNum<markNum){
            throw new RuntimeException("staNum must > markNum");
        }
        init(context);
    }

    private void init(Context context) {
        this.setOrientation(HORIZONTAL);
        imageViews = new ImageView[starNum];
        for (int i = 0; i < markNum; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageDrawable(lightDrawable);
            imageView.setTag(i);
            imageView.setOnClickListener(this);
            imageViews[i] = imageView;
            this.addView(imageView);
        }
        for (int i = markNum; i < starNum; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setTag(i);
            imageView.setImageDrawable(blackDrawable);
            imageView.setOnClickListener(this);
            imageViews[i] = imageView;
            this.addView(imageView);
        }
    }


    @Override
    public void onClick(View v) {
        int clickIndex = (int) v.getTag();
        markNum = clickIndex + 1;
        for (int i = 0; i <= clickIndex; i++) {
            imageViews[i].setImageDrawable(lightDrawable);
        }
        for (int i = clickIndex + 1; i < imageViews.length; i++) {
            imageViews[i].setImageDrawable(blackDrawable);
        }
    }

    public int getScore() {
        return markNum;
    }
}
