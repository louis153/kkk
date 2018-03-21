package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.TAB_DICTDao;
import com.longti.upjc.entity.sporttery.TAB_DICT;


/**
 * 奖励机制信息daoImpl
 */
@Repository
public class TAB_DICTDaoImpl extends BaseDaoImpl<TAB_DICT> implements TAB_DICTDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<TAB_DICT> selectTAB_DICTList(TAB_DICT tab_dict) throws Exception{   	   	
        return findAllByKey(tab_dict,"selectTAB_DICTList");
    }
    /**
     * 条件查询数量
     */
    public int selectTAB_DICTCount(TAB_DICT tab_dict) throws Exception{   	
        return getCountNum(tab_dict,"selectTAB_DICTCount");
    }
    /**
     * 添加
     */
    public int insertTAB_DICT(TAB_DICT tab_dict) throws Exception{
        return save("save",tab_dict);
    }
    /**
     * 批量添加
     */
    public int insertTAB_DICT(List<TAB_DICT> list) throws Exception{
        return save("saveList",list);
    }
    /**
     * 修改
     */
    public int updateTAB_DICT(TAB_DICT tab_dict) throws Exception{
        return update("update",tab_dict);
    }
    /**
     * 批量修改 
     */
    public int updateTAB_DICT(List<TAB_DICT> list) throws Exception{
        return update("updateList",list);
    }
    /**
     * 删除
     */
    public int deleteTAB_DICT(TAB_DICT tab_dict) throws Exception{
        return delete("delete",tab_dict);
    }


	
}