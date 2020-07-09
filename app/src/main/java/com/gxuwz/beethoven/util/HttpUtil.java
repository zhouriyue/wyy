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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {

    public static void get(String httpUrl, Handler handler) {
        //子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(httpUrl);
                    //强制转换
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    //给连接指定请求的方式
                    conn.setRequestMethod("GET");
                    //默认是毫秒，所以5秒
                    conn.setConnectTimeout(5000);
                    int code = conn.getResponseCode();
                    if(code == 200){
                        //获取服务器返回的内容，而且通过输入流的方式得到
                        InputStream in = conn.getInputStream();
                        //判断输入流有多少可以读的字节
                        //int i = in.available();
                        //定义字节数组接受回来的数据
                        //byte[] b = new byte[1000];
                        //将输入存入字节数组
                        //in.read(b);
                        //先把字节数组转换成字符串
                        String result = IOUtils.toString(in);
                        //把子线程数据回传到主线程
                        Message msg = new Message();
                        //构建bundler对象，并且把数据给到bundler
                        Bundle bundle = new Bundle();
                        bundle.putString("result",result);
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

    public static Bitmap getImage(String UrlPath) {
        Bitmap bm = null;
        String urlpath = UrlPath;
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bm;
    }

    public static Bitmap getRes(String imageName, Context context) {
        ApplicationInfo appInfo = context.getApplicationInfo();
        int resID = context.getResources().getIdentifier(imageName, "drawable", appInfo.packageName);
        return BitmapFactory.decodeResource(context.getResources(), resID);
    }

}
