/*
 *
 *  *
 *  *   Copyright(c) 2015, QuCai8, Inc. All rights reserved.
 *  *   This software is the confidential and proprietary information of QuCai8, Inc.
 *  *   You shall not disclose such Confidential Information and shall use it only in
 *  *   accordance with the terms of the license agreement you entered into with QuCai8.
 *  *
 *
 *
 */

package uguess.qucai.com.merchant.business.user.logic;

import uguess.qucai.com.merchant.business.common.cache.Cache;
import uguess.qucai.com.merchant.business.common.module.User;
import uguess.qucai.com.merchant.business.common.protocol.ProcessStatus;
import uguess.qucai.com.merchant.business.user.logic.event.UserEventArgs;
import uguess.qucai.com.merchant.business.user.protocol.GetPublicKeyProcess;
import uguess.qucai.com.merchant.business.user.protocol.LoginProcess;
import uguess.qucai.com.merchant.framework.event.EventArgs;
import uguess.qucai.com.merchant.framework.event.EventId;
import uguess.qucai.com.merchant.framework.event.EventListener;
import uguess.qucai.com.merchant.framework.event.OperErrorCode;
import uguess.qucai.com.merchant.framework.event.StatusEventArgs;
import uguess.qucai.com.merchant.framework.logic.BaseLogic;
import uguess.qucai.com.merchant.framework.protocol.ResponseListener;
import uguess.qucai.com.merchant.framework.util.Logger;
import uguess.qucai.com.merchant.framework.util.StringUtil;
import uguess.qucai.com.merchant.util.Const;

/**
 * Created by NO1 on 2015/1/18.
 */
public class UserLogic extends BaseLogic {

    public static class Factory implements BaseLogic.Factory {
        @Override
        public BaseLogic create() {
            return new UserLogic();
        }
    }


    private static Logger logger = new Logger("com.QuCai.business.logic.UserLogic");

    /**
     * 通过登录页面进行登录
     *
     * @param uName    userName含义不固定，具体内容由服务端判断，客户端仅保持此userName以保证可登录
     * @param password
     */
    public void login(final String uName, final String password, final boolean isFirstLogin, final EventListener listener) {
        logger.d("Login with UserName and Password:" + uName);

        //登录所用的数据
        final User user = new User(uName, password);
        user.setDeviceId("1234567890");
        user.setIsFirstLogin(isFirstLogin);
        user.getUserName();
        User cachedUser = Cache.getInstance().getUser();
        cachedUser.setUserName(uName);
        cachedUser.setDeviceId("1234567890");
        cachedUser.setPassWord(password);
        //登录后台交互过程
        final LoginProcess process = new LoginProcess();
        process.setParamUser(user);

        fetchPublicKey(new EventListener() {
            @Override
            public void onEvent(EventId id, EventArgs args) {
                OperErrorCode errorCode = ((StatusEventArgs) args).getErrCode();
                if (errorCode == OperErrorCode.Success) {
                    process.run(new ResponseListener() {
                        @Override
                        public void onResponse(String requestId) {
                            // 状态转换：从调用结果状态转为操作结果状态
                            OperErrorCode errCode = ProcessStatus.convertFromStatus(process.getStatus());
                            logger.d("login process response, " + errCode);
                            UserEventArgs userEventArgs = new UserEventArgs(process.getResultUser(), errCode);
                            if (errCode == OperErrorCode.Success) {
                                callbackForCacheLogin(process.getResultUser());
                                Cache.getInstance().refreshCacheUser();
                                Cache.getInstance().setLoginFlag(true);
                            }
                            //发送事件
                            fireEvent(listener, userEventArgs);
                        }
                    });
                }
            }
        });
    }

    public void loginWithCache(final EventListener listener) {
        User cachedUser = Cache.getInstance().getUser();
        if (cachedUser == null || StringUtil.isEmpty(Cache.getInstance().getPublicKey())) {
            OperErrorCode errorCode = OperErrorCode.NotLogin;
            fireStatusEvent(listener, errorCode);
            return;
        }
        if (StringUtil.isEmpty(cachedUser.getUserId()) || StringUtil.isEmpty(cachedUser.getPassWord())) {
            OperErrorCode errorCode = OperErrorCode.NotLogin;
            fireStatusEvent(listener, errorCode);
            return;
        }
        //登录后台交互过程
        final LoginProcess process = getProcessForCacheLogin();
        process.run(new ResponseListener() {
            @Override
            public void onResponse(String requestId) {
                // 状态转换：从调用结果状态转为操作结果状态
                OperErrorCode errCode = ProcessStatus.convertFromStatus(process.getStatus());
                logger.d("login process response, " + errCode);
                UserEventArgs userEventArgs = new UserEventArgs(process.getResultUser(), errCode);
                if (errCode == OperErrorCode.Success) {
                    callbackForCacheLogin(process.getResultUser());
                    Cache.getInstance().setLoginFlag(true);
                }
                //发送事件
                fireEvent(listener, userEventArgs);
            }
        });
    }

    private LoginProcess getProcessForCacheLogin() {
        User cachedUser = Cache.getInstance().getUser();
        //登录所用的数据
        User user = new User();
        user.setUserName(cachedUser.getUserName());
        user.setPassWord(cachedUser.getPassWord());
        user.setLoginMode(cachedUser.getLoginMode());
        user.setDeviceId(cachedUser.getDeviceId());
        if (StringUtil.isEmpty(cachedUser.getOpenId())) {
            user.setIsFirstLogin(false);
        }
        if (user.getLoginMode() > -1) {
            user.setOpenId(cachedUser.getOpenId());
        }
        user.setDeviceId(cachedUser.getDeviceId());
        user.setIsFirstLogin(cachedUser.isFirstLogin());
        //登录后台交互过程
        LoginProcess process = new LoginProcess();
        process.setParamUser(user);
        return process;
    }

    private void callbackForCacheLogin(User resultUser) {
        User user = Cache.getInstance().getUser();
        user.setPassWord(resultUser.getPassWord());
        user.setDeviceId(resultUser.getDeviceId());
        user.setLoginMode(resultUser.getLoginMode());
        if (user.getLoginMode() > -1) {
            user.setOpenId(resultUser.getOpenId());
        }
        user.setToken(resultUser.getToken());
        user.setNickName(resultUser.getNickName());
        user.setPortraitURL(resultUser.getPortraitURL());
        user.setUserImId(resultUser.getUserImId());
        user.setBirthday(resultUser.getBirthday());
        user.setGender(resultUser.getGender());
        user.setUserId(resultUser.getUserId());
        user.setCellNum(resultUser.getCellNum());
        user.setAvatarsToken(resultUser.getAvatarsToken());
        user.setImagesToken(resultUser.getImagesToken());
        user.setDocumentToken(resultUser.getDocumentToken());
        user.setGrade(resultUser.getGrade());
        user.setPayPwdSetted(resultUser.isPayPwdSetted());
        user.setInviteCode(resultUser.getInviteCode());
        user.setDeviceId(resultUser.getDeviceId());
        Cache.getInstance().refreshCacheUser();
    }

    /**
     * 获取并持久化公钥
     */
    public void fetchPublicKey(final EventListener listener) {
        final GetPublicKeyProcess process = new GetPublicKeyProcess();
        process.run(new ResponseListener() {
            @Override
            public void onResponse(String requestId) {
                // 状态转换：从调用结果状态转为操作结果状态
                OperErrorCode errCode = ProcessStatus.convertFromStatus(process.getStatus());
                if (errCode != OperErrorCode.Success) {
                    /**
                     *获取公钥出错
                     */
                    fireStatusEvent(listener, errCode);

                } else if (errCode == OperErrorCode.Success) {
                    fireStatusEvent(listener, errCode);
                }
            }
        });
    }

    /**
     * 取出公钥
     *
     * @return
     */
    public String getPublicKey() {
        return Cache.getInstance().getPublicKey();
    }

}
