package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.entity.sporttery.TAB_WARN_RECEIVE;
import com.longti.upjc.dao.sporttery.TAB_WARN_RECEIVEDao;


/**
 * 告警消息接受人daoImpl
 */
@Repository
public class TAB_WARN_RECEIVEDaoImpl extends BaseDaoImpl<TAB_WARN_RECEIVE> implements TAB_WARN_RECEIVEDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<TAB_WARN_RECEIVE> selectTAB_WARN_RECEIVEList(TAB_WARN_RECEIVE tab_warn_receive) throws Exception{
        return getSqlSession().selectList("com.longti.upjc.entity.sporttery.TAB_WARN_RECEIVE.selectTAB_WARN_RECEIVEList",tab_warn_receive);
    }
    
    
	
}