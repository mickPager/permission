package com.mick.permission.policy.iml;

import com.mick.permission.PermissionRequest;
import com.mick.permission.PermissionService;
import com.mick.permission.policy.RejectPolicy;

/**
 * 当用户点击拒绝权限时,将弹出弹框,该弹框可以自定义,如果未自定义
 * Created by mick on 2018/3/7.
 */

public class UIRejectPolicy implements RejectPolicy {

    @Override
    public void onReject(PermissionService permission
            , PermissionRequest request) {
        permission.showRequestPermissionRationale(request, false);
    }

    @Override
    public void onRejectCompletely(PermissionService permission
            , PermissionRequest request) {
       permission.showRequestPermissionRationale(request, true);
    }
}
