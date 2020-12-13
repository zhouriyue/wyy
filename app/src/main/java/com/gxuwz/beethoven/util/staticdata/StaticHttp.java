package com.gxuwz.beethoven.util.staticdata;

import com.gxuwz.beethoven.R;

public class StaticHttp {
    public static String HTTPURL = "https://www.zhou.website/wyy-0.0.1-SNAPSHOT/sysUserRest/zhouriyue";
    //public static String HTTPURL = "http://10.0.2.2:8082/wyy-0.0.1-SNAPSHOT/sysUserRest/zhouriyue";//本地服务器

    /** 协议 **/
    public static final String USER_ID = "userId";      //用户编号
    public static final String PLAY_MODEL = "play_model"; //播放模式 0:循环播放 1:随机 2：单曲
    public static final String CHECK_BOX = "check_box";   //协议勾选状态
    public static final String DATA = "data";             //数据
    public static final String IS_AUTH = "is_auth";       //一键登录授权状态
    public static final String LONGIN_STATUS = "loginStatus";  //登录状态
    public static final String IS_REFRE_SONGLIST = "isRefreSonglist";  //是否更新歌单列表

    //public static final String IP = "192.168.137.1";
    public static final String IP = "10.0.2.2";
    public static final String POST = "8080";
    public static final String REQUEST_TYPE = "http";

    public static final String FDFS_PATH = "http://47.114.190.44";

    //基础路径
    public static final String BASEURL = REQUEST_TYPE+"://"+IP+":"+POST+"/business";
    //系统基础路径
    public static final String SYSTEM_BASEURL = REQUEST_TYPE+"://"+IP+":"+POST+"/system";
    //静态文件基础路径
    public static final String STATIC_BASEURL = FDFS_PATH;

    public static final String LOCAL_HOST = REQUEST_TYPE+"://"+IP+":"+POST;

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
    public static final String GET_SYSTEM = "/user/getSysUser/android";
    //获取用户详情信息
    public static final String GET_USER_DETAIL = "/user/getUserDetail/android";
    //注册
    public static final String REGISTER_USER = "/user/register/android";
    //校验用户名是否存在
    public static final String CHECK_USERNAME = "/user/checkUsername/android";
    /**
     * 会员
     */
    //获取会员信息
    public static final String GET_MEMBER = "/member/getMember/android";
    //获取华为账号信息
    public static final String GET_TOKEN = "/member/getToKen/android";
    /**
     * 歌单
     */
    //获取歌单
    public static final String GET_SONGLIST = "/songlist/getUserSonglist/android";
    //歌曲歌单信息
    public static final String SONGLIST_FINDBYSLID = "/songlist/findBySlId/android";
    //添加歌单
    public static final String ADD_SONGLIST = "/songlist/add/android";
    //添加歌曲到歌单
    public static final String ADD_TO_SONGLIST = "/songlist/addToSonglist/android";
    //歌单默认图片
    public static final int DEFALUT_SONGLIST_COVERPICTURE = R.drawable.default_songlist_coverpicture_109951165349970832;
    public static final String ICON_VIP = LOCAL_HOST+"/static/icon/icon_vip.png";
    public static final String ICON_NOT = LOCAL_HOST+"/static/icon/icon_not.png";
    public static final String ICON_ALIPAY = LOCAL_HOST+"/static/icon/icon_alipay.png";
    public static final String ICON_WXPAY = LOCAL_HOST+"/static/icon/icon_wx.png";

    //public static String DEFALUT_SONGLIST_COVERPICTURE = STATIC_BASEURL+"/static/images/default_songlist_coverpicture_109951165349970832.jpg";
    //获取歌单的首个歌曲
    public static final String SELECT_INDEX_SONG = "/songlist/getIndexSong/android";
    //删除歌单的歌曲
    public static final String DELECT_SONGLIST_SONG = "/songlist/deleteSonglistSong/android";
    public static final String DELECT_SONGLIST = "/songlist/open/deleteSonglist";
    /**
     * 歌曲
     */
    public static final String SELECT_SONG = "/song/selectSongKey/android";
    public static final String SELECT_SONG_ALL = "/song/selectAll/android";
    public static final String SELECT_SINGER = "/song/selectSinger/android";
    public static final String SELECT_LYRIC = "/lyric/selectLyric/android";
    public static final String SELECT_SONGLIST_HOT = "/songlist/getHotSonglist/android";
    public static final String SELECT_TODAY_HOT = "/song/selectTodeyHot/android";

    /**
     * 收藏表
     */
    //是否收藏
    public static final String COLLECTIONES_ISCOLLECT = "/collectiones/isCollect/android";
    //保存
    public static final String COLLECTIONES_ADDAND = "/collectiones/addAnd/android";
    //获取收藏列表
    public static final String COLLECTIONES_ALL = "/collectiones/findAll/android";
    //取消收藏
    public static final String COLLECTIONES_CANCEL = "/collectiones/cancel/android";


    /**
     * 操作
     */
    public static final String SELECT_OPERATE_ALL = "/operate/findAll/android";
    public static final String SELECT_OPERATE_TYPE = "/operate/selectType/android";
    public static final String SELECT_OPERATE_AllTYPE = "/operate/findAllType/android";

    /**
     * 套餐
     */
    //获取套餐
    public static final String SELECT_SETMEAL = "/set_meal/selectSetMeal/android";

    /**
     * 一键登录
     */
    public static final String SELECT_OPENID = "/openid/findByOpenId/android";

    /**
     * 发现页
     */
    public static final String SELCET_FIND = "/find/selectFind/android";
    //排行榜
    public static final String RANKINGLIST_GETHOTRANKINGLIST = "/rankinglist/getHotRankingList/android";
    //官方榜
    public static final String GETOFFRANKINGLIST = "/rankinglist/getOffRankingList/android";
    //全球榜
    public static final String GETNOTOFF = "/rankinglist/getNotOff/android";
}
