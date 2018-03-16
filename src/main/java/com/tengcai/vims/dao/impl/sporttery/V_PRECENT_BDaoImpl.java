package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;import com.tengcai.vims.entity.sporttery.V_PRECENT_B;
import com.tengcai.vims.dao.sporttery.V_PRECENT_BDao;


/**
 * VIEWdaoImpl
 */
@Repository
public class V_PRECENT_BDaoImpl extends BaseDaoImpl<V_PRECENT_B> implements V_PRECENT_BDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<V_PRECENT_B> selectV_PRECENT_BList(V_PRECENT_B V_PRECENT_B) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.V_PRECENT_B.selectV_PRECENT_BList",V_PRECENT_B);
    }
    
    /**
     * 条件查询数量
     */
    public int selectV_PRECENT_BCount(V_PRECENT_B V_PRECENT_B) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.V_PRECENT_B.selectV_PRECENT_BCount",V_PRECENT_B);
    }


	
}