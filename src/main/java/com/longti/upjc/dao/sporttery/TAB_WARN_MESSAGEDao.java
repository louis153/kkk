package com.longti.upjc.dao.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.TAB_WARN_MESSAGE;


/**
 * 告警消息列表dao
 */
public interface TAB_WARN_MESSAGEDao {
	
	/**
	 * 条件查询
	 */
    public List<TAB_WARN_MESSAGE> selectTAB_WARN_MESSAGEList(TAB_WARN_MESSAGE tab_warn_message) throws Exception;

	
    /**
     * 添加
     */
    public int insertTAB_WARN_MESSAGE(TAB_WARN_MESSAGE tab_warn_message) throws Exception;
    
}