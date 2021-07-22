## PermissionMonitor
通过hook监控APP的隐私权限方法调用

## install
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

```
```
implementation 'com.github.huage2580:PermissionMonitor:1.0.0'
```

## how to use
```java
//com.hua.permissionmonitor.PermissionMonitor#start
PermissionMonitor.start(false);
//在application中使用
class App{
    onCreate(){
    //------
        PermissionMonitor.start(false);
    }
}
```
logcat output
```
 I/PermissionMonitor: ########################################
 I/PermissionMonitor: #                                      #
 I/PermissionMonitor: #      *PermissionMonitor 已启用*       #
 I/PermissionMonitor: #          请不要在正式环境使用!          #
 I/PermissionMonitor: #                                      #
 I/PermissionMonitor: ########################################

 I/PermissionMonitor: **检测到风险调用函数-> android.app.ApplicationPackageManager#getInstalledPackagesAsUser
 D/PermissionMonitor: android.app.ApplicationPackageManager.getInstalledPackages(ApplicationPackageManager.java:886)
    cn.jiguang.ar.d.c(Unknown Source:13)
    cn.jiguang.ar.d.a(Unknown Source:0)
    cn.jiguang.ar.d$1.b(Unknown Source:6)
    cn.jiguang.s.d.a(Unknown Source:105)
    cn.jiguang.ar.d.b(Unknown Source:70)
    cn.jiguang.ar.a.a(Unknown Source:16)
    cn.jiguang.ar.f.b(Unknown Source:14)
    cn.jiguang.ar.e.c(Unknown Source:83)
    cn.jiguang.o.a.e(Unknown Source:34)
    cn.jiguang.o.a.a(Unknown Source:0)
    cn.jiguang.o.a$b.a(Unknown Source:15)
    cn.jiguang.o.e.run(Unknown Source:9)
    java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:462)
    java.util.concurrent.FutureTask.run(FutureTask.java:266)
    java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:301)
    java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
    java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
    java.lang.Thread.run(Thread.java:929)
```