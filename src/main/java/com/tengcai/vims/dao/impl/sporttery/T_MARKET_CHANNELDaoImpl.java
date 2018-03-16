package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;import com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL;
import com.tengcai.vims.dao.sporttery.T_MARKET_CHANNELDao;


/**
 * 渠道管理daoImpl
 */
@Repository
public class T_MARKET_CHANNELDaoImpl extends BaseDaoImpl<T_MARKET_CHANNEL> implements T_MARKET_CHANNELDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_MARKET_CHANNEL> selectT_MARKET_CHANNELList(T_MARKET_CHANNEL t_market_channel) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL.selectT_MARKET_CHANNELList",t_market_channel);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_MARKET_CHANNELCount(T_MARKET_CHANNEL t_market_channel) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL.selectT_MARKET_CHANNELCount",t_market_channel);
    }


	
}