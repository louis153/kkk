/**
 * 
 */
/**
 * @author Dell
 *
 */
package com.longti.upjc.sp;

import java.util.Arrays;

public class SPQ {
	private double[] sps;
	private double[] actbs;
	private double firstb = 30000000;// 初始盘口（初始投注额）

	public double getFirstb() {
		return firstb;
	}

	public void setFirstb(double v) {
		firstb = v;
	}

	private double[] getFirstbs() {
		double[] firstbs = new double[sps.length];
		double q = getQ();
		for (int i = 0; i < sps.length; i++) {
			firstbs[i] = q / sps[i] * firstb;
		}
		return firstbs;
	}

	public double[] getNowps() {
		double[] nowps = new double[sps.length];
		double[] firstbs = getFirstbs();
		double sumActBs=0;
		for(double d:actbs){
			sumActBs+=d;
		}
		for (int i = 0; i < sps.length; i++) {
			nowps[i] = getQ() / ((actbs[i] + firstbs[i]) / (sumActBs + firstb));
			nowps[i]=Math.max(1.01, nowps[i]);
		}
		return nowps;
	}

	public void setSps(double[] sps) {
		this.sps = sps;
	}

	private double getQP() {
		double o = 0;
		for (double d : sps) {
			o += 1 / d;
		}
		return 1 / o;
	}

	private double getQ0() {
		return 0.8;
	}

	private double getQ() {
		if (getQP() > getQ0()) {// 判断实际反奖率是否大于预定的最大反奖率
			double b = 0;
			for (double d : actbs) {
				b += d;
			}
			return Math.exp(-b / 1000) * (getQP() - getQ0()) + getQ0();
		} else {
			return getQP();
		}
	}

	public void setActbs(double[] actbs) {
		this.actbs = actbs;
	}

	public static void main(String[] args) {
		SPQ spq = new SPQ();
		spq.setSps(new double[] { 1.3, 6.3, 6.3 });
		spq.setActbs(new double[]{1000000,1010000,1030000});
		System.out.println(Arrays.toString(spq.getNowps()));
	}
}