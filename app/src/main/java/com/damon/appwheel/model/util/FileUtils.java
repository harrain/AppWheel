package com.damon.appwheel.model.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileUtils {

	/**
	 * 获取sd卡的保存位置
	 * @param path:
	 */
	public static String getDir(Context context, String path) {
		File dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//		File dir =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		path=dir.getAbsolutePath()+"/"+path;
		return path;
	}
	
	/**
	 * 修改本地任意文件名称
	 * @param context
	 * @param oldName
	 * @param newName
	 */
	public static void renameFileName(Context context, String oldName, String newName){
		String dir = getDir(context, oldName);
		File oldFile=new File(dir);
		dir=getDir(context, newName);
		File newFile=new File(dir);
		oldFile.renameTo(newFile);
	}

	public static File createFile(String path){
		File file=new File(path);

		if(!file.getParentFile().exists()){//若不存在目录，则创建
			boolean isSuccess = file.getParentFile().mkdirs();
			if(!isSuccess){//若文件所在目录创建失败，则返回
				return null;
			}
		}
		return file;
	}
}
