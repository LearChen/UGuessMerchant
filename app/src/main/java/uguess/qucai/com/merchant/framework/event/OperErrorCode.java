package uguess.qucai.com.merchant.framework.event;

public enum OperErrorCode {

    /**
     * 成功
     */
    Success,

    /**
     * 网络不可用
     */
    NetNotAviable,

    /**
     * 定位服务不可用
     */
    LocationNotAviable,

    /**
     * 用户名不存在
     */
    UidNoExist,

    /**
     * 用户名已存在
     */
    UidExist,

    /**
     * 用户名无效
     */
    UidInvalid,

    /**
     * 密码错误
     */
    PasswordError,

    /**
     * 要删除的照片为头像
     */
    PhotoIsPortait,

    /**
     * 他在我的黑名单中
     */
    InBlack,

    /**
     * 不需要升级
     */
    NoNeedUpgrade,

    /**
     * 未知错误
     */
    Unknown,

    /**
     * 初始化状态
     */
    None,

    /**
     * 非法请求
     */
    IllegalRequest,

    /**
     * 验证码过期
     */
    VerifyCodeExpire,
    /**
     * 验证码错误
     */
    VerifyCodeWrong,
    /**
     * 手机号码已存在
     */
    CellNumExist,
    /**
     * 文件上传失败
     */
    FileUpLoadFailed,

    /**
     * 未找到数据
     */
    NoDataFound,
    /**
     * 没有登陆
     */
    NotLogin,
    /**
     * 结果没有权限
     */
    ResultNotPermit,

    /**没有争抢到派发福利机会，请重试*/
    ResultNoGrab,
    /**福利已派完*/
    ResultDistributeFinished,
    /**行程无福利*/
    ResultNoBenefit,
    /**登录超时*/
    ErrLoginTimeOut,
    /**商品已经售罄*/
    GoodIsNull,
    /**商品不可重复购买*/
    GoodIsRepeat,
    /**
     * 未设置支付密码
     */
    PayPwdUnset,
    /**
     * 支付密码错误
     */
    PayPwdWrong,

    /**
     * 参与名额已争抢完毕
     */
    JoinNumIsOver,
    /**
     * 猜豆不足
     */
    ResultGoldNotSufficient,
    /**
     * 红包余额不足
     */
    ResultMoneyNotSufficient,
    /**
     * 钻石不足
     */
    DiamondNotSufficient,
    /**
     * 交易号不存在
     */
    TradeNoNotExist,
    /**
     * 用户支付中
     */
    PayInProgress,
    /**
     * 未支付
     */
    NotPayed,
    /**
     * 支付已关闭
     */
    PayClosed,
    /**
     * 支付撤销
     */
    PayCanceled,
    /**
     * 支付已转入退款
     */
    PayInReturn,
    /**
     * 支付异常重新查询
     */
    PayRequery,

    TicketNotExist,

    TicketAlreadyUsed,

    TicketExpired,

    TicketNoPermision,

}
