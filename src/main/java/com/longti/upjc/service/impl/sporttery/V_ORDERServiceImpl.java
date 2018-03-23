package com.longti.upjc.service.impl.sporttery;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.icu.math.BigDecimal;
import com.longti.upjc.dao.sporttery.LOTO_ENDao;
import com.longti.upjc.dao.sporttery.LOTO_FNDao;
import com.longti.upjc.dao.sporttery.LOTO_ORDERDao;
import com.longti.upjc.dao.sporttery.T_LOTO_SIS_EDao;
import com.longti.upjc.dao.sporttery.T_LOTO_SIS_FDao;
import com.longti.upjc.dao.sporttery.V_ORDERDao;
import com.longti.upjc.entity.sporttery.LOTO_F;
import com.longti.upjc.entity.sporttery.LOTO_ORDER;
import com.longti.upjc.entity.sporttery.TAB_SALES_THRESHOLD;
import com.longti.upjc.entity.sporttery.T_LOTO_E;
import com.longti.upjc.entity.sporttery.T_LOTO_SIS_E;
import com.longti.upjc.entity.sporttery.T_LOTO_SIS_F;
import com.longti.upjc.entity.sporttery.V_ORDER;
import com.longti.upjc.service.sporttery.V_ORDERService;
import com.longti.upjc.sp.SPQ;
import com.longti.upjc.util.StringUtil;
import com.longti.upjc.util.jdbet.BetUtils;

/**
 * serviceImpl
 */
@Service
public class V_ORDERServiceImpl implements V_ORDERService {
	protected final transient static Logger logger = LoggerFactory.getLogger(V_ORDERServiceImpl.class);
	@Autowired
	private V_ORDERDao v_ORDERDao;
	@Autowired
	private LOTO_ORDERDao loto_ORDERDao;
	
	@Autowired
	private T_LOTO_SIS_FDao t_LOTO_SIS_FDao;
	@Autowired
	private T_LOTO_SIS_EDao t_LOTO_SIS_EDao;
	
	@Autowired
	private LOTO_FNDao loto_FNDao;
	@Autowired
	private LOTO_ENDao loto_ENDao;

	/**
	 * 条件查询
	 */
	public List<V_ORDER> selectV_ORDERList(V_ORDER v_order) throws Exception {
		return v_ORDERDao.selectV_ORDERList(v_order);
	}

	/**
	 * 条件查询数量
	 */
	public int selectV_ORDERCount(V_ORDER v_order) throws Exception {
		return v_ORDERDao.selectV_ORDERCount(v_order);
	}

	/**
	 * 添加
	 */
	public int insertV_ORDER(V_ORDER v_order) throws Exception {
		return v_ORDERDao.insertV_ORDER(v_order);
	}

	/**
	 * 批量添加
	 */
	public int insertV_ORDER(List<V_ORDER> list) throws Exception {
		return v_ORDERDao.insertV_ORDER(list);
	}

	/**
	 * 修改
	 */
	public int updateV_ORDER(V_ORDER v_order) throws Exception {
		return v_ORDERDao.updateV_ORDER(v_order);
	}

	/**
	 * 批量修改
	 */
	public int updateV_ORDER(List<V_ORDER> list) throws Exception {
		return v_ORDERDao.updateV_ORDER(list);
	}

	/**
	 * 删除
	 */
	public int deleteV_ORDER(V_ORDER v_order) throws Exception {
		return v_ORDERDao.deleteV_ORDER(v_order);
	}

	private void updateFPs(String issue, HashMap<String, Boolean> canChangeOdd, List<String> canBet,
			TAB_SALES_THRESHOLD tab_SALES_THRESHOLD) {
		logger.info("设置足球当前投注赔率开始-->");

		T_LOTO_SIS_F t_loto_sis_f = new T_LOTO_SIS_F();
		t_loto_sis_f.setIssue(issue);
		List<T_LOTO_SIS_F> lstT_LOTO_SIS_F = null;
		try {
			lstT_LOTO_SIS_F = t_LOTO_SIS_FDao.selectT_LOTO_SIS_FList(t_loto_sis_f);
		} catch (Exception e1) {
			logger.error("取得足球当前投注额出错-->" + e1.getMessage(), e1);
		}
		if (lstT_LOTO_SIS_F.isEmpty() == false) {
			t_loto_sis_f = lstT_LOTO_SIS_F.get(0);
		}

		SPQ spq = new SPQ();

		LOTO_F loto_fn = new LOTO_F();
		loto_fn.setIssue(issue);

		List<LOTO_F> lst_F = null;
		try {
			lst_F = loto_FNDao.selectLOTO_FNList(loto_fn);
		} catch (Exception e) {
			logger.error("取得足球初始赔率出错" + e.getMessage(), e);
		}
		loto_fn = lst_F.get(0);
		if (lst_F.size() > 0) {
			if (tab_SALES_THRESHOLD.getXnpktze() != null || tab_SALES_THRESHOLD.getXnpktze() != 0) {
				spq.setFirstb(tab_SALES_THRESHOLD.getXnpktze());
			}
			double[] nowps = null;
			boolean hasChange = false;
			if (canBet.indexOf(loto_fn.getIssue() + "|301") >= 0) {
				loto_fn.setHad_bet(0);
				hasChange = true;
			}
			
			if (canChangeOdd.get(loto_fn.getIssue() + "|301") != null
					&& canChangeOdd.get(loto_fn.getIssue() + "|301")) {
				if (StringUtil.isEmpty(loto_fn.getHad_a()) && StringUtil.isEmpty(loto_fn.getHad_a_0())) {

				} else {
					spq.setSps(new double[] {
							Double.parseDouble(
									loto_fn.getHad_h_0().equals("") ? loto_fn.getHad_h() : loto_fn.getHad_h_0()),
							Double.parseDouble(
									loto_fn.getHad_d_0().equals("") ? loto_fn.getHad_d() : loto_fn.getHad_d_0()),
							Double.parseDouble(
									loto_fn.getHad_a_0().equals("") ? loto_fn.getHad_a() : loto_fn.getHad_a_0()) });

					spq.setActbs(new double[] { t_loto_sis_f.getHad_h_d(), t_loto_sis_f.getHad_d_d(),
							t_loto_sis_f.getHad_a_d() });

					nowps = spq.getNowps();
					loto_fn.setHad_h(String.format("%.2f", nowps[0]));
					loto_fn.setHad_d(String.format("%.2f", nowps[1]));
					loto_fn.setHad_a(String.format("%.2f", nowps[2]));
				}
				hasChange = true;
			}
			
			try {
				if (hasChange) {
					if (loto_fn.getHad_h().equals("1.01") || loto_fn.getHad_d().equals("1.01")
							|| loto_fn.getHad_a().equals("1.01")) {
						loto_fn.setHad_bet(0);
					}
					
					loto_FNDao.updateLOTO_FN(loto_fn);
				}
			} catch (Exception e) {
				logger.error("修改足球即时投注赔率出错-->" + e.getMessage(), e);
			}

		}

		logger.info("设置当前足球投注赔率成功结束-->");
	}

	@Override
	public int insertV_ORDER(V_ORDER vOrder, List<LOTO_ORDER> lstLotoOrder, HashMap<String, Boolean> canChangeOdd,
			List<String> canBet, TAB_SALES_THRESHOLD tab_SALES_THRESHOLD) throws Exception {

		if (vOrder.getBet_type() == 501) {
			loto_ORDERDao.insertLOTO_ORDER(lstLotoOrder);
			v_ORDERDao.insertV_ORDER(vOrder);
		} else {
			loto_ORDERDao.insertLOTO_ORDER(lstLotoOrder);
			v_ORDERDao.insertV_ORDER(vOrder);
		}
		// 统计支持率
		for (LOTO_ORDER t : lstLotoOrder) {

			if (t.getBet_type() == 301) {
				T_LOTO_SIS_F t_loto_sis_f = new T_LOTO_SIS_F();
				t_loto_sis_f.setIssue(t.getIssue());
				t_loto_sis_f.setHad_h(t.getBet_info().startsWith("had_h") ? 1 : 0);
				t_loto_sis_f.setHad_d(t.getBet_info().startsWith("had_d") ? 1 : 0);
				t_loto_sis_f.setHad_a(t.getBet_info().startsWith("had_a") ? 1 : 0);
				t_loto_sis_f.setHad_h_d(t.getBet_info().startsWith("had_h") ? t.getBet_fee() : 0);
				t_loto_sis_f.setHad_d_d(t.getBet_info().startsWith("had_d") ? t.getBet_fee() : 0);
				t_loto_sis_f.setHad_a_d(t.getBet_info().startsWith("had_a") ? t.getBet_fee() : 0);
				this.t_LOTO_SIS_FDao.saveSisT_LOTO_SIS_F(t_loto_sis_f);
			} else if (t.getBet_type() == 305) {
				T_LOTO_SIS_F t_loto_sis_f = new T_LOTO_SIS_F();
				t_loto_sis_f.setIssue(t.getIssue());
				t_loto_sis_f.setHad_h(0);
				t_loto_sis_f.setHad_d(0);
				t_loto_sis_f.setHad_a(0);
				t_loto_sis_f.setHad_h_d(0);
				t_loto_sis_f.setHad_d_d(0);
				t_loto_sis_f.setHad_a_d(0);

				this.t_LOTO_SIS_FDao.saveSisT_LOTO_SIS_F(t_loto_sis_f);
			} else if (t.getBet_type() == 407) {
				T_LOTO_SIS_E t_loto_sis_e = new T_LOTO_SIS_E();
				t_loto_sis_e.setIssue(t.getIssue());
				t_loto_sis_e.setOne(t.getBet_info().startsWith("odds_one") ? 1L : 0L);
				t_loto_sis_e.setTwo(t.getBet_info().startsWith("odds_two") ? 1L : 0L);
				t_loto_sis_e.setThree(t.getBet_info().startsWith("odds_three") ? 1L : 0L);
				t_loto_sis_e.setOne_d(t.getBet_info().startsWith("odds_one") ? t.getBet_fee() : 0L);
				t_loto_sis_e.setTwo_d(t.getBet_info().startsWith("odds_two") ? t.getBet_fee() : 0L);
				t_loto_sis_e.setThree_d(t.getBet_info().startsWith("odds_three") ? t.getBet_fee() : 0L);
				this.t_LOTO_SIS_EDao.saveSis(t_loto_sis_e);
			}
		}

		logger.info("开始调用支付接口");
		String password="";
		String betResult = BetUtils.Bet(vOrder.getUser_pin(),vOrder.getElectronic_code(),vOrder.getOrder_id(),String.format("%.2f", vOrder.getBet_fee()/BetUtils.preMul),password).status;
		
		if (betResult == null) {
			throw new Exception("调用接口异常");
		}
		if (betResult.equals("success")) {
			logger.info(String.format("调用亚创支付接口成功----->userPin:%s bet_type:%s bet_info:%s bet_fee:%s",
					lstLotoOrder.get(0).getUser_pin(), lstLotoOrder.get(0).getBet_type(),
					lstLotoOrder.get(0).getBet_info(), lstLotoOrder.get(0).getBet_fee()));
		} else {			

			throw new Exception("调用亚创支付接口失败");
		}

		if (vOrder.getBet_type() == 301 ) {
			updateFPs(vOrder.getIssume(), canChangeOdd, canBet, tab_SALES_THRESHOLD);
		} else if(vOrder.getBet_type()==501){
			updateDPs(vOrder.getIssume(), canChangeOdd, canBet);
		}

		return 0;
	}

	
	@Override
	public void updateDPs(String issume, HashMap<String, Boolean> canChangeOdd, List<String> canBet) throws Exception {
		T_LOTO_E loto_en = new T_LOTO_E();
		loto_en.setIssue(issume);
		List<T_LOTO_E> lst_LOTO_E = loto_ENDao.selectLOTO_ENList(loto_en);
		if (lst_LOTO_E.size() > 0) {
			BigDecimal odds_one=new BigDecimal(lst_LOTO_E.get(0).getOdds_one());
			BigDecimal odds_two=new BigDecimal(lst_LOTO_E.get(0).getOdds_two());
			BigDecimal odds_three=new BigDecimal(lst_LOTO_E.get(0).getOdds_three());			
			
			if (canBet.indexOf(issume + "|501") >= 0 
					|| odds_one.compareTo(new BigDecimal("1.01"))<0
					|| odds_two.compareTo(new BigDecimal("1.01"))<0
					|| odds_three.compareTo(new BigDecimal("1.01"))<0) {
				lst_LOTO_E.get(0).setMnl_bet(0);
				loto_ENDao.updateLOTO_EN(lst_LOTO_E.get(0));
			}
		}
	}

}