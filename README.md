

### <font color="#f33">简介：</font>

<font color="#191">Activity 和 ReactNative  混合开发时，有时我们需要直接引入已经写好的reactNative js组件。此时就需要我们对Activity如何引用ReactNative进行了解。 本篇介绍了Activity如何直接应用一个写好的ReactNative组件。</font>



####<font color="#f33">具体步骤:</font>


#####1，AndroidStudio创建  项目：

1.1 命令行进入项目根目录执行命令：

```

> npm init

> npm install react react-native --save

```

生成如下：node_noudle 和  package.json

![这里写图片描述](http://img.blog.csdn.net/20171227181338440?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMzIzMzA5Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


1.2 package.json:

```
{
  "name": "androidkissrn",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    //添加 命令：
    "start": "node node_modules/react-native/local-cli/cli.js start"
  },
  "author": "zhaoq",
  "license": "ISC",

  //添加   依赖   如果  没生成  依赖上面命令  请自行添加  然后执行  npm install 或者  yarn install：
  "dependencies": {
    "react": "^16.2.0",
    "react-native": "^0.51.0"
  }
}

```

#####2，gradle 配置：

```
//项目  根目录gradle
allprojects {
    repositories {
        jcenter()
        maven { url 'https://maven.google.com' }

        //2，配置   maven 库  指定  node_modules  模块
        maven {
            // All of React Native (JS, Android binaries) is installed from npm
            url "$rootDir/node_modules/react-native/android"
        }
    }
}
```


```
//App   moudle中 gradle  配置
dependencies {
    //3，添加   gradle 依赖：
    compile "com.facebook.react:react-native:+" // From node_modules.
}
```


#####3，代码：

 权限检查：
```
 //1,  检查   权限：   配置权限以便开发中的红屏错误能正确显示   开启悬浮窗 权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 154);
            }
        }
```

index.js  文件：
```
import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

class HelloWorld extends React.Component {
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.hello}>我是Rn 界面</Text>
      </View>
    )
  }
}
var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
});

AppRegistry.registerComponent('MyReactNativeApp', () => HelloWorld);
```

执行  rn文件和路径
```
class Communicate1Activity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mReactRootView = new ReactRootView(this);
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index")
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();

        // 注意这里的MyReactNativeApp必须对应“index.android.js”中的
        // “AppRegistry.registerComponent()”的第一个参数
        mReactRootView.startReactApplication(mReactInstanceManager, "MyReactNativeApp", null);

        setContentView(mReactRootView);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }
}

```

##### 4，生成bundle 文件：

执行命令：  生成bundle文件 到assets 目录下  如果没有该目录，请自建：

```
F:\androidStudioworkspace\AndroidKissReactNative> react-native bundle --platform android --dev false --entry-file index.js --bundle-output app/src/main/assets/index.android.bundle --as
sets-dest app/src/main/res/
Scanning folders for symlinks in F:\androidStudioworkspace\AndroidKissReactNative\node_modules (30ms)
Scanning folders for symlinks in F:\androidStudioworkspace\AndroidKissReactNative\node_modules (33ms)
Loading dependency graph, done.
bundle: start
bundle: finish
bundle: Writing bundle output to: app/src/main/assets/index.android.bundle
bundle: Done writing bundle output

```


运行  App：   activity  直接引用Rn 组件完成下面就可以愉快的用Rn写我们的界面了。

![这里写图片描述](http://img.blog.csdn.net/20171228085249680?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMzIzMzA5Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)