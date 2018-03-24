package com.mick.permission.platform.iml;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * vivo
 * Created by mick on 2018/3/20.
 */

public class VivoSystem extends AbstractAndroidSystem {

    @Override
    public void jumpToSettings(Context context) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packagename", context.getPackageName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            intent.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity"));
        } else {
            intent.setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.safeguard.SoftPermissionDetailActivity"));
        }
        try {
            context.startActivity(intent);
        }catch (Exception e) {
            super.jumpToSettings(context);
        }
    }

    @Override
    public String androidOperateSystemName() {
        return VIVO;
    }
}
