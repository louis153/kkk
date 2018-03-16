/**
 * @author 杨阳
 * 2017-08-03 14:49
 */
package com.tengcai.vims.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.T_LOTO_E;
import com.tengcai.vims.service.sporttery.T_LOTO_ENService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.DateUtils;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;
import com.tengcai.vims.util.StringUtil;

/**
 * 查看蓝球赛事场次列表
 * 
 * @return
 */
@Component("loto_en_list")
public class TLotoEnListStrategy implements IMethodStrategy {

	public static class LotoEnDetail {
		public String issue; // 比赛编号
		public String endtime; // 截止投注时间
		public String home_team_name; // 主队名称
		public String guest_team_name; // 客队名称
		public String l; // 大小分标准/让球数
		public String leaguename; // 比赛名称
		public String mh = "";
		public String ma = "";
		public int m_bet=1;
		public String image_name = "";
		public String game_name = "";
		public String match_mode = "";

	}

	public static class LotoEnData {
		public List<LotoEnDetail> lst = new ArrayList<LotoEnDetail>();
	}

	protected final transient static Logger logger = LoggerFactory.getLogger(TLotoEnListStrategy.class);
	@Autowired
	private T_LOTO_ENService lotoENService;

	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {

		ReturnValue<LotoEnData> rv = new ReturnValue<LotoEnData>();
		rv.setData(new LotoEnData());
		logger.info("loto_bn_list开始调用查看电竞赛事场次列表接口doJsonMethod------>");
		String rem_issue = getDjRem_issue(jsonRequest);
		T_LOTO_E lotoE = new T_LOTO_E();
		lotoE.setStatus(1);
		Date saledate=DateUtils.getStrToDate(jsonRequest.get("saleday").toString().replace("-", ""),"yyyyMMdd");
		String saleday=DateUtils.getDateToStr(saledate,"yyyy-MM-dd");
		lotoE.setStartIssue(saleday+" 00:00:00");
		lotoE.setEndIssue(saleday + " 23:59:59");
		lotoE.setEndtime(DateUtils.getDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));	
		lotoE.setMnl_bet(1);

		List<T_LOTO_E> lst = null;
		try {

			lst = lotoENService.selectT_LOTO_ENList(lotoE);
			
			if (StringUtil.isEmpty(rem_issue) == false) {
				int irecommend = -1;
				for (int i = 0; i < lst.size(); i++) {
					T_LOTO_E f = lst.get(i);
					if (f.getIssue().equals(rem_issue)) {
						irecommend = i;
						break;
					}
				}
				if (irecommend != -1) {
					lst.remove(irecommend);
				}
			}
			for (T_LOTO_E e : lst) {
				LotoEnDetail lotoEnDetail = new LotoEnDetail();
				lotoEnDetail.endtime = e.getEndtime().replace("-", "").replace(" ", "").replace(":", "");
				lotoEnDetail.home_team_name = e.getHome_team_name();
				lotoEnDetail.guest_team_name = e.getGuest_team_name();
				lotoEnDetail.issue = e.getIssue();
				lotoEnDetail.l = "";
				lotoEnDetail.leaguename = e.getLeaguename();
				lotoEnDetail.image_name = (e.getImg_name()==null?"":e.getImg_name());
				lotoEnDetail.game_name = e.getGame_name();
				lotoEnDetail.match_mode = e.getMatch_mode();
				if (StringUtil.isEmpty(e.getMnl_h()) == false) {
					lotoEnDetail.mh = e.getMnl_h();
					lotoEnDetail.ma = e.getMnl_a();
					lotoEnDetail.m_bet=1;
				}else{
					lotoEnDetail.m_bet=0;
				}

				rv.getData().lst.add(lotoEnDetail);
			}
			rv.setStatus(ErrorMessage.SUCCESS.getCode());
			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
		} catch (Exception e) {
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.info("查询蓝球赛事列表失败----->");
			throw e;
		}

		logger.info("查询蓝球赛事列表成功----->");
		return JSONObject.toJSONString(rv);
	}

	private String getDjRem_issue(JSONObject jsonRequest) throws Exception {
		T_LOTO_E lotoE = new T_LOTO_E();
		lotoE.setIs_hot(1);
		lotoE.setMnl_bet(1);
		List<T_LOTO_E> lst = null;
		lotoE.setEndtime(DateUtils.getDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
		String saleday=jsonRequest.get("saleday").toString();
		saleday=saleday.substring(0, 4)+"-"+saleday.substring(4,6)+"-"+saleday.substring(6,8);
		lotoE.setStartIssue(saleday+" 00:00:00");
		lotoE.setEndIssue(saleday + " 23:59:59");
		lst = lotoENService.selectT_LOTO_ENList(lotoE);
		if (lst.isEmpty()) {
			lotoE.setIs_hot(null);
			lotoE.setIs_recommend(1);
			lst = lotoENService.selectT_LOTO_ENList(lotoE);
		}
		if (lst.size() > 0) {
			return lst.get(0).getIssue();
		} else {
			return "";
		}
	}
}