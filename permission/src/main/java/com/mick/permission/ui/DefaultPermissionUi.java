package com.mick.permission.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mick.permission.PermissionRequest;
import com.mick.permission.PermissionService;
import com.mick.permission.R;

/**
 * 默认对话框展示
 * Created by mick on 2018/3/8.
 */

public class DefaultPermissionUi extends AbstractPermissionUi {


    public DefaultPermissionUi(PermissionService permissionService) {
        super(permissionService);
    }

    @Override
    public void showRequestPermissionRationale(PermissionRequest request
            , boolean isRejectCompletely) {
        new PermissionDialog(request.getActivity(), request.getUIConfig())
                .isRejectCompletely(isRejectCompletely)
                .setAgreeClickListener(generateCommonClickListener(request, isRejectCompletely))
                .show();
    }

    /**
     * 用户拒绝权限申请后弹出的对话框
     */
    private static final class PermissionDialog extends Dialog {

        private View.OnClickListener agreeClickListener;

        private @StringRes
        int dialogContentText;

        /**
         * 完全拒绝
         */
        private boolean isRejectCompletely;

        private final UIConfig uiConfig;

        PermissionDialog(Context context, UIConfig uiConfig) {
            super(context);
            setCanceledOnTouchOutside(uiConfig.isOutsideCancelable());

            dialogContentText = isRejectCompletely ? (uiConfig.getRejectExplainCompletely() == 0
                    ? uiConfig.getRejectExplain() : uiConfig.getRejectExplainCompletely())
                    : uiConfig.getRejectExplain();

            this.uiConfig = uiConfig;

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Window window = getWindow();
            if (window != null) {
                window.requestFeature(Window.FEATURE_NO_TITLE);
                window.setBackgroundDrawableResource(android.R.color.transparent);
            }

            WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            Point outSize = new Point();
            assert windowManager != null;
            windowManager.getDefaultDisplay().getSize(outSize);

            View contentView = getLayoutInflater().inflate(R.layout.layout_permission_service_denied, (ViewGroup) getWindow().getDecorView(), false);

            Button okTv = (Button) contentView.findViewById(R.id.tv_service_permission_nice);
            TextView tipTv = (TextView) contentView.findViewById(R.id.tv_service_permission_tip);
            TextView contentTv = (TextView) contentView.findViewById(R.id.tv_service_permission_content);
            ImageView tipBgIv = (ImageView) contentView.findViewById(R.id.iv_service_permission);

            okTv.setText(isRejectCompletely ? "去设置" : "去允许");

            okTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (agreeClickListener != null)
                        agreeClickListener.onClick(v);
                    dismiss();
                }
            });

            tipTv.setVisibility(View.VISIBLE);

            if (uiConfig.getRejectExplainTip() == 0) {
                tipTv.setVisibility(View.GONE);
            } else {
                tipTv.setText(uiConfig.getRejectExplainTip());
            }

            contentTv.setText(dialogContentText);

            tipBgIv.setImageResource(isRejectCompletely ? R.drawable.icon_permission_service_reject_r : R.drawable.icon_permission_sevice_reject);

            setContentView(contentView
                    , new ViewGroup.LayoutParams(outSize.x * 4 / 5, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        PermissionDialog setAgreeClickListener(View.OnClickListener agreeClickListener) {
            this.agreeClickListener = agreeClickListener;
            return this;
        }


        PermissionDialog isRejectCompletely(boolean isRejectCompletely) {
            this.isRejectCompletely = isRejectCompletely;
            return this;
        }
    }
}
