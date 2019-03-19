package com.yeev.rxaspectj.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author:
 * @Date: Created in 16:42 2019/3/1
 * @Description:
 */
@Aspect
public class AsynAspect {

    @Around("execution(@com.yeev.rxaspectj.annotation.RxAsync void *(..))")
    public void doAsync(final ProceedingJoinPoint joinPoint) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                try {
                    //执行原始的方法
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.newThread()).
                subscribe();
    }

    @Around("execution(@com.yeev.rxaspectj.annotation.RxUiThread void *(..))")
    public void doMainThread(final ProceedingJoinPoint joinPoint) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                try {
                    //执行原始的方法
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).
                subscribe();
    }

    @Around("execution(@com.yeev.rxaspectj.annotation.RxUiThread void *(..))")
    public void doIO(final ProceedingJoinPoint joinPoint) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                try {
                    //执行原始的方法
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io()).
                subscribe();
    }

}
