package com.damon.appwheel.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.damon.appwheel.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ButtonBottomMenuActivity extends AppCompatActivity {

    @BindView(R.id.weixin_iv)
    ImageView mWeixinIv;
    @BindView(R.id.contact_iv)
    ImageView mContactIv;
    @BindView(R.id.find_iv)
    ImageView mFindIv;
    @BindView(R.id.profile_iv)
    ImageView mProfileIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_bottom_menu);
        ButterKnife.bind(this);
    }

    public void front(View v) {



        mWeixinIv.setImageResource(R.drawable.weixin_pressed);
        mContactIv.setImageResource(R.drawable.contact_list_normal);
        mFindIv.setImageResource(R.drawable.find_normal);
        mProfileIv.setImageResource(R.drawable.profile_normal);
    }

    public void list(View view) {

        mWeixinIv.setImageResource(R.drawable.weixin_normal);
        mContactIv.setImageResource(R.drawable.contact_list_pressed);
        mFindIv.setImageResource(R.drawable.find_normal);
        mProfileIv.setImageResource(R.drawable.profile_normal);
    }

    public void track(View v) {

    }

    /**
     * 导航按钮2点击事件
     *
     * @param v
     */
    public void search(View v) {

//        fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.replace(R.id.fl, mTraceFragment);
//        fragmentTransaction.show(mTraceFragment);
//        fragmentTransaction.commit();
    }

    public void device(View v) {


        mWeixinIv.setImageResource(R.drawable.weixin_normal);
        mContactIv.setImageResource(R.drawable.contact_list_normal);
        mFindIv.setImageResource(R.drawable.find_normal);
        mProfileIv.setImageResource(R.drawable.profile_pressed);
    }
}
