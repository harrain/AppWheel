package com.damon.appwheel.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.damon.appwheel.LogUtils;
import com.damon.appwheel.R;
import com.damon.appwheel.view.base.BaseTitleActivity;
import com.damon.appwheel.view.util.AlertDialogUtil;
import com.damon.appwheel.view.util.PermissionsUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseTitleActivity {


    RxPermissions rxPermissions;
    private boolean firstEnter = true;
    Context mContext;
    private String tag = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        rxPermissions = new RxPermissions(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        rxPermissions.requestEach(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {// `permission.name` is granted !
                            LogUtils.i(tag, "granted");

                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // Denied permission without ask never again
                            String permissionName = "";
                            switch (permission.name) {
                                case Manifest.permission.ACCESS_FINE_LOCATION:
                                    permissionName = "定位";
                                    break;
                                case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                                    permissionName = "存储";
                                    break;
                                case Manifest.permission.READ_PHONE_STATE:
                                    permissionName = "设备信息";
                                    break;
                            }
                            LogUtils.i(tag, "!granted");
                            AlertDialogUtil.showForceAlertDialog(mContext, "你拒绝了应用所需'" + permissionName + "'的权限", "点击 [确认] 去开启'" + permission.name + "'权限", new AlertDialogUtil.AlertListener() {
                                @Override
                                public void positiveResult(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    PermissionsUtil.goToSettingsForRequestPermission(mContext);
                                    return;
                                }
                            });
                        } else {
                            // Denied permission with ask never again
                            // Need to go to the settings
                            LogUtils.i(tag, "ungranted");
                            AlertDialogUtil.showForceAlertDialog(mContext, "你拒绝了应用'" + permission.name + "'所需的权限", "点击 [确认] 去开启'" + permission.name + "'权限", new AlertDialogUtil.AlertListener() {
                                @Override
                                public void positiveResult(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    PermissionsUtil.goToSettingsForRequestPermission(mContext);
                                    return;
                                }
                            });

                        }
                    }
                });
        if (firstEnter) {
//            front.performClick();
            firstEnter = false;
        }
    }

    public void onCheckedChange(View v){
        switch (v.getId()){
            default:break;
        }
    }


}
