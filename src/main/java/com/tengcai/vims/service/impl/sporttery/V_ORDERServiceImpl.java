package com.tengcai.vims.service.impl.sporttery;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengcai.vims.entity.sporttery.LOTO_B;
import com.tengcai.vims.entity.sporttery.LOTO_F;
import com.tengcai.vims.entity.sporttery.LOTO_ORDER;
import com.tengcai.vims.entity.sporttery.T_LOTO_E;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_E;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_F;
import com.tengcai.vims.entity.sporttery.T_QUOTATION_CONTROL;
import com.tengcai.vims.entity.sporttery.V_ORDER;
import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.dao.sporttery.LOTO_BNDao;
import com.tengcai.vims.dao.sporttery.LOTO_ENDao;
import com.tengcai.vims.dao.sporttery.LOTO_FNDao;
import com.tengcai.vims.dao.sporttery.LOTO_ORDERDao;
import com.tengcai.vims.dao.sporttery.LOTO_ORDER_EDao;
import com.tengcai.vims.dao.sporttery.T_LOTO_SIS_BDao;
import com.tengcai.vims.dao.sporttery.T_LOTO_SIS_EDao;
import com.tengcai.vims.dao.sporttery.T_LOTO_SIS_FDao;
import com.tengcai.vims.dao.sporttery.V_ORDERDao;
import com.tengcai.vims.dao.sporttery.V_ORDER_EDao;
import com.tengcai.vims.service.sporttery.V_ORDERService;
import com.tengcai.vims.sp.SPQ;
import com.tengcai.vims.util.DateUtils;
import com.tengcai.vims.util.IOUtils;
import com.tengcai.vims.util.Md5;
import com.tengcai.vims.util.PostUtils;
import com.tengcai.vims.util.StringUtil;
import com.tengcai.vims.util.jdbet.BetUtils;

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
	private V_ORDER_EDao v_ORDER_EDao;
	@Autowired
	private LOTO_ORDER_EDao loto_ORDER_EDao;
	@Autowired
	private T_LOTO_SIS_BDao t_LOTO_SIS_BDao;
	@Autowired
	private T_LOTO_SIS_FDao t_LOTO_SIS_FDao;
	@Autowired
	private T_LOTO_SIS_EDao t_LOTO_SIS_EDao;
	@Autowired
	private LOTO_BNDao loto_BNDao;
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

	private void updateBPs(String issue, HashMap<String, Boolean> canChangeOdd, List<String> canBet,
			T_QUOTATION_CONTROL t_quotation_control) {
		logger.info("设置篮球当前投注赔率开始-->");

		T_LOTO_SIS_B t_loto_sis_b = new T_LOTO_SIS_B();
		t_loto_sis_b.setIssue(issue);
		List<T_LOTO_SIS_B> lstT_LOTO_SIS_B = null;
		try {
			lstT_LOTO_SIS_B = t_LOTO_SIS_BDao.selectT_LOTO_SIS_BList(t_loto_sis_b);
		} catch (Exception e1) {
			logger.error("取得篮球当前投注额出错-->" + e1.getMessage(), e1);
		}
		if (lstT_LOTO_SIS_B.isEmpty() == false) {
			t_loto_sis_b = lstT_LOTO_SIS_B.get(0);
		}
		SPQ spq = new SPQ();

		LOTO_B loto_bn = new LOTO_B();
		loto_bn.setIssue(issue);

		List<LOTO_B> lst_B = null;
		try {
			lst_B = loto_BNDao.selectLOTO_BNList(loto_bn);
		} catch (Exception e) {
			logger.error("取得篮球初始赔率出错" + e.getMessage(), e);
		}
		loto_bn = lst_B.get(0);
		if (lst_B.size() > 0) {
			boolean hasChange = false;
			if (t_quotation_control.getXnpktze() != null || t_quotation_control.getXnpktze() != 0) {
				spq.setFirstb(t_quotation_control.getXnpktze());
			}
			if (canBet.indexOf(loto_bn.getIssue() + "|309") >= 0) {
				loto_bn.setHilo_bet(0);
				hasChange = true;
			}
			if (canBet.indexOf(loto_bn.getIssue() + "|307") >= 0) {
				loto_bn.setMnl_bet(0);
				hasChange = true;
			}
			if (canChangeOdd.get(loto_bn.getIssue() + "|309") != null
					&& canChangeOdd.get(loto_bn.getIssue() + "|309")) {
				spq.setSps(new double[] {
						Double.parseDouble(
								loto_bn.getHilo_h_0().equals("") ? loto_bn.getHilo_h() : loto_bn.getHilo_h_0()),
						Double.parseDouble(
								loto_bn.getHilo_l_0().equals("") ? loto_bn.getHilo_l() : loto_bn.getHilo_l_0()) });

				spq.setActbs(new double[] { t_loto_sis_b.getHilo_h_d(), t_loto_sis_b.getHilo_l_d() });

				double[] nowps = spq.getNowps();
				loto_bn.setHilo_h(String.format("%.2f", nowps[0]));
				loto_bn.setHilo_l(String.format("%.2f", nowps[1]));
				hasChange = true;
			}

			if (canChangeOdd.get(loto_bn.getIssue() + "|307") != null
					&& canChangeOdd.get(loto_bn.getIssue() + "|307")) {
				spq.setSps(
						new double[] {
								Double.parseDouble(
										loto_bn.getMnl_h_0().equals("") ? loto_bn.getMnl_h() : loto_bn.getMnl_h_0()),
								Double.parseDouble(
										loto_bn.getMnl_a_0().equals("") ? loto_bn.getMnl_a() : loto_bn.getMnl_a_0()) });

				spq.setActbs(new double[] { t_loto_sis_b.getMnl_h_d(), t_loto_sis_b.getMnl_a_d() });

				double[] nowps = spq.getNowps();
				loto_bn.setMnl_h(String.format("%.2f", nowps[0]));
				loto_bn.setMnl_a(String.format("%.2f", nowps[1]));
				hasChange = true;
			}
			try {
				if (hasChange) {
					if (loto_bn.getHilo_h().equals("1.01") || loto_bn.getHilo_l().equals("1.01")) {
						loto_bn.setHilo_bet(0);
					}
					if (loto_bn.getMnl_h().equals("1.01") || loto_bn.getMnl_a().equals("1.01")) {
						loto_bn.setMnl_bet(0);
					}
					loto_BNDao.updateLOTO_BN(loto_bn);
				}
			} catch (Exception e) {
				logger.error("修改篮球即时投注赔率出错-->" + e.getMessage(), e);
			}

		}

		logger.info("设置当前篮球投注赔率成功结束-->");
	}

	private void updateFPs(String issue, HashMap<String, Boolean> canChangeOdd, List<String> canBet,
			T_QUOTATION_CONTROL t_quotation_control) {
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
			if (t_quotation_control.getXnpktze() != null || t_quotation_control.getXnpktze() != 0) {
				spq.setFirstb(t_quotation_control.getXnpktze());
			}
			double[] nowps = null;
			boolean hasChange = false;
			if (canBet.indexOf(loto_fn.getIssue() + "|301") >= 0) {
				loto_fn.setHad_bet(0);
				hasChange = true;
			}
			if (canBet.indexOf(loto_fn.getIssue() + "|305") >= 0) {
				loto_fn.setHhad_bet(0);
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
			if (canChangeOdd.get(loto_fn.getIssue() + "|305") != null
					&& canChangeOdd.get(loto_fn.getIssue() + "|305")) {
				if (StringUtil.isEmpty(loto_fn.getHhad_a()) && StringUtil.isEmpty(loto_fn.getHhad_a_0())) {

				} else {
					spq.setSps(new double[] {
							Double.parseDouble(
									loto_fn.getHhad_h_0().equals("") ? loto_fn.getHhad_h() : loto_fn.getHhad_h_0()),
							Double.parseDouble(
									loto_fn.getHhad_d_0().equals("") ? loto_fn.getHhad_d() : loto_fn.getHhad_d_0()),
							Double.parseDouble(
									loto_fn.getHhad_a_0().equals("") ? loto_fn.getHhad_a() : loto_fn.getHhad_a_0()) });

					spq.setActbs(new double[] { t_loto_sis_f.getHhad_h_d(), t_loto_sis_f.getHhad_d_d(),
							t_loto_sis_f.getHhad_a_d() });

					nowps = spq.getNowps();
					loto_fn.setHhad_h(String.format("%.2f", nowps[0]));
					loto_fn.setHhad_d(String.format("%.2f", nowps[1]));
					loto_fn.setHhad_a(String.format("%.2f", nowps[2]));
				}
				hasChange = true;
			}
			try {
				if (hasChange) {
					if (loto_fn.getHad_h().equals("1.01") || loto_fn.getHad_d().equals("1.01")
							|| loto_fn.getHad_a().equals("1.01")) {
						loto_fn.setHad_bet(0);
					}
					if (loto_fn.getHhad_h().equals("1.01") || loto_fn.getHhad_d().equals("1.01")
							|| loto_fn.getHhad_a().equals("1.01")) {
						loto_fn.setHhad_bet(0);
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
			List<String> canBet, T_QUOTATION_CONTROL t_quotation_control) throws Exception {

		if (vOrder.getBet_type() == 407) {
			loto_ORDER_EDao.insertLOTO_ORDER(lstLotoOrder);
			v_ORDER_EDao.insertV_ORDER(vOrder);
		} else {
			loto_ORDERDao.insertLOTO_ORDER(lstLotoOrder);
			v_ORDERDao.insertV_ORDER(vOrder);
		}
		// 统计支持率
		for (LOTO_ORDER t : lstLotoOrder) {

			if (t.getBet_type() == 309) {
				T_LOTO_SIS_B t_loto_sis_b = new T_LOTO_SIS_B();
				t_loto_sis_b.setIssue(t.getIssue());

				t_loto_sis_b.setHilo_h(t.getBet_info().startsWith("hilo_h") ? 1 : 0);
				t_loto_sis_b.setHilo_l(t.getBet_info().startsWith("hilo_l") ? 1 : 0);
				t_loto_sis_b.setHilo_h_d(t.getBet_info().startsWith("hilo_h") ? t.getBet_fee() : 0);
				t_loto_sis_b.setHilo_l_d(t.getBet_info().startsWith("hilo_l") ? t.getBet_fee() : 0);
				t_loto_sis_b.setMnl_a(0);
				t_loto_sis_b.setMnl_h(0);
				t_loto_sis_b.setMnl_h_d(0);
				t_loto_sis_b.setMnl_a_d(0);

				this.t_LOTO_SIS_BDao.saveSis(t_loto_sis_b);

			} else if (t.getBet_type() == 307) {
				T_LOTO_SIS_B t_loto_sis_b = new T_LOTO_SIS_B();
				t_loto_sis_b.setIssue(t.getIssue());
				t_loto_sis_b.setHilo_h(0);
				t_loto_sis_b.setHilo_l(0);
				t_loto_sis_b.setHilo_h_d(0);
				t_loto_sis_b.setHilo_l_d(0);
				t_loto_sis_b.setMnl_h(t.getBet_info().startsWith("mnl_h") ? 1 : 0);
				t_loto_sis_b.setMnl_a(t.getBet_info().startsWith("mnl_a") ? 1 : 0);
				t_loto_sis_b.setMnl_h_d(t.getBet_info().startsWith("mnl_h") ? t.getBet_fee() : 0);
				t_loto_sis_b.setMnl_a_d(t.getBet_info().startsWith("mnl_a") ? t.getBet_fee() : 0);
				this.t_LOTO_SIS_BDao.saveSis(t_loto_sis_b);
			} else if (t.getBet_type() == 301) {
				T_LOTO_SIS_F t_loto_sis_f = new T_LOTO_SIS_F();
				t_loto_sis_f.setIssue(t.getIssue());
				t_loto_sis_f.setHad_h(t.getBet_info().startsWith("had_h") ? 1 : 0);
				t_loto_sis_f.setHad_d(t.getBet_info().startsWith("had_d") ? 1 : 0);
				t_loto_sis_f.setHad_a(t.getBet_info().startsWith("had_a") ? 1 : 0);
				t_loto_sis_f.setHad_h_d(t.getBet_info().startsWith("had_h") ? t.getBet_fee() : 0);
				t_loto_sis_f.setHad_d_d(t.getBet_info().startsWith("had_d") ? t.getBet_fee() : 0);
				t_loto_sis_f.setHad_a_d(t.getBet_info().startsWith("had_a") ? t.getBet_fee() : 0);
				t_loto_sis_f.setHhad_h(0);
				t_loto_sis_f.setHhad_d(0);
				t_loto_sis_f.setHhad_a(0);
				t_loto_sis_f.setHhad_h_d(0);
				t_loto_sis_f.setHhad_d_d(0);
				t_loto_sis_f.setHhad_a_d(0);
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
				t_loto_sis_f.setHhad_h(t.getBet_info().startsWith("hhad_h") ? 1 : 0);
				t_loto_sis_f.setHhad_d(t.getBet_info().startsWith("hhad_d") ? 1 : 0);
				t_loto_sis_f.setHhad_a(t.getBet_info().startsWith("hhad_a") ? 1 : 0);
				t_loto_sis_f.setHhad_h_d(t.getBet_info().startsWith("hhad_h") ? t.getBet_fee() : 0);
				t_loto_sis_f.setHhad_d_d(t.getBet_info().startsWith("hhad_d") ? t.getBet_fee() : 0);
				t_loto_sis_f.setHhad_a_d(t.getBet_info().startsWith("hhad_a") ? t.getBet_fee() : 0);

				this.t_LOTO_SIS_FDao.saveSisT_LOTO_SIS_F(t_loto_sis_f);
			} else if (t.getBet_type() == 407) {
				T_LOTO_SIS_E t_loto_sis_e = new T_LOTO_SIS_E();
				t_loto_sis_e.setIssue(t.getIssue());
				t_loto_sis_e.setMnl_h(t.getBet_info().startsWith("mnl_h") ? 1 : 0);
				t_loto_sis_e.setMnl_a(t.getBet_info().startsWith("mnl_a") ? 1 : 0);
				t_loto_sis_e.setMnl_h_d(t.getBet_info().startsWith("mnl_h") ? t.getBet_fee() : 0);
				t_loto_sis_e.setMnl_a_d(t.getBet_info().startsWith("mnl_a") ? t.getBet_fee() : 0);
				this.t_LOTO_SIS_EDao.saveSis(t_loto_sis_e);
			}
		}

		logger.info("开始调用支付接口");
		Integer betResult = BetUtils.Bet(vOrder).getCode();
		if (betResult == null) {
			throw new Exception("调用接口异常");
		}
		if (betResult == 200) {
			logger.info(String.format("调用京东神豆bet接口成功----->userPin:%s bet_type:%s bet_info:%s bet_fee:%s",
					lstLotoOrder.get(0).getUser_pin(), lstLotoOrder.get(0).getBet_type(),
					lstLotoOrder.get(0).getBet_info(), lstLotoOrder.get(0).getBet_fee()));
		} else {
			if (betResult == 201) {
				logger.info(
						String.format("调用京东神豆bet接口 201 成功但没有符合要求的数据----->userPin:%s bet_type:%s bet_info:%s bet_fee:%s",
								lstLotoOrder.get(0).getUser_pin(), lstLotoOrder.get(0).getBet_type(),
								lstLotoOrder.get(0).getBet_info(), lstLotoOrder.get(0).getBet_fee()));

			} else if (betResult == 300) {
				logger.info(String.format("调用京东神豆bet接口,300 游戏已下线----->userPin:%s bet_type:%s bet_info:%s bet_fee:%s",
						lstLotoOrder.get(0).getUser_pin(), lstLotoOrder.get(0).getBet_type(),
						lstLotoOrder.get(0).getBet_info(), lstLotoOrder.get(0).getBet_fee()));
			} else if (betResult == 301) {
				logger.info(String.format("调用京东神豆bet接口,301 接口版本错误----->userPin:%s bet_type:%s bet_info:%s bet_fee:%s",
						lstLotoOrder.get(0).getUser_pin(), lstLotoOrder.get(0).getBet_type(),
						lstLotoOrder.get(0).getBet_info(), lstLotoOrder.get(0).getBet_fee()));
			} else if (betResult == 302) {
				logger.info(String.format(
						"调用京东神豆bet接口,302 中奖结果通知处理失败，校验后重新提交（可能存在重复数据）----->userPin:%s bet_type:%s bet_info:%s bet_fee:%s",
						lstLotoOrder.get(0).getUser_pin(), lstLotoOrder.get(0).getBet_type(),
						lstLotoOrder.get(0).getBet_info(), lstLotoOrder.get(0).getBet_fee()));
			} else if (betResult == 400) {
				logger.info(String.format("调用京东神豆bet接口,400 请求参数错误----->userPin:%s bet_type:%s bet_info:%s bet_fee:%s",
						lstLotoOrder.get(0).getUser_pin(), lstLotoOrder.get(0).getBet_type(),
						lstLotoOrder.get(0).getBet_info(), lstLotoOrder.get(0).getBet_fee()));
			} else if (betResult == 500) {
				logger.info(String.format("调用京东神豆bet接口,500 服务器错误----->userPin:%s bet_type:%s bet_info:%s bet_fee:%s",
						lstLotoOrder.get(0).getUser_pin(), lstLotoOrder.get(0).getBet_type(),
						lstLotoOrder.get(0).getBet_info(), lstLotoOrder.get(0).getBet_fee()));
			}

			throw new Exception("扣除京东神豆失败");
		}

		if (vOrder.getBet_type() == 309 || vOrder.getBet_type() == 307) {
			updateBPs(vOrder.getIssume(), canChangeOdd, canBet, t_quotation_control);
		} else if (vOrder.getBet_type() == 301 || vOrder.getBet_type() == 305) {
			updateFPs(vOrder.getIssume(), canChangeOdd, canBet, t_quotation_control);
		} else {
			///updateDPs(vOrder.getIssume(), canChangeOdd, canBet);
		}

		return 0;
	}

	private static String djUrl = IOUtils.getConfigParam("dj.url", "dj.properties");
	private static String djKey = IOUtils.getConfigParam("dj.key", "dj.properties");

	@Override
	public void updateDPs(String issume, HashMap<String, Boolean> canChangeOdd, List<String> canBet) throws Exception {

		if (canChangeOdd.get(issume + "|407") != null && canChangeOdd.get(issume + "|407")) {
			String timestamp = DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"); // 时间戳

			String signature = Md5.EncoderByMd5(timestamp + "#" + djKey).toLowerCase(); // 签名
																						// MD5(timestamp+"#"+key)

			// 对阵编号
			logger.info("调用计算电竞赔率开始---->");
			try{
			@SuppressWarnings("unused")
			JSONObject pvRv = JSONObject.parseObject(PostUtils.doPost(djUrl, String.format(
					"{\"timestamp\":\"%s\",\"signature\":\"%s\",\"issue\":\"%s\"}", timestamp, signature, issume)));
			logger.info("调用计算电竞赔率成功---->"+JSONObject.toJSONString(pvRv));
			}catch(Exception e){
				logger.info("调用计算电竞赔率异常---->"+djUrl+"|"+String.format(
						"{\"timestamp\":\"%s\",\"signature\":\"%s\",\"issue\":\"%s\"}", timestamp, signature, issume));
			}
			
		}
		T_LOTO_E loto_en = new T_LOTO_E();
		loto_en.setIssue(issume);
		List<T_LOTO_E> lst_LOTO_E = loto_ENDao.selectLOTO_ENList(loto_en);
		if (lst_LOTO_E.size() > 0) {
			if (canBet.indexOf(issume + "|407") >= 0 || lst_LOTO_E.get(0).getMnl_h().equals("1.01")
					|| lst_LOTO_E.get(0).getMnl_a().equals("1.01")) {
				lst_LOTO_E.get(0).setMnl_bet(0);
				loto_ENDao.updateLOTO_EN(lst_LOTO_E.get(0));
			}
		}
	}

}