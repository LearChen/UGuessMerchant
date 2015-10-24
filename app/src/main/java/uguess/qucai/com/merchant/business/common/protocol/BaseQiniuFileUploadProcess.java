/*
 * Copyright(c) 2015, QuCai, Inc. All rights reserved.
 * This software is the confidential and proprietary information of QuCai, Inc.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with QuCai.
 */

package uguess.qucai.com.merchant.business.common.protocol;

import android.os.AsyncTask;

import java.io.File;

import uguess.qucai.com.merchant.business.common.cache.Cache;
import uguess.qucai.com.merchant.business.common.module.User;
import uguess.qucai.com.merchant.framework.protocol.ResponseListener;
import uguess.qucai.com.merchant.framework.util.StringUtil;
import uguess.qucai.com.merchant.util.Const;

/**
 * BaseQiniuFileUploadProcess.java是启程APP的上传文件到七牛云存储服务器基础类。
 *
 * @author 花树峰
 * @version 1.0 2015年6月12日
 */
public class BaseQiniuFileUploadProcess {

    /**
     * 文件用途 0：普通图片文件
     */
    public static final byte FILE_USAGE_SIMPLE = 0;

    /**
     * 文件用途 1:头像图片文件
     */
    public static final byte FILE_USAGE_AVATAR = 1;

    /**
     * 文件用途 2：视频文件
     */
    public static final byte FILE_USAGE_VIDEO = 2;

    /**
     * 文件用途 3：文本文件
     */
    public static final byte FILE_USAGE_TEXT = 3;
    /**
     * 文件用途 4:大V认证文件
     */
    public static final byte V_DOCUMENT = 4;

    /**
     * 上传失败，原因是上传令牌无效或过期
     */
    public static final int FAILURE_EXPIRE = 401;

    /**
     * 请求ID
     */
    protected String requestId;

    /**
     * 文件用途
     */
    protected byte fileUsage;

    /**
     * 结果响应监听器
     */
    protected ResponseListener mListener = null;

    /**
     * 通信结果错误码
     */
    protected ProcessStatus.Status mStatus = ProcessStatus.Status.Success;

    public ProcessStatus.Status getStatus() {
        return mStatus;
    }

    /**
     * 获取上传到七牛云存储服务器的文件Key值。
     *
     * @param file 待上传文件对象
     * @return 文件Key值
     */
    protected String getFileKey(File file) {
        String fileName = file.getName();
        fileName = fileName.replaceAll("-", "");
        User cacheUser = Cache.getInstance().getUser();
        String userIdMD5 = StringUtil.MD5(cacheUser.getUserId());
        String fileKey = userIdMD5 + fileName;
        return fileKey;
    }

    /**
     * 获取上传文件Token。
     *
     * @return 上传文件Token
     */
    protected String getUploadToken() {
        User cacheUser = Cache.getInstance().getUser();
        String uploadToken = null;
        if (fileUsage == FILE_USAGE_SIMPLE) {
            uploadToken = cacheUser.getImagesToken();
        } else if (fileUsage == FILE_USAGE_AVATAR) {
            uploadToken = cacheUser.getAvatarsToken();
        } else if (fileUsage == V_DOCUMENT) {
            uploadToken = cacheUser.getDocumentToken();
        }
        return uploadToken;
    }

    protected class AsyncComm extends AsyncTask<Void, Void, Void> {

        private String mRequestId = "";
        private ResponseListener mListener = null;

        public AsyncComm(String requestId, ResponseListener listener) {
            mRequestId = requestId;
            mListener = listener;
        }

        @Override
        protected Void doInBackground(Void... params) {
            // 重登录系统，获取新的上传文件令牌
            Const.Application.reLoginAndRepeat();
            return null;
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
