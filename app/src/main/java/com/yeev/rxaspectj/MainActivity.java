package com.yeev.rxaspectj;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.yeev.rxaspectj.annotation.RxAsync;
import com.yeev.rxaspectj.annotation.RxUiThread;
import com.yeev.rxaspectj.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBinding.thread1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAsync();
            }
        });
        mBinding.mainThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMain();
            }
        });
    }

    @RxAsync
    public void doAsync() {
        try {
            System.out.println(Thread.currentThread().getName());
            Log.i("DDDD", "线程");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RxUiThread
    public void doMain() {
        System.out.println(Thread.currentThread().getName());
        Log.i("DDDD", "主线程");
        mBinding.mainThread.setText("MainThread");

    }

}
