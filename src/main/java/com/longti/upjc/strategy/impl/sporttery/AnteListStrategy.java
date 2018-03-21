/**
 * @author 张世才
 * 2018-03-20 17:49
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.entity.sporttery.TAB_CHIP;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.TAB_CHIPService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.ReturnValue;

/**
 * 获取投注底注
 * 
 * @return
 */
@Component("ante_list")
public class AnteListStrategy implements IMethodStrategy {
	
	public static class Tab_ChipDetail {
		public List<String> gtos; //GTO底注
		public List<String> eths; //ETH底注
		public List<String> uzs; //U钻底注	
	}
	
	protected final transient static Logger logger = LoggerFactory.getLogger(AnteListStrategy.class);
	@Autowired
	private TAB_CHIPService tab_CHIPService;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<Tab_ChipDetail> rv = new ReturnValue<Tab_ChipDetail>();
		logger.info("ante_list开始调用获取投注底注接口doJsonMethod------>");
		
		TAB_CHIP tab_chip = new TAB_CHIP();		
		List<TAB_CHIP> tab_chipList = null;
		try {	
			tab_chipList = tab_CHIPService.selectTAB_CHIPList(tab_chip);
			List<String> cgtos = new ArrayList<String>(); //GTO底注
			List<String> ceths = new ArrayList<String>(); //ETH底注
			List<String> cuzs = new ArrayList<String>(); //U钻底注
			for(TAB_CHIP ant : tab_chipList){
				if("GTO".equals(ant.getCurrency_type())){
					cgtos.add(ant.getOptions_one());
					cgtos.add(ant.getOptions_two());
					cgtos.add(ant.getOptions_three());
					cgtos.add(ant.getOptions_four());
				}else if("ETH".equals(ant.getCurrency_type())){
					ceths.add(ant.getOptions_one());
					ceths.add(ant.getOptions_two());
					ceths.add(ant.getOptions_three());
					ceths.add(ant.getOptions_four());
				}else if("UZ".equals(ant.getCurrency_type())){
					cuzs.add(ant.getOptions_one());
					cuzs.add(ant.getOptions_two());
					cuzs.add(ant.getOptions_three());
					cuzs.add(ant.getOptions_four());
				}
			}
			Tab_ChipDetail tab_ChipDetail = new Tab_ChipDetail();
			tab_ChipDetail.gtos = cgtos;
			tab_ChipDetail.eths = ceths;
			tab_ChipDetail.uzs = cuzs;
		    rv.setData(tab_ChipDetail);	
			rv.setStatus(ErrorMessage.SUCCESS.getCode());
			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			logger.info("获取投注底注成功----->");
		} catch (Exception e) {
			e.printStackTrace();
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.info("获取投注底注失败----->");
		}
		return JSONObject.toJSONString(rv);
	}
}