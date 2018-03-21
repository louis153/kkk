package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.TAB_CHIPDao;
import com.longti.upjc.entity.sporttery.TAB_CHIP;


/**
 * 投注底注信息daoImpl
 */
@Repository
public class TAB_CHIPDaoImpl extends BaseDaoImpl<TAB_CHIP> implements TAB_CHIPDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<TAB_CHIP> selectTAB_CHIPList(TAB_CHIP tab_chip) throws Exception{   	   	
        return findAllByKey(tab_chip,"selectTAB_CHIPList");
    }
    /**
     * 条件查询数量
     */
    public int selectTAB_CHIPCount(TAB_CHIP tab_chip) throws Exception{   	
        return getCountNum(tab_chip,"selectTAB_CHIPCount");
    }
    /**
     * 添加
     */
    public int insertTAB_CHIP(TAB_CHIP tab_chip) throws Exception{
        return save("save",tab_chip);
    }
    /**
     * 批量添加
     */
    public int insertTAB_CHIP(List<TAB_CHIP> list) throws Exception{
        return save("saveList",list);
    }
    /**
     * 修改
     */
    public int updateTAB_CHIP(TAB_CHIP tab_chip) throws Exception{
        return update("update",tab_chip);
    }
    /**
     * 批量修改 
     */
    public int updateTAB_CHIP(List<TAB_CHIP> list) throws Exception{
        return update("updateList",list);
    }
    /**
     * 删除
     */
    public int deleteTAB_CHIP(TAB_CHIP tab_chip) throws Exception{
        return delete("delete",tab_chip);
    }


	
}