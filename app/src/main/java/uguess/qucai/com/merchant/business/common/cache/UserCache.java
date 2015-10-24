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

package uguess.qucai.com.merchant.business.common.cache;

import uguess.qucai.com.merchant.business.common.module.User;
import uguess.qucai.com.merchant.business.common.persistor.PersistorManager;

/**
 * 操作SharePerformance中的用户缓存数据
 */
public class UserCache {

    private User mUser = null;

    public void onCreate() {
        mUser = PersistorManager.getInstance().getLastestUser();
    }

    public User getCashedUser() {
        return mUser;
    }

    public void setCache(User user) {
        mUser = user;
        PersistorManager.getInstance().setUser(user);
    }
}
