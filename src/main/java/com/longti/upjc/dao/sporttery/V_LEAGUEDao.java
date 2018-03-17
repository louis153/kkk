package com.longti.upjc.dao.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.V_LEAGUE;


/**
 * VIEWdao
 */
public interface V_LEAGUEDao {
	
   
    /**
	 * 条件查询
	 */
    public List<V_LEAGUE> selectV_LEAGUEList(V_LEAGUE v_league) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectV_LEAGUECount(V_LEAGUE v_league) throws Exception;


	
}