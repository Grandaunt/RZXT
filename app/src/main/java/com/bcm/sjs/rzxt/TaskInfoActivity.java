package com.bcm.sjs.rzxt;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bcm.sjs.rzxt.Adapter.TaskInfoDownReAdapter;
import com.bcm.sjs.rzxt.Adapter.TaskInfoGetReAdapter;
import com.bcm.sjs.rzxt.DB.MEDIA;
import com.bcm.sjs.rzxt.DB.MediaPro;
import com.bcm.sjs.rzxt.DB.ServerBean;
import com.bcm.sjs.rzxt.DB.TASK;
import com.bcm.sjs.rzxt.DB.TaskPro;
import com.bcm.sjs.rzxt.Utils.FileUtils;
import com.bcm.sjs.rzxt.view.ActionSheetDialog;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.PhotoPreviewActivity;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class TaskInfoActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();
    private TextView tv_task_info_toolbar,tv_com_info_name,tv_com_info_addr,tv_com_info_tel,tv_com_info_con_tel,tv_com_con_name;
    private EditText com_info_memo;
    private Button saveBtn;
    private RecyclerView Down_RecyclerView,Get_RecyclerView1,Get_RecyclerView2,Get_RecyclerView3;
    private LinearLayoutManager mLayoutManager;
    private TaskInfoDownReAdapter mDownAdapter;
    private ImageView im_com_info_visibility,im_down_visibility,im_get_visibility1,im_get_visibility2,im_get_visibility3,im_memo_visibility;
    private boolean isVisible = true;
    private LinearLayout ly_com_info,ly_download,ly_memo,ly_get1,ly_get2,ly_get3;
    private String URL="";
    private SharedPreferences sharedPrefs;
    private ProgressDialog progressDialog;
    private String AUTH_TOKEN;
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;
    private String mPhotoname;
    private MEDIA mMedialist;
    private String task_no;
    private  MediaPro mediapro;
    private String userAcc;
    private ArrayList<HashMap<String, String>> task_info_list;

    private TaskInfoGetReAdapter mGetAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);
        x.view().inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.task_info_toolbar);
        toolbar.setTitle("");//设置主标题
        toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_left);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new Toolbar.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(TaskInfoActivity.this, MainActivity.class);
                startActivity(intent1);

//                finish();
            }
        });

         sharedPrefs =getSharedPreferences("RZShare", Context.MODE_PRIVATE);
        userAcc = sharedPrefs.getString("USER_ACCOUNT", "null");
        im_com_info_visibility=(ImageView)findViewById(R.id.com_info_visibility);
        im_down_visibility=(ImageView)findViewById(R.id.down_info_visibility);
        im_get_visibility1=(ImageView)findViewById(R.id.get_info_visibility1);
        im_get_visibility2=(ImageView)findViewById(R.id.get_info_visibility2);
        im_get_visibility3=(ImageView)findViewById(R.id.get_info_visibility3);
        im_memo_visibility=(ImageView)findViewById(R.id.memo_info_visibility);
        ly_com_info=(LinearLayout) findViewById(R.id.ly_com_info);
        ly_download=(LinearLayout) findViewById(R.id.ly_download);
        ly_memo=(LinearLayout) findViewById(R.id.ly_com_info_memo);
        ly_get1=(LinearLayout) findViewById(R.id.ly_get1);
        ly_get2=(LinearLayout) findViewById(R.id.ly_get2);
        ly_get3=(LinearLayout) findViewById(R.id.ly_get3);
        im_com_info_visibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    isVisible = false;
                    ly_com_info.setVisibility(View.VISIBLE);//这一句显示布局LinearLayout区域
                } else {
                    ly_com_info.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
                    isVisible = true;
                }
            }
        });
                im_down_visibility.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isVisible) {
                            isVisible = false;
                            ly_download.setVisibility(View.VISIBLE);//这一句显示布局LinearLayout区域
                        } else {
                            ly_download.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
                            isVisible = true;
                        }
                    }
                });
        im_get_visibility1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    isVisible = false;
                    ly_get1.setVisibility(View.VISIBLE);//这一句显示布局LinearLayout区域
                } else {
                    ly_get1.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
                    isVisible = true;
                }
            }
        });
        im_get_visibility2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    isVisible = false;
                    ly_get2.setVisibility(View.VISIBLE);//这一句显示布局LinearLayout区域
                } else {
                    ly_get2.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
                    isVisible = true;
                }
            }
        });
        im_get_visibility3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    isVisible = false;
                    ly_get3.setVisibility(View.VISIBLE);//这一句显示布局LinearLayout区域
                } else {
                    ly_get3.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
                    isVisible = true;
                }
            }
        });
     im_memo_visibility.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isVisible) {
                            isVisible = false;
                            ly_memo.setVisibility(View.VISIBLE);//这一句显示布局LinearLayout区域
                        } else {
                            ly_memo.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
                            isVisible = true;
                        }
                    }
                });
        Intent i = getIntent();
        TaskPro taskPro = new TaskPro(TaskInfoActivity.this);
        task_no=i.getStringExtra("task_no");
        task_info_list = taskPro.showTaskInfo(task_no);

//        tv_task_info_toolbar =(TextView)findViewById(R.id.task_info_toolbar_tv);
//        tv_task_info_toolbar.setText(task_info_list.get(0).get("task_com_name"));
        tv_com_info_name =(TextView)findViewById(R.id.com_info_name);
        tv_com_info_addr =(TextView)findViewById(R.id.com_info_addr);
        tv_com_info_tel =(TextView)findViewById(R.id.com_info_com_tel);
        tv_com_info_con_tel =(TextView)findViewById(R.id.com_info_con_tel);
        tv_com_con_name =(TextView)findViewById(R.id.com_info_con_name);
        com_info_memo =(EditText)findViewById(R.id.info_memo);
        Log.i(TAG,task_info_list.size()+"");
        Log.i(TAG,task_info_list.get(0)+"");
        Log.i(TAG,task_info_list.get(0).get("task_com_addr")+"");
        tv_com_info_name.setText(task_info_list.get(0).get("task_com_name"));
        tv_com_info_addr.setText(task_info_list.get(0).get("task_com_addr"));
        tv_com_info_tel.setText(task_info_list.get(0).get("task_com_tel"));
        tv_com_con_name .setText(task_info_list.get(0).get("task_com_con_name"));
        sharedPrefs = getSharedPreferences("RZShare", Context.MODE_PRIVATE);
        com_info_memo.setText(task_info_list.get(0).get("task_note"));

        //获取数据库中下载的信息
         mediapro = new MediaPro(TaskInfoActivity.this);
        mMedialist= taskPro.getMedialistDown(task_no);
        DownReView(mMedialist);
        //开始设置RecyclerView
        Get_RecyclerView1=(RecyclerView)findViewById(R.id.recyclerview_info_get1);
        Get_RecyclerView2=(RecyclerView)findViewById(R.id.recyclerview_info_get2);
        Get_RecyclerView3=(RecyclerView)findViewById(R.id.recyclerview_info_get3);
        GetReView(Get_RecyclerView1,mMedialist);
        GetReView(Get_RecyclerView2,mMedialist);
        GetReView(Get_RecyclerView3,mMedialist);

        saveBtn = (Button)findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadHttp(URL+"/MVNFHM/appInterface/appUploadFile");

            }
        });

    }



    private void DownReView(MEDIA  medialist) {

        //开始设置RecyclerView
        Down_RecyclerView=(RecyclerView)findViewById(R.id.recyclerview_info_down);
        //设置固定大小
        Down_RecyclerView.setHasFixedSize(true);
        //创建线性布局
        mLayoutManager = new LinearLayoutManager(TaskInfoActivity.this);
//        mLayoutManager = new MyCustomLayoutManager(getActivity());
//        Goos_RecyclerView.setLayoutManager(mLayoutManager);
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        //给RecyclerView设置布局管理器
//        recyclerView_one.setLayoutManager(mLayoutManager);
//        Down_RecyclerView.setLayoutManager(new GridLayoutManager(TaskInfoActivity.this,4));
        Down_RecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.HORIZONTAL));

        //创建适配器，并且设置
        mDownAdapter = new TaskInfoDownReAdapter(TaskInfoActivity.this,medialist,task_no);
        Down_RecyclerView.setAdapter(mDownAdapter);
        mDownAdapter.setOnItemClickLitener(new TaskInfoDownReAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, final int position,final String num)
            {
                new ActionSheetDialog(TaskInfoActivity.this).builder()
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("下载", ActionSheetDialog.SheetItemColor.DEFAULT,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Log.i(TAG,"ActionSheetDialog文件下载");
                                        String path= Environment.getExternalStorageDirectory().getAbsolutePath()
                                                + File.separator +"RZXT/"+userAcc+task_no+"/Down/"+num;
                                        if(!FileUtils.isFileExist(path)){
                                            try {
                                                File file= FileUtils.createSDDir(path);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                       downloadFile(URL+"/MVNFHM/appInterface/appDownFile"+position,path);
                                    }
                                })
                        .addSheetItem("查看", ActionSheetDialog.SheetItemColor.DEFAULT,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                                                + File.separator +"RZXT/"+userAcc+task_no+"/Down/num";
                                        if(!FileUtils.isFileExist(path)){
                                            Toast.makeText(TaskInfoActivity.this,"本地无该文件",Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            /***
                                             * 一、music：

                                             String file_type = "audio/*";


                                             二、movice：

                                             String file_type = "video/*";


                                             三、pdf：

                                             String file_type = "application/*";


                                             四、picture：

                                             String file_type = "image/*";
                                             */
                                            Uri uri = Uri.parse(path);//调用系统自带的播放器
//                    Uri uri = Uri.parse("sdcard/VideoRecorderTest/"+FileToStr(videoList)[position]);//调用系统自带的播放器
                                            Intent intent = new Intent(Intent.ACTION_VIEW);
                                            intent.setDataAndType(uri, "*/*");
                                            startActivity(intent);
                                        }
                                        Log.i(TAG,"ActionSheetDialog查看");
                                    }
                                }).show();
            }

            @Override
            public void onItemLongClick(View view, int position,String num)
            {
//                Intent intent = new Intent(getActivity(),PayActivity.class);
//                intent.putExtra("goodsNum", "num");
//                startActivity(intent);
//                Toast.makeText(getActivity(), position + " long click",
//                        Toast.LENGTH_SHORT).show();
//                mAdapter.removeData(position);
            }
        });


    }

    private void GetReView(RecyclerView Get_RecyclerView,MEDIA  medialist) {

        //开始设置RecyclerView
        Get_RecyclerView=(RecyclerView)findViewById(R.id.recyclerview_info_get1);
        //设置固定大小
        Get_RecyclerView.setHasFixedSize(true);
        //创建线性布局
        mLayoutManager = new LinearLayoutManager(TaskInfoActivity.this);
//        mLayoutManager = new MyCustomLayoutManager(getActivity());
//        Goos_RecyclerView.setLayoutManager(mLayoutManager);
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
//        recyclerView_one.setLayoutManager(mLayoutManager);
        Get_RecyclerView.setLayoutManager(new GridLayoutManager(TaskInfoActivity.this,4));
//        Get_RecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,
//                StaggeredGridLayoutManager.HORIZONTAL));

        //创建适配器，并且设置
        mGetAdapter = new TaskInfoGetReAdapter(TaskInfoActivity.this,medialist,task_no);
        Get_RecyclerView.setAdapter(mGetAdapter);
        mGetAdapter.setOnItemClickLitener(new TaskInfoGetReAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, final int position, String num)
            {
                mPhotoname=num + ".JPG";
                new ActionSheetDialog(TaskInfoActivity.this).builder()
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.DEFAULT,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Log.i(TAG,"ActionSheetDialog拍照");
                                        GalleryFinal.openCamera(REQUEST_CODE_CAMERA, mOnHanlderResultCallback);
                                    }
                                })
                        .addSheetItem("相册", ActionSheetDialog.SheetItemColor.DEFAULT,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, mOnHanlderResultCallback);


                                    }
                                }).show();
            }

            @Override
            public void onItemLongClick(View view, int position,String num)
            {

                PhotoInfo ptinfo = new PhotoInfo();
                ptinfo.setPhotoPath( Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator +"RZXT/"+userAcc+task_no+"/Get/" + mPhotoname);
                //mPhotoname=0@qwe
                List<PhotoInfo> PhotoList= new ArrayList<>();
                PhotoList.add(0, ptinfo);

                Intent intent = new Intent(TaskInfoActivity.this, PhotoPreviewActivity.class);
                intent.putExtra("photo_list", (Serializable) PhotoList);
                startActivity(intent);
            }
        });

    }
    private void downloadFile(String url, String path) {
         progressDialog = new ProgressDialog(this);
        RequestParams requestParams = new RequestParams(url);
        requestParams.addParameter("AUTH_TOKEN", AUTH_TOKEN);
        requestParams.setSaveFilePath(path);
        Log.i(TAG,"requestParams="+requestParams);
        x.http().get(requestParams, new Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {
            }

            @Override
            public void onStarted() {
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMessage("亲，努力下载中。。。");
                progressDialog.show();
                progressDialog.setMax((int) total);
                progressDialog.setProgress((int) current);
            }

            @Override
            public void onSuccess(File result) {
                Toast.makeText(TaskInfoActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                Toast.makeText(TaskInfoActivity.this, "下载失败，请检查网络和SD卡", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void uploadHttp(String url){
        String[] GetInfo =mMedialist.mt_u_desc.split("%");
        for (int imagei = 0; imagei < GetInfo.length; imagei++) {
            String[] img_desc = GetInfo[imagei].split("@");
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +"RZXT/"+userAcc+task_no+"/Get/" + mPhotoname;
            Log.i(TAG, "path=" + path);
            File f = new File(path);
            if (f.exists()) {
                RequestParams params = new RequestParams(url);
                //支持断点续传
                params.setAutoResume(true);
                params.setMultipart(true);
                /**
                 * 多媒体文件上传
                 *
                 * @param auth_token 手机令牌
                 * @param file 文件对象
                 * @param media_type 多媒体文件类型，0-照片，1-录像
                 * @param userAccount 信贷员ID
                 * @param task_no 任务号，客户基本信息的任务号为info
                 * @param img_desc 文件描述，标明文件的位置及说明
                 * @return
                 * setAsJsonContent（boolean is）
                 * 如果有中文，要进行URLEncoder.encode方法
                 *params.addBodyParameter("deviceIntoTime",URLEncoder.encode(deviceIntoTime, "utf-8"));
                 */
                params.addBodyParameter("AUTH_TOKEN", AUTH_TOKEN);
                params.addBodyParameter("FILE", new File(path));
//                params.addBodyParameter("media_type", "0");
//                params.addBodyParameter("userAccount", userAccount);
//                params.addBodyParameter("task_no", EventId);
//                params.addBodyParameter("img_desc", URLEncoder.encode(photoArray[imagei], "utf-8"));

                Log.i(TAG, "params:" + params);
                x.http().post(params, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        mMedialist.mt_status=2+"";
                        mediapro.UpdateStatus(mMedialist);

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
                });

            } else {
                Log.i(TAG, path + "照片上传失败，不存在");
            }
        }

    }
    //拍照照片回调函数
    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {

        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {

            String path= Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator +"RZXT/"+userAcc+task_no+"/Get/";
            if(!FileUtils.isFileExist(path)){
                try {
                    File file= FileUtils.createSDDir(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                //获取图片
                Bitmap Bitmap = BitmapFactory.decodeFile(resultList.get(0).getPhotoPath());

                Log.i(TAG,resultList.get(0).getPhotoPath());
                //新路径
//                String photoBimpstr = Environment.getExternalStorageDirectory()+"/XHLS/" + UserID + "/" + event_id + "/PHOTO/";



                //把新图片放到指定路径
                FileUtils.saveBitmap(path,mPhotoname,Bitmap);
                //删除旧图片
//                String fileName = resultList.get(0).getPhotoPath();
//                File file = new File(fileName);
//                FileUtils.delFile(file);

//                String value= getmediaList.get(0).get("mt_image_desc");
//                String[] array = value.split("=");
//                for(int i=0;i< array.length;i++ ){
//                    File f = new File(path, i + ".JPG");
//                    //如果同名照片存在则添加到list
//                    if (f.exists()) {
//                        PhotoInfo ptinfo = new PhotoInfo();
//                        ptinfo.setPhotoPath(path+ i + ".JPG");
//                        resultList.add(i,ptinfo);
//                    }
//                    else{
//                        resultList.add(i,null);
//                    }
//                }


//                mPhotoList.addAll(resultList);
//                mChoosePhotoListAdapter.notifyDataSetChanged();
//                initView();
//                Toast.makeText(EventEventActivity.this, "resultList" + resultList, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(TaskInfoActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onStart() {
        sharedPrefs = getSharedPreferences("RZShare", Context.MODE_PRIVATE);
        URL= "http://"+sharedPrefs.getString("CONNECT_IP", "null")+":"+sharedPrefs.getString("CONNECT_PORT", "null");
        AUTH_TOKEN = sharedPrefs.getString("AUTH_TOKEN", "0");
        super.onStart();
    }

    @Override
    protected void onResume() {
        URL = "http://"+sharedPrefs.getString("CONNECT_IP", "null")+":"+sharedPrefs.getString("CONNECT_PORT", "null");
        DownReView(mMedialist);
        GetReView(Get_RecyclerView1,mMedialist);
        GetReView(Get_RecyclerView2,mMedialist);
        GetReView(Get_RecyclerView3,mMedialist);
        super.onResume();
    }

    @Override
    protected void onStop() {
        TaskPro repo = new TaskPro(TaskInfoActivity.this);
        TASK task = new TASK();
        task.task_no=task_no;
        task.task_type =  task_info_list.get(0).get("task_type");
        task.task_status =  task_info_list.get(0).get("task_status");
        task.task_is_early_file = task_info_list.get(0).get("task_is_early_file");


        //检查机构代码
        task.task_check_org_no = task_info_list.get(0).get("task_check_org_no");
        //检查机构名称
        task.task_check_org_name =  task_info_list.get(0).get("task_check_org_name");
        //检查类型(首次检查、定期检查、不定期检查)
        task.task_check_type =  task_info_list.get(0).get("task_check_type");
        //检查选项(按合同检查)
        task.task_check_option = task_info_list.get(0).get("ln_check_option");
        //任务检查人账号
        task.task_iner_acc =  task_info_list.get(0).get("task_iner_acc");


        //任务检查人姓名
        task.task_iner_name =  task_info_list.get(0).get("task_iner_name");
        //任务开始日期
        task.task_start_date = task_info_list.get(0).get("task_start_date");
        //任务截止日期
        task.task_end_date =  task_info_list.get(0).get("task_end_date");
        //任务完成日期
        task.task_finish_date =  task_info_list.get(0).get("task_finish_date");
        //审核人账号
        task.task_audit_acc =  task_info_list.get(0).get("task_audit_acc");


        //审核人姓名
        task.task_audit_name =  task_info_list.get(0).get("task_audit_name");
        //申请公司编号
        task.task_com_no =  task_info_list.get(0).get("task_com_no");
        //申请公司名称
        task.task_com_name =  task_info_list.get(0).get("task_com_name");
        //申请合同编号
        task.task_con_no =  task_info_list.get(0).get("task_con_no");
        //申请项目编号
        task.task_item_no =  task_info_list.get(0).get("task_item_no");


        //申请产品编号
        task.task_prdt_no =  task_info_list.get(0).get("task_prdt_no");
        //申请产品名称
        task.task_prdt_name =  task_info_list.get(0).get("task_prdt_name");
        //申请生产类型
        task.task_prdt_type =  task_info_list.get(0).get("task_prdt_type");
        //申请认证范围
        task.task_rz_scope =  task_info_list.get(0).get("task_rz_scope");
        //申请认证类型
        task.task_rz_type =  task_info_list.get(0).get("task_rz_type");

        //申请公司地址
        task.task_com_addr =  task_info_list.get(0).get("task_com_addr");
        //申请公司电话
        task.task_com_tel =  task_info_list.get(0).get("task_com_tel");
        // 申请公司邮编
        task.task_com_post_code =  task_info_list.get(0).get("task_com_post_code");
        //申请公司邮箱
        task.task_com_email =  task_info_list.get(0).get("task_com_email");
        //申请公司传真
        task.task_com_fax =  task_info_list.get(0).get("task_com_fax");


        //申请公司网络url
        task.task_web_url =  task_info_list.get(0).get("task_web_url");
        //公司联系人姓名
        task.task_com_con_name =  task_info_list.get(0).get("task_com_con_name");
        //公司联系人电话
        task.task_com_con_tel =  task_info_list.get(0).get("task_com_con_tel");
        //公司联系人职位
        task.task_com_con_ide =  task_info_list.get(0).get("task_com_con_ide");
        //申请产品介绍
        task.task_item_info =  task_info_list.get(0).get("task_item_info");

        //信息采集模板ID
        task.task_mt_id = task_info_list.get(0).get("task_mt_id");
        //任务备注
        task.task_note =  com_info_memo.getText().toString() ;
        repo.update(task);

//        sharedPrefs = getSharedPreferences("RZShare", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPrefs.edit();
//        editor.putString("EDIT_MEMO", com_info_memo.getText().toString());
//        editor.commit();
        Log.i(TAG,"onStop");
        super.onStop();
    }

    @Override
    protected void onPostResume() {
        Log.i(TAG,"onPostResume");
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"onDestroy");
        super.onDestroy();
    }
}
