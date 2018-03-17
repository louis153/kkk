package com.longti.upjc.service.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.V_LEAGUE;


/**
 * VIEWservice
 */
public interface V_LEAGUE_FService {
	
   
    /**
	 * 条件查询
	 */
    public List<V_LEAGUE> selectV_LEAGUE_FList(V_LEAGUE v_league_f) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectV_LEAGUE_FCount(V_LEAGUE v_league_f) throws Exception;


	
}