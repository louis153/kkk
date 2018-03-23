package com.longti.upjc.dao.impl.sporttery;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.TAB_PLAYMETHOD_LANGDao;
import com.longti.upjc.entity.sporttery.TAB_PLAYMETHOD_LANG;


/**
 * 话题竞猜玩法信息语言字典daoImpl
 */
@Repository
public class TAB_PLAYMETHOD_LANGDaoImpl extends BaseDaoImpl<TAB_PLAYMETHOD_LANG> implements TAB_PLAYMETHOD_LANGDao  {
	
	/**
	 * 条件查询map
     */
    public List<HashMap<String,Object>> selectTAB_PLAYMETHOD_LANGMap(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception{
    	return  getSqlSession().selectList("selectTAB_PLAYMETHOD_LANGMap",tab_playmethod_lang);
    }
    /**
	 * 条件查询
	 */
    public List<TAB_PLAYMETHOD_LANG> selectTAB_PLAYMETHOD_LANGList(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception{   	   	
        return findAllByKey(tab_playmethod_lang,"selectTAB_PLAYMETHOD_LANGList");
    }
    /**
     * 条件查询数量
     */
    public int selectTAB_PLAYMETHOD_LANGCount(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception{   	
        return getCountNum(tab_playmethod_lang,"selectTAB_PLAYMETHOD_LANGCount");
    }
    /**
     * 添加
     */
    public int insertTAB_PLAYMETHOD_LANG(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception{
        return save("save",tab_playmethod_lang);
    }
    /**
     * 批量添加
     */
    public int insertTAB_PLAYMETHOD_LANG(List<TAB_PLAYMETHOD_LANG> list) throws Exception{
        return save("saveList",list);
    }
    /**
     * 修改
     */
    public int updateTAB_PLAYMETHOD_LANG(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception{
        return update("update",tab_playmethod_lang);
    }
    /**
     * 批量修改 
     */
    public int updateTAB_PLAYMETHOD_LANG(List<TAB_PLAYMETHOD_LANG> list) throws Exception{
        return update("updateList",list);
    }
    /**
     * 删除
     */
    public int deleteTAB_PLAYMETHOD_LANG(TAB_PLAYMETHOD_LANG tab_playmethod_lang) throws Exception{
        return delete("delete",tab_playmethod_lang);
    }


	
}