package com.bawie.luoxuzhong.tablayout_viewpager_fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 罗许忠
 * on 2017/5/19 21:39
 * 实现思路：
 * 1，
 * 2，
 * 作用：
 */

public class MyAdapter extends FragmentPagerAdapter {

    private List<Fragment> list = new ArrayList<>();
    private List<MyBean.ResultBean.DateBean> lists = new ArrayList();

    public MyAdapter(FragmentManager fm) {
        super(fm);
        Log.e("MyAdapter: ", "首次创建MyAdapter");
    }

    public void setLists( List<Fragment> list,List<MyBean.ResultBean.DateBean> lists){
        Log.e("MyAdapter: ", "MyAdapter更新数据");
        if (list!=null){
            this.list.addAll(list);
            Log.e("setLists: ", "更新数据list_Fragment长度  "+list.size());
        }
        if (lists!=null){
            this.lists.addAll(lists);
            Log.e("setLists: ", "更新数据lists长度  "+lists.size()+ lists.toString());
        }
    }

    @Override
    public Fragment getItem(int position) {
        Log.e("MyAdapter: ", "getItem  "+list.get(position));
        return list.get(position);
    }

    @Override
    public int getCount() {
        Log.e("MyAdapter: ", "getCount  "+list.size());
        return list.size();
    }


    /**
     * 这是tablayout的设置标题的回调方法
     */
    @Override
    public CharSequence getPageTitle(int position) {
        Log.e("MyAdapter: ", "getPageTitle  "+lists.get(position).getTitle()+"--"+lists.get(position).getTitle());
        return lists.get(position).getTitle();
    }
}
