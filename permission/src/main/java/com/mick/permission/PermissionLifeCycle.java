package com.mick.permission;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * activity 生命周期 监控
 * Created by mick on 2018/3/5.
 */
class PermissionLifeCycle implements Application
        .ActivityLifecycleCallbacks {

    interface OnActivityResumeListener {

        void onActivityOnResume(Activity activity);

    }

    private final Activity activity;

    private final OnActivityResumeListener callback;

    PermissionLifeCycle(Activity activity
            , OnActivityResumeListener callback) {
        this.activity = activity;
        this.callback = callback;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
        //如果请求权限的activity 展示在用户面前 将检查用户是否已经手动通过了权限
        if (this.activity.equals(activity)) {
            if (callback != null)
                callback.onActivityOnResume(activity);
            activity.getApplication()
                    .unregisterActivityLifecycleCallbacks(this);
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }
}
