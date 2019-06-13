package com.example.dell.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.myapplication.adapter.HomeAdapter;
import com.example.dell.myapplication.bean.RootBean;
import com.example.dell.myapplication.presenter.Presenter;
import com.example.dell.myapplication.view.Iview;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements Iview {

    private TextView mTvTool;
    private Toolbar mTool;
    private RecyclerView mRlvHome;
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    private void initData() {
        Presenter presenter = new Presenter(this);
        presenter.setDate();
    }

    private void initView() {
        mTvTool = (TextView) findViewById(R.id.tv_tool);
        mTool = (Toolbar) findViewById(R.id.tool);
        mRlvHome = (RecyclerView) findViewById(R.id.rlv_home);
        mTool.setTitle("");
        setSupportActionBar(mTool);
        mTvTool.setText("主页");
        mRlvHome.setLayoutManager(new LinearLayoutManager(this));
        mRlvHome.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new HomeAdapter(this);
        mRlvHome.setAdapter(mAdapter);
        mAdapter.setOnClickListener(new HomeAdapter.onClickListener() {
            @Override
            public void onClick(RootBean.ResultBean rootBean, int position) {

            }
        });
    }

    @Override
    public void onSuccess(RootBean rootBean) {
        if (rootBean != null) {
            List<RootBean.ResultBean> list = rootBean.getResult();
            mAdapter.mList.addAll(list);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(this, "请求数据错误", Toast.LENGTH_SHORT).show();
    }
}
