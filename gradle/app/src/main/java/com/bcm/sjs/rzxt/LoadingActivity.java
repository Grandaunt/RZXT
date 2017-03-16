package com.bcm.sjs.rzxt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class LoadingActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();
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
//        SharedPreferences sharedPrefs = getSharedPreferences("RZShare", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPrefs.edit();
//        editor.putString("CONNENT_NAME",null);
//        editor.putString("CONNECT_IP",null);
//        editor.putString("CONNECT_PORT",null);
//        editor.putString("AUTH_TOKEN", null);
//        editor.putString("USER_ACCOUNT", null);
//        editor.putString("USER_NAME", null);
//        editor.putString("USER_IDE", null);
//        editor.putString("USER_TEL", null);
//        editor.putString("USER_DEPT_NAME", null);
//        editor.putString("USER_DEPT_ORG_CODE", null);
//        editor.putString("USER_ICON",null);
//        editor.commit();
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
