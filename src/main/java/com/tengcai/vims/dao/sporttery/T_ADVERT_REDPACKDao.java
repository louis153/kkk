package com.tengcai.vims.dao.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.T_ADVERT_REDPACK;


/**
 * 红包雨dao
 */
public interface T_ADVERT_REDPACKDao {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_REDPACK> selectT_ADVERT_REDPACKList(T_ADVERT_REDPACK t_advert_redpack) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_REDPACKCount(T_ADVERT_REDPACK t_advert_redpack) throws Exception;


	
}