package com.longti.upjc.service.impl.sporttery;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.TAB_TEAMNAME_LANGDao;
import com.longti.upjc.entity.sporttery.TAB_TEAMNAME_LANG;
import com.longti.upjc.service.sporttery.TAB_TEAMNAME_LANGService;


/**
 * 球队语言字典serviceImpl
 */
@Service
public class TAB_TEAMNAME_LANGServiceImpl implements TAB_TEAMNAME_LANGService  {
	@Autowired
	private TAB_TEAMNAME_LANGDao TAB_TEAMNAME_LANGDao;
   
	/**
	 * 条件查询map
     */
    public HashMap<String,String> selectTAB_TEAMNAME_LANGMap(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception{
    	HashMap<String,String> map = new HashMap<String,String>();
    	List<HashMap<String,Object>> tab_teamname_langs = TAB_TEAMNAME_LANGDao.selectTAB_TEAMNAME_LANGMap(tab_teamname_lang);
    	for(HashMap<String,Object> hs : tab_teamname_langs){
    		String key = ((Object)hs.get("key")).toString().trim();
    		String value = ((Object)hs.get("value")).toString().trim();
    		map.put(key, value);
    	}
    	return map;
    }
	/**
	 * 条件查询
	 */
    public List<TAB_TEAMNAME_LANG> selectTAB_TEAMNAME_LANGList(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception{
    	return TAB_TEAMNAME_LANGDao.selectTAB_TEAMNAME_LANGList(tab_teamname_lang);
    }
    /**
     * 条件查询数量
     */
    public int selectTAB_TEAMNAME_LANGCount(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception{
    	return TAB_TEAMNAME_LANGDao.selectTAB_TEAMNAME_LANGCount(tab_teamname_lang);
    }
    /**
     * 添加
     */
    public int insertTAB_TEAMNAME_LANG(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception{
    	return TAB_TEAMNAME_LANGDao.insertTAB_TEAMNAME_LANG(tab_teamname_lang);
    }
    /**
     * 批量添加
     */
    public int insertTAB_TEAMNAME_LANG(List<TAB_TEAMNAME_LANG> list) throws Exception{
    	return TAB_TEAMNAME_LANGDao.insertTAB_TEAMNAME_LANG(list);
    }
    /**
     * 修改
     */
    public int updateTAB_TEAMNAME_LANG(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception{
    	return TAB_TEAMNAME_LANGDao.updateTAB_TEAMNAME_LANG(tab_teamname_lang);
    }
    /**
     * 批量修改
     */
    public int updateTAB_TEAMNAME_LANG(List<TAB_TEAMNAME_LANG> list) throws Exception{
    	return TAB_TEAMNAME_LANGDao.updateTAB_TEAMNAME_LANG(list);
    }
    /**
     * 删除
     */
    public int deleteTAB_TEAMNAME_LANG(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception{
    	return TAB_TEAMNAME_LANGDao.deleteTAB_TEAMNAME_LANG(tab_teamname_lang);
    }


	
}