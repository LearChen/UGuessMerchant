package uguess.qucai.com.merchant.business.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

/**
 * Created by Administrator on 2015/9/1.
 */
public class PaymentUtil {

    //微信支付工具
    public static class WXPay{
        //产生随机串
        public static String CreateNoncestr() {
            String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            String res = "";
            for (int i = 0; i < 16; i++) {
                Random rd = new Random();
                res += chars.charAt(rd.nextInt(chars.length() - 1));
            }
            return res;
        }

        public static String createSign(String characterEncoding,SortedMap<Object,Object> parameters){
            StringBuffer sb = new StringBuffer();
            Set es = parameters.entrySet();
            Iterator it = es.iterator();
            while(it.hasNext()) {
                Map.Entry entry = (Map.Entry)it.next();
                String k = (String)entry.getKey();
                Object v = entry.getValue();
                if(null != v && !"".equals(v)
                        && !"sign".equals(k) && !"key".equals(k)) {
                    sb.append(k + "=" + v + "&");
                }
            }
            sb.append("key=" + "55b6e90a0cf2b2ea15177596tx7c6p51");
            String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
            return sign;
        }
    }




}
