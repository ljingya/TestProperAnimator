package com.example.shuiai.testproperanimator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2016/8/3
 */
public class MyView extends View {
    private float RADIOUS = 50f;
    private Paint paint;
    private Point point;
    String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        paint.setColor(Color.parseColor(color));
        invalidate();
    }

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (point == null) {
            point = new Point(RADIOUS, RADIOUS);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }

    private void startAnimation() {
        Point startPoint = new Point(getWidth() / 2, RADIOUS);
        Point endPoint = new Point(getWidth() / 2, getHeight() - RADIOUS);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvalutor(), startPoint, endPoint);
        anim.setRepeatMode(ValueAnimator.INFINITE);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                point = (Point) valueAnimator.getAnimatedValue();

                invalidate();
            }
        });
        ObjectAnimator anim2 = ObjectAnimator.ofObject(this, "color", new ColorEvalutor(), "#AA0000", "#0000AA");
        anim2.setRepeatMode(ValueAnimator.INFINITE);
        AnimatorSet set = new AnimatorSet();
        set.play(anim).with(anim2);
//        set.setInterpolator(new BounceInterpolator());
        set.setInterpolator(new DefineInterpolation());
        set.setDuration(5000);


        set.start();

    }

    private void drawCircle(Canvas canvas) {
        float x = point.getX();
        float y = point.getY();
        canvas.drawCircle(x, y, RADIOUS, paint);
    }

    private StartAnimtorListener startAnimatorListener;

    public void setStartAnimatorListener(StartAnimtorListener startAnimatorListener) {
        this.startAnimatorListener = startAnimatorListener;
    }

    public interface StartAnimtorListener {
        void startAnimation();
    }

}
