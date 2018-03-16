package com.tengcai.vims.dao.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.T_ADVERT_BANNER;


/**
 * 首页通栏广告dao
 */
public interface T_ADVERT_BANNERDao {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_BANNER> selectT_ADVERT_BANNERList(T_ADVERT_BANNER t_advert_banner) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_BANNERCount(T_ADVERT_BANNER t_advert_banner) throws Exception;


	
}