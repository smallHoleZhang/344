package com.bignerdranch.android.beom;

import android.app.Application;

import com.bignerdranch.android.beom.Uilt.StaticClass;

import cn.bmob.v3.Bmob;

/**
 * Created by hasee on 2017/3/8.
 */

public class BaseApplication extends Application {
    //创建

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, StaticClass.Bmob_key);
    }
}
