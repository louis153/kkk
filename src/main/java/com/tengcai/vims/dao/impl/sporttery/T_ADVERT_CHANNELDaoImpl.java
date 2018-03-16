package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;import com.tengcai.vims.entity.sporttery.T_ADVERT_CHANNEL;
import com.tengcai.vims.dao.sporttery.T_ADVERT_CHANNELDao;


/**
 * 首页频道图片
daoImpl
 */
@Repository
public class T_ADVERT_CHANNELDaoImpl extends BaseDaoImpl<T_ADVERT_CHANNEL> implements T_ADVERT_CHANNELDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_CHANNEL> selectT_ADVERT_CHANNELList(T_ADVERT_CHANNEL t_advert_channel) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.T_ADVERT_CHANNEL.selectT_ADVERT_CHANNELList",t_advert_channel);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_CHANNELCount(T_ADVERT_CHANNEL t_advert_channel) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.T_ADVERT_CHANNEL.selectT_ADVERT_CHANNELCount",t_advert_channel);
    }


	
}