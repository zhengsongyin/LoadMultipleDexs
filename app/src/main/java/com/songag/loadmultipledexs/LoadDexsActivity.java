package com.songag.loadmultipledexs;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;

/**
 * 用于展示界面，并且异步加载dex，就不会出现anr，加载完在返回主进程
 * Created by zhengsongyin on 2018/3/22.
 */

public class LoadDexsActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(new Intent(LoadDexsActivity.this, MainActivity.class));
            finish();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                //异步加载dex
                try {
                    MultiDex.install(getApplicationContext());
                } catch (RuntimeException multiDexException) {
                    multiDexException.printStackTrace();
                }

                //由于demo没有多个dex，所以会很快跳转过去，所以可以自己延时3秒看下效果
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //打开主进程的页面
                        startActivity(new Intent(LoadDexsActivity.this, MainActivity.class));
                        finish();
                        //该动画可以避免让用户看到页面切换的过程
                        overridePendingTransition(0, R.anim.alpha_out);
                    }
                });
            }
        }).start();


        setContentView(R.layout.activity_load_dexs);
    }
}
