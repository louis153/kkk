package com.longti.upjc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;


public final class StringUtil {
	protected final transient static Logger logger = LoggerFactory.getLogger(PostUtils.class);
    /**
     * 返回星期字符串
     * @param weekItem	星期数组
     * @param suffix	后缀
     * @param @return
     * @return String
     * @throws
     */
    public static String getWeekStr(String[] weekItem, String suffix) {
        if (weekItem == null) {
            return "";
        }
        boolean boo = false;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < weekItem.length; i++) {
            if (weekItem[i].endsWith(suffix)) {
                sb.append(weekItem[i].split("\\.")[0]).append(" ");
                boo = true;
            }
        }
        if (boo) {
            return sb.deleteCharAt(sb.length() - 1).toString();
        }

        return "";
    }
    // 把字符串解析成07:00格式
    public static String parseStrformat(String str) {
        // 长度小于2
        if (str.length() < 2) {
            return "0" + str + ":00";
        } else {
            return str + ":00";
        }
    }

    // hour_price_map的key
    public static String hpmKey(String from, String to) {
        return parseStrformat(from) + "-" + parseStrformat(to);
    }

    public static int subtract(String hour_from, String hour_to) {
        return Integer.parseInt(hour_to) - Integer.parseInt(hour_from);
    }

    public static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes("UTF-8");
            // 获得MD5摘要算法的 MessageDigest 对象
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
            return new String(str);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public static HashMap<String, String> GetReqParams(HttpServletRequest request) {
        BufferedReader reader = null;
        HashMap<String, String> hmParams = new HashMap<String, String>();
        String line;
        try {
            reader = request.getReader();
        } catch (IOException e) {
            
            logger.error(e.getMessage());
        }

        try {
            while ((line = reader.readLine()) != null) {
                String[] lines = java.net.URLDecoder.decode(line, "utf-8").split("&");
                for (int i = 0; i < lines.length; i++) {
                    String str = lines[i];
                    hmParams.put(str.substring(0, str.indexOf("=")), str.substring(str.indexOf("=") + 1, str.length()).replace("____", "&"));
                }
            }
        } catch (IOException e) {
            
            logger.error(e.getMessage());
        }
        return hmParams;

    }

    //整数
    public static boolean isInteger(String input) {
        Matcher mer = Pattern.compile("^[0-9]+$").matcher(input);
        return mer.find();
    }

    //正整数
    public static boolean isSInteger(String input) {
        Matcher mer = Pattern.compile("/^[1-9]+\\d*$").matcher(input);
        return mer.find();
    }

    //非零整数
    public static boolean isDInteger(String input) {
        Matcher mer = Pattern.compile("/^[1-9]+\\d*$").matcher(input);
        boolean rv = mer.find();
        return rv;
    }

    //判断小数
    public static boolean isDecimal(String input) {
        Matcher mer = Pattern.compile("^(-?\\d+)(\\.\\d+)?$").matcher(input);
        return mer.find();
    }

    /**
    * 验证日期时间
    * 
    * @param 待验证的字符串
    * @return 如果是符合网址格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
    */
    public static boolean isDate(String str) {
        // 严格验证时间格式的(匹配[2002-01-31], [1997-04-30],
        // [2004-01-01])不匹配([2002-01-32], [2003-02-29], [04-01-01])
        // String regex =
        // "^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((01,3-9])|(1[0-2]))-(29|30)))))$";
        // 没加时间验证的YYYY-MM-DD
        // String regex =
        // "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
        // 加了时间验证的YYYY-MM-DD 00:00:00
        String regex = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";
        Matcher mer = Pattern.compile(regex).matcher(str);
        boolean rv = mer.find();
        return rv;
    }

    ///HH:00-HH:00格式的验证
    public static boolean isTimeDuring(String str) {
        String regex = "^\\d{2}:00-\\d{2}:00$";
        Matcher mer = Pattern.compile(regex).matcher(str);
        boolean rv = mer.find();
        return rv;
    }

    public static boolean isRuleDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static String FormatBadMoney(Object dv) {
        String change = "";
        if (dv == null) {
            change = "0";
        } else {
            change = String.format("%.2f", dv).replace(".00", "");
        }
        return (change.endsWith("0") && (!change.equals("0"))) ? change.substring(0, change.length() - 1) : change;
    }

    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        byte[] btInput = str.getBytes("utf-8");
        // 获得MD5摘要算法的 MessageDigest 对象
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        // 使用指定的字节更新摘要
        mdInst.update(btInput);
        // 获得密文
        byte[] md = mdInst.digest();
        // 把密文转换成十六进制的字符串形式
        int j = md.length;
        char s[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            s[k++] = hexDigits[byte0 >>> 4 & 0xf];
            s[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(s);
    }

    public static String[] getJsonToStringArray(String str) {
        JSONArray jsonArray = JSONArray.fromObject(str);
        String[] arr = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            arr[i] = jsonArray.getString(i);
        }
        return arr;
    }

    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    public static <T> T ifnull(T o, T def) {
        if (o == null)
            return def;
        else
            return o;
    }

    public static String ifnull(String str) {
        return ifnull(str, "");
    }

    public static boolean isEmpty(String... str) {
        for (String string : str) {
            if (isEmpty(string)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(String str) {
        return ifnull(str, "").trim().isEmpty();
    }
    public static <T> boolean isEmpty(T str) {
        return ifnull(str, "").toString().trim().isEmpty();
    }
    public static void main(String[] args) {
        System.out.println(String.format("%08d", 1234567));
    }

}
