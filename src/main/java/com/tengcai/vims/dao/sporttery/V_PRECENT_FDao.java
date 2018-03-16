package com.tengcai.vims.dao.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.V_PRECENT_F;


/**
 * VIEWdao
 */
public interface V_PRECENT_FDao {
	
   
    /**
	 * 条件查询
	 */
    public List<V_PRECENT_F> selectV_PRECENT_FList(V_PRECENT_F v_precent_f) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectV_PRECENT_FCount(V_PRECENT_F v_precent_f) throws Exception;


	
}