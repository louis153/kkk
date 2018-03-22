package com.longti.upjc.dao.impl.sporttery;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.TAB_TEAMNAME_LANGDao;
import com.longti.upjc.entity.sporttery.TAB_TEAMNAME_LANG;


/**
 * 球队语言字典daoImpl
 */
@Repository
public class TAB_TEAMNAME_LANGDaoImpl extends BaseDaoImpl<TAB_TEAMNAME_LANG> implements TAB_TEAMNAME_LANGDao  {
	
	/**
	 * 条件查询map
     */
    public List<HashMap<String,Object>> selectTAB_TEAMNAME_LANGMap(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception{
    	return  getSqlSession().selectList("selectTAB_TEAMNAME_LANGMap",tab_teamname_lang);
    }
    /**
	 * 条件查询
	 */
    public List<TAB_TEAMNAME_LANG> selectTAB_TEAMNAME_LANGList(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception{   	   	
        return findAllByKey(tab_teamname_lang,"selectTAB_TEAMNAME_LANGList");
    }
    /**
     * 条件查询数量
     */
    public int selectTAB_TEAMNAME_LANGCount(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception{   	
        return getCountNum(tab_teamname_lang,"selectTAB_TEAMNAME_LANGCount");
    }
    /**
     * 添加
     */
    public int insertTAB_TEAMNAME_LANG(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception{
        return save("save",tab_teamname_lang);
    }
    /**
     * 批量添加
     */
    public int insertTAB_TEAMNAME_LANG(List<TAB_TEAMNAME_LANG> list) throws Exception{
        return save("saveList",list);
    }
    /**
     * 修改
     */
    public int updateTAB_TEAMNAME_LANG(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception{
        return update("update",tab_teamname_lang);
    }
    /**
     * 批量修改 
     */
    public int updateTAB_TEAMNAME_LANG(List<TAB_TEAMNAME_LANG> list) throws Exception{
        return update("updateList",list);
    }
    /**
     * 删除
     */
    public int deleteTAB_TEAMNAME_LANG(TAB_TEAMNAME_LANG tab_teamname_lang) throws Exception{
        return delete("delete",tab_teamname_lang);
    }


	
}