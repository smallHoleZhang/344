package com.bignerdranch.android.beom.Uilt;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * 项目名： begin
 * 包名：   com.bignerdranch.android.begin
 * 文件名： UtilTools
 * 创建者： Zhang
 * 描述：   工具类
 */

public class UtilTools {

    public static  void setFont(Context context , TextView textView)
    {
        Typeface typeface =  Typeface.createFromAsset(context.getAssets(),"fonts/FONT.TTF");
        textView.setTypeface(typeface);

    }
}
