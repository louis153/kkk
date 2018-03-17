package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.V_SALEDAYDao;
import com.longti.upjc.entity.sporttery.V_SALEDAY;


/**
 * VIEWdaoImpl
 */
@Repository
public class V_SALEDAYDaoImpl extends BaseDaoImpl<V_SALEDAY> implements V_SALEDAYDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<V_SALEDAY> selectV_SALEDAYList(V_SALEDAY v_saleday) throws Exception{
        return getSqlSession().selectList("com.longti.upjc.entity.sporttery.V_SALEDAY.selectV_SALEDAYList",v_saleday);
    }
    
    

	
}