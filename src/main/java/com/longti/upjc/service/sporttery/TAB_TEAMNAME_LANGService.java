package com.longti.upjc.service.sporttery;
import java.util.HashMap;
import java.util.List;

import com.longti.upjc.entity.sporttery.TAB_TEAMNAME_LANG;


/**
 * 球队语言字典service
 */
public interface TAB_TEAMNAME_LANGService {
	
	/**
	 * 条件查询map
     */
    public HashMap<String,String> selectTAB_TEAMNAME_LANGMap(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception;
	/**
	 * 条件查询
	 */
    public List<TAB_TEAMNAME_LANG> selectTAB_TEAMNAME_LANGList(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception;
    /**
     * 条件查询数量
     */
    public int selectTAB_TEAMNAME_LANGCount(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception;
    /**
     * 添加
     */
    public int insertTAB_TEAMNAME_LANG(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception;
    /**
     * 批量添加
     */
    public int insertTAB_TEAMNAME_LANG(List<TAB_TEAMNAME_LANG> list) throws Exception;
    /**
     * 修改
     */
    public int updateTAB_TEAMNAME_LANG(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception;
    /**
     * 批量修改
     */
    public int updateTAB_TEAMNAME_LANG(List<TAB_TEAMNAME_LANG> list) throws Exception;
    /**
     * 删除
     */
    public int deleteTAB_TEAMNAME_LANG(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception;


	
}