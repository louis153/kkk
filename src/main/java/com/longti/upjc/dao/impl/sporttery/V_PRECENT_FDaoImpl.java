package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.V_PRECENT_FDao;
import com.longti.upjc.entity.sporttery.V_PRECENT_F;


/**
 * VIEWdaoImpl
 */
@Repository
public class V_PRECENT_FDaoImpl extends BaseDaoImpl<V_PRECENT_F> implements V_PRECENT_FDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<V_PRECENT_F> selectV_PRECENT_FList(V_PRECENT_F v_precent_f) throws Exception{
        return getSqlSession().selectList("com.longti.upjc.entity.sporttery.V_PRECENT_F.selectV_PRECENT_FList",v_precent_f);
    }
    
    /**
     * 条件查询数量
     */
    public int selectV_PRECENT_FCount(V_PRECENT_F v_precent_f) throws Exception{
        return getSqlSession().selectOne("com.longti.upjc.entity.sporttery.V_PRECENT_F.selectV_PRECENT_FCount",v_precent_f);
    }


	
}