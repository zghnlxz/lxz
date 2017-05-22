package com.bawie.luoxuzhong.tablayout_viewpager_fragment;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 罗许忠
 * on 2017/5/19 21:30
 * 实现思路：
 * 1，
 * 2，
 * 作用：
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
