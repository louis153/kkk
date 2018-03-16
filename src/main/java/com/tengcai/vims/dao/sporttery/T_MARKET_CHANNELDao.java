package com.tengcai.vims.dao.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL;


/**
 * 渠道管理dao
 */
public interface T_MARKET_CHANNELDao {
	
   
    /**
	 * 条件查询
	 */
    public List<T_MARKET_CHANNEL> selectT_MARKET_CHANNELList(T_MARKET_CHANNEL t_market_channel) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_MARKET_CHANNELCount(T_MARKET_CHANNEL t_market_channel) throws Exception;


	
}