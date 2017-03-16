package com.bcm.sjs.rzxt;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by SJS on 2017/3/3.
 */

public class mTabLayout extends TabLayout {
    public mTabLayout(Context context) {
        super(context);
    }

    public mTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public mTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager, boolean autoRefresh) {
        super.setupWithViewPager(viewPager, autoRefresh);
    }

    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        super.setupWithViewPager(viewPager);
    }
}
