package com.example.appskeleton.util;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.appskeleton.R;
import com.example.appskeleton.constant.Url;
import com.example.appskeleton.model.OnCompleteListener;
import com.example.appskeleton.model.net.OkUtils;
import com.example.appskeleton.model.util.SharedPrefrenceUtils;
import com.example.appskeleton.view.base.BaseActivity;

import java.io.File;

public class ShowCrashActivity extends BaseActivity {

    RelativeLayout rl;
    private String path;
    private String crash;
    Button uploadLogBtn;

    @Override
    public void initView() {
        super.initView();
        tag = "ShowCrashActivity";
        setContentView(R.layout.activity_show_crash);
        rl = (RelativeLayout) findViewById(R.id.crash_rl);
        TextView tv = (TextView) findViewById(R.id.textView);
        uploadLogBtn = (Button) findViewById(R.id.uploadlog_btn);
        path = getIntent().getStringExtra("crash_file_path");
        crash = getIntent().getStringExtra("crash");
        tv.setText(crash);
        findViewById(R.id.exit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrashUtils.flag[0] = false;
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
        uploadLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadToServer(path);
            }
        });
        uploadToServer(path);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NetworkUtils.isConnected()){
            if (SharedPrefrenceUtils.getInstance().getHasCrashLogUnupload()) uploadToServer(path);
            return;
        }
        SharedPrefrenceUtils.getInstance().setHasCrashLogUnupload(true);
        Snackbar.make(rl, R.string.net_tip_forcrash, Snackbar.LENGTH_INDEFINITE)
                .setAction("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
        uploadLogBtn.setVisibility(View.VISIBLE);
    }

    private void uploadToServer(String path) {
        File file = new File(path);
        if (file.exists()){
            OkUtils<String> okUtils = new OkUtils<>(mContext);
            okUtils.url(Url.uploadCrashLogUrl);
            okUtils.addFile2(file);
            okUtils.targetClass(String.class);
            okUtils.execute(new OnCompleteListener<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.i("crash_uploadToServer",result);
                    SharedPrefrenceUtils.getInstance().removeHasCrashLogUnupload();
                    ToastUtil.showShortToast("上传日志成功");
                }

                @Override
                public void onError(String error) {
                    Log.e("crash_uploadToServer",error);
                }
            });
        }
    }
}
