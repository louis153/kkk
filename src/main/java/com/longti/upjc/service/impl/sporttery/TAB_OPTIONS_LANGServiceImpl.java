package com.longti.upjc.service.impl.sporttery;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.TAB_OPTIONS_LANGDao;
import com.longti.upjc.entity.sporttery.TAB_OPTIONS_LANG;
import com.longti.upjc.service.sporttery.TAB_OPTIONS_LANGService;


/**
 * 话题竞猜投注选项语言字典serviceImpl
 */
@Service
public class TAB_OPTIONS_LANGServiceImpl implements TAB_OPTIONS_LANGService  {
	@Autowired
	private TAB_OPTIONS_LANGDao TAB_OPTIONS_LANGDao;
   
	/**
	 * 条件查询map
     */
    public HashMap<String,String> selectTAB_OPTIONS_LANGMap(TAB_OPTIONS_LANG tab_options_lang) throws Exception{
    	HashMap<String,String> map = new HashMap<String,String>();
    	List<HashMap<String,Object>> tab_options_langs = TAB_OPTIONS_LANGDao.selectTAB_OPTIONS_LANGMap(tab_options_lang);
    	for(HashMap<String,Object> hs : tab_options_langs){
    		String key = ((Object)hs.get("key")).toString().trim();
    		String value = ((Object)hs.get("value")).toString().trim();
    		map.put(key, value);
    	}
    	return map;
    }
	/**
	 * 条件查询
	 */
    public List<TAB_OPTIONS_LANG> selectTAB_OPTIONS_LANGList(TAB_OPTIONS_LANG tab_options_lang) throws Exception{
    	return TAB_OPTIONS_LANGDao.selectTAB_OPTIONS_LANGList(tab_options_lang);
    }
    /**
     * 条件查询数量
     */
    public int selectTAB_OPTIONS_LANGCount(TAB_OPTIONS_LANG tab_options_lang) throws Exception{
    	return TAB_OPTIONS_LANGDao.selectTAB_OPTIONS_LANGCount(tab_options_lang);
    }
    /**
     * 添加
     */
    public int insertTAB_OPTIONS_LANG(TAB_OPTIONS_LANG tab_options_lang) throws Exception{
    	return TAB_OPTIONS_LANGDao.insertTAB_OPTIONS_LANG(tab_options_lang);
    }
    /**
     * 批量添加
     */
    public int insertTAB_OPTIONS_LANG(List<TAB_OPTIONS_LANG> list) throws Exception{
    	return TAB_OPTIONS_LANGDao.insertTAB_OPTIONS_LANG(list);
    }
    /**
     * 修改
     */
    public int updateTAB_OPTIONS_LANG(TAB_OPTIONS_LANG tab_options_lang) throws Exception{
    	return TAB_OPTIONS_LANGDao.updateTAB_OPTIONS_LANG(tab_options_lang);
    }
    /**
     * 批量修改
     */
    public int updateTAB_OPTIONS_LANG(List<TAB_OPTIONS_LANG> list) throws Exception{
    	return TAB_OPTIONS_LANGDao.updateTAB_OPTIONS_LANG(list);
    }
    /**
     * 删除
     */
    public int deleteTAB_OPTIONS_LANG(TAB_OPTIONS_LANG tab_options_lang) throws Exception{
    	return TAB_OPTIONS_LANGDao.deleteTAB_OPTIONS_LANG(tab_options_lang);
    }


	
}