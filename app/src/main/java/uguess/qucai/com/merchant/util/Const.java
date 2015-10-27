package uguess.qucai.com.merchant.util;

import android.os.Environment;

import uguess.qucai.com.merchant.QucaiApplication;


public class Const {

    /**
     * For 友盟事件统计
     */

    public static class MobclickAgent {
        //新增行程
        public static final String EVENT_ADD_TRIP = "add_trip";
        //新增动态
        public static final String EVENT_ADD_ACTIVITY = "add_activity";
        //进入聊天
        public static final String EVENT_BEGIN_CHAT = "begin_chat";
        //public static final String EVENT_IMAGE_UPLOAD = "upload_image";
        //摇一摇
        public static final String EVENT_SHAKE_BENEFIT = "shake_benefit";
        //转发福利
        public static final String EVENT_TRANS_BENEFIT = "trans_benefit";
        //求福利
        public static final String EVENT_BEG_BENEFIT = "beg_benefit";
        //分享动态
        public static final String EVENT_SHARE_ACTIVITY = "share_activity";
        //赞
        public static final String EVENT_LIKE_ACTIVITY = "like_activity";
        //取消赞
        public static final String EVENT_UNLIKE_ACTIVITY = "unlike_activity";
    }


    /**
     * startyActivityForResult中的request code
     */
    public static class RequestCode {

        // 从相册获取照片
        public static final int PhotoFromAlbum = 1000;

        // 拍照获取照片
        public static final int PhotoFromCamera = 1001;

        // 剪裁照片
        public static final int PhotoCrop = 1002;

        // 地图位置选择
        public static final int Location = 1003;

        // 充值猜豆
        public static final int RECHARGE_REQUEST_CODE = 9527;

    }

    /**
     * intent中的extra的key
     */
    public static class Intent {

        public static final String FRIEND = "friend";
        /**
         * 用户id的KEY
         */
        public static final String USER_ID = "user_id";
        /**
         * 充值钻石
         */
        public static final String CHARGE_SOURCE_DIAMOND = "source_diamond";
        /**
         * 充值猜豆
         */
        public static final String CHARGE_SOURCE_BEANS = "source_beans";
        /**
         * 我的资源
         */
        public static final String SOURCE_RESOURCE = "resource";
        /**
         * 我的卡券
         */
        public static final String SOURCE_COUPON = "coupon";
        /**
         * 卡券商品
         */
        public static final String GOODS_COUPON = "goods_coupon";

        /**
         * MainActivity所需的登陆状态标示
         */
        public static final String IS_LOGIN = "is_login";

        /**
         * 针对不同页有不同的含义
         */
        public static final String Type = "type";
        /**
         * 通过intent传递商品信息
         */
        public static final String GOODS = "goods";
        /**
         * 通过intent传递用户的财产信息
         */
        public static final String ASSETS = "assets";
        /**
         * 粉丝列表
         */
        public static final int SOURCE_FANS = 2;
        /**
         * 关注者列表
         */
        public static final int SOURCE_FOLLOWER = 1;
        /**
         * 用户头像URL
         */
        public static final String PORTRAIT_URL = "user_portrait_url";

        /**
         * 迁移到OriginalPicture页面的图片URLKey
         */
        public static final String ORIGINAL_PICTURE_URL_KEY = "original.picture.url";

        public static final String UPDATE_USER_INFORMATION_RESULT = "result_value";
        public static final String UPDATE_USER_INFORMATION_TYPE = "update_type";
        public static final String UPDATE_USER_INFORMATION_VALUE = "update_value";
        public static final String UPDATE_USER_INFORMATION_TITLE = "update_title";


        /**
         * 相册下标Key
         */
        public static final String ALBUM_ITEM_INDEX_KEY = "index";

        /**
         * 相册列表Key
         */
        public static final String ALBUM_LIST_KEY = "photos";

        /**
         * 更新密码来源key
         */
        public static final String UPDATE_PASSWORD_RESOURCE = "update_password_resource";
        /**
         * 猜的摄像头图片Key
         */
        public static final String GUESS_CAMERA_KEY = "guess_camera_key";
        /**
         * 猜的相册图片Key
         */
        public static final String GUESS_IMAGE_KEY = "guess_image_key";
        /**
         * 竞猜题目的内容key
         */
        public static final String GUESS_QUESTION_CONTENT_KEY = "guess_question_content_key";
        /**
         * 竞猜的话题ID key
         */
        public static final String GUESS_QUESTION_TOPIC_ID_KEY = "guess_question_topic_id_key";
        /**
         * 竞猜题目的图片key
         */
        public static final String GUESS_QUESTION_PICTURE_KEY = "guess_question_picture_key";
        /**
         * 竞猜截止时间key
         */
        public static final String GUESS_DEADLINE_KEY = "guess_deadline_key";
        /**
         * 竞猜答案key
         */
        public static final String GUESS_ANSWERS_KEY = "guess_answers_key";

        /**
         * 查询类型key
         */
        public static final String QUERY_TYPE_KEY = "guess_type_key";

        /**
         * 查询参与类型key
         */
        public static final String QUERY_JOIN_TYPE_KEY = "guess_join_type_key";

        /**
         * 悄悄猜类型key
         */
        public static final String SECRET_GUESS_KEY = "secret_guess_key";

        /**
         * 点击序号key
         */
        public static final String INDEX_KEY = "index_key";
        /**
         * 竞猜ID的key
         */
        public static final String QUERY_GUESS_ID_KEY = "guess_id_key";

        /**
         * 竞猜详情key
         */
        public static final String GUESS_DETAIL_KEY = "guess_detail_key";

        /**
         * 竞猜评论key
         */
        public static final String GUESS_COMMENT_KEY = "guess_comment_key";
        /**
         * 竞猜真相到添加好友页面Key
         */
        public static final String PRICE_TO_FRIEND_KEY = "price_to_friend_key";
        /**
         * 添加好友到竞猜真相页面Key
         */
        public static final String FRIEND_TO_PRICE_KEY = "price_to_friend_key";
        /**
         * 查询竞猜列表类型的key
         */
        public static final String GUESS_SEARCH_KEY = "guess_search_key";

        /**
         * 结果确认的key
         */
        public static final String TO_USER_CONFIRM_KEY = "to_user_confirm_key";

        /**
         * 结果确认奖励限定数的key
         */
        public static final String TO_USER_CONFIRM_PRICE_NUM_KEY = "to_user_confirm_price_num_key";

        /**
         * 迁移到用户详细信息页面的用户详细信息Key
         */
        public static final String USER_DETAIL_KEY = "com.qicheng.business.module.UserDetail";
        /**
         * 任务类型Key
         */
        public static final String TASK_TYPE_KEY = "task_type_key";

        /**
         * 消息类型Key
         */
        public static final String MESSAGE_TYPE_KEY = "message_type_key";

        /**
         * 消息详细信息类型Key
         */
        public static final String MESSAGE_DETAIL_KEY = "message_detail_key";

        /**
         * 忘记密码
         */
        public static final String FORGET_PASSWORD = "forgetPassword";
        /**
         * 评论条数
         */
        public static final String COMMENT_NUM_KEY = "comment_num";
        /**
         * 搜索关键字
         */
        public static final String KEY_WORD = "search_keyword";

        /**
         * 二维码字符串
         */
        public static final String COMMON_QRCODE_STRING = "common_qrcode_string";
        /**
         * 选取话题的key
         */
        public static final String CHOOSE_TOPIC_KEY = "choose_topic_key";
        /**
         * 零钱余额
         */
        public static final String BONUS_HISTORY = "bonus_history";
        /**
         * 支付信息
         */
        public static final String PAY_INFO = "pay_info";
        /**
         * 支付来源
         */
        public static final String PAY_SOURCE = "pay_source";

        /**
         * 能否看答案
         */
        public static final String CAN_SEE_ANSWER = "can_see_answer";
        /**
         * 能否看中奖结果
         */
        public static final String CAN_SEE_REWARD = "can_see_reward";
        /**
         * 支付密码加密串
         */
        public static final String ENCRYPY_KEY = "pay_encrypt";
        /**
         * 猜豆数目
         */
        public static final String BEANS_NUM = "beans_num";
        /**
         * 证据的KEY
         */
        public static final String PROF_KEY = "prof";

        /**
         * 加密串
         */
        public static final String ENCRYPY = "encrypt";

        /**
         * 支付密码Key
         */
        public static final String PAY_PWD_KEY = "pay_pwd";


        public static final String GUIDE_ADD = "guide_add";
        public static final String GUIDE_SLIDE = "guide_slide";
        public static final String GUIDE_MALL = "guide_mall";
        public static final String GUIDE_SQUARE = "guide_square";
        public static final String GUIDE_MINE = "guide_mine";
        public static final String GUIDE_GUESS_JOIN = "guide_guess_join";
        public static final String GUIDE_GUESS_PUBLISH = "guide_guess_publish";
    }

    /**
     * intentFlag 标志
     */
    public static class IntentFlag {
        /**
         * 单例模式intent
         */
        public static final int SINGLE_INTENT = 1001;
        /**
         * 非单例模式intent
         */
        public static final int NOT_SINGLE_INTENT = 1002;
    }

    /**
     * 默认的值
     */
    public static class DefaultValue {
        public static final int Age = -1;
        public static final int Time = -1;
        public static final int Distance = -1;
    }

    /**
     * SharePerformance的Key
     */
    public static class SharedPreferenceKey {

        public static final String UserPreference = "user";

        public static final String CHECK_GUIDE_PREFERENCE = "check_guide";
    }

    public static QucaiApplication Application = null;

    // 调试
    public static final boolean FakeProtocol = false;

    public static final String WorkDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/QuCai/";

    //服务端接口地址
//    public static final String BASE_URL = "http://test.qucai8.com:8080";
    public static final String BASE_URL = "http://www.uguess.me:8080";
//    public static final String BASE_URL = "http://192.168.1.127:8080/qps";

    // 地图缩放
    public static final int MapZoom = 16;
    public static final int PoiResultCount = 25;
    public static final int PoiSearchRaduis = 5 * 1000;
    /**
     * time
     */
    public static final int MessageCountInterval = 5;
    public static final int MessageChatInterval = 10;

    /**
     * 两次点击back退出应用的最小时间间隔
     */
    public static final int ExitAppClickBackMinInterval = 2000;

    public static final int ADD_FRIEND_TO_GUESS = 3;


    /**
     * 照片下载最大失败次数
     */
    public static final int PhotoMaxTryTime = 3;

    /**
     * Activity ResultCode
     */
    public static class ActivityResultCode {
        /**
         * 响应结果码 1:成功
         */
        public static final int RESULT_SUCCESS = 1;

        /**
         * 响应结果码 -1:失败
         */
        public static final int RESULT_FAIL = -1;
    }

    public static class PayConst {
        /**
         * 发送好友猜的红包
         */
        public static final int PAY_FOR_FRIEND_GUESS = 0;
        /**
         * 发送粉丝猜的红包
         */
        public static final int PAY_FOR_FAN_GUESS = 1;
        /**
         * 充值钻石
         */
        public static final int PAY_FOR_CHARGE_DIAMOND = 2;
        /**
         * 商城充值钻石
         */
        public static final int PAY_FOR_CHARGE_DIAMOND_STORE = 3;
        /**
         * 支付成功
         */
        public static final int PAY_RESULT_SUCCESS = 4;
        /**
         * 支付失败
         */
        public static final int PAY_RESULT_FAILURE = 5;
    }

    /**
     * 服务端响应结果代码常量类
     */
    public static class ResponseResultCode {

        /**
         * 响应结果码 9999:内部异常
         */
        public static final int RESULT_EXCEPTION = 9999;

        /**
         * 响应结果码 0:成功
         */
        public static final int RESULT_SUCCESS = 0;

        /**
         * 响应结果码 1:失败
         */
        public static final int RESULT_FAIL = 1;

        /**
         * 响应结果码 2:非法调用
         */
        public static final int RESULT_ILLEGAL_CALL = 2;

        /**
         * 响应结果码 3:未登录
         */
        public static final int RESULT_NOT_LOGINED = 3;

        /**
         * 响应结果码 4:登录超时，请重登录
         */
        public static final int RESULT_LOGIN_TIMEOUT = 4;

        /**
         * 响应结果码 5:用户名为空
         */
        public static final int RESULT_USER_NAME_EMPTY = 5;

        /**
         * 响应结果码 6:用户密码为空
         */
        public static final int RESULT_PWD_EMPTY = 6;

        /**
         * 响应结果码 7:手机号码已存在
         */
        public static final int RESULT_CELL_NUM_EXIST = 7;

        /**
         * 响应结果码 8:手机号码不存在
         */
        public static final int RESULT_CELL_NUM_NOT_EXIST = 8;

        /**
         * 响应结果码 9:验证码错误
         */
        public static final int RESULT_VERIFY_CODE_ERROR = 9;

        /**
         * 响应结果码 10:验证码过期
         */
        public static final int RESULT_VERIFY_CODE_INVALID = 10;

        /**
         * 响应结果码 11:用户密码错误
         */
        public static final int RESULT_PWD_ERROR = 11;

        /**
         * 响应结果码 12:用户名不存在
         */
        public static final int RESULT_USER_NAME_NOT_EXIST = 12;

        /**
         * 响应结果码 13:该用户已无效
         */
        public static final int RESULT_USER_NAME_INVALID = 13;

        /**
         * 响应结果码 14:昵称已存在
         */
        public static final int RESULT_NICKNAME_EXIST = 14;

        /**
         * 响应结果码 15:没有响应数据，即响应体(body)不存在
         */
        public static final int RESULT_BODY_IS_NOT_EXIST = 15;

        /**
         * 响应结果码 16:没有权限
         */
        public static final int RESULT_NOT_PERMIT = 16;

        /**
         * 响应结果码 17:设备ID为空
         */
        public static final int RESULT_DEVICE_ID_IS_NULL = 17;

        /**
         * 响应结果码 18:没有争抢到派发福利机会，请重试
         */
        public static final int RESULT_NO_GRAB = 18;

        /**
         * 响应结果码 19:福利已派发完了
         */
        public static final int RESULT_DISTRIBUTE_FINISHED = 19;
        /**
         * 响应结果码20：猜豆余额不足
         */
        public static final int RESULT_SCORE_FINISHED = 20;
        /**
         * 响应结果码21：红包余额不足
         */
        public static final int RESULT_BONUS_FINISHED = 21;
        /**
         * 响应结果码22：已经是好友关系
         */
        public static final int RESULT_HAD_BEEN_FRIEND = 22;
        /**
         * 响应结果码23：钻石余额不足
         */
        public static final int RESULT_DIAMOND_FINISHED = 23;
        /**
         * 响应结果码24：商品已售罄
         */
        public static final int RESULT_GOODS_NULL = 24;
        /**
         * 此商品不可重复购买
         */
        public static final int RESULT_GOODS_REPEAT = 25;

        /**
         * 未设置支付密码
         */
        public static final int RESULT_PAY_PWD_UNSET = 26;
        /**
         * 支付密码错误
         */
        public static final int RESULT_PAY_PWD_WRONG = 27;
        /**
         * 参与名额已争抢完毕
         */
        public static final int JOIN_NUM_IS_OVER = 28;

        /**
         * 身份验证过期，需重新登录
         */
        public static final int RESULT_AUTH_EXPIRED = 29;
        /**
         * 用户充值账户余额不足
         */
        public static final int USER_PAYMENT_BALANCE_NOT_INSUFFICIENT = 30;
        /**
         * 充值交易号不存在
         */
        public static final int RESULT_PAY_TRADENO_NOT_EXIST = 31;
        /**
         * 用户支付中
         */
        public static final int RESULT_PAY_IN_PROGRESS = 32;
        /**
         * 未支付
         */
        public static final int RESULT_PAY_UNDO = 33;
        /**
         * 支付已关闭
         */
        public static final int RESULT_PAY_CLOSED = 34;
        /**
         * 支付已撤销
         */
        public static final int RESULT_PAY_CANCEL = 35;
        /**
         * 支付已转入退款
         */
        public static final int RESULT_PAY_REBACK = 36;
        /**
         * 支付平台系统异常，请再发起查询
         */
        public static final int RESULT_PAY_REQUERY = 37;

        public static final int RESULT_TICKET_NOT_EXIST = 40;

        public static final int RESULT_TICKET_ALREADY_USED = 41;

        public static final int RESULT_TICKET_EXPIRED = 42;

        public static final int RESULT_TICKET_NO_PERMISION = 43;

    }


    /**
     * 查询方向 0：往最新方向查询
     */
    public static final byte ORDER_BY_NEWEST = 0;

    /**
     * 查询方向 1：往最早方向查询
     */
    public static final byte ORDER_BY_EARLIEST = 1;

    /**
     * 图片加载器的滚动状态标识
     */
    public static final String STATE_PAUSE_ON_SCROLL = "STATE_PAUSE_ON_SCROLL";

    /**
     * 图片加载器的滑动状态标识
     */
    public static final String STATE_PAUSE_ON_FLING = "STATE_PAUSE_ON_FLING";


    /**
     * 互动操作 0：举报
     */
    public static final byte INTERACT_ACTION_REPORTED = 0;

    /**
     * 互动操作 1：忽略
     */
    public static final byte INTERACT_ACTION_IGNORE = 1;

    /**
     * 性别 -1:全部
     */
    public static final byte SEX_ALL = -1;

    /**
     * 性别 1:男
     */
    public static final byte SEX_MAN = 1;

    /**
     * 性别 0:女
     */
    public static final byte SEX_FEMALE = 0;

    /**
     * 更新用户个人信息枚举值
     */
    public static class UserUpdateCode {
        /**
         * 更新昵称
         */
        public static final int UPDATE_NICKNAME = 8;
        /**
         * 更新生日
         */
        public static final int UPDATE_BIRTHDAY = 1;
        /**
         * 更新头像
         */
        public static final int UPDATE_PORTRAIT_URL = 2;
        /**
         * 更新家乡
         */
        public static final int UPDATE_HOMETOWN = 4;
        /**
         * 更新学历
         */
        public static final int UPDATE_EDUCATION = 5;
        /**
         * 更新行业
         */
        public static final int UPDATE_INDUSTRY = 6;
        /**
         * 更新居住地
         */
        public static final int UPDATE_RESIDENCE = 7;

    }

    /**
     * ID类型 0：用户ID
     */
    public static final byte ID_TYPE_USER_ID = 0;


    /**
     * 修改密码
     */
    public static final byte UPDATE_PWD_FROM_MODIFY = 0;
    /**
     * 忘记密码
     */
    public static final byte UPDATE_PWD_FROM_FORGET = 1;

    /**
     * 操作类型 0:注册
     */
    public static final byte ACTION_TYPE_REGISTER = 0;

    /**
     * 操作类型 1:找回密码
     */
    public static final byte ACTION_TYPE_FIND = 1;

    /**
     * 操作类型 2:修改手机号码
     */
    public static final byte ACTION_TYPE_MODIFY = 2;
    /**
     * 发布普通动态
     */
    public static final int DYN_PUBLISH_TYPE_PIC = 0;
    /**
     * 发布邀约动态
     */
    public static final int DYN_PUBLISH_TYPE_INVITE = 3;
    /**
     * 发布投票动态
     */
    public static final int DYN_PUBLISH_TYPE_VOTE = 4;

    public static final int FORGET_PASSWORD = 5;

    /**
     * 分享相关
     */
    public class ShareConst {
        /**
         * 微信
         */
        public static final int MODE_WX = 0;
        /**
         * 微博
         */
        public static final int MODE_WB = 1;
        /**
         * QQ
         */
        public static final int MODE_QQ = 2;
        /**
         * 正常通过手机号登录
         */
        public static final int MODE_NORMAL = 3;
        /**
         * 注册
         */
        public static final int REG = 4;
        /**
         * 微信信息
         */
        public static final int INFO_WX = 5;
        public static final int INFO_QQ = 6;
        public static final int INFO_WB = 7;
        public static final int INFO_LOGIN = 8;
        public static final int INFO_OTHER = 9;
        /**
         * 微信SDK
         */
        public static final String WX_APP_ID = "wxedd00cc7958add32";
        public static final String WX_MCH_ID = "1264073301";//微信支付商户号
        public static final String WX_APP_SECRET = "71761248a4e46942dddf21ba80ba277d";
        public static final String WX_REQUEST_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        public static final String WX_REQUEST_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        /**
         * 微博SDK
         */
        public static final String WB_APP_KEY = "1361477323";
        public static final String WB_REDIRECT_URL = "http://www.sina.com";
        public static final String WB_SCOPE = "email,direct_messages_read,direct_messages_write,"
                + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                + "follow_app_official_microblog," + "invitation_write";
        /**
         * QQ SDK
         */
        public static final String QQ_APP_KEY = "O0m54QV3J7q8ylRN";
        public static final String QQ_APP_ID = "1104771761";

        /**
         * 分享的链接
         */
        public static final String SHARE_BASE_URL = "http://www.uguess.me:8080/web";

        public static final String SHARE_HOME_URL = "http://www.qucai8.com";

    }

    /*  猜的图片名称 */
    public static final String IMAGE_FILE_NAME = "dynImage.jpg";

    /**
     * 用于竞猜问题
     */
    public static final int USE_FOR_QUESTION = 1;
    /**
     * 用于竞猜答案
     */
    public static final int USE_FOR_ANSWER = 2;

    public class GuessConst {
        /**
         * 待我参与的好友的竞猜
         */
        public static final int QUERY_FRIEND_GUESS = 1;
        /**
         * 待我参与的V星人的竞猜
         */
        public static final int QUERY_V_GUESS = 2;
        /**
         * 我参与的竞猜
         */
        public static final int QUERY_JOIN_GUESS = 3;
        /**
         * 我发布的竞猜
         */
        public static final int QUERY_PUBLISH_GUESS = 4;
        /**
         * 答案类型 0：文字
         */
        public static final int ANSWER_TYPE_TEXT = 0;
        /**
         * 答案类型 1：图片
         */
        public static final int ANSWER_TYPE_PIC = 1;

        /**
         * 答案类型 0：猜豆
         */
        public static final int GUESS_TYPE_GOLD = 0;
        /**
         * 答案类型 1：红包
         */
        public static final int GUESS_TYPE_MONEY = 1;
        /**
         * 答案类型 2：福利
         */
        public static final int GUESS_TYPE_BENEFIT = 2;
        /**
         * 答案类型 3：自定义
         */
        public static final int GUESS_TYPE_CUSTOM = 3;
        /**
         * 确认添加好友 0：未确认
         */
        public static final int ADD_FRIEND_CONFIRM_UNCONFIRM = 0;
        /**
         * 确认添加好友 1：同意
         */
        public static final int ADD_FRIEND_CONFIRM_AGREE = 1;
        /**
         * 确认添加好友 2：拒绝
         */
        public static final int ADD_FRIEND_CONFIRM_IGNORE = 2;

        /**
         * 竞猜答案类型     0：  普通类
         */
        public static final int GUESS_ANSWER_TYPE_COMMON = 0;
        /**
         * 竞猜答案类型    1： 枚举类
         */
        public static final int GUESS_ANSWER_TYPE_ENUM = 1;

        /**
         * 竞猜答案类型   0：举报
         */
        public static final int GUESS_ACTION_REPORTED = 0;

        /**
         * 竞猜答案类型   1：忽略
         */
        public static final int GUESS_ACTION_IGNORE = 1;
        /**
         * 竞猜类型 0:图文猜
         */
        public static final int GUESS_TYPE_TEXT_PIC = 0;
        /**
         * 竞猜类型 1:视频猜
         */
        public static final int GUESS_TYPE_VEDIO = 1;
        /**
         * 竞猜类型 2:语音猜
         */
        public static final int GUESS_TYPE_VOICE = 2;
        /**
         * 竞猜类型 3:游戏猜
         */
        public static final int GUESS_TYPE_GAME = 3;


        /**
         * 竞猜开放类型 0：面向所有好友
         */
        public static final int OPEN_MODE_ALL_FRIEND_GUESS = 0;

        /**
         * 竞猜开放类型 1：面向指定好友
         */
        public static final int OPEN_MODE_SECRET_GUESS = 1;

        /**
         * 竞猜开放类型 2：面向粉丝
         */
        public static final int OPEN_MODE_FANS_GUESS = 2;
        /**
         * 竞猜结束标识 0:未结束
         */
        public static final int GUESS_UNFINISHED = 0;
        /**
         * 竞猜结束标识 1：已结束
         */
        public static final int GUESS_FINISHED = 1;
        /**
         * 消息已读
         */
        public static final int MESSAGE_IS_READED = 1;

        /**
         * 竞猜结束标识 1：已确认
         */
        public static final int GUESS_CONFIRMED = 1;

        /**
         * 竞猜输赢标识 0:输
         */
        public static final int GUESS_LOSE = 0;
        /**
         * 竞猜输赢标识 1：赢
         */
        public static final int GUESS_WIN = 1;

        public static final int ANSWER_RIGHT = 1;
        /**
         * 任务完成
         */
        public static final int TASK_FINISHED = 0;
        /**
         * 钱包记录
         */
        public static final int PURSE_RECORD = 1;

        /**
         * 任务类型 0：新手任务
         */
        public static final int TASK_NEW_USER = 0;
        /**
         * 任务类型1：每日任务
         */
        public static final int TASK_EVERY_DAY = 1;
        /**
         * 任务类型2：每周任务
         */
        public static final int TASK_EVERY_WEEK = 2;
        /**
         * 任务类型3：成就任务
         */
        public static final int TASK_LONG_TIME = 3;
        /**
         * 任务类型4：拉新任务
         */
        public static final int TASK_INVITE = 4;
        /**
         * 消息类型 0：系统消息
         */
        public static final int MESSAGE_TYPE_SYSTEM = 0;
        /**
         * 消息类型 1：竞猜消息
         */
        public static final int MESSAGE_TYPE_GUESS = 1;
        /**
         * 消息类型 2：社交消息
         */
        public static final int MESSAGE_TYPE_SOCIAL = 2;
        /**
         * 答案为正确答案
         */
        public static final int ANSWER_IS_CORRECT = 1;
        /**
         * 已经参与了此竞猜
         */
        public static final int GUESS_IS_JOIN = 1;
        /**
         * 参与需要积分
         */
        public static final int JOIN_NEED_SCORE = 1;

    }

    ;

    /**
     * 用户好友
     */
    public static class UserContact {
        /**
         * 我的好友
         */
        public static final int USER_GROUP_FRIENDS = 0;
        /**
         * 添加新好友
         */
        public static final int USER_CONTACT_NEW_FRIENDS = 1;
        /**
         * 好友组
         */
        public static final int USER_CONTACT_GROUP = 2;

    }

    public static class SquareSearchKind {
        /**
         * 搜索种类为用户
         */
        public static final int SEARCH_USER = 0;
        /**
         * 搜索种类为猜
         */
        public static final int SEARCH_GUESS = 1;
        /**
         * 搜索种类为话题flag
         */
        public static final int SEARCH_TOPIC = 2;
    }

    public static class Store {
        /**
         * 传参标志
         */
        public static final String TAB_FLAG = "flag";
        /**
         * 猜豆tab页
         */
        public static final int TAB_BEANS = 0;
        /**
         * 钻石tab页
         */
        public static final int TAB_DIAMOND = 1;

        public static final String GOODS = "goods";

        public static final String ADDRESS = "address";
        /**
         * 0:趣猜平台商品 1:兑吧平台商品
         */
        public static final int QC_PLA = 0;

        public static final int DB_PLA = 1;
    }

    public static class Square {
        /**
         * 广场的种类标识
         */
        public static final String CATEGORY_KIND_FLAG = "flag";
        /**
         * 广场的种类
         */
        public static final String CATEGORY = "category";
        /**
         * 广场的话题
         */
        public static final String TOPIC = "topic";
        /**
         * 广场的达人用户
         */
        public static final String SUPER_USERS = "super_users";
        /**
         * 广场的种类
         */
        public static final String KIND_GUESSES = "kind_guesses";
    }

    public static class Group {
        /**
         * 用户id
         */
        public static final String USER_ID = "userId";
        /**
         * 来源
         */
        public static final String GROUP_SOURCE = "source";
        /**
         * 用户组
         */
        public static final String GROUP = "group";
        /**
         * 组id
         */
        public static final String GROUP_ID = "groupId";
        /**
         * 组名称
         */
        public static final String GROUP_NAME = "groupName";
        /**
         * 组列表
         */
        public static final String GROUP_LIST = "groupList";
        /**
         * 联系人列表
         */
        public static final String CONTACT_LIST = "contact_list";
    }

    /**
     * 用户的支付信息
     */
    public static class PayInfo {
        /**
         * 有支付密码
         */
        public static final int STATUS_HAVE_PASSWORD = 1;
        /**
         * 没有支付密码
         */
        public static final int STATUS_NO_PASSWORD = 0;
        /**
         * 微信的支付方式
         */
        public static final int PAY_WAY_WECHAT = 0;
        /**
         * 支付宝支付方式
         */
        public static final int PAY_WAY_ALIPAY = 1;
        /**
         * 零钱支付方式
         */
        public static final int PAY_WAY_CHANGE = 2;
    }

    /**
     * 优惠券信息
     */
    public static class CouponInfo {
        /**
         * 系统内部优惠码标识
         */
        public static final String TYPE_SYS_CODE = "uguess";
        /**
         * 未使用
         */
        public static final int STATUS_NOT_USE = 0;
        /**
         * 已使用
         */
        public static final int STATUS_HAD_USED = 1;
        /**
         * 已过期
         */
        public static final int STATUS_OUT_DATE = 2;

    }

    /**
     * 商城优易兑
     */
    public static class StoreWeb {
        /**
         * app_key
         */
        public static final String STORE_APP_KEY = "2J9NDzSCKHSCSARmgHgJJ4dCjMSV";
        /**
         * app_secret
         */
        public static final String STORE_APP_SECRET = "F31kdH11G8TEoxH3wb8Gp9un5Vh";
        /**
         * 用户id
         */
        public static final String STORE_USER_ID = "uid";
        /**
         * 用户猜豆数目
         */
        public static final String STORE_BEANS_NUM = "credits";
        /**
         * 用户登录重定向
         */
        public static final String STORE_REDIRECT = "redirect";
        /**
         * 用户登录跳转至主页
         */
        public static final String STORE_HOME_URL = "http://www.duiba.com.cn/chome/index";
        /**
         * 用户的在兑吧自动登录的 base url
         */
        public static final String STORE_LOGIN_URL = "http://www.duiba.com.cn/autoLogin/autologin?";
    }

    public static class ThirdPlatform {
        /**
         * 邀请好友
         */
        public static final int TRD_INVITE = 0;
        /**
         * 分享
         */
        public static final int TRD_SHARE = 1;
    }

    public static class VIPType {
        /**
         * 职业认证
         */
        public static final int USER_JOB = 1;
        /**
         * 自媒体认证
         */
        public static final int USER_MEDIA = 2;
        /**
         * 机构认证
         */
        public static final int INSTITUTION = 3;
        /**
         * 媒体认证
         */
        public static final int MEDIA = 4;
    }

    /**
     * 客户端数据库表
     */
    public static class FriendTable {
        /**
         * 数据库名称
         */
        public static final String DATA_BASE_NAME = "Friend";
        /**
         * 好友表名称
         */
        public static final String TABLE_NAME = "Friends";
        /**
         * 好友id类
         */
        public static final String USER_ID = "userId";
        /**
         * 好友昵称
         */
        public static final String NICKNAME = "nickName";
        /**
         * 好友备注名
         */
        public static final String REMARK_NAME = "remarkName";
    }

    public static class Goods {
        /**
         * 虚拟商品标志
         */
        public static final int VIRTUAL_GOODS = 9527;
    }
}
