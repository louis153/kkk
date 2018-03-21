package com.longti.upjc.dao.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.TAB_DICT;


/**
 * 奖励机制信息dao
 */
public interface TAB_DICTDao {
	
   
    /**
	 * 条件查询
	 */
    public List<TAB_DICT> selectTAB_DICTList(TAB_DICT tab_dict) throws Exception;
    /**
     * 条件查询数量
     */
    public int selectTAB_DICTCount(TAB_DICT tab_dict) throws Exception;
    /**
     * 添加
     */
    public int insertTAB_DICT(TAB_DICT tab_dict) throws Exception;
    /**
     * 批量添加s
     */
    public int insertTAB_DICT(List<TAB_DICT> list) throws Exception;
    /**
     * 修改
     */
    public int updateTAB_DICT(TAB_DICT tab_dict) throws Exception;
    /**
     * 批量修改
     */
    public int updateTAB_DICT(List<TAB_DICT> list) throws Exception;
    /**
     * 删除
     */
    public int deleteTAB_DICT(TAB_DICT tab_dict) throws Exception;
    
    


	
}