package com.bcm.sjs.rzxt;


import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.IOException;

/**
 * Created by janiszhang on 2016/6/10.
 */

public class TabFragment2 extends android.support.v4.app.Fragment implements View.OnClickListener{
    private String TAG = this.getClass().getSimpleName();
    private String URL="http://172.16.10.242:8080/MVNFHM/appInterface/isUpdate";
    private String path="";
    private LinearLayout Updately,Memoly,Clearly,AboutLy,Settingly,OutLy;
    private ImageButton UserIconIm;
    private TextView UserAccountTv,UserNameTv;
    private File apkFile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, null);
        //注入view和事件
        x.view().inject(getActivity());

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.tab2_toolbar);
        toolbar.setTitle("");//设置主标题
        toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
        Updately = (LinearLayout)view.findViewById(R.id.ly_update);
        Memoly = (LinearLayout)view.findViewById(R.id.ly_memo);
        Clearly = (LinearLayout)view.findViewById(R.id.ly_clear);
        AboutLy = (LinearLayout)view.findViewById(R.id.ly_about);
        Settingly = (LinearLayout)view.findViewById(R.id.ly_setting);
        OutLy = (LinearLayout)view.findViewById(R.id.ly_out);
        UserIconIm = (ImageButton) view.findViewById(R.id.im_user_icon);
        UserAccountTv = (TextView) view.findViewById(R.id.tv_user_account);
        UserNameTv = (TextView) view.findViewById(R.id.tv_user_name);

        Updately.setOnClickListener(this);
        Memoly.setOnClickListener(this);
        Clearly.setOnClickListener(this);
        AboutLy.setOnClickListener(this);
        Settingly.setOnClickListener(this);
        OutLy.setOnClickListener(this);
        UserIconIm.setOnClickListener(this);
        UserAccountTv.setOnClickListener(this);
        UserNameTv.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //版本更新
            case R.id.ly_update:

//                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                    //2.获取SD卡的路径,使用File.separator维护"/"或者"\"
                    path = Environment.getExternalStorageDirectory().getAbsolutePath()
                            + File.separator +"RZXT/"+ "rzxt.apk";
                    Log.i(TAG, "PATH= "+path);
                    if(isFileExist(path)){
                        downloadAPK(""+0,path);
                    }
                    else{
                        try {
                            apkFile= createSDDir(path);
                            downloadAPK(""+0,path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

//                }
                break;
            //备忘录
            case R.id.ly_memo:

                break;
            //清除缓存
            case R.id.ly_clear:

                break;
            //设置ip
            case R.id.ly_setting:

                break;
            //关于我们
            case R.id.ly_about:

                break;
            //退出
            case R.id.ly_out:

//                ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
//                manager.restartPackage(getActivity().getPackageName());
                break;
            default:
                break;
        }
    }
    private void downloadAPK(String appVersion,String Feilpath) {
        //1.检查SD卡是否挂载上

        //3.发送请求，获取指定APK，放置到指定位置
        RequestParams params = new RequestParams(URL);
        params.addParameter("userAccount", "11000");
        params.addParameter("appVersion", "0");
//        params.setSaveFilePath(path);
        //4.发送请求，传送参数(要下载的apk文件的url，下载成功后存储位置，回调函数)

        Callback.Cancelable post =
                x.http().post(params, new Callback.ProgressCallback<String>() {

                    @Override

                    public void onSuccess(String result) {
                        installAPK(result);
                    }

                    @Override

                    public void onError(Throwable ex, boolean isOnCallback) {
                        //下载失败
                        Log.d(TAG, "下载失败");
                    }

                    @Override

                    public void onCancelled(CancelledException cex) {

                    }

                    @Override

                    public void onFinished() {
                    }

                    @Override

                    public void onWaiting() {

                    }

                    @Override

                    public void onStarted() {

                    }

                    @Override
                    public void onLoading(long total, long current, boolean isDownloading) {
                        //文件下载时回调的方法

                        Log.i("xxxxxxxxxxxxx", "current<<" + current + "total<<" + total);
                    }
                });
    }




    /**
     * 开启安装APK页面的逻辑
     *
     */
    private void installAPK(String downAPKurl) {
        RequestParams params1 = new RequestParams(downAPKurl);
        params1.setAutoRename(true);//断点下载
        params1.setSaveFilePath(path);
        Callback.Cancelable post = x.http().post(params1, new Callback.ProgressCallback<File>() {
                    @Override
                    public void onSuccess(File result) {
                        Log.d(TAG, "aaaaaaaaaaaaaa下载成功result"+result);
                        //系统应用界面，安装apk入口，看源码
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.addCategory("android.intent.category.DEFAULT");
//        intent.setData(Uri.fromFile(file));
//        intent.setType("application/vnd.android.package-archive");

                        //切记当要同时配Data和Type时一定要用这个方法，否则会出错
                        intent.setDataAndType(Uri.fromFile(new File(path)),"application/vnd.android.package-archive");
                        startActivityForResult(intent,0);

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }

                    @Override
                    public void onWaiting() {

                    }

                    @Override
                    public void onStarted() {

                    }

                    @Override
                    public void onLoading(long total, long current, boolean isDownloading) {

                    }
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.fromFile(new File( Environment.getExternalStorageDirectory().getAbsolutePath()
//                        + File.separator + "rzxt.apk")),
//                "application/vnd.android.package-archive");
//        startActivity(intent);
    });
    }
//创建SD文件夹

    public  File createSDDir(String dirName) throws IOException {
        File dir = new File(dirName);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            dir.mkdirs();

//			System.out.println("createSDDir:" + dir.getAbsolutePath());
//			System.out.println("createSDDir:" + dir.mkdir());
        }
        Log.i(TAG,"dir="+dir);
        return dir;
    }

    //判断文件是否存在
    public  boolean isFileExist(String fileName) {
        File file = new File(fileName);
        file.isFile();
        Log.i(TAG,"file.exists()="+file.exists());
        return file.exists();
    }

}
