package com.mick.permission.policy;

import com.mick.permission.PermissionRequest;
import com.mick.permission.PermissionService;

/**
 * 用户拒绝的策略接口
 * Created by mick on 2018/3/7.
 */

public interface RejectPolicy {

    /**
     * 在用户点击拒绝但未点击不再询问时调用
     *
     * @param permission 权限调用管理
     * @param request    正在执行的权限请求
     */
    void onReject(PermissionService permission, PermissionRequest request);

    /**
     * 在用户点击拒绝并且点击不再询问时调用
     *
     * @param permission 权限调用管理
     * @param request    正在执行的权限请求
     */
    void onRejectCompletely(PermissionService permission, PermissionRequest request);
}
