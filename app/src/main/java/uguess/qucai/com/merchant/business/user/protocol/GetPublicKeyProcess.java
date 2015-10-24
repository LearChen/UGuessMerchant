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

import org.json.JSONObject;

import uguess.qucai.com.merchant.QucaiApplication;
import uguess.qucai.com.merchant.business.common.cache.Cache;
import uguess.qucai.com.merchant.framework.protocol.BaseProcess;

/**
 * Created by NO1 on 2015/2/3.
 */
public class GetPublicKeyProcess extends BaseProcess {

    private final String url = "/common/get_public_key.html";

    private static int MAX_NUM = 5;

    @Override
    public String getRequestUrl() {
        return url;
    }

    @Override
    public String getInfoParameter() {
        return null;
    }

    @Override
    protected void onResult(JSONObject o) {
        //获取状态码
        int value = o.optInt("result_code");
        if (value == 0) {
            /**
             * 获取公钥并持久化
             */
            JSONObject key = o.optJSONObject("body");
            if (key != null) {
                String keyString = key.optString("public_key");
                Cache.getInstance().setPublicKey(keyString);
            } else {

            }
        }
        setProcessStatus(value);
    }

    public void storePublicKey(JSONObject o) {
        JSONObject key = o.optJSONObject("body");
        if (key == null && MAX_NUM > -1) {
            QucaiApplication.getInstance().getPublicKey();
            MAX_NUM--;
        } else if (key != null) {
            String keyString = key.optString("public_key");
            Cache.getInstance().setPublicKey(keyString);
        } else {

        }
    }

    @Override
    protected String getFakeResult() {
        return null;
    }
}
