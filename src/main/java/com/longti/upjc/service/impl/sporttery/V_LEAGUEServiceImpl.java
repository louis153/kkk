package com.longti.upjc.service.impl.sporttery;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.V_LEAGUEDao;
import com.longti.upjc.entity.sporttery.V_LEAGUE;
import com.longti.upjc.service.sporttery.V_LEAGUEService;


/**
 * VIEWserviceImpl
 */
@Service
public class V_LEAGUEServiceImpl implements V_LEAGUEService  {
	@Autowired
	private V_LEAGUEDao v_LEAGUE_BDao;
   
    /**
	 * 条件查询
	 */
    public List<V_LEAGUE> selectV_LEAGUE_List(V_LEAGUE v_league) throws Exception{
        return v_LEAGUE_BDao.selectV_LEAGUEList(v_league);
    }
    
    /**
     * 条件查询数量
     */
    public int selectV_LEAGUE_Count(V_LEAGUE v_league) throws Exception{
        return v_LEAGUE_BDao.selectV_LEAGUECount(v_league);
    }

	


	
}