package com.longti.upjc.service.impl.sporttery;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.TAB_LEAGUENAME_LANGDao;
import com.longti.upjc.entity.sporttery.TAB_LEAGUENAME_LANG;
import com.longti.upjc.service.sporttery.TAB_LEAGUENAME_LANGService;


/**
 * 赛事语言字典serviceImpl
 */
@Service
public class TAB_LEAGUENAME_LANGServiceImpl implements TAB_LEAGUENAME_LANGService  {
	@Autowired
	private TAB_LEAGUENAME_LANGDao TAB_LEAGUENAME_LANGDao;
   
	/**
	 * 条件查询map
     */
    public HashMap<String,String> selectTAB_LEAGUENAME_LANGMap(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception{
    	HashMap<String,String> map = new HashMap<String,String>();
    	List<HashMap<String,Object>> tab_leaguename_langs = TAB_LEAGUENAME_LANGDao.selectTAB_LEAGUENAME_LANGMap(tab_leaguename_lang);
    	for(HashMap<String,Object> hs : tab_leaguename_langs){
    		String key = ((Object)hs.get("key")).toString().trim();
    		String value = ((Object)hs.get("value")).toString().trim();
    		map.put(key, value);
    	}
    	return map;
    }
	/**
	 * 条件查询
	 */
    public List<TAB_LEAGUENAME_LANG> selectTAB_LEAGUENAME_LANGList(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception{
    	return TAB_LEAGUENAME_LANGDao.selectTAB_LEAGUENAME_LANGList(tab_leaguename_lang);
    }
    /**
     * 条件查询数量
     */
    public int selectTAB_LEAGUENAME_LANGCount(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception{
    	return TAB_LEAGUENAME_LANGDao.selectTAB_LEAGUENAME_LANGCount(tab_leaguename_lang);
    }
    /**
     * 添加
     */
    public int insertTAB_LEAGUENAME_LANG(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception{
    	return TAB_LEAGUENAME_LANGDao.insertTAB_LEAGUENAME_LANG(tab_leaguename_lang);
    }
    /**
     * 批量添加
     */
    public int insertTAB_LEAGUENAME_LANG(List<TAB_LEAGUENAME_LANG> list) throws Exception{
    	return TAB_LEAGUENAME_LANGDao.insertTAB_LEAGUENAME_LANG(list);
    }
    /**
     * 修改
     */
    public int updateTAB_LEAGUENAME_LANG(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception{
    	return TAB_LEAGUENAME_LANGDao.updateTAB_LEAGUENAME_LANG(tab_leaguename_lang);
    }
    /**
     * 批量修改
     */
    public int updateTAB_LEAGUENAME_LANG(List<TAB_LEAGUENAME_LANG> list) throws Exception{
    	return TAB_LEAGUENAME_LANGDao.updateTAB_LEAGUENAME_LANG(list);
    }
    /**
     * 删除
     */
    public int deleteTAB_LEAGUENAME_LANG(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception{
    	return TAB_LEAGUENAME_LANGDao.deleteTAB_LEAGUENAME_LANG(tab_leaguename_lang);
    }


	
}