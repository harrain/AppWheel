package com.example.appskeleton.model.util;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.example.appskeleton.constant.AppConstants;
import com.example.appskeleton.constant.UIConstants;
import com.example.appskeleton.util.LogUtils;
import com.example.appskeleton.util.ToastUtil;
import com.example.appskeleton.view.util.AlertDialogUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.IOException;

import io.reactivex.functions.Consumer;

import static com.example.appskeleton.constant.UIConstants.REQUEST_CODE.CHOOSE_PHOTO;
import static com.example.appskeleton.constant.UIConstants.REQUEST_CODE.TAKE_PHOTO;

/**
 * 提示是拍照，还是从相册中选取。抽取统一处理
 */

public class GalaryHelper {
    private Context mContext;
    private Uri imageUri;
    private ShowImageListener mShowImageListener;
    private RxPermissions rxpermissions;
    private java.lang.String tag = "GalaryHelper";

    public GalaryHelper(Context context) {
        mContext = context;
        rxpermissions = new RxPermissions((Activity) mContext);
    }

    /**
     * 启动dialog， 提示是拍照，还是从相册中选取
     * which : 0  拍照 ； 1  从相册选取
     */
    public void showChooseDialog(){
        AlertDialogUtil.showItemDialog(mContext, new String[]{"拍照", "从相册选取"}, new AlertDialogUtil.AlertListener() {
            @Override
            public void positiveResult(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (which == 0){
                    /** 启动系统拍照程序*/
                    startTakePhoto();
                }else if (which == 1){
                    /** 启动相册*/
                    openAlbum();
                }
            }
        });
    }

    private void startTakePhoto() {
        if (Build.VERSION.SDK_INT >=  23){
            if (!rxpermissions.isGranted(Manifest.permission.CAMERA)){
                rxpermissions.request(Manifest.permission.CAMERA).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) openCamera();
                        else {
                            ToastUtil.showShortToast("您拒绝了应用获取 【拍照】 权限，无法拍照");
                            LogUtils.i("rxpermissions","拍照 "+aBoolean);
                        }
                    }
                });
            }else {
                openCamera();
            }
        }else {
            openCamera();
        }

    }

    private void openCamera() {
        // 创建File对象，用于存储拍照后的图片
        File outputImage = new File(mContext.getExternalCacheDir(), "output_image_"+ SystemClock.elapsedRealtime()+".jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < 24) {
            imageUri = Uri.fromFile(outputImage);
        } else {
            imageUri = FileProvider.getUriForFile(mContext, AppConstants.FILEPROVIDER_AUTHORITY, outputImage);
        }

        // 启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        ((AppCompatActivity)mContext).startActivityForResult(intent, TAKE_PHOTO);
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        ((AppCompatActivity)mContext).startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
    }

    /**
     * 在context所在的activity的onActivityResult调用一下方法，接管onActivityResult处理结果
     */
    public void handleResult(int requestCode,Intent data,ShowImageListener listener) {
        mShowImageListener = listener;
        if (requestCode == UIConstants.REQUEST_CODE.TAKE_PHOTO) {
            try {
                // 将拍摄的照片显示出来
//                Bitmap bitmap = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(imageUri));
//                        picture.setImageBitmap(bitmap);
                LogUtils.i(tag,"handlerresult path "+imageUri.toString());
                if (listener!=null&& !TextUtils.isEmpty(imageUri.getPath()))
                listener.showImage(imageUri.getPath(),imageUri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == UIConstants.REQUEST_CODE.CHOOSE_PHOTO){
            // 判断手机系统版本号
            if (Build.VERSION.SDK_INT >= 19) {
                // 4.4及以上系统使用这个方法处理图片
                handleImageOnKitKat(data);
            } else {
                // 4.4以下系统使用这个方法处理图片
                handleImageBeforeKitKat(data);
            }
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(mContext, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = mContext.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
//            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
//            picture.setImageBitmap(bitmap);
            if (mShowImageListener!=null&& !TextUtils.isEmpty(imagePath))
                mShowImageListener.showImage(imagePath,null);
        } else {
            ToastUtil.showShortToast("获取照片失败");
        }
    }

    public void release(){
        mShowImageListener = null;
        mContext = null;
    }

    public interface ShowImageListener{
        void showImage(String imagePath,Uri uri);
    }
}
