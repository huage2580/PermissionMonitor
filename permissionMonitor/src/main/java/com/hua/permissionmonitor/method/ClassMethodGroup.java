package com.hua.permissionmonitor.method;

import java.util.LinkedList;
import java.util.Objects;

public class ClassMethodGroup {
    private String targetClassName;//android.accounts.AbstractAccountAuthenticator$Transport
    private LinkedList<String> methodGroup = new LinkedList<>();//所有需要监测的风险函数

    public ClassMethodGroup(String className) {
        this.targetClassName = className;
    }

    public void addMethod(String name){
        methodGroup.add(name);
    }

    public String getTargetClassName() {
        return targetClassName;
    }

    public LinkedList<String> getMethodGroup() {
        return methodGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassMethodGroup that = (ClassMethodGroup) o;
        return Objects.equals(targetClassName, that.targetClassName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetClassName);
    }
}
