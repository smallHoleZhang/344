package com.bignerdranch.android.beom.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.bignerdranch.android.beom.MainActivity;
import com.bignerdranch.android.beom.R;
import com.bignerdranch.android.beom.Uilt.ShareUtils;
import com.bignerdranch.android.beom.Uilt.StaticClass;
import com.bignerdranch.android.beom.Uilt.UtilTools;

/**
 * Created by hasee on 2017/3/10.
 */

public class SplashActivity  extends BaseActivity{
    /**
     * 1.延时2000ms
     * 2.判断程序是否第一次运行
     * 3.自定义字体
     * 4.Activity全屏主题
     */
    private TextView tv_splash;

    private  Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case StaticClass.HANDLER_SPLASH:
                    //判断程序是否是第一次运行
                    if(isFirst())
                    {
                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                        finish();
                    }else
                    {
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                        finish();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity_layout);
        initView();
    }

    private  void initView()
    {
        mHandler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH,2000);
        tv_splash = (TextView) findViewById(R.id.splash_text);
        UtilTools.setFont(this,tv_splash);

    }

    private  boolean isFirst()
    {
        boolean isFirst = ShareUtils.getBoonlean(this,StaticClass.SHARE_IS_FIRST,true);
        if(isFirst)
        {
            ShareUtils.putBoolean(this,StaticClass.SHARE_IS_FIRST,false);
            return  true;

        }else
        {
            return  false;
        }


    }
}
