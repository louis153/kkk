package com.tengcai.vims.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.LOTO_B;
import com.tengcai.vims.entity.sporttery.LOTO_F;
import com.tengcai.vims.entity.sporttery.T_ADVERT_CHANNEL;
import com.tengcai.vims.entity.sporttery.T_LOTO_E;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_E;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_F;
import com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL;
import com.tengcai.vims.entity.sporttery.T_QUOTATION_CONTROL;
import com.tengcai.vims.service.sporttery.LOTO_BNService;
import com.tengcai.vims.service.sporttery.LOTO_FNService;
import com.tengcai.vims.service.sporttery.T_ADVERT_CHANNELService;
import com.tengcai.vims.service.sporttery.T_LOTO_ENService;
import com.tengcai.vims.service.sporttery.T_LOTO_SIS_BService;
import com.tengcai.vims.service.sporttery.T_LOTO_SIS_EService;
import com.tengcai.vims.service.sporttery.T_LOTO_SIS_FService;
import com.tengcai.vims.service.sporttery.T_MARKET_CHANNELService;
import com.tengcai.vims.service.sporttery.T_QUOTATION_CONTROLService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.DateUtils;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;
import com.tengcai.vims.util.StringUtil;

/**
 * 查询足球推荐信息
 * 
 * @return
 * @throws Exception
 */
@Component("loto_homepage_rem_list")
public class LotoHomePageRemListStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(LotoHomePageRemListStrategy.class);

	public static class Homepage_remDetail {
		public String home_team_name = "";
		public String guest_team_name = "";
		public String leaguename = "";
		public String endtime = "";
		public String issue = "";
		public String l = "";

	}

	public static class Homepage_remDetailFoot extends Homepage_remDetail {
		public String hh = "";
		public String hd = "";
		public String ha = "";
		public String rh = "";
		public String rd = "";
		public String ra = "";
		public String hh_p = "";
		public String hd_p = "";
		public String ha_p = "";
		public String rh_p = "";
		public String rd_p = "";
		public String ra_p = "";
		public int h_bet=1;
		public int r_bet=1;
		
	}

	public static class Homepage_remDetailBasket extends Homepage_remDetail {
		public String hh = "";
		public String hl = "";
		public String mh = "";
		public String ma = "";
		public String hh_p = "";
		public String hl_p = "";
		public String mh_p = "";
		public String ma_p = "";
		public int h_bet=1;
		public int m_bet=1;
	}

	public static class Homepage_remDetailDj extends Homepage_remDetail {
		public String mh = "";
		public String ma = "";
		public String mh_p = "";
		public String ma_p = "";
		public String match_mode = "";
		public String image_name = "";
		public int m_bet=1;
	}

	public static class Homepage_Rem_Model {
		private String position;
		private int index;
		private int count;
		private List<Homepage_remDetail> lst_rem;

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public List<Homepage_remDetail> getLst_rem() {
			return lst_rem;
		}

		public void setLst_rem(List<Homepage_remDetail> lst_rem) {
			this.lst_rem = lst_rem;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}
	}

	public static class Homepage_RemData {
		private List<Homepage_Rem_Model> lst;

		public List<Homepage_Rem_Model> getLst() {
			return lst;
		}

		public void setLst(List<Homepage_Rem_Model> lst) {
			this.lst = lst;
		}
	}

	@Autowired
	private T_ADVERT_CHANNELService t_advert_channelService;
	@Autowired
	private T_MARKET_CHANNELService t_MARKET_CHANNELService;
	@Autowired
	private LOTO_FNService lotoFNService;
	@Autowired
	private LOTO_BNService lotoBNService;
	@Autowired
	private T_LOTO_ENService lotoENService;
	@Autowired
	private T_LOTO_SIS_FService t_LOTO_SIS_FService;
	@Autowired
	private T_LOTO_SIS_BService t_LOTO_SIS_BService;
	@Autowired
	private T_LOTO_SIS_EService t_LOTO_SIS_EService;
	@Autowired
	private T_QUOTATION_CONTROLService t_QUOTATION_CONTROLService;

	private T_QUOTATION_CONTROL quotation_foot_control;
	private T_QUOTATION_CONTROL quotation_basket_control;
	private T_QUOTATION_CONTROL quotation_dj_control;

	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("进入loto_homepage_rem_list首页比赛列表的方法：doJsonMethod");
		ReturnValue<Homepage_RemData> rv = new ReturnValue<Homepage_RemData>();
		rv.setData(new Homepage_RemData());
		rv.getData().setLst(new ArrayList<Homepage_Rem_Model>());
		T_QUOTATION_CONTROL quotation_control = new T_QUOTATION_CONTROL();
		List<T_QUOTATION_CONTROL> lstQuotation = t_QUOTATION_CONTROLService
				.selectT_QUOTATION_CONTROLList(quotation_control);
		for (T_QUOTATION_CONTROL q : lstQuotation) {
			if (q.getId() == 2) {
				quotation_foot_control = q;
			} else if (q.getId() == 3) {
				quotation_basket_control = q;
			} else if (q.getId() == 4) {
				quotation_dj_control = q;
			}
		}
		try {
			T_ADVERT_CHANNEL t_advert_channel = new T_ADVERT_CHANNEL();
			t_advert_channel.setAdtype(1);
			t_advert_channel.setAdstatus(1);
			String order_source = "";
			if (!jsonRequest.containsKey("order_source")) {
				order_source = "1";
			} else {
				order_source = jsonRequest.getString("order_source").trim().isEmpty() ? "1"
						: jsonRequest.getString("order_source");
			}
			if (StringUtil.isInteger(order_source) == false) {
				order_source = "1";
			}
			T_MARKET_CHANNEL t_market_channel = new T_MARKET_CHANNEL();
			t_market_channel.setChannelstate("1");
			t_market_channel.setChannelnum(Integer.valueOf(order_source));
			if (t_MARKET_CHANNELService.selectT_MARKET_CHANNELCount(t_market_channel) == 0) {
				order_source = "1";
			}
			;
			t_advert_channel.setOrder_source(order_source);
			List<T_ADVERT_CHANNEL> lst = this.t_advert_channelService.selectT_ADVERT_CHANNELList(t_advert_channel);
			for (T_ADVERT_CHANNEL o : lst) {
				// 获取足球热点
				if(o.getPosition().equals("2")){
				getFootRemList(rv, quotation_foot_control);
				}
				// 获取篮球热点
				else if(o.getPosition().equals("3")){
					getBasketRemList(rv, quotation_basket_control);
				}
				// 获取电竞热点
				else if(o.getPosition().equals("4")){
					getDjRemList(rv, quotation_dj_control);
				}
			}
			rv.setStatus(ErrorMessage.SUCCESS.getCode());
			rv.setMessage(ErrorMessage.SUCCESS.getMessage());

		} catch (Exception e) {
			logger.error("查询首页热门比赛列表失败----->");
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			throw e;
		}

		logger.info("查询首页热门比赛列表成功----->");
		return JSONObject.toJSONString(rv);
	}

	private void getFootRemList(ReturnValue<Homepage_RemData> rv, T_QUOTATION_CONTROL quotation_control)
			throws Exception {
		logger.info("进入getFootRemList查询足球推荐的方法：doJsonMethod");
		LOTO_F lotoF = new LOTO_F();
		lotoF.setIs_recommend(1);
		List<LOTO_F> lst = null;
		T_LOTO_SIS_F t_LOTO_SIS_F = new T_LOTO_SIS_F();

		List<T_LOTO_SIS_F> lstPrecent = null;

		Homepage_Rem_Model homepage_Rem_Model = new Homepage_Rem_Model();
		homepage_Rem_Model.setIndex(1);
		homepage_Rem_Model.setPosition("2");
		List<Homepage_remDetail> lst_rem = new ArrayList<Homepage_remDetail>();
		homepage_Rem_Model.setLst_rem(lst_rem);

		try {
			lotoF.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
			lotoF.setPage_size(2);
			lotoF.setIs_recommend(1);
			lotoF.setRow_start(0);
			lotoF.setHad_bet(1);
			lotoF.setHhad_bet(1);
			lst = lotoFNService.selectLOTO_FNList(lotoF);
			if (lst.size() < 2) {
				lotoF.setPage_size(2 - lst.size());
				lotoF.setIs_recommend(0);
				lotoF.setIs_hot(0);
				lotoF.setRow_start(0);
				lst.addAll(lotoFNService.selectLOTO_FNList(lotoF));
			}
			homepage_Rem_Model.setCount(lst.size());
			if (lst.isEmpty() == false) {
				for (LOTO_F loto_F : lst) {

					t_LOTO_SIS_F.setIssue(loto_F.getIssue());
					lstPrecent = t_LOTO_SIS_FService.selectT_LOTO_SIS_FList(t_LOTO_SIS_F);
					Homepage_remDetailFoot homepage_remDetail = new Homepage_remDetailFoot();
					homepage_remDetail.endtime = (loto_F.getEndtime());
					homepage_remDetail.guest_team_name = (loto_F.getGuest_team_name());
					homepage_remDetail.home_team_name = (loto_F.getHome_team_name());
					homepage_remDetail.issue = (loto_F.getIssue());
					homepage_remDetail.leaguename = (loto_F.getLeaguename());
					homepage_Rem_Model.getLst_rem().add(homepage_remDetail);
					homepage_remDetail.l = (loto_F.getLetcount().toString());
					if (loto_F.getHad_h() != null && loto_F.getHad_h().isEmpty() == false && loto_F.getHad_bet() == 1) {

						double total1 = 0;
						if (lstPrecent.isEmpty() || lstPrecent.get(0) == null) {
							lstPrecent.add(new T_LOTO_SIS_F());
						}
						if (lstPrecent.get(0).getHad_h() == null) {
							lstPrecent.get(0).setHad_h(0);
							lstPrecent.get(0).setHad_d(0);
							lstPrecent.get(0).setHad_a(0);
							lstPrecent.get(0).setHad_h_d(0);
							lstPrecent.get(0).setHad_d_d(0);
							lstPrecent.get(0).setHad_a_d(0);
						} else {
							total1 = lstPrecent.get(0).getHad_h() + lstPrecent.get(0).getHad_d()
									+ lstPrecent.get(0).getHad_a();

						}

						if (total1 == 0) {
							homepage_remDetail.hh = loto_F.getHad_h();
							homepage_remDetail.hd = loto_F.getHad_d();
							homepage_remDetail.ha = loto_F.getHad_a();
							homepage_remDetail.hh_p = String.format("%.0f%%", 0.00);
							homepage_remDetail.hd_p = String.format("%.0f%%", 0.00);
							homepage_remDetail.ha_p = String.format("%.0f%%", 0.00);

						} else {
							homepage_remDetail.hh = loto_F.getHad_h();
							homepage_remDetail.hd = loto_F.getHad_d();
							homepage_remDetail.ha = loto_F.getHad_a();
							homepage_remDetail.hh_p = String.format("%.0f%%",
									lstPrecent.get(0).getHad_h() / total1 * 100);
							homepage_remDetail.hd_p = String.format("%.0f%%",
									lstPrecent.get(0).getHad_d() / total1 * 100);
							homepage_remDetail.ha_p = String.format("%.0f%%",
									lstPrecent.get(0).getHad_a() / total1 * 100);
						}

						// checkFootHadCanBet(lstPrecent.get(0), loto_F);
						homepage_remDetail.h_bet=1;
					}else{
						homepage_remDetail.h_bet=0;
					}
					if (loto_F.getHhad_h() != null && loto_F.getHhad_h().isEmpty() == false
							&& loto_F.getHhad_bet() == 1) {

						double total1 = 0;
						if (lstPrecent.isEmpty() || lstPrecent.get(0) == null) {
							lstPrecent.add(new T_LOTO_SIS_F());
						}
						if (lstPrecent.get(0).getHhad_h() == null) {
							lstPrecent.get(0).setHhad_h(0);
							lstPrecent.get(0).setHhad_d(0);
							lstPrecent.get(0).setHhad_a(0);
							lstPrecent.get(0).setHhad_h_d(0);
							lstPrecent.get(0).setHhad_d_d(0);
							lstPrecent.get(0).setHhad_a_d(0);
						} else {
							total1 = lstPrecent.get(0).getHhad_h() + lstPrecent.get(0).getHhad_d()
									+ lstPrecent.get(0).getHhad_a();

						}

						if (total1 == 0) {
							homepage_remDetail.rh = loto_F.getHhad_h();
							homepage_remDetail.rd = loto_F.getHhad_d();
							homepage_remDetail.ra = loto_F.getHhad_a();
							homepage_remDetail.rh_p = String.format("%.0f%%", 0.00);
							homepage_remDetail.rd_p = String.format("%.0f%%", 0.00);
							homepage_remDetail.ra_p = String.format("%.0f%%", 0.00);

						} else {
							homepage_remDetail.rh = loto_F.getHhad_h();
							homepage_remDetail.rd = loto_F.getHhad_d();
							homepage_remDetail.ra = loto_F.getHhad_a();
							homepage_remDetail.rh_p = String.format("%.0f%%",
									lstPrecent.get(0).getHhad_h() / total1 * 100);
							homepage_remDetail.rd_p = String.format("%.0f%%",
									lstPrecent.get(0).getHhad_d() / total1 * 100);
							homepage_remDetail.ra_p = String.format("%.0f%%",
									lstPrecent.get(0).getHhad_a() / total1 * 100);

						}

						// checkFootHhadCanBet(lstPrecent.get(0), loto_F);
						
					}
					homepage_remDetail.r_bet=(StringUtil.isEmpty(homepage_remDetail.rh)?0:1);
					homepage_remDetail.h_bet=(StringUtil.isEmpty(homepage_remDetail.hh)?0:1);
				}
				rv.getData().getLst().add(homepage_Rem_Model);
			}
		} catch (Exception e) {
			logger.error("查询查询足球热门的方法失败----->");
			throw e;
		}
		logger.info("查询查询足球热门的方法成功----->");
	}

	private void getBasketRemList(ReturnValue<Homepage_RemData> rv, T_QUOTATION_CONTROL quotation_control)
			throws Exception {
		logger.info("进入getBasketRemList查询篮球推荐的方法：doJsonMethod");
		LOTO_B lotoB = new LOTO_B();
		lotoB.setIs_recommend(1);
		List<LOTO_B> lst = null;
		T_LOTO_SIS_B t_LOTO_SIS_B = new T_LOTO_SIS_B();

		List<T_LOTO_SIS_B> lstPrecent = null;

		Homepage_Rem_Model homepage_Rem_Model = new Homepage_Rem_Model();
		homepage_Rem_Model.setIndex(2);
		homepage_Rem_Model.setPosition("3");
		List<Homepage_remDetail> lst_rem = new ArrayList<Homepage_remDetail>();
		homepage_Rem_Model.setLst_rem(lst_rem);

		try {
			lotoB.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
			lotoB.setPage_size(2);
			lotoB.setIs_recommend(1);
			lotoB.setRow_start(0);
			lotoB.setHilo_bet(1);
			lotoB.setMnl_bet(1);
			lst = lotoBNService.selectLOTO_BNList(lotoB);
			if (lst.size() < 2) {
				lotoB.setPage_size(2 - lst.size());
				lotoB.setIs_recommend(0);
				lotoB.setIs_hot(0);
				lotoB.setRow_start(0);
				lst.addAll(lotoBNService.selectLOTO_BNList(lotoB));
			}
			homepage_Rem_Model.setCount(lst.size());
			if (lst.isEmpty() == false) {
				for (LOTO_B loto_B : lst) {

					t_LOTO_SIS_B.setIssue(loto_B.getIssue());
					lstPrecent = t_LOTO_SIS_BService.selectT_LOTO_SIS_BList(t_LOTO_SIS_B);
					Homepage_remDetailBasket homepage_remDetail = new Homepage_remDetailBasket();
					homepage_remDetail.endtime = (loto_B.getEndtime());
					homepage_remDetail.guest_team_name = (loto_B.getHome_team_name());
					homepage_remDetail.home_team_name = (loto_B.getGuest_team_name());
					homepage_remDetail.issue = (loto_B.getIssue());
					homepage_remDetail.leaguename = (loto_B.getLeaguename());
					homepage_Rem_Model.getLst_rem().add(homepage_remDetail);
					homepage_remDetail.l = (loto_B.getHilo_presetscore());
					if (loto_B.getHilo_h() != null && loto_B.getHilo_h().isEmpty() == false
							&& loto_B.getHilo_bet() == 1) {

						double total1 = 0;
						if (lstPrecent.isEmpty() || lstPrecent.get(0) == null) {
							lstPrecent.add(new T_LOTO_SIS_B());
						}
						if (lstPrecent.get(0).getHilo_h() == null) {
							lstPrecent.get(0).setHilo_h(0);
							lstPrecent.get(0).setHilo_l(0);
							lstPrecent.get(0).setHilo_h_d(0);
							lstPrecent.get(0).setHilo_l_d(0);
						} else {
							total1 = lstPrecent.get(0).getHilo_h() + lstPrecent.get(0).getHilo_l();
						}

						if (total1 == 0) {
							homepage_remDetail.hh = loto_B.getHilo_h();
							homepage_remDetail.hl = loto_B.getHilo_l();
							homepage_remDetail.hh_p = String.format("%.0f%%", 0.00);
							homepage_remDetail.hl_p = String.format("%.0f%%", 0.00);
						} else {
							homepage_remDetail.hh = loto_B.getHilo_h();
							homepage_remDetail.hl = loto_B.getHilo_l();
							homepage_remDetail.hh_p = String.format("%.0f%%",
									lstPrecent.get(0).getHilo_h() / total1 * 100);
							homepage_remDetail.hl_p = String.format("%.0f%%",
									lstPrecent.get(0).getHilo_l() / total1 * 100);
						}

						// checkBasketHiloCanBet(lstPrecent.get(0), loto_B);
					}
					if (loto_B.getMnl_h() != null && loto_B.getMnl_h().isEmpty() == false && loto_B.getMnl_bet() == 1) {

						double total1 = 0;
						if (lstPrecent.isEmpty() || lstPrecent.get(0) == null) {
							lstPrecent.add(new T_LOTO_SIS_B());
						}
						if (lstPrecent.get(0).getMnl_h() == null) {
							lstPrecent.get(0).setMnl_h(0);
							lstPrecent.get(0).setMnl_a(0);
							lstPrecent.get(0).setMnl_h_d(0);
							lstPrecent.get(0).setMnl_a_d(0);
						} else {
							total1 = lstPrecent.get(0).getMnl_h() + lstPrecent.get(0).getMnl_a();
						}

						if (total1 == 0) {
							homepage_remDetail.mh = loto_B.getMnl_h();
							homepage_remDetail.ma = loto_B.getMnl_a();
							homepage_remDetail.mh_p = String.format("%.0f%%", 0.00);
							homepage_remDetail.ma_p = String.format("%.0f%%", 0.00);

						} else {
							homepage_remDetail.mh = loto_B.getMnl_h();
							homepage_remDetail.ma = loto_B.getMnl_a();
							homepage_remDetail.mh_p = String.format("%.0f%%",
									lstPrecent.get(0).getMnl_h() / total1 * 100);
							homepage_remDetail.ma_p = String.format("%.0f%%",
									lstPrecent.get(0).getMnl_a() / total1 * 100);
						}

						// checkBasketMnlCanBet(lstPrecent.get(0), loto_B);
					}
					homepage_remDetail.h_bet=(StringUtil.isEmpty(homepage_remDetail.hh)?0:1);
					homepage_remDetail.m_bet=(StringUtil.isEmpty(homepage_remDetail.mh)?0:1);
				}
				rv.getData().getLst().add(homepage_Rem_Model);
			}
		} catch (Exception e) {
			logger.error("查询查询篮球热门的方法失败----->");
			throw e;
		}
		logger.info("查询查询篮球热门的方法成功----->");
	}

	private void getDjRemList(ReturnValue<Homepage_RemData> rv, T_QUOTATION_CONTROL quotation_control)
			throws Exception {
		logger.info("进入getDjRemList查询篮球推荐的方法：doJsonMethod");
		T_LOTO_E lotoE = new T_LOTO_E();
		lotoE.setIs_recommend(1);
		List<T_LOTO_E> lst = null;
		T_LOTO_SIS_E t_LOTO_SIS_E = new T_LOTO_SIS_E();

		List<T_LOTO_SIS_E> lstPrecent = null;

		Homepage_Rem_Model homepage_Rem_Model = new Homepage_Rem_Model();
		homepage_Rem_Model.setIndex(3);
		homepage_Rem_Model.setPosition("4");
		List<Homepage_remDetail> lst_rem = new ArrayList<Homepage_remDetail>();
		homepage_Rem_Model.setLst_rem(lst_rem);

		try {
			lotoE.setEndtime(DateUtils.getDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
			lotoE.setPage_size(2);
			lotoE.setRow_start(0);
			lotoE.setIs_recommend(1);
			lotoE.setMnl_bet(1);
			lst = lotoENService.selectT_LOTO_ENList(lotoE);
			if (lst.size() < 2) {
				lotoE.setPage_size(2 - lst.size());
				lotoE.setRow_start(0);
				lotoE.setIs_recommend(0);
				lotoE.setIs_hot(0);
				lst.addAll(lotoENService.selectT_LOTO_ENList(lotoE));
			}
			homepage_Rem_Model.setCount(lst.size());
			if (lst.isEmpty() == false) {
				rv.getData().getLst().add(homepage_Rem_Model);
				for (T_LOTO_E loto_E : lst) {

					t_LOTO_SIS_E.setIssue(loto_E.getIssue());
					lstPrecent = t_LOTO_SIS_EService.selectT_LOTO_SIS_EList(t_LOTO_SIS_E);
					Homepage_remDetailDj homepage_remDetail = new Homepage_remDetailDj();
					homepage_remDetail.endtime = (loto_E.getEndtime().replace("-", "").replace(" ", "").replace(":",
							""));
					homepage_remDetail.guest_team_name = (loto_E.getGuest_team_name());
					homepage_remDetail.home_team_name = (loto_E.getHome_team_name());
					homepage_remDetail.issue = (loto_E.getIssue());
					homepage_remDetail.leaguename = (loto_E.getLeaguename());
					homepage_remDetail.match_mode = loto_E.getMatch_mode();
					homepage_remDetail.image_name = loto_E.getImg_name() == null ? "" : loto_E.getImg_name();
					homepage_remDetail.l = ("0");
					homepage_Rem_Model.getLst_rem().add(homepage_remDetail);

					double total1 = 0;
					if (lstPrecent.isEmpty() || lstPrecent.get(0) == null) {
						lstPrecent.add(new T_LOTO_SIS_E());
					}
					if (lstPrecent.get(0).getMnl_h() == null) {
						lstPrecent.get(0).setMnl_h(0);
						lstPrecent.get(0).setMnl_h_d(0);
						lstPrecent.get(0).setMnl_a(0);
						lstPrecent.get(0).setMnl_a_d(0);
					} else {
						total1 = lstPrecent.get(0).getMnl_h() + lstPrecent.get(0).getMnl_a();
					}

					if (total1 == 0) {
						homepage_remDetail.mh = loto_E.getMnl_h();
						homepage_remDetail.ma = loto_E.getMnl_a();
						homepage_remDetail.mh_p = String.format("%.0f%%", 0.00);
						homepage_remDetail.ma_p = String.format("%.0f%%", 0.00);

					} else {
						homepage_remDetail.mh = loto_E.getMnl_h();
						homepage_remDetail.ma = loto_E.getMnl_a();
						homepage_remDetail.mh_p = String.format("%.0f%%", lstPrecent.get(0).getMnl_h() / total1 * 100);
						homepage_remDetail.ma_p = String.format("%.0f%%", lstPrecent.get(0).getMnl_a() / total1 * 100);
					}
					homepage_remDetail.m_bet=(StringUtil.isEmpty(homepage_remDetail.mh)?0:1);
					// checkDjCanBet(lstPrecent.get(0), loto_E);
				}
			}
		} catch (Exception e) {
			logger.error("查询查询电竞热门的方法失败----->");
			throw e;
		}
		logger.info("查询查询电竞热门的方法成功----->");
	}
	/*
	 * private void checkFootHadCanBet(T_LOTO_SIS_F precent, LOTO_F loto_F) {
	 * double had_sum = precent.getHad_a_d() + precent.getHad_d_d() +
	 * precent.getHad_h_d(); if (loto_F.getHad_a().equals("1.01") ||
	 * loto_F.getHad_d().equals("1.01") || loto_F.getHad_h().equals("1.01") ||
	 * had_sum >= quotation_foot_control.getDcxssx_s()) { loto_F.setHad_bet(0);
	 * } else { loto_F.setHad_bet(1); } }
	 * 
	 * private void checkFootHhadCanBet(T_LOTO_SIS_F precent, LOTO_F loto_F) {
	 * double hhad_sum = precent.getHhad_a_d() + precent.getHhad_d_d() +
	 * precent.getHhad_h_d(); if (loto_F.getHhad_a().equals("1.01") ||
	 * loto_F.getHhad_d().equals("1.01") || loto_F.getHhad_h().equals("1.01") ||
	 * hhad_sum >= quotation_foot_control.getDcxssx_s()) { loto_F.setHhadbet(0);
	 * } else { loto_F.setHhadbet(1); } }
	 * 
	 * private void checkBasketHiloCanBet(T_LOTO_SIS_B precent, LOTO_B loto_B) {
	 * double hilo_sum = precent.getHilo_h_d() + precent.getHilo_l_d(); if
	 * (loto_B.getHilo_h().equals("1.01") || loto_B.getHilo_l().equals("1.01")
	 * || hilo_sum >= quotation_basket_control.getDcxssx_s()) {
	 * loto_B.setHilobet(0); } else { loto_B.setHilobet(1); } }
	 * 
	 * private void checkBasketMnlCanBet(T_LOTO_SIS_B precent, LOTO_B loto_B) {
	 * double mnl_sum = precent.getMnl_h_d() + precent.getMnl_a_d(); if
	 * (loto_B.getMnl_h().equals("1.01") || loto_B.getMnl_a().equals("1.01") ||
	 * mnl_sum >= quotation_basket_control.getDcxssx_s()) { loto_B.setMnlbet(0);
	 * } else { loto_B.setMnlbet(1); } }
	 * 
	 * private void checkDjCanBet(T_LOTO_SIS_E precent, T_LOTO_E loto_E) {
	 * double mnl_sum = precent.getMnl_a_d() + precent.getMnl_a_d(); if
	 * (loto_E.getMnl_a().equals("1.01") || loto_E.getMnl_h().equals("1.01") ||
	 * mnl_sum >= quotation_dj_control.getDcxssx_s()) { loto_E.setMnlbet(0); }
	 * else { loto_E.setMnlbet(1); } }
	 */
}
