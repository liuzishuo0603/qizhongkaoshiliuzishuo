package com.example.dell.myapplication.model;

import android.util.Log;

import com.example.dell.myapplication.api.ApiSerivce;
import com.example.dell.myapplication.bean.RootBean;
import com.example.dell.myapplication.callback.ICallBack;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model implements Imodel {
    private static final String TAG = "Model";

    @Override
    public void getData(final ICallBack callBack) {
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiSerivce.mUrl)
                .build();
        retrofit.create(ApiSerivce.class).getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RootBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(RootBean rootBean) {
                        if (rootBean != null) {
                            if (rootBean.getCode() == 200) {
                                int size = rootBean.getResult().size();
                                Log.e(TAG, "onNext: 数据" + size);
                                if (size != 0) {
                                    callBack.onSuccess(rootBean);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getCause().getMessage());
                        Log.e(TAG, "onError: " + e.getCause().getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }
                });
    }
}
