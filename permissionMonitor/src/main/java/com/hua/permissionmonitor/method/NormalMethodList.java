package com.hua.permissionmonitor.method;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanSettings;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.media.AudioRecord;
import android.media.ImageReader;
import android.media.MediaRecorder;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.WorkSource;
import android.provider.Settings;

import com.hua.permissionmonitor.handler.ContentResolverHandler;
import com.hua.permissionmonitor.handler.SettingsResolverHandler;

import java.net.NetworkInterface;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;


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
 * 危险的监控项目
 * Created on 2021/7/22 9:11.
 * @author hua
 * @file NormalMethodList
 */
public class NormalMethodList extends HookMethodList {

    @Override
    public LinkedList<ClassMethodGroup> getAbsMethodList() {
        LinkedList<ClassMethodGroup> list = new LinkedList<>();
        //PackageManager
        ClassMethodGroup pkgManagerHook = new ClassMethodGroup("android.app.ApplicationPackageManager");
        pkgManagerHook.addMethod("getInstalledPackagesAsUser");
        list.add(pkgManagerHook);
        //SmsManager    短信
        ClassMethodGroup smsManagerHook = new ClassMethodGroup("android.telephony.SmsManager");
        smsManagerHook.addMethod("sendTextMessageInternal");
        smsManagerHook.addMethod("getDefault");
        list.add(smsManagerHook);
        //runtime
        ClassMethodGroup runtimeHook = new ClassMethodGroup("java.lang.Runtime");
        runtimeHook.addMethod("exec");
        list.add(runtimeHook);
        return list;
    }

    @SuppressLint("NewApi")
    @Override
    protected LinkedList<MethodWrapper> addList(LinkedList<MethodWrapper> list) {
        //--------------debug-----------------
//        list.add(new MethodWrapper(Activity.class,"onResume"));
//        list.add(new MethodWrapper(Host.class,"fuck",int.class));
        //------------------------------------
        //TODO 添加数据
        //-------设备信息
        list.add(new MethodWrapper(android.telephony.TelephonyManager.class,"getDeviceId"));
        list.add(new MethodWrapper(android.telephony.TelephonyManager.class,"getDeviceId",int.class));
        list.add(new MethodWrapper(android.telephony.TelephonyManager.class,"getSubscriberId",int.class));
        list.add(new MethodWrapper(android.telephony.TelephonyManager.class,"getImei"));
        list.add(new MethodWrapper(android.telephony.TelephonyManager.class,"getImei",int.class));
        list.add(new MethodWrapper(android.telephony.TelephonyManager.class,"getMeid",int.class));
        list.add(new MethodWrapper(android.telephony.TelephonyManager.class,"getSimSerialNumber",int.class));
        list.add(new MethodWrapper(android.telephony.TelephonyManager.class,"getSimOperatorNameForPhone",int.class));
        list.add(new MethodWrapper(android.telephony.TelephonyManager.class,"getSimCountryIsoForPhone",int.class));
        list.add(new MethodWrapper(android.telephony.TelephonyManager.class,"getTypeAllocationCode",int.class));
        list.add(new MethodWrapper(android.telephony.TelephonyManager.class,"getManufacturerCode",int.class));
        list.add(new MethodWrapper(android.telephony.TelephonyManager.class,"getNaiBySubscriberId",int.class));
        list.add(new MethodWrapper(android.telephony.TelephonyManager.class,"getPhoneTypeFromNetworkType",int.class));
        list.add(new MethodWrapper(android.telephony.TelephonyManager.class,"getTelephonyProperty",int.class,String.class,String.class));
        list.add(new MethodWrapper(android.telephony.TelephonyManager.class,"getTelephonyProperty",String.class,String.class));
        list.add(new MethodWrapper(Build.class,"getSerial"));
        list.add(new MethodWrapper(android.net.wifi.WifiInfo.class,"getMacAddress"));
        list.add(new MethodWrapper(java.net.NetworkInterface.class,"getHardwareAddress"));
        MethodWrapper s1 = new MethodWrapper(android.provider.Settings.Secure.class,"getString", android.content.ContentResolver.class,String.class);
        s1.setMethodHandler(new SettingsResolverHandler());
        list.add(s1);
        MethodWrapper s2 = new MethodWrapper(Settings.System.class,"getString", android.content.ContentResolver.class,String.class);
        s2.setMethodHandler(new SettingsResolverHandler());
        list.add(s2);
        //-----定位
        list.add(new MethodWrapper(android.location.LocationManager.class,"getLastLocation"));
        list.add(new MethodWrapper(android.location.LocationManager.class,"getLastKnownLocation",String.class));
        //--屏幕
        list.add(new MethodWrapper(android.view.SurfaceControl.class,"screenshot", Rect.class,Integer.TYPE,Integer.TYPE,Boolean.TYPE,Integer.TYPE));
        list.add(new MethodWrapper(MediaRecorder.class,"start"));
        list.add(new MethodWrapper(MediaRecorder.class,"prepare"));
        list.add(new MethodWrapper(ImageReader.class,"acquireLatestImage"));
        //--网络
        list.add(new MethodWrapper(WifiManager.class,"setWifiEnabled",boolean.class));
        list.add(new MethodWrapper(WifiManager.class,"isWifiEnabled"));
        list.add(new MethodWrapper(WifiManager.class,"startScan"));
        list.add(new MethodWrapper(NetworkInterface.class,"getNetworkInterfaces"));
        //--系统特殊处理
        MethodWrapper contentResolverWrap = new MethodWrapper(ContentResolver.class,"query", Uri.class,String[].class,String.class,String[].class,String.class);
        contentResolverWrap.setMethodHandler(new ContentResolverHandler());
        list.add(contentResolverWrap);
        //--蓝牙
        list.add(new MethodWrapper(BluetoothAdapter.class,"enable"));
        list.add(new MethodWrapper(BluetoothAdapter.class,"startLeScan", UUID[].class, BluetoothAdapter.LeScanCallback.class));
        list.add(new MethodWrapper(BluetoothLeScanner.class,"startScan",List.class, ScanSettings.class, WorkSource.class, ScanCallback.class, PendingIntent.class,List.class));
        list.add(new MethodWrapper(BluetoothDevice.class,"createBond"));
        list.add(new MethodWrapper(BluetoothAdapter.class,"getProfileConnectionState",int.class));
        list.add(new MethodWrapper(BluetoothAdapter.class,"getBondedDevices"));
        //--录音
        list.add(new MethodWrapper(AudioRecord.class,"startRecording"));
        list.add(new MethodWrapper(MediaRecorder.class,"start"));
        list.add(new MethodWrapper(MediaRecorder.class,"prepare"));
        //--拍照
        list.add(new MethodWrapper(Camera.class,"startPreview"));
        list.add(new MethodWrapper(CameraManager.class,"openCameraDeviceUserAsync",String.class, CameraDevice.StateCallback.class, Executor.class,int.class));
        //--剪贴板
        list.add(new MethodWrapper(ClipboardManager.class,"getPrimaryClip"));
        list.add(new MethodWrapper(ClipboardManager.class,"getText"));
        list.add(MethodWrapper.newPrint(ClipboardManager.class,"setText",CharSequence.class));
        list.add(MethodWrapper.newPrint(ClipboardManager.class,"setPrimaryClip", ClipData.class));
        list.add(new MethodWrapper(ClipData.class,"getItemAt",int.class));


        //-----------

        return list;
    }
}
