package com.mick.permissions;

import android.app.Application;

import com.mick.permission.PermissionManager;
import com.mick.permission.PermissionService;

/**
 * create by mick.
 *
 * @DATE 2018/3/24
 * @PACKAGE com.mick
 * @PROJECTNAME permission
 */

public class AppDelegate extends Application {

    private PermissionService mPermissionService;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public PermissionService getPermissionService() {
        if (mPermissionService == null) {
            mPermissionService = new PermissionManager();
        }
        return mPermissionService;
    }
}
