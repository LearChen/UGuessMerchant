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

package uguess.qucai.com.merchant.business.user.logic.event;

import uguess.qucai.com.merchant.business.common.module.User;
import uguess.qucai.com.merchant.framework.event.OperErrorCode;
import uguess.qucai.com.merchant.framework.event.StatusEventArgs;

/**
 * Created by NO1 on 2015/2/6.
 */
public class UserEventArgs extends StatusEventArgs {

    private User result = null;
    /**
     * 用户信息列表
     */
    public UserEventArgs(User user, OperErrorCode errCode) {
        super(errCode);
        result = user;
    }

    /**
     * 错误返回
     *
     * @param errCode
     */
    public UserEventArgs(OperErrorCode errCode) {
        super(errCode);
    }

    /**
     * 正确返回
     *
     * @param user
     */
    public UserEventArgs(User user) {
        super(OperErrorCode.Success);
        result = user;
    }

    public User getResult() {
        return result;
    }

    @Override
    public OperErrorCode getErrCode() {
        return super.getErrCode();
    }

}
