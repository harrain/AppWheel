package com.damon.appwheel.ui.rlPart.holder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.damon.appwheel.constant.AppConstants;
import com.damon.appwheel.R;
import com.damon.appwheel.model.operation.Link;
import com.damon.appwheel.ui.rlPart.base.AdapterDataOperation;
import com.damon.appwheel.ui.rlPart.base.AdapterLinkOperation;
import com.damon.appwheel.ui.rlPart.base.BaseMyHolder;
import com.damon.appwheel.ui.widget.SwipeItemLayout;

import java.io.File;

/**
 * Created by stephen on 2017/8/13.
 */

public class FileItemHolder extends BaseMyHolder<Link<String>> {

    TextView mFileTv;
    TextView mLeftMenu;
    TextView mRightMenu;
    SwipeItemLayout mSwipeItemLayout;
    private View contentView;

    private Context mContext;
    private final String tag = "fileItemHolder";

    public FileItemHolder(View itemView, Context context) {
        super(itemView);
        contentView = itemView;
        mFileTv = (TextView) itemView.findViewById(R.id.file_tv);
        mLeftMenu = (TextView) itemView.findViewById(R.id.left_menu);
        mRightMenu = (TextView) itemView.findViewById(R.id.right_menu);
        mSwipeItemLayout = (SwipeItemLayout) itemView.findViewById(R.id.file_swipe_layout);
        mContext = context;
    }


    @Override
    public void bind(final int position, final AdapterDataOperation<Link<String>> ado) {

//        LogUtils.i("fileholder","ado data size: "+ado.getDatas().size() + "---"+ado.getDatas().get(position));
        final int index = ado.getDatas().size() -1 - position;
        mFileTv.setText(ado.getDatas().get(index));

        mFileTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileItemHolder.this.getOnClickListener().onShortClick(v,index);
            }
        });
        mRightMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeItemLayout.close();
                deleteFile(index, (AdapterLinkOperation<String>) ado);
                mOnClickListener.onShortClick(v,index);
            }
        });

    }

    private void deleteFile(int position,AdapterLinkOperation<String> ado) {
        Link<String> datas = ado.getDatas();
        Log.e(tag,"imageSize----"+datas.size());
        int index = position;
        File file = new File(AppConstants.TRACES_DIR + "/" +datas.remove(index));
        Log.e(tag,"delete item----"+String.valueOf(index));
        Log.e(tag,"delete File item----"+String.valueOf(index));
        Log.e(tag,"deFile: "+file.getAbsolutePath()+" - "+file.length());
        if (file.exists()) {
            boolean delete = file.delete();
            if (delete){

                Toast.makeText(mContext,"删除成功！",Toast.LENGTH_SHORT).show();
                ado.deleteItem(position);

            }
        }

    }
}
