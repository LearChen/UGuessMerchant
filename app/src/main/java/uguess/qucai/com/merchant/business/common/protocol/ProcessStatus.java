package uguess.qucai.com.merchant.business.common.protocol;

import uguess.qucai.com.merchant.framework.event.OperErrorCode;

public class ProcessStatus {
    public static enum Status {
        /**
         * 成功
         */
        Success,

        /**
         * email不存在
         */
        ErrUid,

        /**
         * 密码错误
         */
        ErrPass,

        /**
         * email已经存在
         */
        ErrUidExist,

        /**
         * email不合法
         */
        ErrUidInvalid,

        /**
         * 要删除的图片是头像，不能删除
         */
        ErrPhotoIsPortrait,
        /**
         * 没有网络
         */
        ErrNetDisable,

        ErrException,
        ErrUnkown,
        /**
         * 非法请求
         */
        IllegalRequest,

        /**
         * 验证码错误
         */
        ErrWrongVerCode,
        /**
         * 手机号码已存在
         */
        ErrExistCellNum,
        /**
         * 验证码过期
         */
        ErrVerCodeExpire,
        /**
         * 上传文件为空
         */
        ErrEmptyFile,
        /**
         * 未登录
         */
        ErrNotLogin,
        /**
         * 登录超时
         */
        ErrLoginTimeOut,
        /**
         * 用户名为空
         */
        ErrEmptyUserName,
        /**
         * 密码为空
         */
        ErrPwdEmpty,
        /**
         * 手机号码不存在
         */
        ErrCellNumNotExist,
        /**
         * 昵称已存在
         */
        ErrNickNameExist,
        /**
         * 用户名不存在
         */
        ErrUserNameNotExist,
        ErrFailure,
        /**
         * 未找到数据
         */
        InfoNoData,
        /**
         * 没有权限
         */
        ResultNotPermit,
        /**
         * 设备ID为空
         */
        ErrDeviceIdEmpty,
        /**
         * 没有争抢到派发福利机会，请重试
         */
        ResultNoGrab,
        /**
         * 福利已派完
         */
        ResultDistributeFinished,
        /**
         * 猜豆余额不足
         */
        ResultGoldNotSufficient,
        /**
         * 红包余额不足
         */
        ResultMoneyNotSufficient,
        /**
         * 已经是好友关系了
         */
        IsAlreadyFriend,
        /**
         * 钻石余额不足
         */
        DiamondNotSufficient,
        /**
         * 支付密码未设置
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
         * 行程无福利
         */
        ResultNoBenefit,
        /**
         * 商品已经售罄
         */
        GoodsIsNull,
        /**
         * 商品不可重复购买
         */
        GoodIsRepeat,
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

    }

    public static OperErrorCode convertFromStatus(Status status) {
        switch (status) {
            case Success:
                return OperErrorCode.Success;
            case ErrUid:
                return OperErrorCode.UidNoExist;
            case ErrPass:
                return OperErrorCode.PasswordError;
            case ErrPhotoIsPortrait:
                return OperErrorCode.PhotoIsPortait;
            case ErrUidExist:
                return OperErrorCode.UidExist;
            case ErrUidInvalid:
                return OperErrorCode.UidInvalid;
            case ErrNetDisable:
                return OperErrorCode.NetNotAviable;
            case IllegalRequest:
                return OperErrorCode.IllegalRequest;
            case ErrWrongVerCode:
                return OperErrorCode.VerifyCodeWrong;
            case ErrVerCodeExpire:
                return OperErrorCode.VerifyCodeExpire;
            case ErrExistCellNum:
                return OperErrorCode.CellNumExist;
            case ErrEmptyFile:
                return OperErrorCode.FileUpLoadFailed;
            case InfoNoData:
                return OperErrorCode.NoDataFound;
            case ResultNotPermit:
                return OperErrorCode.ResultNotPermit;
            case ResultNoGrab:
                return OperErrorCode.ResultNoGrab;
            case ResultDistributeFinished:
                return OperErrorCode.ResultDistributeFinished;
            case ResultNoBenefit:
                return OperErrorCode.ResultNoBenefit;
            case ErrLoginTimeOut:
                return OperErrorCode.ErrLoginTimeOut;
            case GoodsIsNull:
                return OperErrorCode.GoodIsNull;
            case GoodIsRepeat:
                return OperErrorCode.GoodIsRepeat;
            case ErrUserNameNotExist:
                return OperErrorCode.UidNoExist;
            case PayPwdUnset:
                return OperErrorCode.PayPwdUnset;
            case PayPwdWrong:
                return OperErrorCode.PayPwdWrong;
            case JoinNumIsOver:
                return OperErrorCode.JoinNumIsOver;
            case ResultGoldNotSufficient:
                return OperErrorCode.ResultGoldNotSufficient;
            case ResultMoneyNotSufficient:
                return OperErrorCode.ResultMoneyNotSufficient;
            case DiamondNotSufficient:
                return OperErrorCode.DiamondNotSufficient;
            case TradeNoNotExist:
                return OperErrorCode.TradeNoNotExist;
            case PayInProgress:
                return OperErrorCode.NotPayed;
            case PayClosed:
                return OperErrorCode.PayCanceled;
            case PayInReturn:
                return OperErrorCode.PayRequery;
            case ErrNickNameExist:
                return OperErrorCode.UidExist;
            default:
                return OperErrorCode.Unknown;
        }
    }
}

