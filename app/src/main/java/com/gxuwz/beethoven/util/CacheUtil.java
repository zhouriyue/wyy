package com.gxuwz.beethoven.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 缓存工具类
 */
public class CacheUtil {

    /**
     * 缓存文件 cache file
     * @param context
     * @param fileName
     * @return
     */
    public static String cacheFile(Context context,String fileName){
        String CacheFilePath = context.getCacheDir().getAbsolutePath();
        File cacheFile = new File(CacheFilePath,fileName);
        if(!cacheFile.exists()) {
            try {
                if(cacheFile.createNewFile()) {
                    return cacheFile.getAbsolutePath();
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return cacheFile.getAbsolutePath();
    }

    /**
     * file转换为uri  file change uri
     * @param context
     * @param file
     * @return
     */
    private static Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {//简单地拦截一下
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context, "com.hjl.android7.fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    /**
     * uri 转换为 file   uri change file
     * @param uri
     * @return
     */
    public static File uriFile(Uri uri) {
        return new File(uri.getPath());
    }

    /**
     * 文件路径转Bitmap   filepath change bitmap
     * @param filePath
     * @return
     */
    public Bitmap FileBitmap(String filePath,Context context) throws IOException {
        File file = new File(filePath);
        Uri uri = Uri.fromFile(file);
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        return bitmap;
    }

    /**
     * Bitmap 转文件  bitmap change file
     * @param bitmap
     * @param file
     * @param quality
     * @return
     */
    public static boolean writeBitmapToFile(Bitmap bitmap, File file, int quality){
        boolean flag = false;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            flag = bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
