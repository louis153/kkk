package com.tengcai.vims.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class IOUtils {
	protected final static transient Log logger = LogFactory.getLog(IOUtils.class);
	/**
	 * 获取Properties对象
	 * @param params
	 * @param fileName
	 * @return
	 */
	public static String getConfigParam(String params, String fileName) {
		String result = "";
		if (StringUtil.isEmpty(fileName) || StringUtil.isEmpty(params)) {
			return result;
		}
		try {
			Properties properties = loadConfig(fileName);
			result = properties.getProperty(params);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public static Properties loadConfig(String fileName) {
		Properties properties = new Properties();
		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			if (classLoader == null) {
				classLoader = IOUtils.class.getClassLoader();
			}
			InputStream is = classLoader.getResourceAsStream(fileName);
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}
}
