package com.mick.permission.policy.iml;

import com.mick.permission.PermissionRequest;
import com.mick.permission.PermissionResult;
import com.mick.permission.PermissionService;

/**
 * 重新请求权限,可设置最大重试次数 默认2次
 * 超过最大次数可接收到回调方法 结果码 {@link com.mick.permission.PermissionResult#RESULT_CODE_SHOW_RATIONALE_REFUSED}
 * Created by mick on 2018/3/7.
 */

public class RetryPolicy extends AbstractPolicy {

    private final int maxRetryCount;

    private int currentRetryCount;

    public RetryPolicy() {
        this(2);
    }

    public RetryPolicy(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    @Override
    public void onReject(PermissionService permission, PermissionRequest request) {
        if (currentRetryCount < maxRetryCount) {
            permission.executeSystemPermissionRequest(request);
            currentRetryCount ++;
        } else {
            if (request.getCallBack() != null)
                request.getCallBack().onPermissionResult(new PermissionResult(PermissionResult.RESULT_CODE_SHOW_RATIONALE_REFUSED, request.getPermission()));
        }
    }
}
