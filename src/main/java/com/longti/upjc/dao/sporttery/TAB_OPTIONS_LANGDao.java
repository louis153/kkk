package com.longti.upjc.dao.sporttery;
import java.util.HashMap;
import java.util.List;

import com.longti.upjc.entity.sporttery.TAB_OPTIONS_LANG;


/**
 * 话题竞猜投注选项语言字典dao
 */
public interface TAB_OPTIONS_LANGDao {
	
	
   /**
    * 条件查询map
	*/
	public List<HashMap<String,Object>> selectTAB_OPTIONS_LANGMap(TAB_OPTIONS_LANG tab_options_lang) throws Exception;
    /**
	 * 条件查询
	 */
    public List<TAB_OPTIONS_LANG> selectTAB_OPTIONS_LANGList(TAB_OPTIONS_LANG tab_options_lang) throws Exception;
    /**
     * 条件查询数量
     */
    public int selectTAB_OPTIONS_LANGCount(TAB_OPTIONS_LANG tab_options_lang) throws Exception;
    /**
     * 添加
     */
    public int insertTAB_OPTIONS_LANG(TAB_OPTIONS_LANG tab_options_lang) throws Exception;
    /**
     * 批量添加s
     */
    public int insertTAB_OPTIONS_LANG(List<TAB_OPTIONS_LANG> list) throws Exception;
    /**
     * 修改
     */
    public int updateTAB_OPTIONS_LANG(TAB_OPTIONS_LANG tab_options_lang) throws Exception;
    /**
     * 批量修改
     */
    public int updateTAB_OPTIONS_LANG(List<TAB_OPTIONS_LANG> list) throws Exception;
    /**
     * 删除
     */
    public int deleteTAB_OPTIONS_LANG(TAB_OPTIONS_LANG tab_options_lang) throws Exception;
    
    


	
}