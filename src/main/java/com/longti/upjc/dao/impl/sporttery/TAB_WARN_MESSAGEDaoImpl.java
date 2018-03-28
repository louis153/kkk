package com.longti.upjc.dao.impl.sporttery;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.entity.sporttery.TAB_WARN_MESSAGE;
import com.longti.upjc.dao.sporttery.TAB_WARN_MESSAGEDao;


/**
 * 告警消息列表daoImpl
 */
@Repository
public class TAB_WARN_MESSAGEDaoImpl extends BaseDaoImpl<TAB_WARN_MESSAGE> implements TAB_WARN_MESSAGEDao  {
	/**
	 * 条件查询
	 */
    public List<TAB_WARN_MESSAGE> selectTAB_WARN_MESSAGEList(TAB_WARN_MESSAGE tab_warn_message) throws Exception{
        return getSqlSession().selectList("com.longti.upjc.entity.sporttery.TAB_WARN_MESSAGE.selectTAB_WARN_MESSAGEList",tab_warn_message);
    }
   
    /**
     * 添加
     */
    public int insertTAB_WARN_MESSAGE(TAB_WARN_MESSAGE tab_warn_message) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.TAB_WARN_MESSAGE.save",tab_warn_message);
    }
}