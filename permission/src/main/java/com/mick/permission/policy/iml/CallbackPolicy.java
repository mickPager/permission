package com.mick.permission.policy.iml;

import com.mick.permission.PermissionRequest;
import com.mick.permission.PermissionResult;
import com.mick.permission.PermissionService;
import com.mick.permission.policy.RejectPolicy;

/**
 * 在用户拒绝时可收取到回调
 * Created by mick on 2018/3/7.
 */

public class CallbackPolicy implements RejectPolicy {

    @Override
    public void onReject(PermissionService permission, PermissionRequest request) {
        if (request.getCallBack() != null)
            request.getCallBack()
                    .onPermissionResult(new PermissionResult(PermissionResult.RESULT_CODE_SHOW_RATIONALE_REFUSED
                            , request.getPermission()));
    }

    @Override
    public void onRejectCompletely(PermissionService permission, PermissionRequest request) {
        if (request.getCallBack() != null)
            request.getCallBack()
                    .onPermissionResult(new PermissionResult(PermissionResult.RESULT_CODE_REJECTED_COMPLETELY
                            , request.getPermission()));
    }
}
