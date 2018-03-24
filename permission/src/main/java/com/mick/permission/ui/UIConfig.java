package com.mick.permission.ui;

import android.app.Dialog;
import android.support.annotation.StringRes;

/**
 * ui 配置对象
 * Created by mick on 2018/3/13.
 */

public class UIConfig {

    /**
     * 当用户点击拒绝并且不再询问,展示自定义的权限说明框中的文本内容
     */
    private @StringRes
    int rejectExplainCompletely;
    /**
     * 当用户点击拒绝,展示自定义的权限说明框中的文本内容
     */
    private @StringRes
    int rejectExplain;

    /**
     * 当用户点击拒绝,展示自定义的权限说明框中的文本内容
     * 类似于下面的小提示:"拒绝后香港苏宁或者苏宁易购将无法正常运行"
     * 传0将不展示
     */
    private @StringRes
    int rejectExplainTip;

    /**
     * 自定义弹框最大展示次数,默认（理论上）可以一直弹出,可配置
     */
    private int maxShowCount = Integer.MAX_VALUE;

    /**
     * 弹出的自定义权限说明框点击外面能否 @{{@link Dialog#dismiss()}} 默认可以
     */
    private boolean outsideCancelable = true;


    public int getMaxShowCount() {
        return maxShowCount;
    }

    boolean isOutsideCancelable() {
        return outsideCancelable;
    }


    int getRejectExplainTip() {
        return rejectExplainTip;
    }

    /**
     * @see UIConfig#rejectExplain
     */
    public UIConfig withRejectExplain(@StringRes int rejectExplain) {
        this.rejectExplain = rejectExplain;
        return this;
    }

    /**
     * @see UIConfig#rejectExplainTip
     */
    public UIConfig withRejectExplainTip(@StringRes int rejectExplainTip) {
        this.rejectExplainTip = rejectExplainTip;
        return this;
    }

    /**
     * @see UIConfig#rejectExplainCompletely
     */
    public UIConfig withRejectExplainCompletely(@StringRes int rejectExplainCompletely) {
        this.rejectExplainCompletely = rejectExplainCompletely;
        return this;
    }

    /**
     * @see UIConfig#maxShowCount
     */
    public UIConfig setMaxShowCount(int maxShowCount) {
        this.maxShowCount = maxShowCount;
        return this;
    }

    /**
     * @see UIConfig#outsideCancelable
     */
    public UIConfig setOutsideCancelable(boolean outsideCancelable) {
        this.outsideCancelable = outsideCancelable;
        return this;
    }

    /**
     * @see UIConfig#rejectExplainCompletely
     */
    @StringRes
    int getRejectExplainCompletely() {
        return rejectExplainCompletely;
    }

    /**
     * @see UIConfig#rejectExplain
     */
    @StringRes
    int getRejectExplain() {
        return rejectExplain;
    }

}
