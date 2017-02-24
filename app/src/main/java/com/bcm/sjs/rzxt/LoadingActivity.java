package com.bcm.sjs.rzxt;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class LoadingActivity extends AppCompatActivity {
    private static final int TIME = 3000;
    private static final int GO_HOME = 15000;
    private static final int GO_GUIDE = 1001;
//    private boolean isFirstIn = false;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GO_HOME:
                    goHome();
                    finish();
                    break;
//                case GO_GUIDE:
//                    goGuide();
//                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Log.i("LoadingActivity","LoadingActivity欢迎。。。");
        handler.sendEmptyMessageDelayed(GO_HOME,TIME);
    }
    private void goHome(){
        Log.i("LoadingActivity","LoadingActivity欢迎。。。Intent");
        Intent intent = new Intent(LoadingActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
