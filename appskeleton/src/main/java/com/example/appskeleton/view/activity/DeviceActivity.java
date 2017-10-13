package com.example.appskeleton.view.activity;

import android.content.Context;
import android.widget.TextView;

import com.example.appskeleton.R;
import com.example.appskeleton.R2;
import com.example.appskeleton.util.DeviceUtils;
import com.example.appskeleton.view.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DeviceActivity extends BaseTitleActivity {

    @BindView(R2.id.Manufacturer)
    TextView Manufacturer;
    @BindView(R2.id.Model)
    TextView Model;
    @BindView(R2.id.DeviceName)
    TextView DeviceName;
    @BindView(R2.id.Version)
    TextView Version;

    @BindView(R2.id.ROM)
    TextView ROM;
    @BindView(R2.id.CPU)
    TextView CPU;
    @BindView(R2.id.CPU_ABI)
    TextView cpuABI;
    @BindView(R2.id.widthPixel)
    TextView widthPixel;
    @BindView(R2.id.heightPixel)
    TextView heightPixel;
    @BindView(R2.id.physical)
    TextView realSize;


    private Context mContext;


    public void initView() {
        setContentView(R.layout.activity_device);
        super.initView();
        ButterKnife.bind(this);

        mContext = this;
        setmTTitle("我的设备");
        Manufacturer.setText(DeviceUtils.getManufacturer());
        Model.setText(DeviceUtils.getModel());
        DeviceName.setText(DeviceUtils.getDeviceName());
        Version.setText(DeviceUtils.getSDKVersion());

        ROM.setText(DeviceUtils.getDisplay() + "-" + DeviceUtils.getProductName());
        CPU.setText(DeviceUtils.getCpuType());
        cpuABI.setText(DeviceUtils.getCPU_ABI());
        widthPixel.setText(DeviceUtils.getScreenWidth(mContext) + "");
        heightPixel.setText(DeviceUtils.getScreenHeight(mContext) + "");
        realSize.setText("屏幕尺寸 " + DeviceUtils.getScreenInches(this) + "寸" + "  dpi " + DeviceUtils.getDPI(mContext));
    }


}
