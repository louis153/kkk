package com.tengcai.vims.service.impl.sporttery;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengcai.vims.entity.sporttery.LOTO_ORDER;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_E;
import com.tengcai.vims.entity.sporttery.V_ORDER;
import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.dao.sporttery.LOTO_ORDER_EDao;
import com.tengcai.vims.dao.sporttery.T_LOTO_SIS_EDao;
import com.tengcai.vims.dao.sporttery.V_ORDER_EDao;
import com.tengcai.vims.service.sporttery.V_ORDER_EService;
import com.tengcai.vims.util.DateUtils;
import com.tengcai.vims.util.IOUtils;
import com.tengcai.vims.util.Md5;
import com.tengcai.vims.util.PostUtils;
import com.tengcai.vims.util.jdbet.BetUtils;

/**
 * serviceImpl
 */
@Service
public class V_ORDER_EServiceImpl implements V_ORDER_EService {
	protected final transient static Logger logger = LoggerFactory.getLogger(V_ORDER_EServiceImpl.class);
	@Autowired
	private V_ORDER_EDao v_ORDER_EDao;
	@Autowired
	private LOTO_ORDER_EDao loto_ORDER_EDao;

	@Autowired
	private T_LOTO_SIS_EDao t_LOTO_SIS_EDao;
	private static String djUrl=IOUtils.getConfigParam("dj.url", "dj.properties");
	private static String djKey=IOUtils.getConfigParam("dj.key", "dj.properties");
	/**
	 * 条件查询
	 */
	public List<V_ORDER> selectV_ORDERList(V_ORDER v_order) throws Exception {
		return v_ORDER_EDao.selectV_ORDERList(v_order);
	}

	/**
	 * 条件查询数量
	 */
	public int selectV_ORDERCount(V_ORDER v_order) throws Exception {
		return v_ORDER_EDao.selectV_ORDERCount(v_order);
	}

	/**
	 * 添加
	 */
	public int insertV_ORDER(V_ORDER v_order) throws Exception {
		return v_ORDER_EDao.insertV_ORDER(v_order);
	}

	/**
	 * 批量添加
	 */
	public int insertV_ORDER(List<V_ORDER> list) throws Exception {
		return v_ORDER_EDao.insertV_ORDER(list);
	}

	/**
	 * 修改
	 */
	public int updateV_ORDER(V_ORDER v_order) throws Exception {
		return v_ORDER_EDao.updateV_ORDER(v_order);
	}

	/**
	 * 批量修改
	 */
	public int updateV_ORDER(List<V_ORDER> list) throws Exception {
		return v_ORDER_EDao.updateV_ORDER(list);
	}

	/**
	 * 删除
	 */
	public int deleteV_ORDER(V_ORDER v_order) throws Exception {
		return v_ORDER_EDao.deleteV_ORDER(v_order);
	}

	private void updateEPs(String issue) {
		logger.info("设置篮球当前投注赔率开始-->");

		try {
			String timestamp=DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"); //时间戳
        	
        	String signature=Md5.EncoderByMd5(timestamp+"#"+djKey).toLowerCase(); //签名  MD5(timestamp+"#"+key)
        	
        	//对阵编号
        	
        	@SuppressWarnings("unused")
			JSONObject pvRv=JSONObject.parseObject(PostUtils.doPost(djUrl, String.format("{\"timestamp\":\"%s\",\"signature\":\"%s\",\"issue\":\"%s\"}", timestamp,signature,issue)));
        	
		} catch (Exception e) {
			logger.error("修改篮球即时投注赔率出错-->" + e.getMessage(), e);
		}

		logger.info("设置当前篮球投注赔率成功结束-->");
	}

	@Override
	public int insertV_ORDER(V_ORDER vOrder, List<LOTO_ORDER> lstLotoOrder) throws Exception {

		loto_ORDER_EDao.insertLOTO_ORDER(lstLotoOrder);
		v_ORDER_EDao.insertV_ORDER(vOrder);

		// 统计支持率
		for (LOTO_ORDER t : lstLotoOrder) {

			if (t.getBet_type() == 407) {
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

		updateEPs(vOrder.getIssume());
		return 0;
	}

}