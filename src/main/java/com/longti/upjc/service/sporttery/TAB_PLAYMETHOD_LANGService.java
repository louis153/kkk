package com.longti.upjc.service.sporttery;
import java.util.HashMap;
import java.util.List;

import com.longti.upjc.entity.sporttery.TAB_PLAYMETHOD_LANG;


/**
 * 话题竞猜玩法信息语言字典service
 */
public interface TAB_PLAYMETHOD_LANGService {
	
	/**
	 * 条件查询map
     */
    public HashMap<String,String> selectTAB_PLAYMETHOD_LANGMap(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception;
	/**
	 * 条件查询
	 */
    public List<TAB_PLAYMETHOD_LANG> selectTAB_PLAYMETHOD_LANGList(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception;
    /**
     * 条件查询数量
     */
    public int selectTAB_PLAYMETHOD_LANGCount(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception;
    /**
     * 添加
     */
    public int insertTAB_PLAYMETHOD_LANG(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception;
    /**
     * 批量添加
     */
    public int insertTAB_PLAYMETHOD_LANG(List<TAB_PLAYMETHOD_LANG> list) throws Exception;
    /**
     * 修改
     */
    public int updateTAB_PLAYMETHOD_LANG(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception;
    /**
     * 批量修改
     */
    public int updateTAB_PLAYMETHOD_LANG(List<TAB_PLAYMETHOD_LANG> list) throws Exception;
    /**
     * 删除
     */
    public int deleteTAB_PLAYMETHOD_LANG(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception;


	
}