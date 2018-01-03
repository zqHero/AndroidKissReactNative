package com.hero.zhaoq.androidkissreactnative.native_communication;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * author: zhaoqiang
 * date:2018/01/02 / 10:04
 * zhaoqiang:zhaoq_hero@163.com
 */
//通信 接口：
public class CommunicationInterface extends ReactContextBaseJavaModule{

    private  ReactApplicationContext reactContext;

    public CommunicationInterface(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "CommunicationInterface";
    }

    /**
     * Rn 需要调用的方法：
     * @param message
     */
    @ReactMethod
    public void HandleMessage(String message){
        Toast.makeText(reactContext,message,Toast.LENGTH_LONG).show();
    }

    /**
     * 发送消息到  RN 界面
     */
    public void sendMessage(String params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("mEventName", params);
    }
}
