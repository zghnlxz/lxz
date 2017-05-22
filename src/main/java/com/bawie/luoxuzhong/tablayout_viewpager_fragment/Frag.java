package com.bawie.luoxuzhong.tablayout_viewpager_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.net.URL;

/**
 * Created by 罗许忠
 * on 2017/5/20 8:27
 * 实现思路：
 * 1，
 * 2，
 * 作用：
 */

public class Frag extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private FragAdapter fragAdapter;

    private String url ;
    private MyClickListener myClickListener;

 public void setUrl(String url,MyClickListener myClickListener){
     this.url = url;
     this.myClickListener = myClickListener;
 }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("Frag: ", "onCreateView  创建");
        return inflater.inflate(R.layout.frag,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("Frag: ", "onActivityCreated  与Activity 链接");
        initView();

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Frag: ", "onResume  获取焦点");
        httpConn("http://result.eolinker.com/k2BaduF2a6caa275f395919a66ab1dfe4b584cc60685573?uri="+url);
    }

    public void httpConn(String url) {
        Log.e("Frag: ", "httpConn  本次请求的url     " +url);
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("Frag: ", "onSuccess  fragment 请求数据成功1次");
                if (result!= null){
                    Log.e("Frag: ", "onSuccess  fragment 请求数据成功2次");
                    Gson gson = new Gson();
                    FragBean fragBean = gson.fromJson(result,FragBean.class);
                    fragAdapter.setList(fragBean.getResult().getData());
                    recyclerView.setAdapter(fragAdapter);
//                    fragAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("Frag: ", "onError  fragment 请求数据错误");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("Frag: ", "onCancelled  fragment 请求数据取消");
            }

            @Override
            public void onFinished() {
                Log.e("Frag: ", "onFinished  fragment 请求数据完成 Finish！");
            }
        });
    }

    private void initView() {
        recyclerView = (RecyclerView) getView().findViewById(R.id.fragRecyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.fragSwipeRefreshLayout);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),OrientationHelper.VERTICAL));
        fragAdapter = new FragAdapter(getActivity(),myClickListener);

    }


}
