package com.tengcai.vims.dao.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.T_ADVERT_FLOAT;


/**
 * 首页浮动广告dao
 */
public interface T_ADVERT_FLOATDao {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_FLOAT> selectT_ADVERT_FLOATList(T_ADVERT_FLOAT t_advert_float) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_FLOATCount(T_ADVERT_FLOAT t_advert_float) throws Exception;


	
}