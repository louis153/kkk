package com.tengcai.vims.service.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.V_PRECENT_B;


/**
 * VIEWservice
 */
public interface V_PRECENT_BService {
	
   
    /**
	 * 条件查询
	 */
    public List<V_PRECENT_B> selectV_PRECENT_BList(V_PRECENT_B v_precent_b) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectV_PRECENT_BCount(V_PRECENT_B v_precent_b) throws Exception;


	
}