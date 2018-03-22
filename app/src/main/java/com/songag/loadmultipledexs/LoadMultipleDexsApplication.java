package com.songag.loadmultipledexs;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.songag.loadmultipledexs.utils.ProcessUtils;

/**
 *
 * Created by zhengsongyin on 2018/3/22.
 */

public class LoadMultipleDexsApplication extends Application {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (ProcessUtils.isLoadDexProcess(this)) {
            //如果是加载dex的进程就退出，不做在这里做加载dex，这里加载的话会卡住。
            return;
        }

        //由于在LoadDex进程中已经加载dex，所以以下的操作会很快就执行完成（必须做的，不然会崩溃，报找不到类的错误）
        try {
            MultiDex.install(base);
        } catch (RuntimeException multiDexException) {
            multiDexException.printStackTrace();
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (!ProcessUtils.isMainProcess(this)) {
            //非主进程，没有需要做初始化的任务就可以不往下执行了。
            return;
        }

        //处理主进程的相关初始化任务



    }

}
