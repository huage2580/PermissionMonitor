package com.hua.permissionmonitor.method;


import com.hua.permissionmonitor.PermissionMonitor;
import com.hua.permissionmonitor.handler.MethodHandler;
import com.hua.permissionmonitor.handler.ParamsPrintHandler;

public class MethodWrapper {
    private Class<?> targetClass;
    private String targetMethod;
    private Class<?>[] params;
    private MethodHandler methodHandler;

    public static MethodWrapper newPrint(Class<?> targetClass, String targetMethod, Class<?>... params){
        MethodWrapper temp = new MethodWrapper(targetClass, targetMethod, params);
        temp.setMethodHandler(new ParamsPrintHandler());
        return temp;
    }

    public MethodWrapper(Class<?> targetClass, String targetMethod, Class<?>... params) {
        this.targetClass = targetClass;
        this.targetMethod = targetMethod;
        this.params = params;
    }

    public void setMethodHandler(MethodHandler methodHandler) {
        this.methodHandler = methodHandler;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public String getTargetMethod() {
        return targetMethod;
    }

    public Class<?>[] getParams() {
        return params;
    }

    public Object[] getParamsWithDefaultHandler(){
        Object[] temp = new Object[params.length+1];
        System.arraycopy(params, 0, temp, 0, params.length);
        if (methodHandler == null){
            temp[params.length] = PermissionMonitor.defaultMethodHandler;
        }else {
            temp[params.length] = methodHandler;
        }
        return temp;
    }
}
