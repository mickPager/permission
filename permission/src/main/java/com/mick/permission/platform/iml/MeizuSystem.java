package com.mick.permission.platform.iml;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * 魅族
 * Created by mick on 2018/3/20.
 */

public class MeizuSystem extends AbstractAndroidSystem {

    @Override
    public void jumpToSettings(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.jumpToSettings(context);
            return;
        }

        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", context.getPackageName());
        intent.setComponent(new ComponentName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity"));

        try {
            context.startActivity(intent);
        }catch (Exception e) {
            super.jumpToSettings(context);
        }
    }

    @Override
    public String androidOperateSystemName() {
        return MEIZU;
    }
}
