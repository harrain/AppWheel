package com.example.appskeleton.presenter;

import android.content.Context;
import android.content.DialogInterface;

import com.example.appskeleton.constant.Url;
import com.example.appskeleton.model.downloadmanager.utils.UpdateAppManager;
import com.example.appskeleton.util.LogUtils;
import com.example.appskeleton.util.VersionUtils;
import com.example.appskeleton.view.util.AlertDialogUtil;

/**
 * 初始化配置，App的初始化检查
 */

public class InitConfigPresenter extends PresenterBase{

    private String tag = "InitConfigPresenter";

    public InitConfigPresenter(Context context) {
        super(context);
    }

    public void antoUpdateVersion(final String url, final String appName){
        LogUtils.i(tag,"antoUpdateVersion");
        VersionUtils versionUtils = new VersionUtils();
        versionUtils.initHandler();
        versionUtils.registerVersionResultListener(new VersionUtils.VersionResultListener() {
            @Override
            public void onResultFlag(int flag) {
                LogUtils.i(tag,"flag "+flag);
                if (flag == VersionUtils.CODE_UPDATE_DIALOG){
                    AlertDialogUtil.showAlertDialogTwo(mContext,"提示更新","检测到应用有新版本，是否需要更新?", new AlertDialogUtil.AlertListener() {
                        @Override
                        public void positiveResult(DialogInterface dialog, int which) {
                            downloadapk(url, appName);
                        }
                    });
                }
            }
        });
        versionUtils.checkVerson(Url.checkUpdateUrl);
    }

    private void downloadapk(String url,String appName) {
        UpdateAppManager.downloadApk(mContext,url,"应用更新",appName);
    }
}
