package com.example.dell.myapplication.presenter;

import com.example.dell.myapplication.bean.RootBean;
import com.example.dell.myapplication.callback.ICallBack;
import com.example.dell.myapplication.model.Model;
import com.example.dell.myapplication.view.Iview;

public class Presenter implements Ipresenter {
    private Iview mIview;
    private final Model mModel;

    public Presenter(Iview iview) {
        mIview = iview;
        mModel = new Model();
    }

    @Override
    public void setDate() {
        if (mModel != null) {
            mModel.getData(new ICallBack() {
                @Override
                public void onSuccess(RootBean rootBean) {
                    if (rootBean != null) {
                        mIview.onSuccess(rootBean);
                    }
                }

                @Override
                public void onFail(String error) {
                    mIview.onFail(error);
                }
            });
        }

    }
}
