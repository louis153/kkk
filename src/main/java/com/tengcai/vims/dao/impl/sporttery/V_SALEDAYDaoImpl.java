package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;import com.tengcai.vims.entity.sporttery.V_SALEDAY;
import com.tengcai.vims.dao.sporttery.V_SALEDAYDao;


/**
 * VIEWdaoImpl
 */
@Repository
public class V_SALEDAYDaoImpl extends BaseDaoImpl<V_SALEDAY> implements V_SALEDAYDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<V_SALEDAY> selectV_SALEDAYList(V_SALEDAY v_saleday) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.V_SALEDAY.selectV_SALEDAYList",v_saleday);
    }
    
    

	
}