package com.mick.permission.policy.iml;

import android.app.Activity;

import com.mick.permission.PermissionRequest;
import com.mick.permission.PermissionService;
import com.mick.permission.policy.RejectPolicy;

/**
 * 如果子类不重写{@link AbstractPolicy#onRejectCompletely(PermissionService, PermissionRequest)}
 * 默认将跳转系统设置界面，可以在{@link Activity#onResume()} 时接受到回调
 * Created by mick on 2018/3/8.
 */

public abstract class AbstractPolicy implements RejectPolicy {

    @Override
    public void onRejectCompletely(PermissionService permission
            , PermissionRequest request) {
        permission.jumpSystemSettings(request);
    }
}
