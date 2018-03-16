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
import com.tengcai.vims.entity.sporttery.T_LOTO_E;
import com.tengcai.vims.entity.sporttery.LOTO_F;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_E;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_F;
import com.tengcai.vims.entity.sporttery.T_QUOTATION_CONTROL;
import com.tengcai.vims.service.sporttery.LOTO_BNService;
import com.tengcai.vims.service.sporttery.LOTO_FNService;
import com.tengcai.vims.service.sporttery.T_LOTO_ENService;
import com.tengcai.vims.service.sporttery.T_LOTO_SIS_BService;
import com.tengcai.vims.service.sporttery.T_LOTO_SIS_EService;
import com.tengcai.vims.service.sporttery.T_LOTO_SIS_FService;
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
@Component("loto_homepage_hot_list")
public class LotoHomePageHotListStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(LotoHomePageHotListStrategy.class);

	public static class Homepage_hotDetail {

		public String home_team_name="";
		public String guest_team_name="";

		public String leaguename="";
		public String endtime="";
		public String issue="";

		public String l="";
	}

	public static class Homepage_hotDetailFoot extends Homepage_hotDetail {
		public String hh="";
		public String hd="";
		public String ha="";
		public String rh="";
		public String rd="";
		public String ra="";
		public String hh_p="";
		public String hd_p="";
		public String ha_p="";
		public String rh_p="";
		public String rd_p="";
		public String ra_p="";
	}

	public static class Homepage_hotDetailBasket extends Homepage_hotDetail {
		public String hh="";
		public String hl="";
		public String mh="";
		public String ma="";
		public String hh_p="";
		public String hl_p="";
		public String mh_p="";
		public String ma_p="";
	}

	public static class Homepage_hotDetailDj extends Homepage_hotDetail {
		public String mh="";
		public String ma="";
		public String mh_p="";
		public String ma_p="";
		public String match_mode="";
		public String image_name="";
	}

	public static class Homepage_Hot_Model {
		private String position="";
		private int index;
		private int count;
		private List<Homepage_hotDetail> lst_hot=new ArrayList<Homepage_hotDetail>();

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public List<Homepage_hotDetail> getLst_hot() {
			return lst_hot;
		}

		public void setLst_hot(List<Homepage_hotDetail> lst_hot) {
			this.lst_hot = lst_hot;
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

	public static class Homepage_HotData {
		private List<Homepage_Hot_Model> lst;

		public List<Homepage_Hot_Model> getLst() {
			return lst;
		}

		public void setLst(List<Homepage_Hot_Model> lst) {
			this.lst = lst;
		}
	}

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
		logger.info("进入loto_homepage_hot_list首页比赛列表的方法：doJsonMethod");
		ReturnValue<Homepage_HotData> rv = new ReturnValue<Homepage_HotData>();
		rv.setData(new Homepage_HotData());
		rv.getData().setLst(new ArrayList<Homepage_Hot_Model>());
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
			// 获取足球热点	
			
			getFootHotList(rv, quotation_foot_control);				
			// 获取篮球热点
			if(getMatchCount(rv)<2)
				getBasketHotList(rv, quotation_basket_control);
			// 获取电竞热点
			if(getMatchCount(rv)<2)
				getDjHotList(rv, quotation_dj_control);
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
	private int getMatchCount(ReturnValue<Homepage_HotData> rv){
		int count=0;
		for(Homepage_Hot_Model homepage_Hot_Model :rv.getData().lst){
			count+=homepage_Hot_Model.lst_hot.size();
		}
		return count;
	}
	private void getFootHotList(ReturnValue<Homepage_HotData> rv, T_QUOTATION_CONTROL quotation_control)
			throws Exception {
		logger.info("进入getFootHotList查询足球推荐的方法：doJsonMethod");
		LOTO_F lotoF = new LOTO_F();
		lotoF.setIs_hot(1);
		List<LOTO_F> lst = null;
		T_LOTO_SIS_F t_LOTO_SIS_F = new T_LOTO_SIS_F();

		List<T_LOTO_SIS_F> lstPrecent = null;

		Homepage_Hot_Model homepage_Hot_Model = new Homepage_Hot_Model();
		homepage_Hot_Model.setIndex(1);
		homepage_Hot_Model.setPosition("2");
		List<Homepage_hotDetail> lst_hot = new ArrayList<Homepage_hotDetail>();
		homepage_Hot_Model.setLst_hot(lst_hot);
		
		try {
			lotoF.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
			lotoF.setPage_size(2-getMatchCount(rv));
			lotoF.setRow_start(0);
			lotoF.setHad_bet(1);
			lst = lotoFNService.selectLOTO_FNList(lotoF);
			List<Integer> delLst = new ArrayList<Integer>();
			for (int i = 0; i < lst.size(); i++) {
				LOTO_F loto_F = lst.get(i);
				if (StringUtil.isEmpty(loto_F.getHad_h())) {
					delLst.add(i);
				}
			}
			for (int i = 0; i < delLst.size(); i++) {
				lst.remove((int) delLst.get(i));
			}

			if (lst.size() > 0) {
				homepage_Hot_Model.setCount(lst.size());

				for (LOTO_F loto_F : lst) {

					t_LOTO_SIS_F.setIssue(loto_F.getIssue());
					lstPrecent = t_LOTO_SIS_FService.selectT_LOTO_SIS_FList(t_LOTO_SIS_F);
					Homepage_hotDetailFoot homepage_hotDetail = new Homepage_hotDetailFoot();
					homepage_hotDetail.endtime = (loto_F.getEndtime());
					homepage_hotDetail.guest_team_name = (loto_F.getGuest_team_name());
					homepage_hotDetail.home_team_name = (loto_F.getHome_team_name());
					homepage_hotDetail.issue = (loto_F.getIssue());
					homepage_hotDetail.leaguename = (loto_F.getLeaguename());

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
						homepage_hotDetail.hh = loto_F.getHad_h();
						homepage_hotDetail.hd = loto_F.getHad_d();
						homepage_hotDetail.ha = loto_F.getHad_a();
						homepage_hotDetail.hh_p = String.format("%.0f%%", 0.00);
						homepage_hotDetail.hd_p = String.format("%.0f%%", 0.00);
						homepage_hotDetail.ha_p = String.format("%.0f%%", 0.00);
					} else {
						homepage_hotDetail.hh = loto_F.getHad_h();
						homepage_hotDetail.hd = loto_F.getHad_d();
						homepage_hotDetail.ha = loto_F.getHad_a();
						homepage_hotDetail.hh_p = String.format("%.0f%%", lstPrecent.get(0).getHad_h() / total1 * 100);
						homepage_hotDetail.hd_p = String.format("%.0f%%", lstPrecent.get(0).getHad_d() / total1 * 100);
						homepage_hotDetail.ha_p = String.format("%.0f%%", lstPrecent.get(0).getHad_a() / total1 * 100);
					}
					homepage_Hot_Model.lst_hot.add(homepage_hotDetail);
					// checkFootCanBet(lstPrecent, lst);
				}

				rv.getData().getLst().add(homepage_Hot_Model);
			}
		} catch (Exception e) {
			logger.error("查询查询足球热门的方法失败----->");
			throw e;
		}
		logger.info("查询查询足球热门的方法成功----->");
	}

	private void getBasketHotList(ReturnValue<Homepage_HotData> rv, T_QUOTATION_CONTROL quotation_control)
			throws Exception {
		logger.info("进入getBasketHotList查询篮球推荐的方法：doJsonMethod");
		LOTO_B lotoB = new LOTO_B();
		lotoB.setIs_hot(1);
		List<LOTO_B> lst = null;
		T_LOTO_SIS_B t_LOTO_SIS_B = new T_LOTO_SIS_B();

		List<T_LOTO_SIS_B> lstPrecent = null;

		Homepage_Hot_Model homepage_Hot_Model = new Homepage_Hot_Model();
		homepage_Hot_Model.setIndex(2);
		homepage_Hot_Model.setPosition("3");
		List<Homepage_hotDetail> lst_hot = new ArrayList<Homepage_hotDetail>();

		homepage_Hot_Model.setLst_hot(lst_hot);

		try {
			lotoB.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
			lotoB.setPage_size(2-getMatchCount(rv));
			lotoB.setRow_start(0);
			lotoB.setHilo_bet(1);
			lst = lotoBNService.selectLOTO_BNList(lotoB);

			List<Integer> delLst = new ArrayList<Integer>();
			for (int i = 0; i < lst.size(); i++) {
				LOTO_B loto_B = lst.get(i);
				if (StringUtil.isEmpty(loto_B.getHilo_h())) {
					delLst.add(i);
				}
			}

			for (int i = 0; i < delLst.size(); i++) {
				lst.remove((int) delLst.get(i));
			}

			homepage_Hot_Model.setCount(lst.size());
			if (lst.isEmpty() == false) {
				for (LOTO_B loto_B : lst) {

					t_LOTO_SIS_B.setIssue(loto_B.getIssue());
					lstPrecent = t_LOTO_SIS_BService.selectT_LOTO_SIS_BList(t_LOTO_SIS_B);
					Homepage_hotDetailBasket homepage_hotDetail = new Homepage_hotDetailBasket();
					homepage_hotDetail.endtime = (loto_B.getEndtime());
					homepage_hotDetail.guest_team_name = (loto_B.getHome_team_name());
					homepage_hotDetail.home_team_name = (loto_B.getGuest_team_name());
					homepage_hotDetail.issue = (loto_B.getIssue());
					homepage_hotDetail.leaguename = (loto_B.getLeaguename());
					homepage_hotDetail.l = (loto_B.getHilo_presetscore());
					homepage_Hot_Model.getLst_hot().add(homepage_hotDetail);

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
						homepage_hotDetail.hh = loto_B.getHilo_h();
						homepage_hotDetail.hl = loto_B.getHilo_l();
						homepage_hotDetail.hh_p = String.format("%.0f%%", 0.00);
						homepage_hotDetail.hl_p = String.format("%.0f%%", 0.00);
					} else {
						homepage_hotDetail.hh = loto_B.getHilo_h();
						homepage_hotDetail.hl = loto_B.getHilo_l();
						homepage_hotDetail.hh_p = String.format("%.0f%%", lstPrecent.get(0).getHilo_h() / total1 * 100);
						homepage_hotDetail.hl_p = String.format("%.0f%%", lstPrecent.get(0).getHilo_l() / total1 * 100);
					}
					// checkBasketCanBet(lstPrecent, lst);
				}
				rv.getData().getLst().add(homepage_Hot_Model);
			}

		} catch (Exception e) {
			logger.error("查询篮球热门的方法失败----->");
			throw e;
		}

		logger.info("查询篮球热门的方法成功----->");
	}

	private void getDjHotList(ReturnValue<Homepage_HotData> rv, T_QUOTATION_CONTROL quotation_control)
			throws Exception {
		logger.info("进入getDjHotList查询篮球推荐的方法：doJsonMethod");
		T_LOTO_E lotoE = new T_LOTO_E();
		lotoE.setIs_hot(1);
		List<T_LOTO_E> lst = null;
		T_LOTO_SIS_E t_LOTO_SIS_E = new T_LOTO_SIS_E();

		List<T_LOTO_SIS_E> lstPrecent = null;

		Homepage_Hot_Model homepage_Hot_Model = new Homepage_Hot_Model();
		homepage_Hot_Model.setIndex(3);
		homepage_Hot_Model.setPosition("4");
		List<Homepage_hotDetail> lst_hot = new ArrayList<Homepage_hotDetail>();
		homepage_Hot_Model.setLst_hot(lst_hot);

		try {
			lotoE.setEndtime(DateUtils.getDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
			lotoE.setPage_size(2-getMatchCount(rv));
			lotoE.setRow_start(0);
			lotoE.setMnl_bet(1);
			lst = lotoENService.selectT_LOTO_ENList(lotoE);
			homepage_Hot_Model.setCount(lst.size());
			if (lst.isEmpty() == false) {
				for (T_LOTO_E loto_E : lst) {

					t_LOTO_SIS_E.setIssue(loto_E.getIssue());
					lstPrecent = t_LOTO_SIS_EService.selectT_LOTO_SIS_EList(t_LOTO_SIS_E);
					Homepage_hotDetailDj homepage_hotDetail = new Homepage_hotDetailDj();
					homepage_hotDetail.endtime = (loto_E.getEndtime().replace("-", "").replace(":", "").replace(" ",
							""));
					homepage_hotDetail.guest_team_name = (loto_E.getGuest_team_name());
					homepage_hotDetail.home_team_name = (loto_E.getHome_team_name());
					homepage_hotDetail.issue = (loto_E.getIssue());
					homepage_hotDetail.leaguename = (loto_E.getLeaguename());
					homepage_hotDetail.l = ("0");
					homepage_hotDetail.match_mode=loto_E.getMatch_mode();
					homepage_hotDetail.image_name=(loto_E.getImg_name()==null?"":loto_E.getImg_name());
					homepage_Hot_Model.getLst_hot().add(homepage_hotDetail);

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
						homepage_hotDetail.mh = loto_E.getMnl_h();
						homepage_hotDetail.ma = loto_E.getMnl_a();
						homepage_hotDetail.mh_p = String.format("%.0f%%", 0.00);
						homepage_hotDetail.ma_p = String.format("%.0f%%", 0.00);

					} else {
						homepage_hotDetail.mh = loto_E.getMnl_h();
						homepage_hotDetail.ma = loto_E.getMnl_a();
						homepage_hotDetail.mh_p = String.format("%.0f%%", lstPrecent.get(0).getMnl_h() / total1 * 100);
						homepage_hotDetail.ma_p = String.format("%.0f%%", lstPrecent.get(0).getMnl_a() / total1 * 100);
					}

					// checkDjCanBet(lstPrecent, lst);
				}
				rv.getData().getLst().add(homepage_Hot_Model);
			}
		} catch (Exception e) {
			logger.error("查询查询电竞热门的方法失败----->");
			throw e;
		}
		logger.info("查询查询电竞热门的方法成功----->");
	}
	/*
	 * private void checkFootCanBet(List<T_LOTO_SIS_F> lstPrecent,List<LOTO_F>
	 * lst){ double
	 * had_sum=lstPrecent.get(0).getHad_a_d()+lstPrecent.get(0).getHad_d_d()+
	 * lstPrecent.get(0).getHad_h_d();
	 * if(lst.get(0).getHad_a().equals("1.01")||lst.get(0).getHad_d().equals(
	 * "1.01")||lst.get(0).getHad_h().equals("1.01")||had_sum>=
	 * quotation_foot_control.getDcxssx_s()){ lst.get(0).setHadbet(0); }else{
	 * lst.get(0).setHadbet(1); } } private void
	 * checkBasketCanBet(List<T_LOTO_SIS_B> lstPrecent,List<LOTO_B> lst){ double
	 * hilo_sum=lstPrecent.get(0).getHilo_h_d()+lstPrecent.get(0).getHilo_l_d();
	 * if(lst.get(0).getHilo_h().equals("1.01")||lst.get(0).getHilo_l().equals(
	 * "1.01")||hilo_sum>=quotation_basket_control.getDcxssx_s()){
	 * lst.get(0).setHilobet(0); }else{ lst.get(0).setHilobet(1); } } private
	 * void checkDjCanBet(List<T_LOTO_SIS_E> lstPrecent,List<T_LOTO_E> lst){
	 * double
	 * mnl_sum=lstPrecent.get(0).getMnl_a_d()+lstPrecent.get(0).getMnl_a_d();
	 * if(lst.get(0).getMnl_a().equals("1.01")||lst.get(0).getMnl_h().equals(
	 * "1.01")||mnl_sum>=quotation_dj_control.getDcxssx_s()){
	 * lst.get(0).setMnlbet(0); }else{ lst.get(0).setMnlbet(1); } }
	 */
}
