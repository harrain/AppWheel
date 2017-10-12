package com.example.appskeleton.view.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.view.View;
import android.widget.RadioGroup;

import com.example.appskeleton.util.LogUtils;
import com.example.appskeleton.R;
import com.example.appskeleton.view.base.BaseTitleActivity;
import com.example.appskeleton.view.listener.PermissionResultListener;
import com.example.appskeleton.view.util.AlertDialogUtil;
import com.example.appskeleton.view.util.PermissionsUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseTitleActivity {


    RxPermissions rxPermissions;

    public boolean firstEnter = true;
    public RadioGroup radioGroup;


    @Override
    public void init() {
        super.init();

        rxPermissions = new RxPermissions(this);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        super.initView();
        radioGroup = (RadioGroup) findViewById(R.id.main_bottom);
        mTBack.setVisibility(View.GONE);
        mTTitle.setText("mainactivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (rxPermissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE) && rxPermissions.isGranted(Manifest.permission.ACCESS_FINE_LOCATION)
                && rxPermissions.isGranted(Manifest.permission.READ_PHONE_STATE)){

        }else {
            AlertDialogUtil.showForceAlertDialog(mContext, "本应用需要获取以下权限", "读写存储\t\n定位\t\n读取设备信息\t\n\n否则将无法正常使用", new AlertDialogUtil.AlertListener() {
                @Override
                public void positiveResult(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    requestEach();
                }
            });
        }

//        if (firstEnter) {
//            front.performClick();
//            firstEnter = false;
//        }
    }

    private void requestEach() {
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
                                    permissionName = "读写存储";
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
    }

    public void requestPermission(final PermissionResultListener listener, final String... permissions) {

        rxPermissions.request(permissions)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        listener.onHandlePermissionResult(granted);

                    }
                });
    }

    public void onCheckedChange(View v) {

        LogUtils.i("" + v.getId());

    }





}
