package com.benharvey.rulerapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    private LinearLayout mLayoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        mLayoutView = (LinearLayout)findViewById(R.id.myLayout);
        addLinesToScreen();
    }

    private void setViewHeightInInches(float inches, View v) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float mXDpi = metrics.xdpi;
        int setinches = Math.round(inches * mXDpi);
        v.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, setinches));
        v.requestLayout();
    }


    private void addLinesToScreen(){
        addInchesLines();
    }

    private void addInchesLines(){
        for(int i=0; i<(12 * 4); i++){
            if(i%4==0)
                addLine(Color.parseColor("#000000"));
            else
                addLine(Color.parseColor("#d3d3d3"));

            addSpace(0.250f);
        }
    }

    private void addLine(int color){
        View v = new View(this);
        v.setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                5
        ));
        v.setBackgroundColor(color);

        mLayoutView.addView(v);
    }

    private void addSpace(float size){
        View blankv = new View(this);
        blankv.setBackgroundColor(Color.parseColor("#FFFFFF"));
        setViewHeightInInches(size, blankv);
        mLayoutView.addView(blankv);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int Y = (int) event.getY();

        int eventaction = event.getAction();

        switch (eventaction) {

            case MotionEvent.ACTION_DOWN:

                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                float mXDpi = metrics.xdpi;

                float inches = Y/mXDpi;

                Toast.makeText(this, "inches =  " + String.valueOf(inches), Toast.LENGTH_SHORT).show();
                break;

        }

        return true;

    }
}
