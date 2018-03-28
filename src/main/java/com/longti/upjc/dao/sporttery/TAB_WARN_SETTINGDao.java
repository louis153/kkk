package com.longti.upjc.dao.sporttery;
import java.util.List;
import com.longti.upjc.entity.sporttery.TAB_WARN_SETTING;


/**
 * 告警比例设置dao
 */
public interface TAB_WARN_SETTINGDao {
	
   
    /**
	 * 条件查询
	 */
    public List<TAB_WARN_SETTING> selectTAB_WARN_SETTINGList(TAB_WARN_SETTING tab_warn_setting) throws Exception;

    
}