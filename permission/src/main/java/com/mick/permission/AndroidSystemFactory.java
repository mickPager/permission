package com.mick.permission;

import android.os.Build;

import com.mick.permission.platform.AndroidSystem;
import com.mick.permission.platform.iml.AbstractAndroidSystem;
import com.mick.permission.platform.iml.HuaweiSystem;
import com.mick.permission.platform.iml.MeizuSystem;
import com.mick.permission.platform.iml.OppoSystem;
import com.mick.permission.platform.iml.SamsungSystem;
import com.mick.permission.platform.iml.SmartisanSystem;
import com.mick.permission.platform.iml.VivoSystem;
import com.mick.permission.platform.iml.XiaomiSystem;


/**
 * Android 系统工厂
 * Created by mick on 2018/3/20.
 */
class AndroidSystemFactory {

    /**
     * 根据Android系统制造商名字,获取到平台
     *
     * @return 对应的Android操作平台
     */
    static AndroidSystem findAndroidSystem() {
        AndroidSystem androidSystem;
        switch (Build.MANUFACTURER.toLowerCase()) {
            case AndroidSystem.HUAWEI:
                androidSystem = new HuaweiSystem();
                break;

            case AndroidSystem.XIAOMI:
                androidSystem = new XiaomiSystem();
                break;

            case AndroidSystem.SAMSUNG:
                androidSystem = new SamsungSystem();
                break;

            case AndroidSystem.MEIZU:
                androidSystem = new MeizuSystem();
                break;

            case AndroidSystem.OPPO:
                androidSystem = new OppoSystem();
                break;


            case AndroidSystem.VIVO:
                androidSystem = new VivoSystem();
                break;

            case AndroidSystem.SMARTISAN:
                androidSystem = new SmartisanSystem();
                break;

            default:
                androidSystem = new AbstractAndroidSystem() {
                    @Override
                    public String androidOperateSystemName() {
                        return Build.MANUFACTURER.toLowerCase();
                    }
                };
                break;
        }
        return androidSystem;
    }
}
