package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;import com.tengcai.vims.entity.sporttery.V_PRECENT_F;
import com.tengcai.vims.dao.sporttery.V_PRECENT_FDao;


/**
 * VIEWdaoImpl
 */
@Repository
public class V_PRECENT_FDaoImpl extends BaseDaoImpl<V_PRECENT_F> implements V_PRECENT_FDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<V_PRECENT_F> selectV_PRECENT_FList(V_PRECENT_F v_precent_f) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.V_PRECENT_F.selectV_PRECENT_FList",v_precent_f);
    }
    
    /**
     * 条件查询数量
     */
    public int selectV_PRECENT_FCount(V_PRECENT_F v_precent_f) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.V_PRECENT_F.selectV_PRECENT_FCount",v_precent_f);
    }


	
}