/*
 * Copyright(c) 2015, QuCai, Inc. All rights reserved.
 * This software is the confidential and proprietary information of QuCai, Inc.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with QuCai.
 */

package uguess.qucai.com.merchant.business.common.protocol;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.io.File;

import uguess.qucai.com.merchant.framework.protocol.ResponseListener;

/**
 * QiniuImageUploadProcess.java是启程APP的上传单张图片文件到七牛云存储服务器处理类。
 *
 * @author 花树峰
 * @version 1.0 2015年3月31日
 */
public class QiniuImageUploadProcess extends BaseQiniuFileUploadProcess {

    /**
     * 图片文件对象
     */
    private File imageFile;

    /**
     * 图片文件返回URL
     */
    private String resultUrl;

    public void run(String requestId, byte usage, File image, ResponseListener listener) {
        this.requestId = requestId;
        this.fileUsage = usage;
        this.imageFile = image;
        this.mListener = listener;
        uploadFile();
    }

    public String getResultUrl() {
        return resultUrl;
    }

    /**
     * 上传文件到七牛云存储服务器。
     */
    private void uploadFile() {
        try {
            // 获取上传文件Token。
            String uploadToken = getUploadToken();
            if (uploadToken == null) {
                mStatus = ProcessStatus.Status.ErrUnkown;
                mListener.onResponse(requestId);
                return;
            }
            // 获取上传到七牛云存储服务器的文件Key值。
            final String fileKey = getFileKey(imageFile);
            // 创建七牛上传文件管理器
            UploadManager uploadManager = new UploadManager();
            uploadManager.put(imageFile, fileKey, uploadToken, new UpCompletionHandler() {
                @Override
                public void complete(String key, ResponseInfo info, JSONObject response) {
                    if (info.isOK()) {
                        mStatus = ProcessStatus.Status.Success;
                        resultUrl = '/' + fileKey;
                        mListener.onResponse(requestId);
                    } else {
                        if (info.statusCode == FAILURE_EXPIRE) {
                            mStatus = ProcessStatus.Status.ErrLoginTimeOut;
                            new AsyncComm(requestId, mListener).execute();
                        } else {
                            mStatus = ProcessStatus.Status.ErrUnkown;
                            mListener.onResponse(requestId);
                        }
                    }
                }
            }, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
