package com.bignerdranch.android.beom;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bignerdranch.android.beom.Uilt.L;
import com.bignerdranch.android.beom.View.BaseActivity;
import com.bignerdranch.android.beom.View.Setting_Acyivity;
import com.bignerdranch.android.beom.fragment.ButleFragment;
import com.bignerdranch.android.beom.fragment.GrilFragment;
import com.bignerdranch.android.beom.fragment.UserFragment;
import com.bignerdranch.android.beom.fragment.WechaFrament;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private TabLayout mTabLayout;

    private ViewPager mViewPager;

    private List<String> mTitles;

    private  List<Fragment>mFragments;

    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mactivity_laout);
        //去掉影音
        getSupportActionBar().setElevation(0);
        L.d("text");
        initDate();
        initView();
    }

    private void initDate()
    {
        mTitles = new ArrayList<>();
        mTitles.add("服务管家");
        mTitles.add("微信精选");
        mTitles.add("美女社区");
        mTitles.add("个人中心");
        mFragments = new ArrayList<>();
        mFragments.add(new ButleFragment());
        mFragments.add(new GrilFragment());
        mFragments.add(new WechaFrament());
        mFragments.add(new UserFragment());

    }
    private void initView() {

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab_setting);
        mFloatingActionButton.setOnClickListener(this);

        //预加载
        mViewPager.setOffscreenPageLimit(mFragments.size());
        //ViewPager 滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position == 0)
                {

                    mFloatingActionButton.setVisibility(View.GONE);
                }else {
                    mFloatingActionButton.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            //返回item的个数
            @Override
            public int getCount() {
                return mFragments.size();
            }

            //设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles.get(position);
            }
        });
        //绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.fab_setting:
                /*Intent intent  = new Intent(this,Setting_Acyivity.class);
                startActivity(intent);*/
                startActivity(new Intent(this,Setting_Acyivity.class));
                break;
        }

    }
}
