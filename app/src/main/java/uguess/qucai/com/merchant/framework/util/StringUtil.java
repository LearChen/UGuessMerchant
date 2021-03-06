package uguess.qucai.com.merchant.framework.util;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static String bytes2String(byte[] value) {
        return (value == null) ? "" : new String(value);
    }

    public static boolean isEmpty(String paramString) {
        if ((paramString == null) || ("".equals(paramString))) {
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String str) {
        final String number = "0123456789";
        for (int i = 0; i < str.length(); i++) {
            if (number.indexOf(str.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    public static String reverse(String value) {
        StringBuilder sb = new StringBuilder();
        for (int i = value.length() - 1; i >= 0; --i) {
            sb.append(value.charAt(i));
        }
        return sb.toString();
    }

    public static String createUUID() {
        return UUID.randomUUID().toString();
    }

    public static boolean isMobileNum(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
//        System.out.println(m.matches() + "---");
        return m.matches();
    }

    public static Date stringToDate(String str) throws ParseException {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sim.parse(str);
        return d;
    }

    /**
     * MD5加密。
     *
     * @param s 原文
     * @return 密文
     */
    public final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 判断答案是的选项顺序
     * @param answer
     * @return
     */
    public static String checkEume(String answer){
        Pattern pattern = Pattern.compile("[0-9]*");
        if (pattern.matcher(answer).matches()){
            int i = Integer.parseInt(answer);
           return checkEume(i);
        }else {
            return answer;
        }
    }


    /**
     * 通过选项答案的index,check为选项英文
     * @param i
     * @return
     */
    public static String checkEume(int i){
        switch (i){
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "c";
            case 4:
                return "D";
            case 5:
                return "E";
            case 6:
                return "F";
            case 7:
                return "G";
            case 8:
                return "H";
            case 9:
                return "I";
            default:
                return "0";
        }
    }
}
