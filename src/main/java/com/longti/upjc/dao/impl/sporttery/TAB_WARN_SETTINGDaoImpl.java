package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.entity.sporttery.TAB_WARN_SETTING;
import com.longti.upjc.dao.sporttery.TAB_WARN_SETTINGDao;


/**
 * 告警比例设置daoImpl
 */
@Repository
public class TAB_WARN_SETTINGDaoImpl extends BaseDaoImpl<TAB_WARN_SETTING> implements TAB_WARN_SETTINGDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<TAB_WARN_SETTING> selectTAB_WARN_SETTINGList(TAB_WARN_SETTING tab_warn_setting) throws Exception{
        return getSqlSession().selectList("com.longti.upjc.entity.sporttery.TAB_WARN_SETTING.selectTAB_WARN_SETTINGList",tab_warn_setting);
    }
    
    
}