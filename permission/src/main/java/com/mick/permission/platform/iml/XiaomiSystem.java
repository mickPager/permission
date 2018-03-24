package com.mick.permission.platform.iml;

import android.content.Context;
import android.content.Intent;

/**
 * 小米
 * Created by mick on 2018/3/20.
 */

public class XiaomiSystem extends AbstractAndroidSystem {

    private static final String ACTION = "miui.intent.action.APP_PERM_EDITOR";

    @Override
    public void jumpToSettings(Context context) {
        Intent intent = new Intent(ACTION);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("extra_pkgname", context.getPackageName());
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            super.jumpToSettings(context);
        }
    }

    @Override
    public String androidOperateSystemName() {
        return XIAOMI;
    }
}
