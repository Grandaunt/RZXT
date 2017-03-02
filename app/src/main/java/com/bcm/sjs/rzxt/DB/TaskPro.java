package com.bcm.sjs.rzxt.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SJS on 2017/2/28.
 */

public class TaskPro {
    private  String  TAG="TaskPro";
    private DBHelper dbHelper;

    public TaskPro(Context context){
        dbHelper =new DBHelper(context);
    }

    public int insert(TASK task){
        //打开连接，写入数据
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
//        values.put(Goods.GoodsId,goods.goodsId);
        //记录主键
        values.put(TASK.TASK_ID,task.task_id);
        //任务编号
        values.put(TASK.TASK_NO,task.task_no);
        //任务类型
        values.put(TASK.TASK_TYPE,task.task_type);
        //任务状态
        values.put(TASK.TASK_STATUS,task.task_status);
        //初期资料是否齐全
        values.put(TASK.TASK_IS_EARLY_FILE,task.task_is_early_file);


        //检查机构代码
        values.put(TASK.TASK_CHECK_ORG_NO,task.task_check_org_no);
        //检查机构名称
        values.put(TASK.TASK_CHECK_ORG_NAME,task.task_check_org_name);
        //检查类型(首次检查、定期检查、不定期检查)
        values.put(TASK.TASK_CHECK_TYPE,task.task_check_type);
        //检查选项(按合同检查)
        values.put(TASK.TASK_CHECK_OPTION,task.ln_check_option);
        //任务检查人账号
        values.put(TASK.TASK_INER_ACC,task.task_iner_acc);


        //任务检查人姓名
        values.put(TASK.TASK_INER_NAME,task.task_iner_name);
        //任务开始日期
        values.put(TASK.TASK_START_DATE,task.task_start_date);
        //任务截止日期
        values.put(TASK.TASK_END_DATE,task.task_end_date);
        //任务完成日期
        values.put(TASK.TASK_FINISH_DATE,task.task_finish_date);
        //审核人账号
        values.put(TASK.TASK_AUDIT_ACC,task.task_audit_acc);


        //审核人姓名
        values.put(TASK.TASK_AUDIT_NAME,task.task_audit_name);
        //申请公司编号
        values.put(TASK.TASK_COM_NO,task.task_com_no);
        //申请公司名称
        values.put(TASK.TASK_COM_NAME,task.task_com_name);
        //申请合同编号
        values.put(TASK.TASK_CON_NO,task.task_con_no);
        //申请项目编号
        values.put(TASK.TASK_ITEN_NO,task.task_item_no);


        //申请产品编号
        values.put(TASK.TASK_PRDT_NO,task.task_prdt_no);
        //申请产品名称
        values.put(TASK.TASK_PRDT_NAME,task.task_prdt_name);
        //申请生产类型
        values.put(TASK.TASK_PRDT_TYPE,task.task_prdt_type);
        //申请认证范围
        values.put(TASK.TASK_RZ_SCOPE,task.task_rz_scope);
        //申请认证类型
        values.put(TASK.TASK_RZ_TYPE,task.task_rz_type);

        //申请公司地址
        values.put(TASK.TASK_COM_ADDR,task.task_com_addr);
        //申请公司电话
        values.put(TASK.TASK_COM_TEL,task.task_com_tel);
        //申请公司邮编
        values.put(TASK.TASK_COM_POST_CODE,task.task_com_post_code);
        //申请公司邮箱
        values.put(TASK.TASK_COM_EMAIL,task.task_com_email);
        //申请公司传真
        values.put(TASK.TASK_COM_FAX,task.task_com_fax);


        //申请公司网络url
        values.put(TASK.TASK_WEB_URL,task.task_web_url);
        //公司联系人姓名
        values.put(TASK.TASK_COM_CON_NAME,task.task_com_con_name);
        //公司联系人电话
        values.put(TASK.TASK_COM_CON_TEL,task.task_com_con_tel);
        //公司联系人职位
        values.put(TASK.TASK_COM_CON_IDE,task.task_com_con_ide);
        //申请产品介绍
        values.put(TASK.TASK_ITEM_INFO,task.task_item_info);


        //信息采集模板ID
        values.put(TASK.TASK_MT_ID,task.task_mt_id);
        //任务备注
        values.put(TASK.TASK_NOTE,task.task_note);

        long task_Id=db.insert(TASK.TABLE,null,values);
        db.close();
        Log.i(TAG,"task_Id="+task_Id);
        return (int)task_Id;
    }


    public void delete(int task_Id){
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        db.delete(TASK.TABLE,TASK.TASK_ID+"=?", new String[]{String.valueOf(task_Id)});
        db.close();
    }
    public void update(TASK task){
        Log.i(TAG,"update………………………………………………………………");
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        //记录主键
        values.put(TASK.TASK_ID,task.task_id);
        //任务编号
        values.put(TASK.TASK_NO,task.task_no);
        //任务类型
        values.put(TASK.TASK_TYPE,task.task_type);
        //任务状态
        values.put(TASK.TASK_STATUS,task.task_status);
        //初期资料是否齐全
        values.put(TASK.TASK_IS_EARLY_FILE,task.task_is_early_file);


        //检查机构代码
        values.put(TASK.TASK_CHECK_ORG_NO,task.task_check_org_no);
        //检查机构名称
        values.put(TASK.TASK_CHECK_ORG_NAME,task.task_check_org_name);
        //检查类型(首次检查、定期检查、不定期检查)
        values.put(TASK.TASK_CHECK_TYPE,task.task_check_type);
        //检查选项(按合同检查)
        values.put(TASK.TASK_CHECK_OPTION,task.ln_check_option);
        //任务检查人账号
        values.put(TASK.TASK_INER_ACC,task.task_iner_acc);


        //任务检查人姓名
        values.put(TASK.TASK_INER_NAME,task.task_iner_name);
        //任务开始日期
        values.put(TASK.TASK_START_DATE,task.task_start_date);
        //任务截止日期
        values.put(TASK.TASK_END_DATE,task.task_end_date);
        //任务完成日期
        values.put(TASK.TASK_FINISH_DATE,task.task_finish_date);
        //审核人账号
        values.put(TASK.TASK_AUDIT_ACC,task.task_audit_acc);


        //审核人姓名
        values.put(TASK.TASK_AUDIT_NAME,task.task_audit_name);
        //申请公司编号
        values.put(TASK.TASK_COM_NO,task.task_com_no);
        //申请公司名称
        values.put(TASK.TASK_COM_NAME,task.task_com_name);
        //申请合同编号
        values.put(TASK.TASK_CON_NO,task.task_con_no);
        //申请项目编号
        values.put(TASK.TASK_ITEN_NO,task.task_item_no);


        //申请产品编号
        values.put(TASK.TASK_PRDT_NO,task.task_prdt_no);
        //申请产品名称
        values.put(TASK.TASK_PRDT_NAME,task.task_prdt_name);
        //申请生产类型
        values.put(TASK.TASK_PRDT_TYPE,task.task_prdt_type);
        //申请认证范围
        values.put(TASK.TASK_RZ_SCOPE,task.task_rz_scope);
        //申请认证类型
        values.put(TASK.TASK_RZ_TYPE,task.task_rz_type);

        //申请公司地址
        values.put(TASK.TASK_COM_ADDR,task.task_com_addr);
        //申请公司电话
        values.put(TASK.TASK_COM_TEL,task.task_com_tel);
        //申请公司邮编
        values.put(TASK.TASK_COM_POST_CODE,task.task_com_post_code);
        //申请公司邮箱
        values.put(TASK.TASK_COM_EMAIL,task.task_com_email);
        //申请公司传真
        values.put(TASK.TASK_COM_FAX,task.task_com_fax);


        //申请公司网络url
        values.put(TASK.TASK_WEB_URL,task.task_web_url);
        //公司联系人姓名
        values.put(TASK.TASK_COM_CON_NAME,task.task_com_con_name);
        //公司联系人电话
        values.put(TASK.TASK_COM_CON_TEL,task.task_com_con_tel);
        //公司联系人职位
        values.put(TASK.TASK_COM_CON_IDE,task.task_com_con_ide);
        //申请产品介绍
        values.put(TASK.TASK_ITEM_INFO,task.task_item_info);


        //信息采集模板ID
        values.put(TASK.TASK_MT_ID,task.task_mt_id);
        //任务备注
        values.put(TASK.TASK_NOTE,task.task_note);

        db.update(TASK.TABLE,values,TASK.TASK_NO+"=?",new String[] { String.valueOf(task.task_no) });
        db.close();



    }
//
//    //查询该num编号的商品信息
//    public  TASK SelectTask(String num){
//        SQLiteDatabase db= dbHelper.getReadableDatabase();
//
//
//        String selectQuery="SELECT "
//                +TASK.ID+","
//                +Goods.GoodsNum+","
//                +Goods.GoodsName+","
//                +Goods.GoodsPrice+","
//                +Goods.GooodsInfo+","
//
//                +Goods.GoodsImage+","
//                +Goods.GoodsType+","
//                +Goods.GoodsStatus+","
//                +Goods.GoodsNumber+","
//                +Goods.GoodsCost+","
//
//                +Goods.GoodsTemperature+","
//                +Goods.GoodsLight+","
//                +Goods.GoodsLife+
//                " FROM "+Goods.TABLE
//                + " WHERE " +
//                Goods.GoodsNum + "=?";
//        int iCount=0;
//        Goods goods=new Goods();
//
//
//        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(num)});
//        if(cursor.moveToFirst()){
//            do{
//
//                goods.goodsId = cursor.getString(cursor.getColumnIndex(Goods.GoodsId));
//                goods.goodsNum = cursor.getString(cursor.getColumnIndex(Goods.GoodsNum));
//                goods.goodsName = cursor.getString(cursor.getColumnIndex(Goods.GoodsName));
//                goods.goodsPrice = cursor.getString(cursor.getColumnIndex(Goods.GoodsPrice));
//                goods.gooodsInfo = cursor.getString(cursor.getColumnIndex(Goods.GooodsInfo));
//
//                goods.goodsImage = cursor.getString(cursor.getColumnIndex(Goods.GoodsImage));
//                goods.goodsType = cursor.getString(cursor.getColumnIndex(Goods.GoodsType));
//                goods.goodsStatus = cursor.getString(cursor.getColumnIndex(Goods.GoodsStatus));
//                goods.goodsNumber = cursor.getString(cursor.getColumnIndex(Goods.GoodsNumber));
//                goods.goodsCost = cursor.getString(cursor.getColumnIndex(Goods.GoodsCost));
//
//                goods.goodsTemperature = cursor.getString(cursor.getColumnIndex(Goods.GoodsTemperature));
//                goods.goodsLight = cursor.getString(cursor.getColumnIndex(Goods.GoodsLight));
//                goods.goodsLife = cursor.getString(cursor.getColumnIndex(Goods.GoodsLife));
//
//
//            }while(cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return goods;
//    }
//
//
    //查询goods表id
    public boolean getNo(String No){
        boolean rel = false;

        SQLiteDatabase db= dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                TASK.TASK_NO+

                " FROM "+TASK.TABLE +
                "  WHERE " + TASK.TASK_NO + " = '" + No + "'"+" LIMIT 1";


        System.out.println(selectQuery);
        ArrayList<HashMap<String,String>> personalList=new ArrayList<HashMap<String, String>>();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            rel=true;

        }
        Log.i(TAG,"查询结果：rel="+rel);
        cursor.close();
        db.close();
        return rel;
    }
//    //查询可显示goods表
//    public ArrayList<HashMap<String, String>> showGoodsList(){
//        boolean rel = false;
//
//        SQLiteDatabase db= dbHelper.getReadableDatabase();
//        String selectQuery="SELECT "
//                +Goods.GoodsId+","
//                +Goods.GoodsNum+","
//                +Goods.GoodsName+","
//                +Goods.GoodsPrice+","
//                +Goods.GooodsInfo+","
//
//                +Goods.GoodsImage+","
//                +Goods.GoodsType+","
//                +Goods.GoodsStatus+","
//                +Goods.GoodsNumber+","
//                +Goods.GoodsCost+","
//
//                +Goods.GoodsTemperature+","
//                +Goods.GoodsLight+","
//                +Goods.GoodsLife+
//                " FROM "+Goods.TABLE +
//                "  WHERE " + Goods.GoodsStatus + " = '" + 1 + "'";
//
//
//        Log.i(TAG,selectQuery);
//        ArrayList<HashMap<String,String>> showGoodsList=new ArrayList<HashMap<String, String>>();
//        Cursor cursor=db.rawQuery(selectQuery,null);
//        if(cursor.moveToFirst()){
//            do{
//                HashMap<String,String> goods=new HashMap<String,String>();
//                goods.put("goodsId",cursor.getString(cursor.getColumnIndex(Goods.GoodsId)));
//                goods.put("goodsNum", cursor.getString(cursor.getColumnIndex(Goods.GoodsNum)));
//                goods.put("goodsName", cursor.getString(cursor.getColumnIndex(Goods.GoodsName)));
//                goods.put("goodsPrice", cursor.getString(cursor.getColumnIndex(Goods.GoodsPrice)));
//                goods.put("gooodsInfo", cursor.getString(cursor.getColumnIndex(Goods.GooodsInfo)));
//
//                goods.put("goodsImage", cursor.getString(cursor.getColumnIndex(Goods.GoodsImage)));
//                goods.put("goodsType", cursor.getString(cursor.getColumnIndex(Goods.GoodsType)));
//                goods.put("goodsStatus", cursor.getString(cursor.getColumnIndex(Goods.GoodsStatus)));
//                goods.put("goodsNumber",  cursor.getString(cursor.getColumnIndex(Goods.GoodsNumber)));
//                goods.put("goodsCost", cursor.getString(cursor.getColumnIndex(Goods.GoodsCost)));
//
//                goods.put("goodsTemperature", cursor.getString(cursor.getColumnIndex(Goods.GoodsTemperature)));
//                goods.put("goodsLight",  cursor.getString(cursor.getColumnIndex(Goods.GoodsLight)));
//                goods.put("goodsLife", cursor.getString(cursor.getColumnIndex(Goods.GoodsLife)));
//
//                showGoodsList.add(goods);
//            }while(cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return showGoodsList;
//
//    }
//    //查询可管理的goods表
//    public ArrayList<HashMap<String, String>> showGoodsManagerList(){
//        boolean rel = false;
//
//        SQLiteDatabase db= dbHelper.getReadableDatabase();
//        String selectQuery="SELECT "
//                +Goods.GoodsId+","
//                +Goods.GoodsNum+","
//                +Goods.GoodsName+","
//                +Goods.GoodsPrice+","
//                +Goods.GooodsInfo+","
//
//                +Goods.GoodsImage+","
//                +Goods.GoodsType+","
//                +Goods.GoodsStatus+","
//                +Goods.GoodsNumber+","
//                +Goods.GoodsCost+","
//
//                +Goods.GoodsTemperature+","
//                +Goods.GoodsLight+","
//                +Goods.GoodsLife+
//                " FROM "+Goods.TABLE ;
//
//
//        Log.i(TAG,selectQuery);
//        ArrayList<HashMap<String,String>> showGoodsList=new ArrayList<HashMap<String, String>>();
//        Cursor cursor=db.rawQuery(selectQuery,null);
//        if(cursor.moveToFirst()){
//            do{
//                HashMap<String,String> goods=new HashMap<String,String>();
//                goods.put("goodsId",cursor.getString(cursor.getColumnIndex(Goods.GoodsId)));
//                goods.put("goodsNum", cursor.getString(cursor.getColumnIndex(Goods.GoodsNum)));
//                goods.put("goodsName", cursor.getString(cursor.getColumnIndex(Goods.GoodsName)));
//                goods.put("goodsPrice", cursor.getString(cursor.getColumnIndex(Goods.GoodsPrice)));
//                goods.put("gooodsInfo", cursor.getString(cursor.getColumnIndex(Goods.GooodsInfo)));
//
//                goods.put("goodsImage", cursor.getString(cursor.getColumnIndex(Goods.GoodsImage)));
//                goods.put("goodsType", cursor.getString(cursor.getColumnIndex(Goods.GoodsType)));
//                goods.put("goodsStatus", cursor.getString(cursor.getColumnIndex(Goods.GoodsStatus)));
//                goods.put("goodsNumber",  cursor.getString(cursor.getColumnIndex(Goods.GoodsNumber)));
//                goods.put("goodsCost", cursor.getString(cursor.getColumnIndex(Goods.GoodsCost)));
//
//                goods.put("goodsTemperature", cursor.getString(cursor.getColumnIndex(Goods.GoodsTemperature)));
//                goods.put("goodsLight",  cursor.getString(cursor.getColumnIndex(Goods.GoodsLight)));
//                goods.put("goodsLife", cursor.getString(cursor.getColumnIndex(Goods.GoodsLife)));
//
//                showGoodsList.add(goods);
//            }while(cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return showGoodsList;
//
//    }


}
