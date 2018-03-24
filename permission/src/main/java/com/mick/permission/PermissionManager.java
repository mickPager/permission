package com.mick.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.mick.permission.platform.AndroidSystem;
import com.mick.permission.ui.DefaultPermissionUi;
import com.mick.permission.ui.PermissionUi;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 权限 Manager
 * Created by mick on 2018/3/7.
 */

public class PermissionManager implements PermissionService {

    private static final Map<String, PermissionRequest> REQUESTS = new LinkedHashMap<>();

    private static final AndroidSystem mSySYSTEM = AndroidSystemFactory.findAndroidSystem();

    private PermissionUi permissionUi;

    public PermissionManager() {
        this(null);
    }

    public PermissionManager(PermissionUi permissionUi) {
        this.permissionUi = permissionUi == null
                ? new DefaultPermissionUi(this) : permissionUi;
    }


    @Override
    public void executeSystemPermissionRequest(PermissionRequest request) {
        executePermissionRequestInternal(request);
    }

    @Override
    public void executePermissionRequest(final PermissionRequest request) {
        final Activity activity = request.getActivity();

        if (Looper.myLooper() != activity.getMainLooper()) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    executePermissionRequestReal(request);
                }
            });
        }else {
            executePermissionRequestReal(request);
        }
    }

    private void executePermissionRequestReal(PermissionRequest request) {
        final Activity activity = request.getActivity();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || isPermissionGranted(activity, request.getPermission())) {
            dispatchGrantedResult(activity, request, PackageManager.PERMISSION_GRANTED);
        } else {
            if (ActivityCompat
                    .shouldShowRequestPermissionRationale(activity
                            , request.getPermission())) {
                onShowUI(request, false);
            } else {
                //如果用户点击过不再询问并且拒绝，请求权限不会弹框
                executeSystemPermissionRequest(request);
            }
        }
    }

    // 执行请求核心步骤
    private void executePermissionRequestInternal(PermissionRequest request) {
        REQUESTS.put(buildKey(request), request);
        ActivityCompat.requestPermissions(request.getActivity()
                , new String[]{request.getPermission()}, request.getRequestCode());
    }

    /**
     * 构建请求队列 key
     *
     * @param request 即将执行的请求
     * @return key值 由Activity类名加上请求的权限加上请求码构成的
     */
    private String buildKey(PermissionRequest request) {
        return request.getActivity().getClass().getSimpleName() + request.getPermission() + request.getRequestCode();
    }

    private String buildKey(Activity activity, String permission, int requestCode) {
        return activity.getClass().getSimpleName() + permission + requestCode;
    }

    /**
     * 分发权限请求执行完成的结果
     *
     * @param grantResult {@link PackageManager#PERMISSION_GRANTED} and {@link PackageManager#PERMISSION_DENIED}
     */
    private void dispatchGrantedResult(Activity activity, PermissionRequest request, int grantResult) {
        if (grantResult == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat
                    .shouldShowRequestPermissionRationale(activity
                            , request.getPermission())) {
                request.getRejectPolicy().onReject(this, request);
            } else {
                if (PreferenceManager.getDefaultSharedPreferences(activity)
                        .getBoolean(request.getPermission()
                                , false)) {
                    onShowUI(request, true);
                } else {
                    request.getRejectPolicy().onRejectCompletely(this, request);
                    /*如果这个权限被拒绝(并且点击不再询问) ActivityCompat
                    .shouldShowRequestPermissionRationale(activity
                            , request.getPermission()) 这个方法返回值为false 通过sp避免逻辑错误*/
                    PreferenceManager
                            .getDefaultSharedPreferences(activity)
                            .edit().putBoolean(request.getPermission(), true)
                            .apply();
                }
            }
        } else {
            onDispatchCallBack(request, PermissionResult.RESULT_CODE_GRANTED);
        }
    }

    /**
     * 展示自定义弹框
     *
     * @param request            权限请求
     * @param isRejectCompletely true 完全拒绝 : false 仅仅拒绝
     */
    private void onShowUI(PermissionRequest request, boolean isRejectCompletely) {
        int count = PreferenceManager.getDefaultSharedPreferences(request.getActivity()).getInt(buildKey(request), 0);
        if (request.getUIConfig().getMaxShowCount() > count) {
            showRequestPermissionRationale(request,isRejectCompletely);
            PreferenceManager.getDefaultSharedPreferences(request.getActivity())
                    .edit()
                    .putInt(buildKey(request), count + 1)
                    .apply();
        }
    }

    private void onDispatchCallBack(PermissionRequest request, int resultCode) {
        if (resultCode == PermissionResult.RESULT_CODE_GRANTED) {
            PreferenceManager.getDefaultSharedPreferences(request.getActivity())
                    .edit()
                    .remove(buildKey(request))
                    .apply();
        }

        if (request.getCallBack() != null)
            request.getCallBack().onPermissionResult(new PermissionResult(resultCode, request.getPermission()));
    }


    @Override
    public void handleRequestPermissionsResult(Activity activity
            , int requestCode, String[] permissions, int[] grantResults) {

        if (grantResults.length <= 0) return;

        final PermissionRequest remove = REQUESTS.remove(buildKey(activity, permissions[0], requestCode));
        if (remove == null || !remove.getPermission()
                .equals(permissions[0])) {
            return;
        }
        dispatchGrantedResult(activity, remove, grantResults[0]);
    }

    @Override
    public void showRequestPermissionRationale(PermissionRequest request, boolean isRejectExactly) {
        this.permissionUi.showRequestPermissionRationale(request, isRejectExactly);
    }


    @Override
    public boolean isPermissionGranted(Activity activity, String permission) {
        return ContextCompat.checkSelfPermission(activity, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void jumpSystemSettings(final PermissionRequest request) {
        Activity activity = request.getActivity();
        activity.getApplication().registerActivityLifecycleCallbacks(new PermissionLifeCycle(activity, new PermissionLifeCycle.OnActivityResumeListener() {
            @Override
            public void onActivityOnResume(Activity activity) {
                Log.e("haha","onActivityOnResume");
                dispatchGrantedResult(activity, request
                        , ContextCompat.checkSelfPermission(activity
                                , request.getPermission()));
            }
        }));

        mSySYSTEM.jumpToSettings(activity);
    }
}
