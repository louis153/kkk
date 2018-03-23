package com.longti.upjc.service.sporttery;
import java.util.HashMap;
import java.util.List;

import com.longti.upjc.entity.sporttery.TAB_LEAGUENAME_LANG;


/**
 * 赛事语言字典service
 */
public interface TAB_LEAGUENAME_LANGService {
	
	/**
	 * 条件查询map
     */
    public HashMap<String,String> selectTAB_LEAGUENAME_LANGMap(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception;
	/**
	 * 条件查询
	 */
    public List<TAB_LEAGUENAME_LANG> selectTAB_LEAGUENAME_LANGList(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception;
    /**
     * 条件查询数量
     */
    public int selectTAB_LEAGUENAME_LANGCount(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception;
    /**
     * 添加
     */
    public int insertTAB_LEAGUENAME_LANG(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception;
    /**
     * 批量添加
     */
    public int insertTAB_LEAGUENAME_LANG(List<TAB_LEAGUENAME_LANG> list) throws Exception;
    /**
     * 修改
     */
    public int updateTAB_LEAGUENAME_LANG(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception;
    /**
     * 批量修改
     */
    public int updateTAB_LEAGUENAME_LANG(List<TAB_LEAGUENAME_LANG> list) throws Exception;
    /**
     * 删除
     */
    public int deleteTAB_LEAGUENAME_LANG(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception;


	
}