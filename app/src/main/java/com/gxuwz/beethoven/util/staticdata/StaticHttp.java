package com.gxuwz.beethoven.util.staticdata;

import com.gxuwz.beethoven.R;

public class StaticHttp {
    public static String HTTPURL = "https://www.zhou.website/wyy-0.0.1-SNAPSHOT/sysUserRest/zhouriyue";
    //public static String HTTPURL = "http://10.0.2.2:8082/wyy-0.0.1-SNAPSHOT/sysUserRest/zhouriyue";//本地服务器

    public static String IP = "192.168.137.1";
    public static String POST = "8080";
    public static String REQUEST_TYPE = "http";

    //基础路径
    public static String BASEURL = REQUEST_TYPE+"://"+IP+":"+POST+"/business";
    //系统基础路径
    public static String SYSTEM_BASEURL = REQUEST_TYPE+"://"+IP+":"+POST+"/system";
    //静态文件基础路径
    public static String STATIC_BASEURL = REQUEST_TYPE+"://"+IP+":"+POST;

    //服务器
    /*public static String IP = "www.zhou.website";
    public static String POST = "";
    public static String REQUEST_TYPE = "https";
    public static String PROJECT_NAME = "ruoyi-admin(1)";

    //基础路径
    public static String BASEURL = REQUEST_TYPE+"://"+IP+"/"+PROJECT_NAME+"/business";
    //系统基础路径
    public static String SYSTEM_BASEURL = REQUEST_TYPE+"://"+IP+"/"+PROJECT_NAME+"/system";
    //静态文件基础路径
    public static String STATIC_BASEURL = REQUEST_TYPE+"://"+IP+"/"+PROJECT_NAME;*/

    /**
     * 系统用户
     */
    //获取用户信息
    public static String GET_SYSTEM = "/user/getSysUser/android";
    //获取用户详情信息
    public static String GET_USER_DETAIL = "/user/getUserDetail/android";

    /**
     * 会员
     */
    //获取会员信息
    public static String GET_MEMBER = "/member/getMember/android";

    /**
     * 歌单
     */
    //获取歌单
    public static String GET_SONGLIST = "/songlist/getUserSonglist/android";
    //添加歌单
    public static String ADD_SONGLIST = "/songlist/add/android";
    //添加歌曲到歌单
    public static String ADD_TO_SONGLIST = "/songlist/addToSonglist/android";
    //歌单默认图片
    public static int DEFALUT_SONGLIST_COVERPICTURE = R.drawable.default_songlist_coverpicture_109951165349970832;
    public static String ICON_VIP = STATIC_BASEURL+"/static/icon/icon_vip.png";
    public static String ICON_NOT = STATIC_BASEURL+"/static/icon/icon_not.png";
    public static String ICON_ALIPAY = STATIC_BASEURL+"/static/icon/icon_alipay.png";
    public static String ICON_WXPAY = STATIC_BASEURL+"/static/icon/icon_wx.png";

    //public static String DEFALUT_SONGLIST_COVERPICTURE = STATIC_BASEURL+"/static/images/default_songlist_coverpicture_109951165349970832.jpg";
    //获取歌单的首个歌曲
    public static String SELECT_INDEX_SONG = "/songlist/getIndexSong/android";
    //删除歌单的歌曲
    public static String DELECT_SONGLIST_SONG = "/songlist/deleteSonglistSong/android";
    /**
     * 歌曲
     */
    public static String SELECT_SONG = "/song/selectSongKey/android";
    public static String SELECT_SONG_ALL = "/song/selectAll/android";
    public static String SELECT_SINGER = "/song/selectSinger/android";
    public static String SELECT_LYRIC = "/lyric/selectLyric/android";

    /**
     * 操作
     */
    public static String SELECT_OPERATE_ALL = "/operate/findAll/android";
    public static String SELECT_OPERATE_TYPE = "/operate/selectType/android";
    public static String SELECT_OPERATE_AllTYPE = "/operate/findAllType/android";

    /**
     * 套餐
     */
    //获取套餐
    public static String SELECT_SETMEAL = "/set_meal/selectSetMeal/android";
}
