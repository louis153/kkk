package com.tengcai.vims.service.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.V_LEAGUE_F;


/**
 * VIEWservice
 */
public interface V_LEAGUE_FService {
	
   
    /**
	 * 条件查询
	 */
    public List<V_LEAGUE_F> selectV_LEAGUE_FList(V_LEAGUE_F v_league_f) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectV_LEAGUE_FCount(V_LEAGUE_F v_league_f) throws Exception;


	
}