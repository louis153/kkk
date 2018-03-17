package com.longti.upjc.strategy.impl.sporttery;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.longti.upjc.strategy.sporttery.IMethodStrategy;
@Component
public class MethodStrategyMap  {
	HashMap<String,IMethodStrategy> map=new HashMap<String,IMethodStrategy>();
	@Autowired
    private LotoOrderListStrategy loto_orderList;   
    @Autowired
    private TWinListStrategy t_win_list;
    @Autowired
    private VSaledayListStrategy v_saleday_list;
    @Autowired
    private TLotoFnListStrategy loto_fn_list;
    @Autowired
    private LotoRecommendListStrategy loto_recommend_list;
    @Autowired
    public MethodStrategyMap(){
    	map.put("loto_orderList", loto_orderList);// 我的竞猜列表    
    	map.put("t_win_list", t_win_list);//首页中奖公告列表
    	map.put("v_saleday_list", v_saleday_list);//首页中奖公告列表
    	map.put("loto_fn_list", loto_fn_list);//查看足球在售赛事列表	
    	map.put("loto_recommend_list",loto_recommend_list);//查询足球推荐信息
    }
    public IMethodStrategy get(String type){
    	return map.get(type);
    }
}
