package com.mick.permission.ui;

import com.mick.permission.PermissionRequest;

/**
 * 权限ui接口
 * Created by mick on 2018/3/8.
 */

public interface PermissionUi {
    /**
     * 当用户拒绝权限请求后，重走逻辑展示权限解释说明的对话框{@link DefaultPermissionUi}
     *
     * @param request            当前正在执行的请求
     * @param isRejectCompletely true 用户拒绝（点击不再询问） ,false 用户拒绝（未点击不再询问）
     */
    void showRequestPermissionRationale(PermissionRequest request, boolean isRejectCompletely);

}
