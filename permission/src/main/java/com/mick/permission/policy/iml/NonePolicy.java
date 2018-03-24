package com.mick.permission.policy.iml;

import com.mick.permission.PermissionRequest;
import com.mick.permission.PermissionService;
import com.mick.permission.policy.RejectPolicy;

/**
 * 啥事都不干
 * Created by mick on 2018/3/8.
 */

public class NonePolicy implements RejectPolicy {

    @Override
    public void onReject(PermissionService permission
            , PermissionRequest request) {

    }

    @Override
    public void onRejectCompletely(PermissionService permission
            , PermissionRequest request) {

    }
}
