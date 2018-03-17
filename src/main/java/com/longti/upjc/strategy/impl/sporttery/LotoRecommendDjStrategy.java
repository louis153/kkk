package com.longti.upjc.strategy.impl.sporttery;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.entity.sporttery.T_LOTO_E;
import com.longti.upjc.entity.sporttery.T_LOTO_SIS_E;
import com.longti.upjc.service.sporttery.T_LOTO_ENService;
import com.longti.upjc.service.sporttery.T_LOTO_SIS_EService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.DateUtils;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.ReturnValue;
import com.longti.upjc.util.StringUtil;

/**
 * 查询蓝球推荐信息
 * 
 * @return
 * @throws Exception
 */
@Component("loto_recommend_dj")
public class LotoRecommendDjStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(LotoRecommendDjStrategy.class);

	

	public static class RemDjData {
		public String issue=""; // 比赛编号
		public String home_team_name=""; // 主队名称
		public String guest_team_name=""; // 客队名称
		public String home_team_id=""; // 主队编号
		public String guest_team_id=""; // 客队编号
		public String leaguename=""; // 比赛名称
		public String mh="";
		public String ma="";
		public String mh_p="";
		public String ma_p="";
		public int m_bet=1;
		public String image_name="";
		public String game_name="";
		public String match_mode="";
		public String endtime="";
	}

	@Autowired
	private T_LOTO_ENService lotoENService;
	@Autowired
	private T_LOTO_SIS_EService t_LOTO_SIS_EService;

	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("进入loto_recommend_dj查询电竞推荐的方法：doJsonMethod");
		ReturnValue<RemDjData> rv = new ReturnValue<RemDjData>();
		rv.setData(new RemDjData());
		T_LOTO_E lotoE = new T_LOTO_E();
		lotoE.setIs_hot(1);
		lotoE.setMnl_bet(1);
		List<T_LOTO_E> lst = null;
		T_LOTO_SIS_E t_LOTO_SIS_E = new T_LOTO_SIS_E();

		List<T_LOTO_SIS_E> lstPrecent = null;

		try {
			lotoE.setEndtime(DateUtils.getDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
			lst = lotoENService.selectT_LOTO_ENList(lotoE);
			if (lst.isEmpty()) {
				lotoE.setIs_hot(null);
				lotoE.setIs_recommend(1);
				lst = lotoENService.selectT_LOTO_ENList(lotoE);
			}
			if (lst.isEmpty() == false) {
				t_LOTO_SIS_E.setIssue(lst.get(0).getIssue());
				lstPrecent = t_LOTO_SIS_EService.selectT_LOTO_SIS_EList(t_LOTO_SIS_E);

				rv.getData().guest_team_id=(lst.get(0).getGuest_team_id());
				rv.getData().guest_team_name=(lst.get(0).getGuest_team_name());
				rv.getData().home_team_id=(lst.get(0).getHome_team_id());
				rv.getData().home_team_name=(lst.get(0).getHome_team_name());
				rv.getData().leaguename=(lst.get(0).getLeaguename());
				rv.getData().issue=(lst.get(0).getIssue());
				rv.getData().image_name=(lst.get(0).getImg_name()==null?"":lst.get(0).getImg_name());
				rv.getData().game_name=lst.get(0).getGame_name();
				rv.getData().match_mode=lst.get(0).getMatch_mode();
				rv.getData().endtime=lst.get(0).getEndtime().replace("-", "").replace(" ", "").replace(":", "");
				double total1 = 0;
				if (lstPrecent.isEmpty() || lstPrecent.get(0) == null) {
					lstPrecent.add(new T_LOTO_SIS_E());
				}
				if (lstPrecent.get(0).getMnl_h() == null) {
					lstPrecent.get(0).setMnl_a(0);
					lstPrecent.get(0).setMnl_h(0);
				} else {
					total1 = lstPrecent.get(0).getMnl_h() + lstPrecent.get(0).getMnl_a();

				}
				
				if (StringUtil.isEmpty(lst.get(0).getMnl_h()) == false) {
					rv.getData().mh=lst.get(0).getMnl_h().toString();
					rv.getData().ma=lst.get(0).getMnl_a().toString();
					rv.getData().mh_p=String.format("%.0f%%", total1 == 0 ? 0.00 : lstPrecent.get(0).getMnl_h() / total1 * 100);
					rv.getData().ma_p=String.format("%.0f%%", total1 == 0 ? 0.00 : lstPrecent.get(0).getMnl_a() / total1 * 100);
					rv.getData().m_bet=1;
				}else{
					rv.getData().m_bet=0;
				}
				logger.info("查询电竞推荐列表成功----->");
				rv.setStatus(ErrorMessage.SUCCESS.getCode());
				rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			}else{
				logger.info("查询电竞推荐列表为空----->");
				rv.setStatus(ErrorMessage.NO_REC.getCode());
				rv.setMessage(ErrorMessage.NO_REC.getMessage());
			}
			
		} catch (Exception e) {
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.error("查询电竞推荐列表失败----->");
		}

		return JSONObject.toJSONString(rv);
	}
	
}
