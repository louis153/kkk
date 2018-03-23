package com.longti.upjc.service.impl.sporttery;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.TAB_PLAYMETHOD_LANGDao;
import com.longti.upjc.entity.sporttery.TAB_PLAYMETHOD_LANG;
import com.longti.upjc.service.sporttery.TAB_PLAYMETHOD_LANGService;


/**
 * 话题竞猜玩法信息语言字典serviceImpl
 */
@Service
public class TAB_PLAYMETHOD_LANGServiceImpl implements TAB_PLAYMETHOD_LANGService  {
	@Autowired
	private TAB_PLAYMETHOD_LANGDao TAB_PLAYMETHOD_LANGDao;
   
	/**
	 * 条件查询map
     */
    public HashMap<String,String> selectTAB_PLAYMETHOD_LANGMap(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception{
    	HashMap<String,String> map = new HashMap<String,String>();
    	List<HashMap<String,Object>> tab_playmethod_langs = TAB_PLAYMETHOD_LANGDao.selectTAB_PLAYMETHOD_LANGMap(tab_playmethod_lang);
    	for(HashMap<String,Object> hs : tab_playmethod_langs){
    		String key = ((Object)hs.get("key")).toString().trim();
    		String value = ((Object)hs.get("value")).toString().trim();
    		map.put(key, value);
    	}
    	return map;
    }
	/**
	 * 条件查询
	 */
    public List<TAB_PLAYMETHOD_LANG> selectTAB_PLAYMETHOD_LANGList(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception{
    	return TAB_PLAYMETHOD_LANGDao.selectTAB_PLAYMETHOD_LANGList(tab_playmethod_lang);
    }
    /**
     * 条件查询数量
     */
    public int selectTAB_PLAYMETHOD_LANGCount(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception{
    	return TAB_PLAYMETHOD_LANGDao.selectTAB_PLAYMETHOD_LANGCount(tab_playmethod_lang);
    }
    /**
     * 添加
     */
    public int insertTAB_PLAYMETHOD_LANG(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception{
    	return TAB_PLAYMETHOD_LANGDao.insertTAB_PLAYMETHOD_LANG(tab_playmethod_lang);
    }
    /**
     * 批量添加
     */
    public int insertTAB_PLAYMETHOD_LANG(List<TAB_PLAYMETHOD_LANG> list) throws Exception{
    	return TAB_PLAYMETHOD_LANGDao.insertTAB_PLAYMETHOD_LANG(list);
    }
    /**
     * 修改
     */
    public int updateTAB_PLAYMETHOD_LANG(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception{
    	return TAB_PLAYMETHOD_LANGDao.updateTAB_PLAYMETHOD_LANG(tab_playmethod_lang);
    }
    /**
     * 批量修改
     */
    public int updateTAB_PLAYMETHOD_LANG(List<TAB_PLAYMETHOD_LANG> list) throws Exception{
    	return TAB_PLAYMETHOD_LANGDao.updateTAB_PLAYMETHOD_LANG(list);
    }
    /**
     * 删除
     */
    public int deleteTAB_PLAYMETHOD_LANG(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception{
    	return TAB_PLAYMETHOD_LANGDao.deleteTAB_PLAYMETHOD_LANG(tab_playmethod_lang);
    }


	
}