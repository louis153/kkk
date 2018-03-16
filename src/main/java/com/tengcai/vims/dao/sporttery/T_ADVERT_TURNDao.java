package com.tengcai.vims.dao.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.T_ADVERT_TURN;


/**
 * 首页轮播广告dao
 */
public interface T_ADVERT_TURNDao {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_TURN> selectT_ADVERT_TURNList(T_ADVERT_TURN t_advert_turn) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_TURNCount(T_ADVERT_TURN t_advert_turn) throws Exception;


	
}