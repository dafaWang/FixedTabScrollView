package com.app.demo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HoldTabScrollView.OnHoldTabScrollViewScrollChanged {

    private TextView tablayout;
    private CustomScrollViewPager viewPager;
    private HoldTabScrollView scrollView;
    private int mHeight = 0;
    private RelativeLayout centerRl, topRl;
    private boolean canJump = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        centerRl = findViewById(R.id.rl_center);
        topRl = findViewById(R.id.rl_top);
        tablayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        scrollView = findViewById(R.id.root_scrollview);
        scrollView.setOnObservableScrollViewScrollChanged(this);
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new Fragment1(viewPager));
        mFragments.add(new Fragment2(viewPager));

        ViewPager_Fragment_Adapter mAdapter = new ViewPager_Fragment_Adapter(getSupportFragmentManager(),mFragments);
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.resetHeight(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            //获取HeaderView的高度，当滑动大于等于这个高度的时候，需要把topView移除当前布局，放入到外层布局
            mHeight = centerRl.getTop();
        }
    }

    @Override
    public void onObservableScrollViewScrollChanged(int l, int t, int oldl, int oldt) {
        if (t >= mHeight) {
            if (tablayout.getParent() != topRl) {
                centerRl.removeView(tablayout);
                topRl.addView(tablayout);
                canJump = false;
            }
        } else {
            if (tablayout.getParent() != centerRl) {
                topRl.removeView(tablayout);
                centerRl.addView(tablayout);
                canJump = true;
            }
        }
        if (canJump && t >= oldt) {
            scrollView.smoothScrollToSlow(0, mHeight, 300);
        } else if (canJump && t < oldt) {
            scrollView.smoothScrollToSlow(0, 0, 300);
        }
    }
}