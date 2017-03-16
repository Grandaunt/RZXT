package com.bcm.sjs.rzxt;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bcm.sjs.rzxt.Adapter.TabFragmentAdapter;

import java.util.Arrays;

/**
 * Created by janiszhang on 2016/6/10.
 */

public class TabFragment1 extends  android.support.v4.app.Fragment {
    private String TAG = this.getClass().getSimpleName();
    private View viewContent;
    private TabLayout tab_essence;
    private ViewPager vp_essence;

    private final int FIRST_FRAGMENT = 0;
    private final int SECOND_FRAGMENT = 1;
    private final int THIRD_FRAGMENT = 2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.fragment_tab1,container,false);
//        Toolbar toolbar = (Toolbar) viewContent.findViewById(R.id.tab2_toolbar);
//        toolbar.setTitle("");//设置主标题
//        toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
        initConentView(viewContent);
        initData();

        return viewContent;
    }

    public void initConentView(View viewContent) {
        this.tab_essence = (TabLayout) viewContent.findViewById(R.id.tab_essence);
        this.vp_essence = (ViewPager) viewContent.findViewById(R.id.vp_essence);
//        this.vp_essence.setOffscreenPageLimit(1);
    }

    public void initData() {
        //获取标签数据
        String[] titles = getResources().getStringArray(R.array.home_video_tab);
        //创建一个viewpager的adapter
        TabFragmentAdapter adapter = new TabFragmentAdapter(getFragmentManager(), Arrays.asList(titles));
        //关闭预加载，默认一次只加载一个Fragment
        //this.vp_essence.setOffscreenPageLimit(1);
        this.vp_essence.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来
        this.tab_essence.setupWithViewPager(this.vp_essence);

        this.vp_essence.setAdapter(adapter);
        Log.i(TAG,"initData");
    }

    @Override
    public void onResume() {
        initConentView(viewContent);
        initData();
        Log.i(TAG,"onResume");
        super.onResume();
    }

    @Override
    public void onStart() {
        Log.i(TAG,"onStart");
        super.onStart();
    }

    @Override
    public void onPause() {
        Log.i(TAG,"onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG,"onStop");
        super.onStop();
    }
}
