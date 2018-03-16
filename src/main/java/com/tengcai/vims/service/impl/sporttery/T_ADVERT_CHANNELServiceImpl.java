package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.T_ADVERT_CHANNEL;
import com.tengcai.vims.dao.sporttery.T_ADVERT_CHANNELDao;
import com.tengcai.vims.service.sporttery.T_ADVERT_CHANNELService;


/**
 * 首页频道图片
serviceImpl
 */
@Service
public class T_ADVERT_CHANNELServiceImpl implements T_ADVERT_CHANNELService  {
	@Autowired
	private T_ADVERT_CHANNELDao t_advert_channelDao;
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_CHANNEL> selectT_ADVERT_CHANNELList(T_ADVERT_CHANNEL t_advert_channel) throws Exception{
        return t_advert_channelDao.selectT_ADVERT_CHANNELList(t_advert_channel);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_CHANNELCount(T_ADVERT_CHANNEL t_advert_channel) throws Exception{
        return t_advert_channelDao.selectT_ADVERT_CHANNELCount(t_advert_channel);
    }


	
}