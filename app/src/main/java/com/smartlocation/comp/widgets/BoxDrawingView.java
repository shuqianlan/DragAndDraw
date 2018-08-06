package com.smartlocation.comp.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

public class BoxDrawingView extends View {

    /*
    *  PointF: float
    *  Point:  int
    *
    *
    * */

    public static final String TAG = "BoxDrawingView";
    private Box mCurrentBox;
    private List<Box> mBoxs;

    public BoxDrawingView(Context context) {
        this(context, null);
    }

    public BoxDrawingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoxDrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        initialize();

        // TODO:自定义属性.
    }

    private void initialize() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());
        String action = "";
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mCurrentBox = new Box(current);
                mBoxs.add(mCurrentBox);
                action = "Action_Down";
                break;
            case MotionEvent.ACTION_MOVE:
                action = "Action_Move";
                if (mCurrentBox != null) {
                    mCurrentBox.setCurrent(current);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                action = "Action_Up";
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "Action_Cancel";
                mCurrentBox = null;
                break;
            default:
                action = "unKnown";
        }

        Log.d(TAG, action +" at x=" + current.x + ", y=" + current.y);
        return true;
    }
}
