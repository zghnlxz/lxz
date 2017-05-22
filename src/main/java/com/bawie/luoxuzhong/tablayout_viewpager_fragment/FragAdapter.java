package com.bawie.luoxuzhong.tablayout_viewpager_fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 罗许忠
 * on 2017/5/20 10:03
 * 实现思路：
 * 1，
 * 2，
 * 作用：
 */

public class FragAdapter  extends RecyclerView.Adapter<FragAdapter.MyViewHolder>{

    private List<FragBean.ResultBean.DataBean> list = new ArrayList<>();
    private Context context;

    private MyClickListener myClickListener;

    public FragAdapter(Context context,MyClickListener myClickListener){
        this.context = context;
        this.myClickListener = myClickListener;
    }

    public void setList(List<FragBean.ResultBean.DataBean> list){
        if (list!=null){
            this.list.addAll(list);
        }
    }

    class Click implements View.OnClickListener{

        private String url;
        public Click(String url){
            this.url = url;
        }

        @Override
        public void onClick(View v) {
            myClickListener.onClick(url);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragitem,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.e("FragAdapter: ", "onBindViewHolder  ");

        Click click = new Click(list.get(position).getUrl());

        Log.e("FragAdapter: ", "跳转url  "+list.get(position).getUrl());

        holder.titlefRightImage.setOnClickListener(click);
        holder.title.setOnClickListener(click);
        holder.image1.setOnClickListener(click);
        holder.image3.setOnClickListener(click);
        holder.image2.setOnClickListener(click);

        holder.title.setText(list.get(position).getTitle());
        if (list.get(position).getThumbnail_pic_s03()!=null
                && list.get(position).getThumbnail_pic_s02()!=null
                && list.get(position).getThumbnail_pic_s()!=null
                && list.get(position).getThumbnail_pic_s03()!=""
                && list.get(position).getThumbnail_pic_s02()!=""
                && list.get(position).getThumbnail_pic_s()!=""){
            holder.image1.setVisibility(View.VISIBLE);
            holder.image2.setVisibility(View.VISIBLE);
            holder.image3.setVisibility(View.VISIBLE);

            x.image().bind(holder.image1,list.get(position).getThumbnail_pic_s());
            x.image().bind(holder.image2,list.get(position).getThumbnail_pic_s02());
            x.image().bind(holder.image3,list.get(position).getThumbnail_pic_s03());
        } else if (list.get(position).getThumbnail_pic_s03()!=null
                && list.get(position).getThumbnail_pic_s03()!="" ) {
            x.image().bind(holder.titlefRightImage,list.get(position).getThumbnail_pic_s());
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView titlefRightImage;
        ImageView image1;
        ImageView image2;
        ImageView image3;

        public  MyViewHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.fragText);
            this.titlefRightImage = (ImageView) itemView.findViewById(R.id.fragTextRightImage);
            this.image1 = (ImageView) itemView.findViewById(R.id.fragImage1);
            this.image2 = (ImageView) itemView.findViewById(R.id.fragImage2);
            this.image3 = (ImageView) itemView.findViewById(R.id.fragImage3);
        }


    }
}
