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
import java.util.ArrayList;
import java.util.List;

import uguess.qucai.com.merchant.framework.protocol.ResponseListener;

/**
 * QiniuMultiImageUploadProcess.java是启程APP的上传多张图片文件到七牛云存储服务器基础类。
 *
 * @author 花树峰
 * @version 1.0 2015年6月12日
 */
public class QiniuMultiImageUploadProcess extends BaseQiniuFileUploadProcess {

    /**
     * 图片文件对象列表
     */
    private List<File> imageFiles;

    /**
     * 图片文件返回URL列表
     */
    private List<String> resultUrls = new ArrayList<String>();

    /**
     * 是否发生了上传图片文件失败标识
     */
    private boolean isFailure = false;

    public void run(String requestId, byte usage, List<File> imageFiles, ResponseListener listener) {
        this.requestId = requestId;
        this.fileUsage = usage;
        this.imageFiles = imageFiles;
        this.mListener = listener;
        uploadFile();
    }

    public List<String> getResultUrls() {
        if (isFailure) {
            resultUrls.clear();
        }
        return resultUrls;
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
            final int imageFileSize = imageFiles.size();
            for (File imageFile : imageFiles) {
                // 获取上传到七牛云存储服务器的文件Key值。
                final String fileKey = getFileKey(imageFile);
                // 创建七牛上传文件管理器
                UploadManager uploadManager = new UploadManager();
                uploadManager.put(imageFile, fileKey, uploadToken, new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject response) {
                        if (info.isOK()) {
                            String resultUrl = '/' + fileKey;
                            resultUrls.add(resultUrl);
                            if (resultUrls.size() == imageFileSize) {
                                mStatus = ProcessStatus.Status.Success;
                                mListener.onResponse(requestId);
                            }
                        } else {
                            if (isFailure == false) {
                                if (info.statusCode == FAILURE_EXPIRE) {
                                    mStatus = ProcessStatus.Status.ErrLoginTimeOut;
                                    new AsyncComm(requestId, mListener).execute();
                                } else {
                                    mStatus = ProcessStatus.Status.ErrUnkown;
                                    mListener.onResponse(requestId);
                                }
                            }
                            isFailure = true;
                        }
                    }
                }, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
