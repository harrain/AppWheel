package com.damon.appwheel.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.widget.RadioGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.damon.appwheel.util.LogUtils;
import com.damon.appwheel.R;
import com.damon.appwheel.ui.base.BaseTitleActivity;
import com.damon.appwheel.ui.listener.PermissionResultListener;
import com.damon.appwheel.ui.util.AlertDialogUtil;
import com.damon.appwheel.ui.util.PermissionsUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.lang.reflect.Field;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseTitleActivity {


    RxPermissions rxPermissions;

    public boolean firstEnter = true;
    public RadioGroup radioGroup;
    public BottomNavigationBar bottomNavigationBar;
    public ShapeBadgeItem shapeBadgeItem;
    public BottomNavigationItem profileBNI;


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

        TextBadgeItem numberBadgeItem = new TextBadgeItem();
        shapeBadgeItem = new ShapeBadgeItem();
        profileBNI = new BottomNavigationItem(R.drawable.profile_pressed, "我的");
        profileBNI.setActiveColorResource(R.color.wechat_green).setBadgeItem(shapeBadgeItem);

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setBackgroundColor(getResources().getColor(R.color.white));
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setActivated(false);
        bottomNavigationBar

                .addItem(new BottomNavigationItem(R.drawable.weixin_pressed, "会话").setActiveColorResource(R.color.wechat_green).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.find_pressed, "发现").setActiveColorResource(R.color.wechat_green))
                .addItem(new BottomNavigationItem(R.drawable.contact_list_pressed, "目录").setActiveColorResource(R.color.wechat_green))
                .addItem(profileBNI)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                bottomTabSelected(position);
            }
            @Override
            public void onTabUnselected(int position) {
            }
            @Override
            public void onTabReselected(int position) {
            }
        });


        numberBadgeItem.setText("1");
        numberBadgeItem.setBorderWidth(2);
        shapeBadgeItem.setShape(ShapeBadgeItem.SHAPE_OVAL);
        shapeBadgeItem.setSizeInDp(this,8,8);

    }

    public void bottomTabSelected(int position){}


    public void showProfileBadge(){
        shapeBadgeItem.show();
        profileBNI.setBadgeItem(shapeBadgeItem);
        bottomNavigationBar.initialise();
    }

    public void clearProfileBadge(){
        Field badgeField = null;
        try {
            badgeField = profileBNI.getClass().getDeclaredField("mBadgeItem");
            badgeField.setAccessible(true);
            badgeField.set(profileBNI,null);
            bottomNavigationBar.initialise();
        } catch (NoSuchFieldException e) {e.printStackTrace();
        }catch (IllegalAccessException e){e.printStackTrace();}
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

        if (firstEnter) {
            bottomNavigationBar.selectTab(0,true);
            firstEnter = false;
        }
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
