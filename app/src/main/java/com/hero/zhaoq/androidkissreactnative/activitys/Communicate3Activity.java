package com.hero.zhaoq.androidkissreactnative.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.hero.zhaoq.androidkissreactnative.BuildConfig;
import com.hero.zhaoq.androidkissreactnative.native_communication.CommunicationInterface;
import com.hero.zhaoq.androidkissreactnative.native_communication.MReactPacakge;
import com.hero.zhaoq.androidkissreactnative.R;

public class Communicate3Activity extends BaseActivity implements DefaultHardwareBackBtnHandler {

    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;
    private MReactPacakge reactPackage;
    private static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate3);

        mReactRootView = new ReactRootView(this);
        reactPackage = new MReactPacakge();
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
//                .setJSMainModuleName("index")
                .setJSMainModulePath("index")
                .addPackage(new MainReactPackage())
                .addPackage(reactPackage)   //添加  本地 moudel
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();

        // 注意这里的MyReactNativeApp必须对应“index.android.js”中的
        // “AppRegistry.registerComponent()”的第一个参数
        mReactRootView.startReactApplication(mReactInstanceManager, "Communication3", null);

        //将  ReactView 模块添加进 布局
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.root_view);
        linearLayout.addView(mReactRootView);

        //添加  本地按钮的 点击事件：
        findViewById(R.id.native_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reactPackage.getInterf(0)!=null){
                    ((CommunicationInterface)reactPackage.getInterf(0)).sendMessage("这是本地发送的消息" + i++);
                }
            }
        });
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }
}
