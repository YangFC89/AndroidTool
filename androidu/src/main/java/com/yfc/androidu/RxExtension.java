package com.yfc.androidu;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava线程切换
 */

public class RxExtension {

    /**
     * 切换线程；绑定组件生命周期
     */
    public static <T> ObservableTransformer<T, T> applys(RxAppCompatActivity activity) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.compose(applySchedules()).compose(activity.bindToLifecycle());
            }
        };
    }

    /**
     * 切换线程
     */
    public static <T> ObservableTransformer<T, T> applySchedules() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static void dispose(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

}
