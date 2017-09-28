package com.example.appskeleton.constant;

import android.os.Environment;

import com.example.appskeleton.view.util.Utils;

/**
 * Created by data on 2017/9/1.
 */

public class ModelConstants {
    public static final String CRASH_DIR = Utils.getContext()
            .getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/" + "crashes";
}
