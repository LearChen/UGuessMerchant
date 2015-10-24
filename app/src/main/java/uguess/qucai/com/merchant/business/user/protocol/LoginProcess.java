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

package uguess.qucai.com.merchant.business.user.protocol;

import android.text.TextUtils;

import com.qucai8.common.security.RSACoder;

import org.json.JSONObject;

import uguess.qucai.com.merchant.business.common.cache.Cache;
import uguess.qucai.com.merchant.business.common.module.User;
import uguess.qucai.com.merchant.business.common.protocol.ProcessStatus;
import uguess.qucai.com.merchant.framework.protocol.BaseProcess;
import uguess.qucai.com.merchant.framework.util.JSONUtil;
import uguess.qucai.com.merchant.framework.util.Logger;
import uguess.qucai.com.merchant.framework.util.StringUtil;

/**
 * Created by NO1 on 2015/1/19.
 */
public class LoginProcess extends BaseProcess {

    private Logger logger = new Logger("login_parameter");
    //服务端请求地址
    private final String URL = "/enterprise_user/enterprise_login.html";

    //参数对象
    private User paramUser;

    public User getResultUser() {
        return resultUser;
    }

    //结果对象
    private User resultUser;
    //private List<Trip> resultTrips;

    @Override
    protected String getFakeResult() {
        String fakeResult = "{status:0,userName:" + paramUser.getUserName() + ",passWord:" + paramUser.getPassWord() + "}";
        return fakeResult;
    }

    @Override
    public String getRequestUrl() {
        if (paramUser.getLoginMode() > -1) {
            return "/user/third_login.html";
        }
        return URL;
    }

    public void setParamUser(User param) {
        paramUser = param;
    }

    @Override
    public String getInfoParameter() {

        try {
            if (paramUser.getLoginMode() > -1) {
                logger.d(paramUser.toString());
                return RSACoder.getInstance().encryptThirdLoginData(paramUser.getLoginMode() + "", paramUser.getOpenId(), Cache.getInstance().deviceToken, paramUser.isFirstLogin(), Cache.getInstance().getPublicKey());
            } else {
                logger.d("username----->" + paramUser.getUserName()
                        + "password----->" + paramUser.getPassWord()
                        + "deviceid----->" + paramUser.getDeviceId()
                        + "islongin----->" + paramUser.isFirstLogin()
                        + "publickey---->" + Cache.getInstance().getPublicKey());
                return RSACoder.getInstance().encryptLoginData(paramUser.getUserName(), paramUser.getPassWord(), Cache.getInstance().deviceToken, paramUser.isFirstLogin(), Cache.getInstance().getPublicKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onResult(JSONObject o) {
        try {
            //获取状态码
            int value = o.optInt(JSONUtil.STATUS_TAG);
            setProcessStatus(value);
            if (getStatus() == ProcessStatus.Status.Success) {
                /**
                 * 取出返回值
                 */
                resultUser = getUserFromResult(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
            setStatus(ProcessStatus.Status.ErrUnkown);
        }
    }

    public User getUserFromResult(JSONObject o) {

        /**
         * 取出返回值
         */
        JSONObject body = JSONUtil.getResultBody(o);
        if (body != null) {
            String token = body.optString("token");
            String encrypt = body.optString("encrypt");
            String nickname = body.optString("nickname");
            String url = body.optString("portrait_url");
            String gender = body.optString("gender");
            String birthday = body.optString("birthday");
            String userName = body.optString("user_name");
            String userId = body.optString("user_id");
            String cellNum = body.optString("cell_num");
            String avatarsToken = body.optString("avatars_token");
            String imagesToken = body.optString("images_token");
            String documentToken = body.optString("document_token");
            String setPayPwd = body.optString("is_set_pay_pwd");
            int isNewUser = body.optInt("is_new_user");
            int grade = body.optInt("grade");
            String inviteCode = body.optString("invite_code");
            /**
             * 组装返回对象
             */
            User resultUser = new User();
            if (TextUtils.isEmpty(birthday)) {
                User user = Cache.getInstance().getUser();
                if (user.getConfirm() > 0) {
                    resultUser.setNickName(user.getNickName());
                    resultUser.setPortraitURL(user.getPortraitURL());
                    resultUser.setGender(user.getGender());
                }
            } else {
                resultUser.setNickName(nickname);
                resultUser.setPortraitURL(url);
                resultUser.setGender(Integer.parseInt(gender));
            }
            if (StringUtil.isEmpty(setPayPwd)) {
                resultUser.setPayPwdSetted(false);
            } else {
                switch (setPayPwd) {
                    case "0":
                        resultUser.setPayPwdSetted(false);
                        break;
                    case "1":
                        resultUser.setPayPwdSetted(true);
                        break;
                    default:
                        resultUser.setPayPwdSetted(false);
                }
            }
            resultUser.setBirthday(birthday);
            resultUser.setToken(token);
            resultUser.setEncrypt(encrypt);
            resultUser.setUserId(userId);
            resultUser.setLoginMode(paramUser.getLoginMode());
            resultUser.setUserName(userName);
            resultUser.setIsFirstLogin(false);
            resultUser.setPassWord(encrypt);
            resultUser.setDeviceId(Cache.getInstance().deviceToken);
            resultUser.setCellNum(cellNum);
            resultUser.setImagesToken(imagesToken);
            resultUser.setAvatarsToken(avatarsToken);
            resultUser.setDocumentToken(documentToken);
            resultUser.setGrade(grade);
            resultUser.setInviteCode(inviteCode);
            resultUser.setConfirm(isNewUser);
            if (paramUser.getLoginMode() > -1) {
                resultUser.setOpenId(encrypt);
            }
            return resultUser;
        } else

        {
            return null;
        }
    }
}
