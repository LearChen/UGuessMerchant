/*
 * Copyright(c) 2015, QuCai, Inc. All rights reserved.
 * This software is the confidential and proprietary information of QuCai, Inc.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with QuCai.
 */
package uguess.qucai.com.merchant.business.common.persistor;

import uguess.qucai.com.merchant.business.common.module.User;

/**
 * Created by NO1 on 2015/1/20.
 */
public class PersistorManager {

    private static PersistorManager ourInstance = new PersistorManager();

    private UserPreferences userPreferences;

    public static PersistorManager getInstance() {
        return ourInstance;
    }

    private PersistorManager() {
        userPreferences = new UserPreferences();
            }

    public void savePublicKey(String publicKey) {
        userPreferences.setPublicKey(publicKey);
    }

    public String getPublicKey() {
        return userPreferences.getPublicKey();
    }

    public User getLastestUser() {
        return userPreferences.getLastest();
    }

    public void setUser(User user) {
        userPreferences.set(user);
    }


    public void setHasPayPwdFlag() {
        userPreferences.setHasPayPwdFlag();
    }

    public boolean isHasPayPwd() {
        return userPreferences.isHasPayPwd();
    }

}
