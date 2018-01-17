package com.damon.appwheel.ui.activity;

import android.content.Context;
import android.widget.TextView;

import com.damon.appwheel.R;
import com.damon.appwheel.util.DeviceUtils;
import com.damon.appwheel.ui.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DeviceActivity extends BaseTitleActivity {

    @BindView(R.id.Manufacturer)
    TextView Manufacturer;
    @BindView(R.id.Model)
    TextView Model;
    @BindView(R.id.DeviceName)
    TextView DeviceName;
    @BindView(R.id.Version)
    TextView Version;

    @BindView(R.id.ROM)
    TextView ROM;
    @BindView(R.id.CPU)
    TextView CPU;
    @BindView(R.id.CPU_ABI)
    TextView cpuABI;
    @BindView(R.id.widthPixel)
    TextView widthPixel;
    @BindView(R.id.heightPixel)
    TextView heightPixel;
    @BindView(R.id.physical)
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
