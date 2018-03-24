package com.mick.permissions;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mick.permission.PermissionRequest;
import com.mick.permission.PermissionResult;
import com.mick.permission.PermissionService;
import com.mick.permission.ui.UIConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionRequest permissionRequest = new PermissionRequest.Builder(this)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .requestCode(10086)
                .withUIConfig(new UIConfig()
                        .setMaxShowCount(5000)
                        .setOutsideCancelable(true)
                        .withRejectExplain(R.string.permission_storage_reject)
                        .withRejectExplainCompletely(R.string.permission_storage_reject_exactly)
                        .withRejectExplainTip(R.string.permission_storage_reject_tip))
                .callBack(new PermissionService.PermissionCallBack() {
                    @Override
                    public void onPermissionResult(PermissionResult result) {
                        if (result.resultCode == PermissionResult.RESULT_CODE_GRANTED) {
                            //执行读取内存逻辑
                            saveImage();
                        } else {
                            Toast.makeText(getApplicationContext(), "权限被拒绝", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).build();

        getPermissionService().executePermissionRequest(permissionRequest);
    }

    private void saveImage() {
        Toast.makeText(getApplicationContext(), "正在保存图片!", Toast.LENGTH_SHORT).show();
        PermissionRequest permissionRequest = new PermissionRequest.Builder(this)
                .permission(Manifest.permission.READ_CONTACTS)
                .requestCode(10089)
                .withUIConfig(new UIConfig()
                        .setMaxShowCount(50000)
                        .setOutsideCancelable(true)
                        .withRejectExplain(R.string.permission_contacts_reject)
                        .withRejectExplainCompletely(R.string.permission_contacts_reject_exactly))
                .callBack(new PermissionService.PermissionCallBack() {
                    @Override
                    public void onPermissionResult(PermissionResult result) {
                        if (result.resultCode == PermissionResult.RESULT_CODE_GRANTED) {
                            //执行读取内存逻辑
                            readContacts();
                        } else {
                            Toast.makeText(getApplicationContext(), "权限被拒绝", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).build();

        getPermissionService().executePermissionRequest(permissionRequest);
    }

    private void readContacts() {
        Toast.makeText(getApplicationContext(), "读取联系人!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getPermissionService().handleRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    public PermissionService getPermissionService() {
        return ((AppDelegate) getApplication()).getPermissionService();
    }
}
