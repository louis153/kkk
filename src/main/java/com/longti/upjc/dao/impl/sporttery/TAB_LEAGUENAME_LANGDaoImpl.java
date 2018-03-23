package com.longti.upjc.dao.impl.sporttery;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.TAB_LEAGUENAME_LANGDao;
import com.longti.upjc.entity.sporttery.TAB_LEAGUENAME_LANG;


/**
 * 赛事语言字典daoImpl
 */
@Repository
public class TAB_LEAGUENAME_LANGDaoImpl extends BaseDaoImpl<TAB_LEAGUENAME_LANG> implements TAB_LEAGUENAME_LANGDao  {
	
	/**
	 * 条件查询map
     */
    public List<HashMap<String,Object>> selectTAB_LEAGUENAME_LANGMap(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception{
    	return  getSqlSession().selectList("selectTAB_LEAGUENAME_LANGMap",tab_leaguename_lang);
    }
    /**
	 * 条件查询
	 */
    public List<TAB_LEAGUENAME_LANG> selectTAB_LEAGUENAME_LANGList(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception{   	   	
        return findAllByKey(tab_leaguename_lang,"selectTAB_LEAGUENAME_LANGList");
    }
    /**
     * 条件查询数量
     */
    public int selectTAB_LEAGUENAME_LANGCount(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception{   	
        return getCountNum(tab_leaguename_lang,"selectTAB_LEAGUENAME_LANGCount");
    }
    /**
     * 添加
     */
    public int insertTAB_LEAGUENAME_LANG(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception{
        return save("save",tab_leaguename_lang);
    }
    /**
     * 批量添加
     */
    public int insertTAB_LEAGUENAME_LANG(List<TAB_LEAGUENAME_LANG> list) throws Exception{
        return save("saveList",list);
    }
    /**
     * 修改
     */
    public int updateTAB_LEAGUENAME_LANG(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception{
        return update("update",tab_leaguename_lang);
    }
    /**
     * 批量修改 
     */
    public int updateTAB_LEAGUENAME_LANG(List<TAB_LEAGUENAME_LANG> list) throws Exception{
        return update("updateList",list);
    }
    /**
     * 删除
     */
    public int deleteTAB_LEAGUENAME_LANG(TAB_LEAGUENAME_LANG tab_leaguename_lang) throws Exception{
        return delete("delete",tab_leaguename_lang);
    }


	
}