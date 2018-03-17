package com.longti.upjc.dao.impl.sporttery;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.V_LEAGUEDao;
import com.longti.upjc.entity.sporttery.V_LEAGUE;

/**
 * VIEWdaoImpl
 */
@Repository
public class V_LEAGUEDaoImpl extends BaseDaoImpl<V_LEAGUE> implements V_LEAGUEDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<V_LEAGUE> selectV_LEAGUEList(V_LEAGUE v_league) throws Exception{
        return getSqlSession().selectList("com.longti.upjc.entity.sporttery.V_LEAGUE.selectV_LEAGUEList",v_league);
    }
    
    /**
     * 条件查询数量
     */
    public int selectV_LEAGUECount(V_LEAGUE v_league) throws Exception{
        return getSqlSession().selectOne("com.longti.upjc.entity.sporttery.V_LEAGUE.selectV_LEAGUECount",v_league);
    }


	
}