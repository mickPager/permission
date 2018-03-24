package com.mick.permission;

/**
 * 权限请求的结果
 * Created by mick on 2018/3/5.
 */

public class PermissionResult {

    /**
     * 结果码:用户允许
     */
    public static final int RESULT_CODE_GRANTED = 10010;
    /**
     * 结果码:用户拒绝（未点不再询问）
     */
    public static final int RESULT_CODE_REJECTED = 10086;
    /**
     * 结果码:用户拒绝（点不再询问）
     */
    public static final int RESULT_CODE_REJECTED_COMPLETELY = 10099;
    /**
     * 结果码:用户已经拒绝过一次或者几次
     */
    public static final int RESULT_CODE_SHOW_RATIONALE = 10001;
    /**
     * 结果码:用户已经拒绝过一次或者几次
     * ,展示自定义权限说明弹框 {@link com.suning.service.ebuy.permissions.ui.PermissionUi}
     * 用户点击了 重新请求权限按钮 触发回调
     */
    public static final int RESULT_CODE_SHOW_RATIONALE_REFUSED = 110119;

    /**
     * 结果码
     * {@link PermissionResult#RESULT_CODE_GRANTED}
     * {@link PermissionResult#RESULT_CODE_REJECTED}
     * {@link PermissionResult#RESULT_CODE_REJECTED_COMPLETELY}
     * {@link PermissionResult#RESULT_CODE_SHOW_RATIONALE}
     * {@link PermissionResult#RESULT_CODE_SHOW_RATIONALE_REFUSED}
     */
    public int resultCode;

    /**
     * 请求的权限
     */
    public String permission;

    public PermissionResult(int resultCode, String permission) {
        this.resultCode = resultCode;
        this.permission = permission;
    }
}
