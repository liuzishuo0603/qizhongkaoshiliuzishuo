package com.example.dell.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.bean.RootBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {
    private Context mContext;
    public List<RootBean.ResultBean> mList = new ArrayList<>();
    private int positions;
    private onClickListener mListener;
    private onLongClickListener mLongClickListener;

    public HomeAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (i == 0) {
            View view = inflater.inflate(R.layout.home_item_banner, null);
            return new MyViewHolder1(view);
        } else if (i == 1) {
            View view = inflater.inflate(R.layout.home_item_one, null);
            return new MyViewHolder2(view);
        } else if (i == 2) {
            View view = inflater.inflate(R.layout.home_item_two, null);
            return new MyViewHolder3(view);
        } else {
            View view = inflater.inflate(R.layout.home_item_three, null);
            return new MyViewHolder4(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        if (type == 0) {
            final MyViewHolder1 holder1 = (MyViewHolder1) viewHolder;
            holder1.mBanner.setImages(mList).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    RootBean.ResultBean bean = (RootBean.ResultBean) path;
                    Glide.with(mContext).load(bean.getHeader()).placeholder(R.mipmap.ic_launcher).into(imageView);
                }
            }).start();
        } else if (type == 1) {
            if (mList.size() > 0) {
                positions = i - 1;
            }
            MyViewHolder2 holder2 = (MyViewHolder2) viewHolder;
            RootBean.ResultBean bean = mList.get(positions);
            holder2.mTv1.setText(bean.getName());
            holder2.mTv2.setText(bean.getText());
            Glide.with(mContext).load(bean.getImages()).placeholder(R.mipmap.ic_launcher).apply(RequestOptions.circleCropTransform()).into(holder2.mIv);
            if (mListener != null) {
                mListener.onClick(bean, i);
            }
        } else if (type == 2) {
            MyViewHolder3 holder3 = (MyViewHolder3) viewHolder;
            RootBean.ResultBean bean = mList.get(positions);
            holder3.mTv1.setText(bean.getName());
            holder3.mTv2.setText(bean.getText());
            Glide.with(mContext).load(bean.getImages()).placeholder(R.mipmap.ic_launcher).apply(RequestOptions.circleCropTransform()).into(holder3.mIv1);
            Glide.with(mContext).load(bean.getImages()).placeholder(R.mipmap.ic_launcher).apply(RequestOptions.circleCropTransform()).into(holder3.mIv2);
        } else {
            MyViewHolder4 holder4 = (MyViewHolder4) viewHolder;
            RootBean.ResultBean bean = mList.get(positions);
            holder4.mTv1.setText(bean.getName());
            holder4.mTv2.setText(bean.getText());
            Glide.with(mContext).load(bean.getHeader()).placeholder(R.mipmap.ic_launcher).apply(RequestOptions.circleCropTransform()).into(holder4.mIv1);
            Glide.with(mContext).load(bean.getHeader()).placeholder(R.mipmap.ic_launcher).apply(RequestOptions.circleCropTransform()).into(holder4.mIv2);
            Glide.with(mContext).load(bean.getHeader()).placeholder(R.mipmap.ic_launcher).apply(RequestOptions.circleCropTransform()).into(holder4.mIv3);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && mList.size() > 0) {
            return 0;
        } else if (position % 3 == 1) {
            return 1;
        } else if (position % 3 == 2) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public int getItemCount() {
        if (mList.size() > 0) {
            return mList.size() + 1;
        } else {
            return mList.size();
        }
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder {

        private Banner mBanner;

        public MyViewHolder1(View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.banner_home);
        }
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {

        private ImageView mIv;
        private TextView mTv2;
        private TextView mTv1;

        public MyViewHolder2(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv_item_one);
            mTv1 = itemView.findViewById(R.id.tv_itme_one);
            mTv2 = itemView.findViewById(R.id.tv_item_one2);
        }
    }

    class MyViewHolder3 extends RecyclerView.ViewHolder {

        private TextView mTv2;
        private ImageView mIv2;
        private ImageView mIv1;
        private TextView mTv1;

        public MyViewHolder3(View itemView) {
            super(itemView);
            mIv1 = itemView.findViewById(R.id.iv_item_two);
            mIv2 = itemView.findViewById(R.id.iv_item_two2);
            mTv1 = itemView.findViewById(R.id.tv_itme_two);
            mTv2 = itemView.findViewById(R.id.tv_item_two2);
        }
    }

    class MyViewHolder4 extends RecyclerView.ViewHolder {

        private ImageView mIv1;
        private ImageView mIv2;
        private ImageView mIv3;
        private TextView mTv1;
        private TextView mTv2;

        public MyViewHolder4(View itemView) {
            super(itemView);
            mIv1 = itemView.findViewById(R.id.iv_item_three);
            mIv2 = itemView.findViewById(R.id.iv_item_three2);
            mIv3 = itemView.findViewById(R.id.iv_item_three3);
            mTv1 = itemView.findViewById(R.id.tv_itme_three);
            mTv2 = itemView.findViewById(R.id.tv_item_three2);
        }
    }

    public interface onClickListener {
        void onClick(RootBean.ResultBean rootBean, int position);
    }

    public interface onLongClickListener {
        void onLongClick(View v, int position);
    }

    public void setOnClickListener(onClickListener listener) {
        mListener = listener;
    }

    public void setOnLongClickListener(onLongClickListener longClickListener) {
        mLongClickListener = longClickListener;
    }
}
