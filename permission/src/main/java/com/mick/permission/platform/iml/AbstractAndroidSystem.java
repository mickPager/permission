package com.mick.permission.platform.iml;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.mick.permission.platform.AndroidSystem;

/**
 * 原生安卓系统设置详情页面
 * Created by mick on 2018/3/20.
 */

public abstract class AbstractAndroidSystem implements AndroidSystem {

    @Override
    public void jumpToSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.fromParts("package"
                , context.getPackageName(), null));
        context.startActivity(intent);
    }
}
