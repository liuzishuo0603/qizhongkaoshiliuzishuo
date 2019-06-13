package com.example.dell.myapplication.view;

import com.example.dell.myapplication.bean.RootBean;

public interface Iview {
    void onSuccess(RootBean rootBean);

    void onFail(String error);
}
