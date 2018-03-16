package com.tengcai.vims.strategy.impl.sporttery;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
@Component
public class MethodStrategyMap  {
	HashMap<String,IMethodStrategy> map=new HashMap<String,IMethodStrategy>();
	@Autowired
    private LotoOrderListStrategy loto_orderList;
    @Autowired
    private TAdvertTurnListStrategy t_advert_turnList;
    @Autowired
    private TAdvertPopListStrategy t_advert_popList;
    @Autowired
    private TAdvertFootListStrategy t_advert_footList;
    @Autowired
    private TAdvertRecordListStrategy t_advert_recordList;
    @Autowired
    private TWinListStrategy t_win_list;
    @Autowired
    private VSaledayListStrategy v_saleday_list;
    @Autowired
    private TLotoFnListStrategy loto_fn_list;
    @Autowired
    private TLotoBnListStrategy loto_bn_list;
    @Autowired
    private LotoRecommendListStrategy loto_recommend_list;
    @Autowired
    private LotoRecommendBasketStrategy loto_recommend_basket;
    @Autowired
    private TLeagueListStrategy v_league_list;
    @Autowired
    private PayFStrategy payF;
    @Autowired
    private PayBStrategy payB;
    @Autowired
    private ResultStrategy getResult;
    @Autowired 
    private UserwinStrategy get_userwin;
    public MethodStrategyMap(){
    	map.put("loto_orderList", loto_orderList);// 我的竞猜列表    
    	map.put("t_advert_turnList", t_advert_turnList);// 首页轮播图
    	map.put("t_advert_popList", t_advert_popList);// 首页弹出广告
    	map.put("t_advert_footList", t_advert_footList);//首页页尾双图
    	map.put("t_advert_recordList", t_advert_recordList);//我的竞猜页面页尾广告
    	map.put("t_win_list", t_win_list);//首页中奖公告列表
    	map.put("v_saleday_list", v_saleday_list);//首页中奖公告列表
    	map.put("loto_fn_list", loto_fn_list);//查看足球在售赛事列表	
    	map.put("loto_bn_list", loto_bn_list);//查看蓝球在售赛事列表	
    	map.put("loto_recommend_list",loto_recommend_list);//查询足球推荐信息
    	map.put("loto_recommend_basket",loto_recommend_basket);//查询蓝球推荐信息    
    	map.put("v_league_list", v_league_list);//查询蓝球赛事列表
    	map.put("payF", payF);//足球支付;
    	map.put("payB", payB);//蓝球支付;
    	map.put("getResult", getResult);//查看竞猜详情
    	map.put("get_userwin", get_userwin);//查询用户昨天中奖情况
    }
    public IMethodStrategy get(String type){
    	return map.get(type);
    }
}
