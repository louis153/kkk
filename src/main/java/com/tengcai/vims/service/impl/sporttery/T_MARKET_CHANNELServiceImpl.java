package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL;
import com.tengcai.vims.dao.sporttery.T_MARKET_CHANNELDao;
import com.tengcai.vims.service.sporttery.T_MARKET_CHANNELService;


/**
 * 渠道管理serviceImpl
 */
@Service
public class T_MARKET_CHANNELServiceImpl implements T_MARKET_CHANNELService  {
	@Autowired
	private T_MARKET_CHANNELDao t_market_channelDao;
   
    /**
	 * 条件查询
	 */
    public List<T_MARKET_CHANNEL> selectT_MARKET_CHANNELList(T_MARKET_CHANNEL t_market_channel) throws Exception{
        return t_market_channelDao.selectT_MARKET_CHANNELList(t_market_channel);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_MARKET_CHANNELCount(T_MARKET_CHANNEL t_market_channel) throws Exception{
        return t_market_channelDao.selectT_MARKET_CHANNELCount(t_market_channel);
    }


	
}