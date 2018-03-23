package com.longti.upjc.util;

import java.math.BigDecimal;

/**
 * <p>Title:NumberUtils</p>
 * <p>描述:</p>
 * @author liuxiaodong
 * @date 2015年12月28日 下午5:38:56
 */
public final class NumberUtils {

	// 两个数之间的值，并以数组的形式返回
	public static int[] getHourArray(int hourfrom, int hourto){
		int[] hour_array = new int[hourto-hourfrom+1];
		for(int i=0; i<hour_array.length; i++){
			hour_array[i] = hourfrom + i;
		}
		return hour_array;
	}
	public static BigDecimal longDiv(long a,long b){
		BigDecimal a1=new BigDecimal(String.valueOf(a)+".00");
		BigDecimal b1=new BigDecimal(String.valueOf(b)+".00");
		return a1.divide(b1);
	}
}
