package com.mick.permission;

import android.app.Activity;
import com.mick.permission.PermissionRequest;

/**
 * 权限请求发起者API接口
 * Created by mick on 2018/3/12.
 */

public interface PermissionService {

    interface PermissionCallBack {
        /**
         * 权限申请通过将调用该回调方法
         * @param result 权限请求的结果
         */
        void onPermissionResult(PermissionResult result);

    }

    /**
     * 检测权限是否通过
     *
     * @param activity   activity
     * @param permission 权限字符串
     * @return true 通过，false不通过
     */
    boolean isPermissionGranted(Activity activity, String permission);

    /**
     * 跳转到系统设置界面
     * 一般在用户点击拒绝(不再询问)后调用
     */
    void jumpSystemSettings(PermissionRequest request);

    /**
     * 發起系统權限請求
     * @param request 执行的请求
     */
    void executeSystemPermissionRequest(PermissionRequest request);

    /**
     * 發起權限請求
     *
     * @param request     被發起的請求
     */
    void executePermissionRequest(PermissionRequest request);


    /**
     * 收取到權限結果時調用,{@link Activity#onRequestPermissionsResult(int, String[], int[])} 中调用
     * 統一處理結果
     *
     * @param activity     請求權限的activity
     * @param requestCode  請求碼
     * @param permissions  申請的權限
     * @param grantResults 申請權限結果 PackageManager.PERMISSION_GRANTED : PackageManager.PERMISSION_DENIED
     */
    void handleRequestPermissionsResult(Activity activity
            , int requestCode, String[] permissions, int[] grantResults);

    /**
     *  当用户拒绝后展示友好的权限说明提示框
     * @param request 当前执行的权限请求
     * @param isRejectExactly true（拒绝并且不再询问） false 拒绝
     */
    void showRequestPermissionRationale(PermissionRequest request, boolean isRejectExactly);
}
