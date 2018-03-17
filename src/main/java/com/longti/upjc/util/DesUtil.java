package com.longti.upjc.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

public class DesUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(DesUtil.class);

	private final static String DES = "DES";
	private final static String PADDING = "DES/ECB/PKCS5Padding";

	/**
	 * 加密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(PADDING);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);

	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */

	private static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(PADDING);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);

	}

	/**
	 * 密码解密
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */

	public static String decrypt(String data, String key, String memo) {
		try {
			LOGGER.info("对用户PIN进行解密操作---->" + data, data);
			return new String(decrypt(Base64.decodeBase64(data.replace("_", "/").getBytes()), key.getBytes()), "utf-8");
		} catch (Exception e) {
			LOGGER.error("解密失败，pin:{},seed:{}", data, key);
			return memo;
		}
	}

	/**
	 * 密码加密
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */

	public static String encrypt(String code, String key, String memo) {
		try {
			LOGGER.info("对用户PIN进行加密操作---->" + code, code);
			final byte[] encrypt = encrypt(code.getBytes("utf-8"), key.getBytes());
			return Base64.encodeBase64String(encrypt).replace("/", "_");
		} catch (Exception e) {
			LOGGER.error("加密失败", e);
			return memo;
		}
	}

}