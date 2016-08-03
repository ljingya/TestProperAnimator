package com.example.shuiai.testproperanimator;

import android.animation.TypeEvaluator;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2016/8/3
 */
public class PointEvalutor implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x=startPoint.getX()+fraction*( endPoint.getX()-startPoint.getX());
        float y=startPoint.getY()+fraction*(endPoint.getY()-startPoint.getY());
        Point point=new Point(x,y);
        return point;
    }
}
