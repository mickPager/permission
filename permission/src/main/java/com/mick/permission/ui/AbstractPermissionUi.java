package com.mick.permission.ui;

import android.view.View;

import com.mick.permission.PermissionRequest;
import com.mick.permission.PermissionService;

/**
 * 抽象权限ui,可以自定义 like {@link DefaultPermissionUi }
 * 自定义弹窗需继承自 {@link AbstractPermissionUi } 或者实现 {@link PermissionUi }
 * Created by mick on 2018/3/8.
 */

public abstract class AbstractPermissionUi implements PermissionUi {

    final PermissionService permissionService;

    public AbstractPermissionUi(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 生成通用的对话框点击事件
     * 当用户点击"去允许"时并且已经拒绝这个权限申请（点击不再询问）,将跳转到系统设置界面
     * 当用户点击"去允许"时并且已经拒绝这个权限申请（未点击不再询问）,将重新执行这个请求
     *
     * @param request            正在执行的权限请求
     * @param isRejectCompletely 是否是完全拒绝
     * @return 点击事件
     */
    View.OnClickListener generateCommonClickListener(final PermissionRequest request
            , final boolean isRejectCompletely) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRejectCompletely) {
                    permissionService.jumpSystemSettings(request);//跳转设置界面
                } else {
                    permissionService.executeSystemPermissionRequest(request);//重新执行请求
                }
            }
        };
    }
}
