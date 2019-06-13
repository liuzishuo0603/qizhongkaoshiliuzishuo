package com.example.dell.myapplication.callback;

import com.example.dell.myapplication.bean.RootBean;

public interface ICallBack {
    void onSuccess(RootBean rootBean);

    void onFail(String error);
}
