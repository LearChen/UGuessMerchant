package uguess.qucai.com.merchant.framework.protocol;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import uguess.qucai.com.merchant.business.common.cache.Cache;
import uguess.qucai.com.merchant.business.common.module.User;
import uguess.qucai.com.merchant.business.common.protocol.ProcessStatus;
import uguess.qucai.com.merchant.framework.net.HttpComm;
import uguess.qucai.com.merchant.framework.net.HttpResultCallback;
import uguess.qucai.com.merchant.framework.util.Logger;
import uguess.qucai.com.merchant.framework.util.StringUtil;
import uguess.qucai.com.merchant.util.Const;

import static uguess.qucai.com.merchant.business.common.protocol.ProcessStatus.Status.*;
import static  uguess.qucai.com.merchant.util.Const.ResponseResultCode.*;

/**
 * 服务器协议通信单元基类
 *
 * @author hanlixin
 */
abstract public class BaseProcess {

    private final String clazz = getClass().getSimpleName();
    private static final Logger logger = new Logger("protocol");


    /**
     * 获得请求地址
     */
    abstract protected String getRequestUrl();

    /**
     * 获得 请求时的info参数，派生类实现
     */
    abstract protected String getInfoParameter();

    /**
     * 通信完成，解析结果，派生类实现
     */
    abstract protected void onResult(JSONObject result);

    /**
     * 获得测试用假数据
     */
    abstract protected String getFakeResult();

    /**
     * 通信结果错误码
     */
    private ProcessStatus.Status mStatus = Success;

    public ProcessStatus.Status getStatus() {
        return mStatus;
    }

    protected void setStatus(ProcessStatus.Status value) {
        mStatus = value;
    }

    public void run() {
        run(new EmptyResponseListener());
    }

    public void run(ResponseListener listener) {
        run(null, listener);
    }

    public void run(String requestId, ResponseListener listener) {
        new AsyncComm(requestId, listener).execute();
    }

    protected void onCreate() {
    }

    protected void setProcessStatus(int resultCode) {
        switch (resultCode) {
            case RESULT_SUCCESS:
                mStatus = Success;
                break;
            case RESULT_FAIL:
                mStatus = ErrFailure;
                break;
            case RESULT_ILLEGAL_CALL:
                mStatus = IllegalRequest;
                break;
            case RESULT_NOT_LOGINED:
                mStatus = ErrNotLogin;
                break;
            case RESULT_LOGIN_TIMEOUT:
                mStatus = ErrLoginTimeOut;
                break;
            case RESULT_USER_NAME_EMPTY:
                mStatus = ErrEmptyUserName;
                break;
            case RESULT_PWD_EMPTY:
                mStatus = ErrPwdEmpty;
                break;
            case RESULT_CELL_NUM_EXIST:
                mStatus = ErrExistCellNum;
                break;
            case RESULT_CELL_NUM_NOT_EXIST:
                mStatus = ErrCellNumNotExist;
                break;
            case RESULT_VERIFY_CODE_ERROR:
                mStatus = ErrWrongVerCode;
                break;
            case RESULT_VERIFY_CODE_INVALID:
                mStatus = ErrVerCodeExpire;
                break;
            case RESULT_PWD_ERROR:
                mStatus = ErrPass;
                break;
            case RESULT_USER_NAME_NOT_EXIST:
                mStatus = ErrUserNameNotExist;
                break;
            case RESULT_USER_NAME_INVALID:
                mStatus = ErrUidInvalid;
                break;
            case RESULT_NICKNAME_EXIST:
                mStatus = ErrNickNameExist;
                break;
            case RESULT_BODY_IS_NOT_EXIST:
                mStatus = InfoNoData;
                break;
            case RESULT_NOT_PERMIT:
                mStatus = ResultNotPermit;
                break;
            case RESULT_DEVICE_ID_IS_NULL:
                mStatus = ErrDeviceIdEmpty;
                break;
            case RESULT_NO_GRAB:
                mStatus = ResultNoGrab;
                break;
            case RESULT_DISTRIBUTE_FINISHED:
                mStatus = ResultDistributeFinished;
                break;
            case RESULT_SCORE_FINISHED:
                mStatus = ResultGoldNotSufficient;
                break;
            case RESULT_BONUS_FINISHED:
                mStatus = ResultMoneyNotSufficient;
                break;
            case RESULT_HAD_BEEN_FRIEND:
                mStatus = IsAlreadyFriend;
                break;
            case RESULT_DIAMOND_FINISHED:
                mStatus = DiamondNotSufficient;
                break;
            case RESULT_GOODS_NULL:
                mStatus = GoodsIsNull;
                break;
            case RESULT_GOODS_REPEAT:
                mStatus = GoodIsRepeat;
                break;
            case RESULT_PAY_PWD_UNSET:
                mStatus = PayPwdUnset;
                break;
            case RESULT_PAY_PWD_WRONG:
                mStatus = PayPwdWrong;
                break;
            case JOIN_NUM_IS_OVER:
                mStatus = JoinNumIsOver;
                break;
            case RESULT_EXCEPTION:
                mStatus = ErrFailure;
                break;
            case RESULT_PAY_TRADENO_NOT_EXIST:
                mStatus = TradeNoNotExist;
                break;
            case RESULT_PAY_IN_PROGRESS:
                mStatus = PayInProgress;
                break;
            case RESULT_PAY_UNDO:
                mStatus = NotPayed;
                break;
            case RESULT_PAY_CLOSED:
                mStatus = PayClosed;
                break;
            case RESULT_PAY_CANCEL:
                mStatus = PayCanceled;
                break;
            case RESULT_PAY_REBACK:
                mStatus = PayInReturn;
                break;
            case RESULT_PAY_REQUERY:
                mStatus = PayRequery;
                break;
            default:
                mStatus = ErrUnkown;
        }
    }

    private class AsyncComm extends AsyncTask<Void, Void, Void> {

        private String mRequestId = "";
        private ResponseListener mListener = null;
        private String parameter;

        public AsyncComm(String requestId, ResponseListener listener) {
            mRequestId = requestId;
            mListener = listener;
        }

        @Override
        protected Void doInBackground(Void... params) {

            onCreate();
            //从相对路径拼为绝对路径
            String url = Const.BASE_URL + getRequestUrl();
            //加入TOKEN参数
            User cacheUser = Cache.getInstance().getUser();
            String token;
            if (cacheUser == null) {
                token = "";
            } else {
                token = cacheUser.getToken();
            }
            //token="4JC6ZIXW9RDO8GDQT6SCJG1O9EXFHP92YT4W4LHY8RNZLWYLNZM4T8YRUILUVAZLSEC7PEAU0DX";
            if (url.indexOf("?") == -1) {
                url = url + "?t=" + token;
            } else {
                url = url + "&t=" + token;
            }

            parameter = getInfoParameter();
            if (!StringUtil.isEmpty(parameter)) {
                parameter = parameter.replace("\\/", "/");
            }
            logger.d(String.format("send name:%s url:%s param:%s",
                    clazz, url, parameter));

            if (StringUtil.isEmpty(url)) {
                mStatus = ErrUnkown;
                return null;
            }

            if (Const.FakeProtocol) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
//                onResult(getFakeResult());
                return null;
            }

            final HttpComm comm = new HttpComm(false);
            comm.post(url, parameter, new HttpResultCallback() {

                @Override
                public void onResponse(HttpDownloaderResult success, String url, String message) {

                    if (success == HttpDownloaderResult.eSuccessful) {
                        logger.d(String.format("recv name:%s url:%s result:%s",
                                clazz, url, message));
                        mStatus = Success;
                        try {
                            JSONObject o = new JSONObject(message);
                            if (commonExceptionHandler(o)) {
                                onResult(o);
                            } else {
                                //可自恢复的异常
                                onResult(Const.Application.reLoginAndRepeat(url, parameter));
                            }
                        } catch (JSONException e) {
                            logger.d("recv response url:" + url + "; fail");
                            mStatus = ErrNetDisable;
                        }
                    } else {
                        logger.d("recv response url:" + url + "; fail");
                        mStatus = ErrNetDisable;
                    }
                }

                @Override
                public void onProgress(String url, float rate) {
                }
            });
            return null;
        }

        /**
         * 判定未登录或登录超时
         */
        private boolean commonExceptionHandler(JSONObject message) {
            int resultCode = message.optInt("result_code");
            switch (resultCode) {
                case RESULT_NOT_LOGINED:
                    return false;
                case RESULT_LOGIN_TIMEOUT:
                    return false;
                case RESULT_AUTH_EXPIRED:
                    return true;
                default:
                    return true;
            }
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(Void result) {
            mListener.onResponse(mRequestId);
        }

    }
}
