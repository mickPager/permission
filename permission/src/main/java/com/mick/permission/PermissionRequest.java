package com.mick.permission;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.NonNull;

import com.mick.permission.policy.RejectPolicy;
import com.mick.permission.policy.iml.CallbackPolicy;
import com.mick.permission.policy.iml.RetryPolicy;
import com.mick.permission.policy.iml.UIRejectPolicy;
import com.mick.permission.ui.UIConfig;

/**
 * 權限請求对象
 * <p>
 * 用法:
 * 一个接一个的去请求权限，当第一个权限请求成功
 * 在请求权限通过的回调{@link com.mick.permission.PermissionService.PermissionCallBack#onPermissionResult(PermissionResult)}
 * 再发起第二个请求...
 * Created by mick on 2018/1/24.
 */

public class PermissionRequest {

    /**
     * 请求权限所在的 activity
     * 如果在fragment里面请求权限,请使用 {@link Fragment#getActivity()}
     * 需要注意Activity是否已经 {@link Activity#isFinishing()}
     * 不可为 null
     *
     * @see Activity;
     */
    private @NonNull
    Activity activity;

    /**
     * 需要請求的權限
     * 注意 ：如果需要请求的权限在同一个权限组里面，只需要请求其中一个权限;
     * 注意 ： 其中有一个请求通过,该权限組所有权限默认都将通过;
     * 不可为 null
     *
     * @see android.Manifest.permission
     * eg.  Manifest.permission.WRITE_EXTERNAL_STORAGE
     */
    private @NonNull
    String permission;

    /**
     * 请求码
     * 一个权限请求的标志
     * 这个请求码可以重复
     *
     * @see Integer
     */
    private int requestCode;

    /*** 权限请求 被处理完成触发的接口回调*/
    private PermissionService.PermissionCallBack callBack;
    /**
     * 用户拒绝通过权限的策略
     * <p>
     * 默认能收到回调
     *
     * @see CallbackPolicy
     * @see UIRejectPolicy
     * @see RetryPolicy
     */
    private RejectPolicy rejectPolicy;
    /**
     * 用户拒绝权限弹出自定义权限说明对话框最大展示次数
     * 默认一直展示
     */
    private UIConfig uiConfig;

    private PermissionRequest(@NonNull Builder builder) {
        this.activity = builder.activity;
        this.requestCode = builder.requestCode;
        this.callBack = builder.callBack;
        this.rejectPolicy = builder.rejectPolicy;
        this.permission = builder.permission;
        this.uiConfig = builder.uiConfig;
    }

    public Builder newBuilder() {
        return new Builder(this.activity)
                .requestCode(this.requestCode)
                .callBack(this.callBack)
                .withRejectPolicy(this.rejectPolicy)
                .withUIConfig(this.uiConfig)
                .permission(this.permission);
    }

    public UIConfig getUIConfig() {
        return uiConfig;
    }

    int getRequestCode() {
        return requestCode;
    }

    public PermissionService.PermissionCallBack getCallBack() {
        return callBack;
    }

    public @NonNull
    String getPermission() {
        return permission;
    }


    /**
     * @see PermissionRequest#rejectPolicy
     */
    RejectPolicy getRejectPolicy() {
        return rejectPolicy;
    }

    @NonNull
    public Activity getActivity() {
        return activity;
    }

    public static final class Builder {

        /**
         * 请求权限所在的 activity
         * 如果在fragment里面请求权限,请使用 {@link Fragment#getActivity()}
         * 需要注意Activity是否已经 {@link Activity#isFinishing()}
         * 不可为 null
         *
         * @see Activity;
         */
        private final @NonNull
        Activity activity;

        /**
         * 需要請求的權限
         * 注意 ：如果需要请求的权限在同一个权限组里面，只需要请求其中一个权限;
         * 注意 ： 其中有一个请求通过,该权限組所有权限默认都将通过;
         * 不可为 null
         *
         * @see android.Manifest.permission
         * eg.  Manifest.permission.WRITE_EXTERNAL_STORAGE
         */
        private @NonNull
        String permission;

        /**
         * 请求码
         * 一个权限请求的标志
         * 这个请求码可以重复
         *
         * @see Integer
         */
        private int requestCode;

        /*** 权限请求 被处理完成触发的接口回调*/
        private PermissionService.PermissionCallBack callBack;
        /**
         * 用户拒绝通过权限的策略
         * <p>
         * 默认能收到回调
         *
         * @see CallbackPolicy
         * @see UIRejectPolicy
         * @see RetryPolicy
         */
        private RejectPolicy rejectPolicy = new CallbackPolicy();
        /**
         * 用户拒绝权限弹出自定义权限说明对话框最大展示次数
         * 默认一直展示
         */
        private UIConfig uiConfig;

        public Builder(@NonNull Activity activity) {
            this.activity = activity;
        }

        /**
         * @see PermissionRequest#requestCode
         */
        public Builder requestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;
        }

        /**
         * @see PermissionRequest#permission
         */
        public Builder permission(String permission) {
            this.permission = permission;
            return this;
        }

        /**
         * @see PermissionRequest#rejectPolicy
         */
        public Builder withRejectPolicy(RejectPolicy rejectPolicy) {
            this.rejectPolicy = rejectPolicy;
            return this;
        }

        /**
         * @see PermissionRequest#uiConfig
         */
        public Builder withUIConfig(UIConfig uiConfig) {
            this.uiConfig = uiConfig;
            return this;
        }

        /**
         * @see PermissionRequest#callBack
         */
        public Builder callBack(PermissionService.PermissionCallBack callBack) {
            this.callBack = callBack;
            return this;
        }

        public PermissionRequest build() {
            return new PermissionRequest(this);
        }
    }
}
