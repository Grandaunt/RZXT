package com.bcm.sjs.rzxt.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bcm.sjs.rzxt.ContentFragment;
import com.bcm.sjs.rzxt.DB.TaskPro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by janiszhang on 2016/6/10.
 */
//继承FragmentStatePagerAdapter
public class TabFragmentAdapter extends FragmentStatePagerAdapter {
    private String TAG = this.getClass().getSimpleName();
    public static final String TAB_TAG = "@dream@";
    private Context context;

    private List<String> mTitles;

    public TabFragmentAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        mTitles = titles;
        Log.i(TAG,"mTitles="+mTitles);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        //初始化Fragment数据
        ContentFragment fragment = new ContentFragment();
        String[] title = mTitles.get(position).split(TAB_TAG);
        fragment.setType(Integer.parseInt(title[1]));
        fragment.setTitle(title[0]);
        fragment.setPosition(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).split(TAB_TAG)[0];
    }

//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//
//        return super.isViewFromObject(view, object);
//    }
}

