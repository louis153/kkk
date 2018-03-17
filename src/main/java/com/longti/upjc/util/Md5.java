package com.longti.upjc.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.common.hash.Hashing;



public class Md5 {
	

	public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		 //确定计算方法
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

        byte[] btInput = str.getBytes();
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
	public static String getDigest(String bodyXml) {
	    if (bodyXml==null||bodyXml.isEmpty()) return null;
	    try {
	        return Hashing.md5().newHasher().putString(bodyXml, Charset.forName("utf-8")).hash().toString();
	    } catch (Exception e) {
	        return null;
	    }
	}
 
	public static void main(String[] args) {
		String bodyXml="<body><response errorcode=\"0\"><oddsquery lotoid=\"301\" issue=\"\"><odds referodds=\"1.37|4.15|6.30|\" issue=\"201706202001\" bonusnum =\"|\"/><odds referodds=\"4.20|3.35|1.69|\" issue=\"201706202002\" bonusnum =\"|\"/><odds referodds=\"1.87|3.20|3.55|\" issue=\"201706202003\" bonusnum =\"|\"/><odds referodds=\"1.77|3.20|4.00|\" issue=\"201706202004\" bonusnum =\"|\"/><odds referodds=\"1.77|3.15|4.05|\" issue=\"201706202005\" bonusnum =\"|\"/><odds referodds=\"1.78|3.22|3.90|\" issue=\"201706213001\" bonusnum =\"|\"/><odds referodds=\"2.38|3.10|2.59|\" issue=\"201706213002\" bonusnum =\"|\"/><odds referodds=\"1.93|3.25|3.30|\" issue=\"201706213003\" bonusnum =\"|\"/><odds referodds=\"1.71|3.35|4.10|\" issue=\"201706213004\" bonusnum =\"|\"/><odds referodds=\"1.88|3.15|3.57|\" issue=\"201706213005\" bonusnum =\"|\"/><odds referodds=\"1.83|3.25|3.65|\" issue=\"201706213006\" bonusnum =\"|\"/><odds referodds=\"1.87|3.30|3.45|\" issue=\"201706213007\" bonusnum =\"|\"/><odds referodds=\"4.10|3.35|1.71|\" issue=\"201706213008\" bonusnum =\"|\"/><odds referodds=\"3.20|3.00|2.07|\" issue=\"201706213009\" bonusnum =\"|\"/><odds referodds=\"1.50|3.75|5.10|\" issue=\"201706213010\" bonusnum =\"|\"/><odds referodds=\"2.12|3.25|2.86|\" issue=\"201706213011\" bonusnum =\"|\"/><odds referodds=\"1.50|3.65|5.30|\" issue=\"201706213012\" bonusnum =\"|\"/><odds referodds=\"3.05|3.00|2.14|\" issue=\"201706213013\" bonusnum =\"|\"/><odds referodds=\"2.35|3.15|2.60|\" issue=\"201706213014\" bonusnum =\"|\"/><odds referodds=\"3.85|3.15|1.81|\" issue=\"201706213015\" bonusnum =\"|\"/><odds referodds=\"1.11|6.25|14.50|\" issue=\"201706213017\" bonusnum =\"|\"/><odds referodds=\"3.31|3.10|1.98|\" issue=\"201706213019\" bonusnum =\"|\"/><odds referodds=\"3.85|3.15|1.81|\" issue=\"201706213020\" bonusnum =\"|\"/><odds referodds=\"1.65|3.40|4.40|\" issue=\"201706213021\" bonusnum =\"|\"/><odds referodds=\"3.50|3.15|1.90|\" issue=\"201706213023\" bonusnum =\"|\"/><odds referodds=\"2.44|3.35|2.38|\" issue=\"201706213024\" bonusnum =\"|\"/><odds referodds=\"2.60|3.40|2.22|\" issue=\"201706213027\" bonusnum =\"|\"/><odds referodds=\"1.20|5.10|10.00|\" issue=\"201706213028\" bonusnum =\"|\"/><odds referodds=\"1.91|3.30|3.30|\" issue=\"201706213032\" bonusnum =\"|\"/><odds referodds=\"1.47|3.85|5.30|\" issue=\"201706213033\" bonusnum =\"|\"/></oddsquery></response></body>";
			System.out.println(Md5.getDigest("102336b136bc-8c48-4ad7-b40a-8d2f08c82918"+bodyXml));
		
	}
}
