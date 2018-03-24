package com.mick.permission.platform;

import android.content.Context;


/**
 * Android 系统
 * Created by mick on 2018/3/20.
 */

public interface AndroidSystem {
    /**
     * 华为
     */
    String HUAWEI = "huawei";
    /**
     * 小米
     */
    String XIAOMI = "xiaomi";

    /**
     * oppo
     */
    String OPPO = "oppo";
    /**
     * vivo
     */
    String VIVO = "vivo";
    /**
     * 三星
     */
    String SAMSUNG = "samsung";
    /**
     * 魅族
     */
    String MEIZU = "meizu";
    /**
     * 锤子
     */
    String SMARTISAN = "smartisan";

    /**
     * 跳转到各自系统设置详情页面
     *
     * @param context 上下文对象
     */
    void jumpToSettings(Context context);

    /**
     * 各自手机操作系统名字或者标识
     *
     * @return 各自手机操作系统名字
     * @see AndroidSystem#HUAWEI
     * @see AndroidSystem#XIAOMI
     * @see AndroidSystem#MEIZU
     * @see AndroidSystem#VIVO
     * @see AndroidSystem#SMARTISAN
     * @see AndroidSystem#SAMSUNG
     * @see AndroidSystem#OPPO
     */
    String androidOperateSystemName();
}
