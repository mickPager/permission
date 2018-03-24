package com.mick.permission.platform.iml;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * 华为
 * Created by mick on 2018/3/20.
 */

public class HuaweiSystem extends AbstractAndroidSystem {

    private static final String PKG = "com.huawei.systemmanager";

    private static final String CLS = "com.huawei.permissionmanager.ui.MainActivity";

    @Override
    public void jumpToSettings(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.jumpToSettings(context);
            return;
        }

        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(new ComponentName(PKG, CLS));
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            super.jumpToSettings(context);
        }
    }

    @Override
    public String androidOperateSystemName() {
        return HUAWEI;
    }
}
