package com.tengcai.vims.dao.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.T_ADVERT_CHANNEL;


/**
 * 首页频道图片
dao
 */
public interface T_ADVERT_CHANNELDao {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_CHANNEL> selectT_ADVERT_CHANNELList(T_ADVERT_CHANNEL t_advert_channel) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_CHANNELCount(T_ADVERT_CHANNEL t_advert_channel) throws Exception;


	
}