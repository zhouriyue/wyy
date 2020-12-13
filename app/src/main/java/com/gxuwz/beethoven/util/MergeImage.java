package com.gxuwz.beethoven.util;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;
/*Glide.with(SongListFActivity.this)
        .asBitmap()
        .load(StaticHttp.STATIC_BASEURL+songlist.getCoverPicture())
        .into(new SimpleTarget<Bitmap>() {
@Override
public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
        Drawable drawable = new BitmapDrawable(BlurUtil.doBlur(MergeImage.zoomImgDB(resource, (int) WindowPixels.WIDTH,290),1,30));
        diagonal.setBackground(drawable);
        }

        });*/
public class MergeImage {

    public static ObjectAnimator animator;

    public static ObjectAnimator playImage(View view) {
        //第一个参数是需要旋转的View，
        // 第二个是动画类型（包括alpha/rotation/scale/translate），
        // 第三个参数是旋转开始时的角度
        //第四个参数是旋转结束时的角度
        animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360.0f);
        animator.setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());//匀速
        animator.setRepeatCount(-1);//设置动画重复次数（-1代表一直转）
        animator.setRepeatMode(ValueAnimator.RESTART);//动画重复模式
        animator.start();
        return animator;
    }

    //使图片透明的方法
    public static Bitmap transparent(Bitmap bitmap) {
        bitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_4444);
        bitmap.eraseColor(Color.TRANSPARENT);
        return bitmap;
    }

    /**
     * 高斯模糊
     * @param context
     * @param url
     * @param imageView
     * @param scaleRatio
     * @param blurRadius
     */
    public static void doBlur(Context context,String url,ImageView imageView,int scaleRatio,int blurRadius){
        Glide.with(context)
                .load(url)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(blurRadius,scaleRatio)))
                .into(imageView);
    }

    public static void doBlur(Context context,int url,ImageView imageView,int scaleRatio,int blurRadius){
        Glide.with(context)
                .load(url)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(blurRadius,scaleRatio)))
                .into(imageView);
    }

    public static void doBlur(Context context,String url,int error,ImageView imageView,int scaleRatio,int blurRadius){
        Glide.with(context)
                .load(url)
                .centerCrop()
                .error(error)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(blurRadius,scaleRatio)))
                .into(imageView);
    }

    /**
     * 使用Glide渲染图片
     * @param context
     * @param url
     * @param imageView
     * @param width
     * @param height
     * @param redius
     */
    public static void showGlideImg(Context context, String url, ImageView imageView,int width,int height,int redius){
        //RoundedCorners roundedCorners= new RoundedCorners(10);
        //通过RequestOptions扩展功能,override采样率,压缩图片,降低内存消耗
        RequestOptions myOptions = new RequestOptions().transform(new MultiTransformation<>(new CenterCrop(),new RoundedCorners(redius)));
        //RequestOptions options = RequestOptions.transform(new CenterCrop(),roundedCorners));
        Glide.with(context)
                .load(url)
                .transition(new DrawableTransitionOptions().crossFade())
                .centerCrop()
                .apply(myOptions)
                .into(imageView);
    }

    public static void showGlideImgDb(Context context, String url, ImageView imageView,int redius){
        redius = (int) (redius * WindowPixels.DENSITY);
        RequestOptions myOptions = new RequestOptions().transform(new MultiTransformation<>(new CenterCrop(),new RoundedCorners(redius)));
        Glide.with(context)
                .load(url)
                .transition(new DrawableTransitionOptions().crossFade())
                .centerCrop()
                .apply(myOptions)
                .into(imageView);
    }

    public static void showGlideImgDb(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(imageView);
    }

    public static void showGlideImgDb(Context context, Bitmap bitmap, ImageView imageView){
        Glide.with(context)
                .load(bitmap)
                .centerCrop()
                .into(imageView);
    }

    public static void showGlideImgDb(Context context, int url,int error, ImageView imageView,int redius){
        redius = (int) (redius * WindowPixels.DENSITY);
        RequestOptions myOptions = new RequestOptions().transform(new MultiTransformation<>(new CenterCrop(),new RoundedCorners(redius)));
        Glide.with(context)
                .load(url)
                .transition(new DrawableTransitionOptions().crossFade())
                .error(error)
                .centerCrop()
                .apply(myOptions)
                .into(imageView);
    }

    public static void showGlideImgDb(Context context, String url,int error, ImageView imageView,int redius){
        redius = (int) (redius * WindowPixels.DENSITY);
        RequestOptions myOptions = new RequestOptions().transform(new MultiTransformation<>(new CenterCrop(),new RoundedCorners(redius)));
        Glide.with(context)
                .load(url)
                .transition(new DrawableTransitionOptions().crossFade())
                .error(error)
                .centerCrop()
                .apply(myOptions)
                .into(imageView);
    }

    public static void showGlideImgDb(Context context, int url, ImageView imageView,int redius){
        redius = (int) (redius * WindowPixels.DENSITY);
        RequestOptions myOptions = new RequestOptions().transform(new MultiTransformation<>(new CenterCrop(),new RoundedCorners(redius)));
        Glide.with(context)
                .load(url)
                .transition(new DrawableTransitionOptions().crossFade())
                .centerCrop()
                .apply(myOptions)
                .into(imageView);
    }

    public static void glideWhinkTop(Context context,int url,ImageView imageView,int redius){
        CornerTransform transformation = new CornerTransform(context, dip2px(context, redius));
        //只是绘制左上角和右上角圆角
        transformation.setExceptCorner(false, false, true, true);

        Glide.with(context)
                .load(url)
                .skipMemoryCache(true)
                .placeholder(R.drawable.bg)
                .error(StaticHttp.DEFALUT_SONGLIST_COVERPICTURE)
                .transform(transformation)
                .into(imageView);
    }

    public static void glideWhinkBottm(Context context,int url,ImageView imageView,int redius){
        CornerTransform transformation = new CornerTransform(context, dip2px(context, redius));
        //只是绘制左上角和右上角圆角
        transformation.setExceptCorner(true, true, false, false);

        Glide.with(context)
                .asBitmap()
                .load(url)
                .skipMemoryCache(true)
                .placeholder(R.drawable.bg)
                .error(StaticHttp.DEFALUT_SONGLIST_COVERPICTURE)
                .transform(transformation)
                .into(imageView);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 使用Glide渲染图片
     * @param context
     * @param url
     * @param imageView
     * @param width
     * @param height
     */
    public static void showGlideImg(Context context, String url, ImageView imageView,int width,int height){
        //RoundedCorners roundedCorners= new RoundedCorners(10);
        //通过RequestOptions扩展功能,override采样率,压缩图片,降低内存消耗
        RequestOptions myOptions = new RequestOptions().transform(new MultiTransformation<>(new CenterCrop(),new RoundedCorners(10)));
        //RequestOptions options = RequestOptions.transform(new CenterCrop(),roundedCorners));
        Glide.with(context)
                .load(url)
                .transition(new DrawableTransitionOptions().crossFade())
                .centerCrop()
                .apply(myOptions)
                .into(imageView);
    }

    /**
     * 使用Glide渲染图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void showGlideImg(Context context, String url, ImageView imageView){
        //RoundedCorners roundedCorners= new RoundedCorners(10);
        //通过RequestOptions扩展功能,override采样率,压缩图片,降低内存消耗
        RequestOptions myOptions = new RequestOptions().transform(new MultiTransformation<>(new CenterCrop(),new RoundedCorners(10)));
        //RequestOptions options = RequestOptions.transform(new CenterCrop(),roundedCorners));
        Glide.with(context)
                .load(url)
                .transition(new DrawableTransitionOptions().crossFade())
                .centerCrop()
                .apply(myOptions)
                .into(imageView);
    }

    public static void showGlideImg(Context context, int url, ImageView imageView){
        //RoundedCorners roundedCorners= new RoundedCorners(10);
        //通过RequestOptions扩展功能,override采样率,压缩图片,降低内存消耗
        RequestOptions myOptions = new RequestOptions().transform(new MultiTransformation<>(new CenterCrop(),new RoundedCorners(10)));
        //RequestOptions options = RequestOptions.transform(new CenterCrop(),roundedCorners));
        Glide.with(context)
                .load(url)
                .transition(new DrawableTransitionOptions().crossFade())
                .centerCrop()
                .apply(myOptions)
                .into(imageView);
    }

    public static void getGlideBitmap(Context context,String url,Bitmap[] bitmaps,int i){
        Glide.with(context)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//缓存
                .load(url)
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        bitmaps[i] = resource;
                        System.out.println(resource);
                    }
                });
    }

    /**
     * 圆角显示图片
     * @param bitmap
     * @param roundPx
     * @return
     */
    public static Bitmap rounded(Bitmap bitmap,float roundPx){
        if(bitmap==null) return null;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        while (width>100) {
            width /= 2;
            height /= 2;
        }
        bitmap = Bitmap.createScaledBitmap(bitmap,width,height,true);
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width,height);
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 自定义圆角显示图片
     * 像素
     * @param bitmap
     * @return
     */
    public static Bitmap roundedCustom(Bitmap bitmap,int newWidth,int newHeight){
        if(bitmap==null) return null;
        int roundPx = (int)(newWidth * 0.05);
        bitmap = zoomImg(bitmap,newWidth,newHeight);
        bitmap = Bitmap.createScaledBitmap(bitmap,newWidth,newHeight,true);
        Bitmap output = bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, newWidth,newHeight);
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 自定义圆角显示图片
     * db
     * @param bitmap
     * @return
     */
    public static Bitmap roundedCustomDB(Bitmap bitmap,int newWidth,int newHeight){
        if(bitmap==null) return null;
        float radiu = 10*WindowPixels.DENSITY;
        newWidth = (int) (newWidth * WindowPixels.DENSITY);
        newHeight = (int) (newHeight * WindowPixels.DENSITY);
        bitmap = zoomImg(bitmap,newWidth,newHeight);
        bitmap = Bitmap.createScaledBitmap(bitmap,newWidth,newHeight,true);
        Bitmap output = bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, newWidth,newHeight);
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, radiu, radiu, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 自定义圆角显示图片
     * db
     * @param bitmap
     * @return
     */
    public static Bitmap roundedCustomDB(Bitmap bitmap,int newWidth,int newHeight,int radiu){
        if(bitmap==null) return null;
        float newRadiu = radiu*WindowPixels.DENSITY;
        newWidth = (int) (newWidth * WindowPixels.DENSITY);
        newHeight = (int) (newHeight * WindowPixels.DENSITY);
        radiu = (int) (radiu * WindowPixels.DENSITY);
        bitmap = zoomImg(bitmap,newWidth,newHeight);
        bitmap = Bitmap.createScaledBitmap(bitmap,newWidth,newHeight,true);
        Bitmap output = bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, newWidth,newHeight);
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, newRadiu, newRadiu, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 圆角显示图片
     * @param bitmap
     * @return
     */
    public static Bitmap roundedCustom(Bitmap bitmap){
        if(bitmap==null) return null;
        bitmap = zoomImg(bitmap);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int roundPx = (int)(width * 0.05);
        bitmap = Bitmap.createScaledBitmap(bitmap,width,height,true);
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width,height);
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 圆形显示图片
     * @param showBitmap
     * @return
     */
    public static Bitmap circleShow(Bitmap showBitmap) {
        //获得黑胶碟片底图宽和高
        if(showBitmap==null) return null;
        showBitmap = zoomImg(showBitmap);
        int w = showBitmap.getWidth();
        int h = showBitmap.getHeight();
        //根据黑胶碟片底图的宽和高，对专辑图片进行缩放
        showBitmap = Bitmap.createScaledBitmap(showBitmap, w, h, true);
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //这里需要先画出一个圆
        canvas.drawCircle(w / 2, h / 2, w/2, paint);
        //圆画好之后将画笔重置一下
        paint.reset();
        //设置图像合成模式，该模式为只在源图像和目标图像相交的地方绘制源图像
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(showBitmap, 0, 0, paint);
        paint.reset();
        return bm;
    }

    /**
     * 合成碟片图片
     *
     * @param discBitmap  黑胶碟片底图
     * @param albumBitmap 专辑封面图
     * @return
     */
    public static Bitmap mergeThumbnailBitmap(Bitmap discBitmap, Bitmap albumBitmap) {
        if(discBitmap==null||albumBitmap==null) return null;
        //获得黑胶碟片底图宽和高
        int w = discBitmap.getWidth();
        int h = discBitmap.getHeight();
        //根据黑胶碟片底图的宽和高，对专辑图片进行缩放
        albumBitmap = Bitmap.createScaledBitmap(albumBitmap, w, h, true);
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //这里需要先画出一个圆
        canvas.drawCircle(w / 2, h / 2, w / 2 - 20, paint);
        //圆画好之后将画笔重置一下
        paint.reset();
        //设置图像合成模式，该模式为只在源图像和目标图像相交的地方绘制源图像
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(albumBitmap, 0, 0, paint);
        paint.reset();
        canvas.drawBitmap(discBitmap, 0, 0, null);
        return bm;
    }

    // 等比缩放图片
    public static Bitmap zoomImgDB(Bitmap bm, int newWidth ,int newHeight){
        // 获得图片的宽高
        newWidth = (int) (newWidth * WindowPixels.DENSITY);
        newHeight = (int) (newHeight * WindowPixels.DENSITY);
        int x=0,y=0;
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scale = (float) (newWidth*1.0/width*1.0);
        float widthScale = scale*height;
        if(widthScale<newHeight) {
            scale = (float) (newHeight*1.0/height*1.0);
            x = (int) ((scale*width-newWidth)/2);
        }
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        //截取
        newbm = Bitmap.createBitmap(newbm,x,y,newWidth,newHeight);
        return newbm;
    }

    // 等比缩放图片
    public static Bitmap zoomImg(Bitmap bm, int newWidth ,int newHeight){
        // 获得图片的宽高
        int x=0,y=0;
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scale = (float) (newWidth*1.0/width*1.0);
        float widthScale = scale*height;
        if(widthScale<newHeight) {
            scale = (float) (newHeight*1.0/height*1.0);
            x = (int) ((scale*width-newWidth)/2);
        }
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        //截取
        newbm = Bitmap.createBitmap(newbm,x,y,newWidth,newHeight);
        return newbm;
    }

    // 等比缩放图片
    public static Bitmap zoomImg(Bitmap bm){
        int x=0,y=0;
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        int newWh = Math.min(width,height);
        //截取
        if(width<height) {
            y = (height - width)/2;
        } else {
            x = (width - height)/2;
        }
        Bitmap newbm = Bitmap.createBitmap(bm,x,y,newWh,newWh);
        return newbm;
    }
}
