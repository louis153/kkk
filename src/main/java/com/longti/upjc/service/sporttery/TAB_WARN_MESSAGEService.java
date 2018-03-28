package com.longti.upjc.service.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.TAB_WARN_MESSAGE;
import com.longti.upjc.entity.sporttery.TAB_WARN_RECEIVE;
import com.longti.upjc.entity.sporttery.TAB_WARN_SETTING;


/**
 * 告警消息列表service
 */
public interface TAB_WARN_MESSAGEService {
	
	/**
	 * 条件查询
	 */
    public List<TAB_WARN_MESSAGE> selectTAB_WARN_MESSAGEList(TAB_WARN_MESSAGE tab_warn_message) throws Exception;

    
    /**
     * 添加
     */
    public int insertTAB_WARN_MESSAGE(TAB_WARN_MESSAGE tab_warn_message) throws Exception;


	List<TAB_WARN_RECEIVE> selectTAB_WARN_RECEIVEList() throws Exception;


	TAB_WARN_SETTING selectTAB_WARN_SETTING() throws Exception;

    	
}