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

import java.util.Calendar;
import java.util.TimeZone;

import uguess.qucai.com.merchant.business.common.module.User;
import uguess.qucai.com.merchant.business.common.persistor.PersistorManager;

/**
 * 应用的缓存
 */
public class Cache {

    public String deviceToken;
    /**
     * 静态初始化Cache
     */
    private static Cache ins = new Cache();

    /**
     * 获取缓存实例
     */
    public static Cache getInstance() {
        return ins;
    }

    /**
     * 缓存用户信息
     */
    private User mUser;
    /**
     * 缓存的公钥
     */
    private String publicKey;
    /**
     * 是否登录
     */
    private boolean loginFlag = false;
    /**
     * 写入SharePerformance中的用户信息
     */
    private UserCache mUserCache = new UserCache();



    public void onCreate() {
        /*用户对象缓存*/
        mUserCache.onCreate();
        mUser = mUserCache.getCashedUser();
        /*公钥缓存*/
        publicKey = PersistorManager.getInstance().getPublicKey();
    }

    /**
     * 刷新SharePerformance中的用户信息
     */
    public void refreshCacheUser() {
        mUserCache.setCache(mUser);
    }

    public TimeZone getCurrentTimeZone() {
        return Calendar.getInstance().getTimeZone();
    }

    /**
     * 获得公钥
     *
     * @return 公钥
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * 设置公钥
     *
     * @param publicKey 公钥
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
        PersistorManager.getInstance().savePublicKey(publicKey);
    }

    /**
     * 获取缓存的用户信息
     *
     * @return mUser 用户信息
     */
    public User getUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }

    /**
     * 获取是否登录的标识
     *
     * @return true:已登录 false未登录
     */
    public boolean isLoginFlag() {
        return loginFlag;
    }

    /**
     * 设置是否登录的标识
     *
     * @param loginFlag true:已登录 false未登录
     */
    public void setLoginFlag(boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

}
