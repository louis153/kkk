package com.longti.upjc.dao.sporttery;
import java.util.List;
import com.longti.upjc.entity.sporttery.TAB_WARN_RECEIVE;


/**
 * 告警消息接受人dao
 */
public interface TAB_WARN_RECEIVEDao {
	
   
    /**
	 * 条件查询
	 */
    public List<TAB_WARN_RECEIVE> selectTAB_WARN_RECEIVEList(TAB_WARN_RECEIVE tab_warn_receive) throws Exception;

    
}