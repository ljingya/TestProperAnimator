package com.example.shuiai.testproperanimator;

import android.animation.TimeInterpolator;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2016/8/3
 */
public class DefineInterpolation implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        if (input<0.5){
            input= (float) ((Math.sin(input*Math.PI)))/2;
        }else {
            input= (float) (2-Math.sin(input*Math.PI))/2;
        }
        return input;
    }
}
