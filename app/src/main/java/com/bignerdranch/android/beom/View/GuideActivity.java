package com.bignerdranch.android.beom.View;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bignerdranch.android.beom.MainActivity;
import com.bignerdranch.android.beom.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager mGuideViewPager;

    private List<View> mViews = new ArrayList<>();

    private View mView1,mView2,mView3;

    private ImageView point1,ponit2,point3;

    private ImageView iv_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }
    private  void initView()
    {
        mGuideViewPager = (ViewPager) findViewById(R.id.guide_viewpager);
        mView1 = View.inflate(this,R.layout.pager_item_one,null);
        mView2 = View.inflate(this,R.layout.pager_item_two,null);
        mView3 = View.inflate(this,R.layout.pager_item_three,null);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        point1 = (ImageView) findViewById(R.id.point1);
        ponit2 = (ImageView) findViewById(R.id.point2);
        point3 = (ImageView) findViewById(R.id.point3);

        mView3.findViewById(R.id.btn_start).setOnClickListener(this);
        iv_back.setOnClickListener(this);

        mViews.add(mView1);
        mViews.add(mView2);
        mViews.add(mView3);

        mGuideViewPager.setOffscreenPageLimit(mViews.size());
        mGuideViewPager.setAdapter(new GuideAdapter());
        mGuideViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    switch (position)
                    {
                        case 0:
                            setPointImg(true,false,false);
                            iv_back.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            setPointImg(false,true,false);
                            iv_back.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            setPointImg(false,false,true);
                            iv_back.setVisibility(View.GONE);
                            break;
                    }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_start:
            case R.id.iv_back:
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                break;
        }

    }


    class  GuideAdapter extends PagerAdapter{


        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViews.get(position));
            return  mViews.get(position);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(mViews.get(position));
            //super.destroyItem(container, position, object);
        }
    }


    //设置选中小圆点
    private  void setPointImg(boolean isCheck1, boolean isCheck2, boolean isCheck3)
    {
        if(isCheck1)
        {
            point1.setBackgroundResource(R.drawable.point_on);
        }else {
            point1.setBackgroundResource(R.drawable.point_off);
        }
        if(isCheck2)
        {
            ponit2.setBackgroundResource(R.drawable.point_on);
        }else {
            ponit2.setBackgroundResource(R.drawable.point_off);
        }

        if(isCheck3)
        {
            point3.setBackgroundResource(R.drawable.point_on);
        }else {
            point3.setBackgroundResource(R.drawable.point_off);
        }




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
