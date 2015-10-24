package uguess.qucai.com.merchant.business.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StoreWebUtil.java是趣猜App的util类。
 *
 * @author 王强
 * @version 1.0 2015/9/15
 */
public class StoreWebUtil {
    private String appKey;
    private String appSecret;

    public StoreWebUtil(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    /**
     * 通用的url生成方法
     * 如果下面的方法不能满足，可以使用此方法进行生成
     *
     * @param url
     * @param params
     * @return
     */
    public String buildUrlWithSign(String url, Map<String, String> params) {
        Map<String, String> newparams = new HashMap<String, String>(params);
        newparams.put("appKey", appKey);
        newparams.put("appSecret", appSecret);
        if (newparams.get("timestamp") == null) {
            newparams.put("timestamp", System.currentTimeMillis() + "");
        }
        String sign = SignTool.sign(newparams);
        newparams.put("sign", sign);

        newparams.remove("appSecret");

        return AssembleTool.assembleUrl(url, newparams);
    }

    /**
     * 构建在兑吧商城自动登录的url地址
     *
     * @param uid     用户id
     * @param credits 用户积分余额
     * @return 自动登录的url地址
     */
    public String buildCreditAutoLoginRequest(String uid, Long credits) {
        String url = "http://www.duiba.com.cn/autoLogin/autologin?";
        Map<String, String> params = new HashMap<String, String>();
        Long timestamp = new Date().getTime();
        params.put("uid", uid);
        params.put("credits", credits + "");
        params.put("appSecret", appSecret);
        params.put("appKey", appKey);
        params.put("timestamp", timestamp + "");
        String sign = SignTool.sign(params);
        url += "uid=" + uid + "&credits=" + credits + "&appKey=" + appKey + "&sign=" + sign + "&timestamp=" + timestamp;
        return url;
    }

    /**
     * 构建向兑吧查询兑换订单状态的url地址
     *
     * @param orderNum 兑吧的订单号
     * @return
     */
    public String buildCreditOrderStatusRequest(String orderNum, String bizId) {
        if (orderNum == null) {
            orderNum = "";
        }
        if (bizId == null) {
            bizId = "";
        }
        String url = "http://www.duiba.com.cn/status/orderStatus?";
        Map<String, String> params = new HashMap<String, String>();
        Long timestamp = new Date().getTime();
        params.put("orderNum", orderNum);
        params.put("bizId", bizId);
        params.put("appKey", appKey);
        params.put("appSecret", appSecret);
        params.put("timestamp", timestamp + "");
        String sign = SignTool.sign(params);
        url += "orderNum=" + orderNum + "&bizId=" + bizId + "&appKey=" + appKey + "&sign=" + sign + "&timestamp=" + timestamp;
        return url;
    }

    /**
     * 构建开发者审核结果的的方法
     *
     * @param params
     * @return 发起请求的url
     */
    public String buildCreditAuditRequest(CreditAuditParams params) {
        String url = "http://www.duiba.com.cn/audit/apiAudit?";
        Map<String, String> signParams = new HashMap<String, String>();
        Long timestamp = new Date().getTime();
        signParams.put("appKey", appKey);
        signParams.put("appSecret", appSecret);
        signParams.put("timestamp", timestamp + "");
        if (params.getPassOrderNums() != null && params.getPassOrderNums().size() > 0) {
            String s = null;
            for (String o : params.getPassOrderNums()) {
                if (s == null) {
                    s = o;
                } else {
                    s += "," + o;
                }
            }
            signParams.put("passOrderNums", s);
        } else {
            signParams.put("passOrderNums", "");
        }
        if (params.getRejectOrderNums() != null && params.getRejectOrderNums().size() > 0) {
            String s = null;
            for (String o : params.getRejectOrderNums()) {
                if (s == null) {
                    s = o;
                } else {
                    s += "," + o;
                }
            }
            signParams.put("rejectOrderNums", s);
        } else {
            signParams.put("rejectOrderNums", "");
        }
        String sign = SignTool.sign(signParams);

        url += "appKey=" + appKey + "&passOrderNums=" + signParams.get("passOrderNums") + "&rejectOrderNums=" + signParams.get("rejectOrderNums") + "&sign=" + sign
                + "&timestamp=" + timestamp;
        return url;
    }

    /**
     * 构建开发者向兑吧发起兑换成功失败的确认通知请求
     *
     * @param p
     * @return
     */
    public String buildCreditConfirmRequest(CreditConfirmParams p) {
        String url = "http://www.duiba.com.cn/confirm/confirm?";
        Map<String, String> params = new HashMap<String, String>();
        Long timestamp = new Date().getTime();
        params.put("appSecret", appSecret);
        params.put("appKey", appKey);
        params.put("timestamp", timestamp + "");
        params.put("success", p.isSuccess() + "");
        params.put("errorMessage", p.getErrorMessage());
        params.put("orderNum", p.getOrderNum());
        String sign = SignTool.sign(params);
        url += "appKey=" + appKey + "&sign=" + sign + "&timestamp=" + timestamp + "&success=" + p.isSuccess() + "&errorMessage=" + p.getErrorMessage() + "&orderNum=" + p.getOrderNum();
        return url;
    }
}

class AssembleTool {
    public static String assembleUrl(String url, Map<String, String> params) {
        if (!url.endsWith("?")) {
            url += "?";
        }
        for (String key : params.keySet()) {
            try {
                if (params.get(key) == null || params.get(key).length() == 0) {
                    url += key + "=" + params.get(key) + "&";
                } else {
                    url += key + "=" + URLEncoder.encode(params.get(key), "utf-8") + "&";
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return url;
    }
}

class SignTool {
    public static boolean signVerify(String appSecret, Map<String, String> params) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("appSecret", appSecret);

        for (String key : params.keySet()) {
            if (!key.equals("sign")) {
                map.put(key, params.get(key));
            }
        }

        String sign = sign(map);
        if (sign.equals(params.get("sign"))) {
            return true;
        }
        return false;
    }

    private static String toHexValue(byte[] messageDigest) {
        if (messageDigest == null)
            return "";
        StringBuilder hexValue = new StringBuilder();
        for (byte aMessageDigest : messageDigest) {
            int val = 0xFF & aMessageDigest;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * @param params
     * @return
     */
    public static String sign(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String string = "";
        for (String s : keys) {
            string += params.get(s);
        }
        String sign = "";
        try {
            sign = toHexValue(encryptMD5(string.getBytes(Charset.forName("utf-8"))));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("md5 error");
        }
        return sign;
    }

    private static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        return md5.digest();
    }
}

class CreditConfirmParams {
    private boolean success = true;
    private String errorMessage = "";
    private String orderNum = "";

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}

class CreditAuditParams {

    //审核通过的订单列表
    private List<String> passOrderNums = new ArrayList<String>();
    //审核不通过的订单列表
    private List<String> rejectOrderNums = new ArrayList<String>();

    public List<String> getPassOrderNums() {
        return passOrderNums;
    }

    public void setPassOrderNums(List<String> passOrderNums) {
        this.passOrderNums = passOrderNums;
    }

    public List<String> getRejectOrderNums() {
        return rejectOrderNums;
    }

    public void setRejectOrderNums(List<String> rejectOrderNums) {
        this.rejectOrderNums = rejectOrderNums;
    }
}