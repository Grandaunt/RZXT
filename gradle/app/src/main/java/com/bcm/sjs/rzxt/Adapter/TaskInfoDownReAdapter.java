package com.bcm.sjs.rzxt.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bcm.sjs.rzxt.DB.MEDIA;
import com.bcm.sjs.rzxt.R;
import com.bcm.sjs.rzxt.Utils.MyUtils;

import java.text.DecimalFormat;

/**
 * Created by SJS on 2017/1/4.
 */


public class TaskInfoDownReAdapter extends RecyclerView.Adapter<TaskInfoDownReAdapter.ViewHolder> {
    private String TAG = this.getClass().getSimpleName();
    private LayoutInflater mInflater;
    private MEDIA medialist;
    private String imageUrl="";
    private float sumPrice;
    private int number;
    public   DecimalFormat decimalFormat;
    private  String value;
    private String[] DownInfo;
    private String[] type;
    private String task_no;
//    public ViewHolder mholder;

    public TaskInfoDownReAdapter(Context context, MEDIA mediaList,String task_no) {
        this.mInflater = LayoutInflater.from(context);
        this.medialist = mediaList;
        this.task_no=task_no;
         value= medialist.mt_d_desc;
         DownInfo = value.split("%");


    }

    /**
     * item显示类型
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_info_download,parent,false);
        int screenWidth = MyUtils.getScreenMetrics(mInflater.getContext()).widthPixels;
        int space = (int) MyUtils.dp2px(mInflater.getContext(), 30f);
        int mImageWidth = (screenWidth - space) / 4;
        LinearLayout item=(LinearLayout)view.findViewById(R.id.item_down);
        item.setLayoutParams(new RecyclerView.LayoutParams(mImageWidth,250));
//        View view = mInflater.inflate(R.layout.item_info_download), parent, false);
        //view.setBackgroundColor(Color.RED);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
//        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
//                HomeActivity.this).inflate(R.layout.item_home, parent,
//                false));
//        return holder;
    }


    /**
     * 数据的绑定显示
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        Log.i(TAG,"onBindViewHolder+position="+position);
        //初始化数据
         type= DownInfo[position].split("@");
        Log.i(TAG,"DownInfo="+DownInfo[position]);
        Log.i(TAG,"type[0]="+type[0]);
        Log.i(TAG,"type[1]="+type[1]);
        if(type[0].equals("im")||type[0].equals("IM")){
            holder.im.setImageResource(R.mipmap.im);
        }
        else if(type[0].equals("w")||type[0].equals("W")){
            holder.im.setImageResource(R.mipmap.word);
        }
        else if(type[0].equals("p")||type[0].equals("P")){
            holder.im.setImageResource(R.mipmap.pdf);
        }
        else if(type[0].equals("E")||type[0].equals("e")){
            holder.im.setImageResource(R.mipmap.excle);
        }
        holder.tv.setText(type[1]);
////        final String[] DownInfo = new String[0];
////        String value= medialist.mt_d_im_desc;
////        String[] arrayIM = value.split(",");
////        value= medialist.mt_d_w_desc;
////        String[] arrayW = value.split(",");
////        value= medialist.mt_d_p_desc;
////        String[] arrayP = value.split(",");
////        value= medialist.mt_d_e_desc;
////        String[] arrayE = value.split(",");
//
//        if(position>=0&&position<arrayIM.length){
//             holder.im.setImageResource(R.mipmap.im);
//        }
//        else  if(position>=arrayIM.length&&position<arrayIM.length+arrayW.length){
//            holder.im.setImageResource(R.mipmap.word);
//        }
//        else  if(position>=arrayIM.length+arrayW.length&&position<arrayIM.length+arrayW.length+arrayP.length){
//            holder.im.setImageResource(R.mipmap.pdf);
//        }
//        else  if(position>=arrayIM.length+arrayW.length+arrayP.length&&position<arrayIM.length+arrayW.length+arrayP.length+arrayE.length){
//            holder.im.setImageResource(R.mipmap.excle);
//        }
//        int j=0;
////        int leng = arrayIM.length+arrayW.length+arrayP.length+arrayE.length;
//        for(int i=0;i< arrayIM.length;i++ ,j++){
//            DownInfo[j]=arrayIM[i];
//        }
//        for(int i=0;i< arrayW.length;i++ ,j++){
//            DownInfo[j]=arrayW[i];
//        }
//        for(int i=0;i< arrayP.length;i++ ,j++){
//            DownInfo[j]=arrayP[i];
//        }
//        for(int i=0;i< arrayE.length;i++ ,j++){
//            DownInfo[j]=arrayE[i];
//        }
//       holder.tv.setText(DownInfo[position]);
////        holder.item_tv.setText(mTitles[position]);
////        holder.tv.setText(mDatas.get(position));
////        holder.imGoods.setImageResource(mTitles[position]);
//         decimalFormat=new DecimalFormat(".00");
//        //构造方法的字符格式这里如果小数不足2位,会以0补足.
//        imageUrl ="http://123.57.29.113:8080/sell/static/uploadFiles/uploadImgs/"+mGoodsList.get(position).get("goodsImage");
//        Log.i(TAG,"imageUrl="+imageUrl);
////        "http://172.16.11.124:8080/MVNFHM/uploadFiles/uploadImgs/20170209/d9205a29278644fdbe6b37421bab17bb.png";
//        number = Integer.parseInt(mGoodsList.get(position).get("goodsNumber"));
//        sumPrice = Float.parseFloat(mGoodsList.get(position).get("goodsPrice"));
//        Log.i(TAG,"goodsPrice*number="+sumPrice+"*"+number+"="+sumPrice*number);
//        sumPrice = sumPrice*number;
//
//        holder.tvGoodsName.setText(mGoodsList.get(position).get("goodsName"));
////        holder.tvGoodsNumber.setText(mGoodsList.get(position).get("goodsNumber"));
//        holder.tvGoodsSumPrice.setText(decimalFormat.format(sumPrice));

        // 如果设置了回调，则设置点击事件

        if (mOnItemClickLitener != null)
        {

            holder.itemView.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    String num = type[1];
                    Log.i(TAG,"String num ="+num);
                    mOnItemClickLitener.onItemClick(holder.itemView, pos,num);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
//                    int pos = holder.getLayoutPosition();
//                    String num = mGoodsList.get(pos).get("goodsNum");
//                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos,num);
                    return false;
                }
            });
        }
        else{
            Log.i(TAG,"mOnItemClickLitener = null");
        }
    }

    @Override
    public int getItemCount() {
//        return mTitles.length;
//        if(medialist.mt_d_desc.equals("")){
//            return 4;
//        }

                //初始化数据

//        String value= medialist.mt_d_desc;
//        String[] DownInfo = value.split(",");
//        value= medialist.mt_d_w_desc;
//        String[] arrayW = value.split(",");
//        value= medialist.mt_d_p_desc;
//        String[] arrayP = value.split(",");
//        value= medialist.mt_d_e_desc;
//        String[] arrayE = value.split(",");
//        int j=0;
////        int leng = arrayIM.length+arrayW.length+arrayP.length+arrayE.length;
//        for(int i=0;i< arrayIM.length;i++ ,j++){
//            DownInfo[j]=arrayIM[i];
//        }
//        for(int i=0;i< arrayW.length;i++ ,j++){
//            DownInfo[j]=arrayW[i];
//        }
//        for(int i=0;i< arrayP.length;i++ ,j++){
//            DownInfo[j]=arrayP[i];
//        }
//        for(int i=0;i< arrayE.length;i++ ,j++){
//            DownInfo[j]=arrayE[i];
//        }
//        Log.i(TAG,DownInfo.length+"");
        return DownInfo.length;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView im ;
        public TextView tv;

        public ViewHolder(View view) {
            super(view);
            im = (ImageView)view.findViewById(R.id.item_im_download);
            tv = (TextView) view.findViewById(R.id.item_tv_download);
        }
    }

    public void addData(int position) {
//        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
//        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position, String numm);
        void onItemLongClick(View view, int position, String numm);
    }

    private OnItemClickLitener mOnItemClickLitener;
    private AdapterView.OnItemLongClickListener mOnItemLongClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
        this.mOnItemLongClickLitener =mOnItemLongClickLitener;

    }



}