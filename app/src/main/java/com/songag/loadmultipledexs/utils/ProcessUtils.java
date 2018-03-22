package com.songag.loadmultipledexs.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

/**
 *
 * @author zhengsongyin
 * @date 2018/1/8
 */

public class ProcessUtils {


    /**
     *  判断是否不是主线程
     */
    public static boolean isMainProcess(Context context) {
        return isProcess(context,"com.songag.loadmultipledexs");
    }

    /**
     *  判断是否不是主线程
     */
    public static boolean isLoadDexProcess(Context context) {
        return isProcess(context,"com.songag.loadmultipledexs:LoadDexs");
    }


    /**
     *  判断是否不是主线程
     */
    public static boolean isProcess(Context context,String processName) {

        if(TextUtils.isEmpty(processName)){
            return false;
        }

        int pid = android.os.Process.myPid();
        String currentProcessName = "";

        try {
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (manager == null) {
                return true;
            }
            for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
                if (process.pid == pid) {
                    currentProcessName = process.processName;
                    break;
                }
            }
        } catch (Exception e) {
            return true;
        }

        return processName.equals(currentProcessName);

    }
}
