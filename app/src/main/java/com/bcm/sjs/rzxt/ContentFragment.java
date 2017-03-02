package com.bcm.sjs.rzxt;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by janiszhang on 2016/6/6.
 */

public class ContentFragment extends ListFragment {
    private String TAG = this.getClass().getSimpleName();
    private View viewContent;
    private int mType = 0;
    private String mTitle;

    public void setType(int mType) {
        this.mType = mType;
    }
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //布局文件中只有一个居中的TextView
        viewContent = inflater.inflate(R.layout.fragment_content,container,false);
//        TextView textView = (TextView) viewContent.findViewById(R.id.tv_content);
//        textView.setText(this.mTitle);

//        MemoRepo repo = new MemoRepo(MemoActivity.this);
//        ArrayList<HashMap<String, String>> memoList = repo.getMemoAllList();
//        ListAdapter adapter = new SimpleAdapter(MemoActivity.this, memoList, R.layout.item_memo, new String[]{"memo_cif_index", "memo_cif_no", "memo_cif_name"}, new int[]{R.id.tv_memo_cif_index, R.id.tv_memo_cif_no, R.id.tv_memo_cif_name});
//        setListAdapter(adapter);
        return viewContent;
    }

}
