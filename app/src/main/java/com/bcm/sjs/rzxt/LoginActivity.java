package com.bcm.sjs.rzxt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bcm.sjs.rzxt.DB.MEDIA;
import com.bcm.sjs.rzxt.DB.MediaPro;
import com.bcm.sjs.rzxt.DB.ServerBean;
import com.bcm.sjs.rzxt.DB.TASK;
import com.bcm.sjs.rzxt.DB.TaskPro;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    //http://172.16.10.242:8080/MVNFHM//appInterface/appLogin?userAccount=yang&passWord=1
    private String TAG = this.getClass().getSimpleName();
    private Context context = LoginActivity.this;
    private String ACC,PASSWORD,URL;
    private EditText et_Acc,et_Password;
    private ImageButton imBtn_IP_Edit;
    private Button btn_Login;
    private TextView spr_IP;
    private SharedPreferences sharedPrefs;
    private ServerBean serverBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(TAG,"LoginActivity登陆");
        sharedPrefs = getSharedPreferences("RZShare", Context.MODE_PRIVATE);
        x.view().inject(this);
        et_Acc = (EditText)findViewById(R.id.et_acc);
        et_Password = (EditText)findViewById(R.id.et_password);
        imBtn_IP_Edit= (ImageButton) findViewById(R.id.imbtn_ip_edit);
        btn_Login= (Button) findViewById(R.id.btn_login);
        spr_IP= (TextView) findViewById(R.id.spinner_ip);


        imBtn_IP_Edit.setOnClickListener(this);
        btn_Login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.imbtn_ip_edit:
                Intent intent = new Intent(LoginActivity.this,EditIPActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                ACC = et_Acc.getText().toString();
                PASSWORD = et_Password.getText().toString();
//                if(TextUtils.isEmpty(spr_IP.getText())){
                    URL = "http://"+sharedPrefs.getString("CONNECT_IP", "null")+":"+sharedPrefs.getString("CONNECT_PORT", "null");
                    LoginHttp(ACC,PASSWORD,URL);
                    test();
//                }
//               else{
//                    Toast.makeText(LoginActivity.this,"请设置连接",Toast.LENGTH_SHORT).show();
//                }
                break;
            default:
                break;
        }

    }



    private void LoginHttp(String ACC,String PASSWORD,String URL){

        RequestParams params = new RequestParams(URL+"/MVNFHM/appInterface/appLogin");
        params.addParameter("userAccount", ACC);
        //根据当前请求方式添加参数位置
        params.addParameter("passWord", PASSWORD);
        Log.i("LoginActivity_post", "params："+params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG, "onSuccess" + result);
                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<ServerBean>() {}.getType();
                serverBean = gson.fromJson(result, type);
//                serverBean = gson.fromJson(result, ServerBean.class);
                int err = serverBean.getError();
                String mag = serverBean.getMsg();
                if (err == 0) {
                    //解析成功
                    Log.i(TAG, "解析成功：" + mag);

                    ServerBean.User user = serverBean.getUser();
                    List<ServerBean.TaskBean> taskbeanList = serverBean.getTaskList();
                    ServerBean.Media jsonmedia = serverBean.getMedia();
                    if (taskbeanList.size() > 0) {
                        for (int i = 0; i < taskbeanList.size(); i++) {
                            ServerBean.TaskBean bean = taskbeanList.get(i);
                            TASK task = new TASK();
                            task.task_no = bean.getTASK_NO();
                            task.task_type = bean.getTASK_TYPE();
                            task.task_status = bean.getTASK_STATUS();
                            task.task_is_early_file = bean.getTASK_IS_EARLY_FILE();


                            //检查机构代码
                            task.task_check_org_no = bean.getTASK_CHECK_ORG_NO();
                            //检查机构名称
                            task.task_check_org_name = bean.getTASK_CHECK_ORG_NAME();
                            //检查类型(首次检查、定期检查、不定期检查)
                            task.task_check_type = bean.getTASK_CHECK_TYPE();
                            //检查选项(按合同检查)
                            task.ln_check_option = bean.getTASK_CHECK_OPTION();
                            //任务检查人账号
                            task.task_iner_acc = bean.getTASK_INER_ACC();


                            //任务检查人姓名
                            task.task_iner_name = bean.getTASK_INER_NAME();
                            //任务开始日期
                            task.task_start_date = bean.getTASK_START_DATE();
                            //任务截止日期
                            task.task_end_date = bean.getTASK_END_DATE();
                            //任务完成日期
                            task.task_finish_date = bean.getTASK_FINISH_DATE();
                            //审核人账号
                            task.task_audit_acc = bean.getTASK_AUDIT_ACC();


                            //审核人姓名
                            task.task_audit_name = bean.getTASK_AUDIT_NAME();
                            //申请公司编号
                            task.task_com_no = bean.getTASK_COM_NO();
                            //申请公司名称
                            task.task_com_name = bean.getTASK_COM_NAME();
                            //申请合同编号
                            task.task_con_no = bean.getTASK_CON_NO();
                            //申请项目编号
                            task.task_item_no = bean.getTASK_ITEN_NO();


                            //申请产品编号
                            task.task_prdt_no = bean.getTASK_PRDT_NO();
                            //申请产品名称
                            task.task_prdt_name = bean.getTASK_PRDT_NAME();
                            //申请生产类型
                            task.task_prdt_type = bean.getTASK_PRDT_TYPE();
                            //申请认证范围
                            task.task_rz_scope = bean.getTASK_RZ_SCOPE();
                            //申请认证类型
                            task.task_rz_type = bean.getTASK_RZ_TYPE();

                            //申请公司地址
                            task.task_com_addr = bean.getTASK_COM_ADDR();
                            //申请公司电话
                            task.task_com_tel = bean.getTASK_COM_TEL();
                            // 申请公司邮编
                            task.task_com_post_code = bean.getTASK_COM_POST_CODE();
                            //申请公司邮箱
                            task.task_com_email = bean.getTASK_COM_EMAIL();
                            //申请公司传真
                            task.task_com_fax = bean.getTASK_COM_FAX();


                            //申请公司网络url
                            task.task_web_url = bean.getTASK_COM_WEB_URL();
                            //公司联系人姓名
                            task.task_com_con_name = bean.getTASK_COM_CON_NAME();
                            //公司联系人电话
                            task.task_com_con_tel = bean.getTASK_COM_CON_TEL();
                            //公司联系人职位
                            task.task_com_con_ide = bean.getTASK_COM_CON_IDE();
                            //申请产品介绍
                            task.task_item_info = bean.getTASK_ITEM_INFO();

                            //信息采集模板ID
                            task.task_mt_id = bean.getTASK_MT_ID();
                            //任务备注
                            task.task_note = bean.getTASK_NOTE();

                            TaskPro repo = new TaskPro(context);
                            if (repo.getNo(bean.getTASK_NO())) {
                                repo.update(task);
                                Log.i(TAG, "task表更新:" + bean.getTASK_NO());
                            } else {
                                repo.insert(task);
                                Log.i(TAG, "task表插入:" + bean.getTASK_NO());
                            }
                        }

                        MEDIA media = new MEDIA();
                        //记录主键
                        media.mt_id = jsonmedia.getMT_ID();
                        //模板名称
                        media.mt_name = jsonmedia.getMT_NAME();
                        //模板项目信息
                        media.mt_item_info = jsonmedia.getMT_ITEM_INFO();
                        //模板上传照片数量
                        media.mt_u_im_num = jsonmedia.getMT_U_IM_NUM();
                        //模板上传照片描述
                        media.mt_u_im_desc = jsonmedia.getMT_U_IM_DESC();


                        //模板上传word数量
                        media.mt_u_w_num = jsonmedia.getMT_U_W_NUM();
                        //模板上传word描述
                        media.mt_u_w_desc = jsonmedia.getMT_U_W_DESC();
                        //模板上传pdf数量
                        media.mt_u_p_num = jsonmedia.getMT_U_P_NUM();
                        //模板上传pdf描述
                        media.mt_u_p_desc = jsonmedia.getMT_U_P_DESC();
                        //模板上传excel数量
                        media.mt_u_e_num = jsonmedia.getMT_U_E_NUM();


                        //模板上传excel描述
                        media.mt_u_e_desc = jsonmedia.getMT_U_E_DESC();
                        //模板下载照片数量
                        media.mt_d_im_num = jsonmedia.getMT_D_IM_NUM();
                        //模板上下载传照片描述
                        media.mt_d_im_desc = jsonmedia.getMT_D_IM_DESC();
                        //模板下载word数量
                        media.mt_d_w_num = jsonmedia.getMT_D_W_NUM();
                        //模板下载word描述
                        media.mt_d_w_desc = jsonmedia.getMT_D_W_DESC();


                        //模板下载pdf数量
                        media.mt_d_p_num = jsonmedia.getMT_D_P_NUM();
                        //模板下载pdf描述
                        media.mt_d_p_desc = jsonmedia.getMT_D_P_DESC();
                        //模板下载excel数量
                        media.mt_d_e_num = jsonmedia.getMT_D_E_NUM();
                        //模板下载excel描述
                        media.mt_d_e_desc = jsonmedia.getMT_D_E_DESC();
                        //模板备注
                        media.mt_is_note = jsonmedia.getMT_IS_NOTE();


                        //模板状态
                        media.mt_status = jsonmedia.getMT_STATUS();

                        MediaPro repo1 = new MediaPro(context);
                        if (repo1.getID(jsonmedia.getMT_ID())) {
                            repo1.update(media);
                            Log.i(TAG, "media表更新:" + jsonmedia.getMT_ID());
                        } else {
                            repo1.insert(media);
                            Log.i(TAG, "media表插入:" + jsonmedia.getMT_ID());
                        }


                        SharedPreferences.Editor editor = sharedPrefs.edit();
                        editor.putString("AUTH_TOKEN", user.getAUTH_TOKEN());
                        editor.putString("USER_ACCOUNT", user.getUSER_ACCOUNT());
                        editor.putString("USER_NAME", user.getUSER_NAME());
                        editor.putString("USER_IDE", user.getUSER_IDE());
                        editor.putString("USER_TEL", user.getUSER_TEL());
                        editor.putString("USER_DEPT_NAME", user.getUSER_DEPT_NAME());
                        editor.putString("USER_DEPT_ORG_CODE", user.getUSER_DEPT_ORG_CODE());
                        editor.commit();


                        //跳转Activity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("USER_ACCOUNT", user.getUSER_ACCOUNT());
                        startActivity(intent);
                        finish();

                    }
                } else {
                    Log.i(TAG, "解析失败" + mag);
                    et_Acc.setText("");
                    et_Password.setText("");
                    Toast.makeText(LoginActivity.this, "请重新登录", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("LogActivity","onError网络请求错误"+ex);
//                Toast.makeText(LoginActivity.this, "网络连接", Toast.LENGTH_LONG).show();
                et_Acc.setText("");
                et_Password.setText("");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.i("LogActivity","onCancelled网络请求取消");
            }

            @Override
            public void onFinished() {
                Log.i("LogActivity","onFinished网络请求完毕");
            }
        });
//            Toast.makeText(LoginActivity.this, "用户名或密码错误请重新输入", Toast.LENGTH_LONG).show();
//
                            }
        public  void test(){
            //跳转Activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);

            startActivity(intent);
        }

    @Override
    protected void onRestart() {
        super.onRestart();
        spr_IP.setText(sharedPrefs.getString("connect_name", "recome"));
    }

    @Override
    protected void onResume() { //在另一个Activity中
        super.onResume();
        Log.i(TAG,"onResume获取IP");
        Log.i(TAG,sharedPrefs.getString("CONNECT_NAME", "recome"));
        spr_IP.setText(sharedPrefs.getString("CONNECT_NAME", "recome"));
    }
}

