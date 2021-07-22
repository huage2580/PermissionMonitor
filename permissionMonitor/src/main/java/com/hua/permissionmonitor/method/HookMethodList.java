package com.hua.permissionmonitor.method;

import java.util.LinkedList;

public abstract class HookMethodList {
    /**
     * 正常方法
     * @return
     */
    public LinkedList<MethodWrapper> getMethodList(){
        return addList(new LinkedList<MethodWrapper>());
    }

    /**
     * 抽象方法，需要找到实现然后反射？
     * @return
     */
    public abstract LinkedList<ClassMethodGroup> getAbsMethodList();

    protected abstract LinkedList<MethodWrapper> addList(LinkedList<MethodWrapper> list);

}
