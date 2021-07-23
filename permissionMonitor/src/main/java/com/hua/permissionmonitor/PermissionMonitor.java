package com.hua.permissionmonitor;

import android.util.Log;

import com.hua.permissionmonitor.method.ClassMethodGroup;
import com.hua.permissionmonitor.method.HookMethodList;
import com.hua.permissionmonitor.method.StrictMethodGroupList;
import com.hua.permissionmonitor.handler.MethodHandler;
import com.hua.permissionmonitor.method.MethodWrapper;
import com.hua.permissionmonitor.method.NormalMethodList;

import java.lang.reflect.Method;

import de.robv.android.xposed.DexposedBridge;
/**
 * ⣿⣿⣿⣿⣿⣿⢟⣡⣴⣶⣶⣦⣌⡛⠟⣋⣩⣬⣭⣭⡛⢿⣿⣿⣿⣿
 * ⣿⣿⣿⣿⠋⢰⣿⣿⠿⣛⣛⣙⣛⠻⢆⢻⣿⠿⠿⠿⣿⡄⠻⣿⣿⣿
 * ⣿⣿⣿⠃⢠⣿⣿⣶⣿⣿⡿⠿⢟⣛⣒⠐⠲⣶⡶⠿⠶⠶⠦⠄⠙⢿
 * ⣿⠋⣠⠄⣿⣿⣿⠟⡛⢅⣠⡵⡐⠲⣶⣶⣥⡠⣤⣵⠆⠄⠰⣦⣤⡀
 * ⠇⣰⣿⣼⣿⣿⣧⣤⡸ ⣿⡀⠂⠁⣸⣿⣿⣿⣿⣇⠄⠈⢀⣿⣿⠿
 * ⣰⣿⣿⣿⣿⣿⣿⣿⣷⣤⣈⣙⠶⢾⠭⢉⣁⣴⢯⣭⣵⣶⠾⠓⢀⣴
 * ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣉⣤⣴⣾⣿⣿⣦⣄⣤⣤⣄⠄⢿⣿
 * ⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⠈⢿
 * ⣿⣿⣿⣿⣿⣿⡟⣰⣞⣛⡒⢒⠤⠦⢬⣉⣉⣉⣉⣉⣉⣉⡥⠴⠂⢸
 * ⠻⣿⣿⣿⣿⣏⠻⢌⣉⣉⣩⣉⡛⣛⠒⠶⠶⠶⠶⠶⠶⠶⠶⠂⣸⣿
 * ⣥⣈⠙⡻⠿⠿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⠿⠛⢉⣠⣶⣶⣿⣿
 * ⣿⣿⣿⣶⣬⣅⣒⣒⡂⠈⠭⠭⠭⠭⠭⢉⣁⣄⡀⢾⣿⣿⣿⣿⣿⣿
 *
 * 权限监测，记得打包不要加上
 * Created on 2021/7/21 16:11.
 * @author hua
 * @file PermissionMonitor
 */
public class PermissionMonitor {
    public static final String TAG = "PermissionMonitor";
    /**
     * 严格模式
     */
    private static boolean strictMode = false;

    public static final MethodHandler defaultMethodHandler = new MethodHandler();

    /**
     * 开启监控
     * 建议开启严格模式,普通模式只监测常见的问题
     * @param strictMode 严格模式，监测所有权限相关的方法
     */
    public static void start(boolean strictMode){
        PermissionMonitor.strictMode = strictMode;
        hookMethod();
        printWarning();
    }

    private static void printWarning() {
        Log.i(TAG, "########################################");
        Log.i(TAG, "#                                      #");
        Log.i(TAG, "#      *PermissionMonitor 已启用*       #");
        Log.i(TAG, "#          请不要在正式环境使用!          #");
        Log.i(TAG, "#                                      #");
        Log.i(TAG, "########################################");
    }

    public static void registerMethod(MethodWrapper methodWrapper){
        try{
            DexposedBridge.findAndHookMethod(methodWrapper.getTargetClass(),methodWrapper.getTargetMethod(),methodWrapper.getParamsWithDefaultHandler());
        }catch (NoSuchMethodError error){
            Log.wtf(TAG, "registeredMethod: NoSuchMethodError->"+error.getMessage());
        }
    }

    private static void hookMethod() {
        //普通
        HookMethodList methodList = new NormalMethodList();
        for (MethodWrapper methodWrapper : methodList.getMethodList()) {
            registerMethod(methodWrapper);
        }
        for (ClassMethodGroup classMethodGroup : methodList.getAbsMethodList()) {
            registerClass(classMethodGroup);
        }
        //超级严格模式
        if (strictMode){
            loadStrictMethod();
        }
        //logger

    }

    private static void loadStrictMethod() {
        StrictMethodGroupList strictMethodGroupList = new StrictMethodGroupList();
        for (ClassMethodGroup classMethodGroup : strictMethodGroupList.getClassGroupList()) {
            registerClass(classMethodGroup);
        }
    }

    private static void registerClass(ClassMethodGroup classMethodGroup) {
        try {
            Class<?> clazz = Class.forName(classMethodGroup.getTargetClassName());
            Method[] declareMethods = clazz.getDeclaredMethods();

            for (Method method : declareMethods) {
                if (classMethodGroup.getMethodGroup().contains(method.getName())){
                    DexposedBridge.hookMethod(method,defaultMethodHandler);
                }
            }

        } catch (Exception e) {
            Log.wtf(TAG,"registerClass Error-> " + e.getMessage());
        }
    }

}
