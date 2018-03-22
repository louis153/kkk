package com.longti.upjc.dao.impl.sporttery;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.TAB_OPTIONS_LANGDao;
import com.longti.upjc.entity.sporttery.TAB_OPTIONS_LANG;


/**
 * 话题竞猜投注选项语言字典daoImpl
 */
@Repository
public class TAB_OPTIONS_LANGDaoImpl extends BaseDaoImpl<TAB_OPTIONS_LANG> implements TAB_OPTIONS_LANGDao  {
	
	/**
	 * 条件查询map
     */
    public List<HashMap<String,Object>> selectTAB_OPTIONS_LANGMap(TAB_OPTIONS_LANG tab_options_lang) throws Exception{
    	return  getSqlSession().selectList("selectTAB_OPTIONS_LANGMap",tab_options_lang);
    }
    /**
	 * 条件查询
	 */
    public List<TAB_OPTIONS_LANG> selectTAB_OPTIONS_LANGList(TAB_OPTIONS_LANG tab_options_lang) throws Exception{   	   	
        return findAllByKey(tab_options_lang,"selectTAB_OPTIONS_LANGList");
    }
    /**
     * 条件查询数量
     */
    public int selectTAB_OPTIONS_LANGCount(TAB_OPTIONS_LANG tab_options_lang) throws Exception{   	
        return getCountNum(tab_options_lang,"selectTAB_OPTIONS_LANGCount");
    }
    /**
     * 添加
     */
    public int insertTAB_OPTIONS_LANG(TAB_OPTIONS_LANG tab_options_lang) throws Exception{
        return save("save",tab_options_lang);
    }
    /**
     * 批量添加
     */
    public int insertTAB_OPTIONS_LANG(List<TAB_OPTIONS_LANG> list) throws Exception{
        return save("saveList",list);
    }
    /**
     * 修改
     */
    public int updateTAB_OPTIONS_LANG(TAB_OPTIONS_LANG tab_options_lang) throws Exception{
        return update("update",tab_options_lang);
    }
    /**
     * 批量修改 
     */
    public int updateTAB_OPTIONS_LANG(List<TAB_OPTIONS_LANG> list) throws Exception{
        return update("updateList",list);
    }
    /**
     * 删除
     */
    public int deleteTAB_OPTIONS_LANG(TAB_OPTIONS_LANG tab_options_lang) throws Exception{
        return delete("delete",tab_options_lang);
    }


	
}