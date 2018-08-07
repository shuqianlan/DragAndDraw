package com.smartlocation.comp.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BoxDrawingView extends View {

    public static final String TAG = "BoxDrawingView";
    private Box mCurrentBox;
    private List<Box> mBoxs;
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    private float startAngle;
    private float endAngle;

    public BoxDrawingView(Context context) {
        this(context, null);
    }

    public BoxDrawingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoxDrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        mBoxs = new ArrayList<>();

        mBoxPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBoxPaint.setColor(0x22ff0000);

        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setColor(0xfff8efe0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());
        int maskedAction = event.getActionMasked();
        int index = event.getActionIndex();
        int count = event.getPointerCount();
        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
                mCurrentBox = new Box(current);
                mBoxs.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                if (count == 2) {
                    endAngle = getRotation(event);
                } else if (mCurrentBox != null) {
                    mCurrentBox.setCurrent(current);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                mCurrentBox = null;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                if (count == 2) {
                    startAngle = getRotation(event);
                }
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(mBackgroundPaint);
        canvas.rotate((endAngle-startAngle)); // 两次的角度差.

        for(Box box: mBoxs) {
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);
            canvas.drawRect(left, top,right,bottom, mBoxPaint);
        }
    }

    private float getRotation(MotionEvent ev) {
        double delta_x = (ev.getX(0) - ev.getX(1));
        double delta_y = (ev.getY(0) - ev.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable state = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        for (int i = 0; i < mBoxs.size(); i++) {
            Box box = mBoxs.get(i);
            bundle.putFloatArray("Box #" + i, new float[]{box.getOrigin().x, box.getOrigin().y, box.getCurrent().x, box.getCurrent().y});
        }

        bundle.putInt("SUM", mBoxs.size());
        bundle.putFloat("startAngle", startAngle);
        bundle.putFloat("endAngle", endAngle);
        bundle.putParcelable("superData", state);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle)state;
        mBoxs = new ArrayList<>();
        for (int i = 0; i < bundle.getInt("SUM"); i++) {
            Box box = new Box(null);
            float[] arr = bundle.getFloatArray("Box #" + i);
            PointF origin = new PointF(arr[0], arr[1]);
            PointF current = new PointF(arr[2], arr[3]);

            box.setOrigin(origin);
            box.setCurrent(current);
            mBoxs.add(box);
        }

        startAngle = bundle.getFloat("startAngle");
        endAngle = bundle.getFloat("endAngle");
        invalidate();
        Parcelable st = bundle.getParcelable("superData");
        super.onRestoreInstanceState(st);
    }
}
