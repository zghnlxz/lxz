package com.bawie.luoxuzhong.tablayout_viewpager_fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
implements MyClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getResources().getColor(R.color.colorAccent);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        final ViewPager vp = (ViewPager) findViewById(R.id.viewpager);

        tabLayout.setTabTextColors(Color.BLACK,Color.RED);
        final MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(vp);



        x.http().get(new RequestParams(
                "http://result.eolinker.com/gfGTLlHc049c6b450500b16971f52bd8e83f6b2fed305ab?uri=news"),
                new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result!=null){
                    Log.e("onSuccess: ", "首次请求成功");
                    Gson gson = new Gson();
                    MyBean myBean = gson.fromJson(result,MyBean.class);
                    List<Fragment> list = new ArrayList<Fragment>();
                    for (int i = 0; i < myBean.getResult().getDate().size(); i++) {
                        Frag f= new Frag();
                        f.setUrl(myBean.getResult().getDate().get(i).getUri(),MainActivity.this);
                        Log.e("onSuccess: ", "   "+myBean.getResult().getDate().get(i).getUri());
                        list.add(f);
                    }
                    myAdapter.setLists(list,myBean.getResult().getDate());
                    myAdapter.notifyDataSetChanged();
                    tabLayout.setTabsFromPagerAdapter(myAdapter);
//                    final MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
//                    myAdapter.setLists(list,myBean.getResult().getDate());
//
//                    vp.setAdapter(myAdapter);
//                    tabLayout.setTabsFromPagerAdapter(myAdapter);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("onError: ", "首次请求错误");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("onCancelled: ", "首次请求取消");
            }

            @Override
            public void onFinished() {
                Log.e("onFinished: ", "首次请求完成");
            }
        });

    }

    @Override
    public void onClick(String url) {
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);

        intent.putExtra("key",url);
        startActivity(intent);
    }
}
