package com.hero.zhaoq.androidkissreactnative;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hero.zhaoq.androidkissreactnative.activitys.Communicate1Activity;

/**
 * Android  和 ReactNative  通信   Activity直接引用 moudle：
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Communicate1Activity.class));
            }
        });
    }


}
