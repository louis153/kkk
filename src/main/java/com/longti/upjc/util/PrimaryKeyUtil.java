package com.longti.upjc.util;

import java.util.UUID;

/**
 * 主键生成策略
 * @author dong
 *
 */
public class PrimaryKeyUtil {
	
	public static String getId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	
	public static String getId(String busi_tenant_id, String type_code){
		return busi_tenant_id + "_" + type_code + "_" + DateUtils.getDateToStrYMDHMS();
	}
	
	public static void main(String[] args) {
		System.out.println(getId());
	}
		
}
