package com.example.shuiai.testproperanimator;

import android.animation.TypeEvaluator;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2016/8/3
 */
public class ColorEvalutor implements TypeEvaluator {
    private int mCurrentGreen = -1;
    private int mCurrentRed = -1;
    private int mCurrentBlue = -1;

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        String startColor = (String) startValue;
        String endColor = (String) endValue;
        int startGreen = Integer.parseInt(startColor.substring(1, 3), 16);
        int startRed = Integer.parseInt(startColor.substring(3, 5), 16);
        int startBlue = Integer.parseInt(startColor.substring(5, 7), 16);
        int endGreen = Integer.parseInt(endColor.substring(1, 3), 16);
        int endRed = Integer.parseInt(endColor.substring(3, 5), 16);
        int endBlue = Integer.parseInt(endColor.substring(5, 7), 16);
        /**
         * 初始化颜色
         */
        if (mCurrentGreen == -1) {
            mCurrentGreen = startGreen;
        }
        if (mCurrentRed== -1) {
            mCurrentRed = startRed;
        }
        if (mCurrentBlue == -1) {
            mCurrentBlue = startBlue;
        }
        /**
         * 计算结束颜色和出事颜色的差值
         */
        int greenDis = Math.abs(startGreen - endGreen);
        int redDis = Math.abs(startGreen - endGreen);
        int blueDis = Math.abs(startGreen - endGreen);
        int colorDis = greenDis + redDis + blueDis;
        if (mCurrentGreen != endGreen) {
            mCurrentGreen = getCurrentColor(startGreen, endGreen, colorDis, 0, fraction);
        }else if (mCurrentRed!=endRed){
            mCurrentGreen = getCurrentColor(startRed, endRed, colorDis, greenDis, fraction);
        }else if (mCurrentBlue!=endBlue){
            mCurrentBlue = getCurrentColor(startBlue, endBlue, colorDis, redDis+greenDis, fraction);
        }
        String currentColor="#"+HexString(mCurrentGreen)+HexString(mCurrentRed)+HexString(mCurrentBlue);
        return currentColor;
    }

    private int getCurrentColor(int startGreen, int endGreen, int colorDis, int i, float fraction) {
        int currentColor;
        if (startGreen > endGreen) {
            currentColor = (int) (startGreen - (colorDis * fraction - i));
            if (currentColor < endGreen) {
                currentColor = endGreen;
            }
        } else {
            currentColor = (int) (startGreen + (colorDis * fraction - i));
            if (currentColor > endGreen) {
                currentColor = endGreen;
            }
        }
        return currentColor;
    }

    private String HexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString;
    }
}
