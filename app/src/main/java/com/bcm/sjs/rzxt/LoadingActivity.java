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
    private static final int GO_MAIN = 15001;
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
                case GO_MAIN:
                    goMain();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        SharedPreferences sharedPrefs = getSharedPreferences("RZShare", Context.MODE_PRIVATE);
        String error=sharedPrefs.getString("AUTH_TOKEN", "error");
        Log.i(TAG,"sharedPrefs.getString(AUTH_TOKEN, error)="+sharedPrefs.getString("AUTH_TOKEN", "error"));
       if( sharedPrefs.getString("AUTH_TOKEN", "error").equals("error")||sharedPrefs.getString("AUTH_TOKEN", "error").equals(""))
       {
           handler.sendEmptyMessageDelayed(GO_HOME,TIME);
       }
       else {
           handler.sendEmptyMessageDelayed(GO_MAIN, TIME);
       }
        Log.i("LoadingActivity","LoadingActivity欢迎。。。");

    }
    private void goHome(){
        Log.i("LoadingActivity","LoadingActivity欢迎。。。Intent");
        Intent intent = new Intent(LoadingActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
    private void goMain(){
        Log.i("LoadingActivity","LoadingActivity欢迎。。。Intent");
        Intent intent = new Intent(LoadingActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
