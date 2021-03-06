package com.damon.appwheel.constant;

import static com.damon.appwheel.constant.AppConstants.GALARY_DIR;

/**
 * Created by data on 2017/9/1.
 */

public class UIConstants {

    //WHAT 0-10 预留值
    public interface WHAT {
        int SUCCESS = 0;
        int FAILURE = 1;
        int ERROR = 2;
    }

    public interface KEY{
        String IMG_PATH = "IMG_PATH";
        String VIDEO_PATH = "VIDEO_PATH";
        String PIC_WIDTH = "PIC_WIDTH";
        String PIC_HEIGHT = "PIC_HEIGHT";
        String PIC_TIME = "PIC_TIME";

        String LONGITUDE = "LONGITUDE";
        String LATITUDE = "LATITUDE";
        //        String IMG_DIR = "/storage/emulated/0/Android/data/com.example.demowechat/files/DCIM";
        String IMG_DIR = GALARY_DIR+"DemoWeChat";
    }

    public interface REQUEST_CODE {
        int CAMERA = 0;
        int SHOW_PIC = 1;
        int ZXING_CODE = 2;
        int OPEN_GPS = 3;
        int TAKE_PHOTO = 11;
        int CHOOSE_PHOTO = 12;
        int WX_PICKIMAGE = 100;
    }

    public interface RESULT_CODE {
        int RESULT_OK = -1;
        int RESULT_CANCELED = 0;
        int RESULT_ERROR = 1;
    }

    public interface Extra_Key{

    }

    public interface ViewListenerFlag{

    }
}
