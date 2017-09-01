package com.damon.appwheel.model;

import android.os.Environment;

import com.damon.appwheel.view.util.Utils;

/**
 * Created by data on 2017/9/1.
 */

public class ModelConstants {
    public static final String CRASH_DIR = Utils.getContext()
            .getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/" + "crashes";
}
