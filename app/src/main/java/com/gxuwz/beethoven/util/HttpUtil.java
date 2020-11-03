package com.gxuwz.beethoven.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {

    public static final String BASEURL = "https://www.zhou.website/wyy-0.0.1-SNAPSHOT/";
    //public static final String BASEURL = "http://10.0.2.2:8082/wyy-0.0.1-SNAPSHOT/";
    public static final String USER = "/user";
    public static final String LYRIC = "/lyric";

    public static void post(String httpUrl,String data){
        //子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(httpUrl);
                    //强制转换
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //给连接指定请求的方式
                    conn.setRequestMethod("POST");
                    //默认是毫秒，所以5秒
                    conn.setConnectTimeout(5000);
                    conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    conn.setRequestProperty("Content-Length",data.length()+"");
                    conn.setRequestProperty("Charset", "UTF-8");
                    conn.setDoOutput(true);
                    OutputStream os = conn.getOutputStream();
                    os.write(data.getBytes());
                    conn.getResponseCode();
                    conn.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void post(String httpUrl,String data,Handler handler){
        //子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(httpUrl);
                    //强制转换
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //给连接指定请求的方式
                    conn.setRequestMethod("POST");
                    //默认是毫秒，所以5秒
                    conn.setConnectTimeout(5000);
                    conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    conn.setRequestProperty("Content-Length",data.length()+"");
                    conn.setRequestProperty("Charset", "UTF-8");
                    conn.setDoOutput(true);
                    OutputStream os = conn.getOutputStream();
                    os.write(data.getBytes());
                    int code = conn.getResponseCode();
                    if(code==200){
                        //获取服务器返回的内容，而且通过输入流的方式得到
                        InputStream in = conn.getInputStream();
                        //判断输入流有多少可以读的字节
                        //int i = icon_in.available();
                        //定义字节数组接受回来的数据
                        //byte[] b = new byte[1000];
                        //将输入存入字节数组
                        //icon_in.read(b);
                        //先把字节数组转换成字符串
                        String result = IOUtils.toString(in);
                        //把子线程数据回传到主线程
                        Message msg = new Message();
                        //构建bundler对象，并且把数据给到bundler
                        Bundle bundle = new Bundle();
                        bundle.putString("result", result);
                        msg.setData(bundle);
                        //告诉主线程，当前的信息叫什么名字
                        msg.what = 1;
                        //将message发送给handler
                        handler.sendMessage(msg);
                    }
                    conn.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void get(String httpUrl, Handler handler) {
        //子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(httpUrl);
                    //强制转换
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //给连接指定请求的方式
                    conn.setRequestMethod("GET");
                    //默认是毫秒，所以5秒
                    conn.setConnectTimeout(5000);
                    int code = conn.getResponseCode();
                    if (code == 200) {
                        //获取服务器返回的内容，而且通过输入流的方式得到
                        InputStream in = conn.getInputStream();
                        //判断输入流有多少可以读的字节
                        //int i = icon_in.available();
                        //定义字节数组接受回来的数据
                        //byte[] b = new byte[1000];
                        //将输入存入字节数组
                        //icon_in.read(b);
                        //先把字节数组转换成字符串
                        String result = IOUtils.toString(in);
                        //把子线程数据回传到主线程
                        Message msg = new Message();
                        //构建bundler对象，并且把数据给到bundler
                        Bundle bundle = new Bundle();
                        bundle.putString("result", result);
                        msg.setData(bundle);
                        //告诉主线程，当前的信息叫什么名字
                        msg.what = 1;
                        //将message发送给handler
                        handler.sendMessage(msg);
                    }
                    conn.disconnect();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void getImage(String UrlPath, Handler handler) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bm = null;
                String urlpath = BASEURL + UrlPath;
                // 2、获取Uri
                try {
                    URL uri = new URL(urlpath);
                    // 3、获取连接对象、此时还没有建立连接
                    HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
                    // 4、初始化连接对象
                    // 设置请求的方法，注意大写
                    connection.setRequestMethod("GET");
                    // 读取超时
                    connection.setReadTimeout(5000);
                    // 设置连接超时
                    connection.setConnectTimeout(5000);
                    // 5、建立连接
                    connection.connect();

                    // 6、获取成功判断,获取响应码
                    if (connection.getResponseCode() == 200) {
                        // 7、拿到服务器返回的流，客户端请求的数据，就保存在流当中
                        InputStream is = connection.getInputStream();
                        // 8、从流中读取数据，构造一个图片对象GoogleAPI
                        bm = BitmapFactory.decodeStream(is);
                        // 9、把图片设置到UI主线程
                        // ImageView中,获取网络资源是耗时操作需放在子线程中进行,通过创建消息发送消息给主线程刷新控件；

                        Log.i("", "网络请求成功");

                    } else {
                        Log.v("tag", "网络请求失败");
                        bm = null;
                    }
                    connection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                message.obj = bm;
                handler.sendMessage(message);

            }
        }).start();
    }

    public static Bitmap getImage(String UrlPath) {
        Bitmap bm = null;
        String urlpath = BASEURL + UrlPath;
        // 2、获取Uri
        try {
            URL uri = new URL(urlpath);
            // 3、获取连接对象、此时还没有建立连接
            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
            // 4、初始化连接对象
            // 设置请求的方法，注意大写
            connection.setRequestMethod("GET");
            // 读取超时
            connection.setReadTimeout(5000);
            // 设置连接超时
            connection.setConnectTimeout(5000);
            // 5、建立连接
            connection.connect();

            // 6、获取成功判断,获取响应码
            // 7、拿到服务器返回的流，客户端请求的数据，就保存在流当中
            InputStream is = connection.getInputStream();
            // 8、从流中读取数据，构造一个图片对象GoogleAPI
            bm = BitmapFactory.decodeStream(is);
            // 9、把图片设置到UI主线程
            // ImageView中,获取网络资源是耗时操作需放在子线程中进行,通过创建消息发送消息给主线程刷新控件；

            Log.i("", "网络请求成功");
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return bm;
    }

    public static int isExist(Context context, String fileName) {
        String CacheFilePath = context.getCacheDir().getAbsolutePath();
        File cacheFile = new File(CacheFilePath, fileName);
        if (!cacheFile.exists()) {
            return 1;
        } else {
            return 0;
        }
    }

    public static String downFile(Context context, String urlStr, String path, String fileName) {
        String cacheFilePath = context.getCacheDir().getAbsolutePath();
        String newPath = cacheFilePath + path + fileName;
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(cacheFilePath, path + fileName);
                /**
                 * 判断缓存是否存在文件
                 */
                if (!file.exists()) {
                    InputStream inputStream = null;
                    FileOutputStream output = null;
                    try {
                        URL uri = new URL(HttpUtil.BASEURL + urlStr);
                        // 3、获取连接对象、此时还没有建立连接
                        HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
                        connection.connect();
                        inputStream = connection.getInputStream();
                        /**
                         * 创建目录
                         */
                        file = new File(cacheFilePath + path);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        /**
                         * 创建文件
                         */
                        file = new File(newPath);
                        file.createNewFile();
                        output = new FileOutputStream(file);
                        byte[] buffer = new byte[4 * 1024];
                        while (inputStream.read(buffer) != -1) {
                            output.write(buffer);
                            output.flush();
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        return newPath;
    }

    public static String downFile(Context context, String urlStr, String fileName) {
        String cacheFilePath = context.getCacheDir().getAbsolutePath();
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(cacheFilePath, fileName);
                /**
                 * 判断缓存是否存在文件
                 */
                if (!file.exists()) {
                    InputStream inputStream = null;
                    FileOutputStream output = null;
                    try {
                        URL uri = new URL(urlStr);
                        // 3、获取连接对象、此时还没有建立连接
                        HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
                        connection.connect();
                        inputStream = connection.getInputStream();
                        /**
                         * 创建目录
                         */
                        file = new File(cacheFilePath);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        /**
                         * 创建文件
                         */
                        file = new File(cacheFilePath + fileName);
                        Log.i("fileName", fileName + "");
                        file.createNewFile();
                        output = new FileOutputStream(file);
                        byte[] buffer = new byte[4 * 1024];
                        while (inputStream.read(buffer) != -1) {
                            output.write(buffer);
                            output.flush();
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        return cacheFilePath + fileName;
    }

    /**
     * Save Bitmap
     *
     * @param bm picture to save
     */
    public static String saveBitmap(Context context, Bitmap bm, String path, String fileName) {
        String cacheFilePath = context.getCacheDir().getAbsolutePath();
        String newPath = cacheFilePath + path + "/" + fileName;
        File file = new File(cacheFilePath + path);
        if (!file.exists()) {
            file.mkdirs();
        }
        //判断文件是否存在
        File saveFile = new File(newPath);
        try {
            FileOutputStream saveImgOut = new FileOutputStream(saveFile);
            // compress - 压缩的意思
            bm.compress(Bitmap.CompressFormat.PNG, 80, saveImgOut);
            //存储完成后需要清除相关的进程
            saveImgOut.flush();
            saveImgOut.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return newPath;
    }

    public static Bitmap getLocalImage(String path) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(fis);
        return bitmap;
    }

    public static String getFileName(String url) {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        return fileName;
    }

    public static Bitmap getRes(String imageName, Context context) {
        ApplicationInfo appInfo = context.getApplicationInfo();
        int resID = context.getResources().getIdentifier(imageName, "drawable", appInfo.packageName);
        return BitmapFactory.decodeResource(context.getResources(), resID);
    }

}
