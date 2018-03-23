package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.entity.sporttery.TAB_SALES_THRESHOLD;
import com.longti.upjc.dao.sporttery.TAB_SALES_THRESHOLDDao;


/**
 * 销售阈值daoImpl
 */
@Repository
public class TAB_SALES_THRESHOLDDaoImpl extends BaseDaoImpl<TAB_SALES_THRESHOLD> implements TAB_SALES_THRESHOLDDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<TAB_SALES_THRESHOLD> selectTAB_SALES_THRESHOLDList(TAB_SALES_THRESHOLD tab_sales_threshold) throws Exception{
        return getSqlSession().selectList("com.longti.upjc.entity.sporttery.TAB_SALES_THRESHOLD.selectTAB_SALES_THRESHOLDList",tab_sales_threshold);
    }
    
    /**
     * 条件查询数量
     */
    public int selectTAB_SALES_THRESHOLDCount(TAB_SALES_THRESHOLD tab_sales_threshold) throws Exception{
        return getSqlSession().selectOne("com.longti.upjc.entity.sporttery.TAB_SALES_THRESHOLD.selectTAB_SALES_THRESHOLDCount",tab_sales_threshold);
    }

    /**
     * 添加
     */
    public int insertTAB_SALES_THRESHOLD(TAB_SALES_THRESHOLD tab_sales_threshold) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.TAB_SALES_THRESHOLD.save",tab_sales_threshold);
    }
    /**
     * 批量添加
     */
    public int insertTAB_SALES_THRESHOLD(List<TAB_SALES_THRESHOLD> list) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.TAB_SALES_THRESHOLD.saveList",list);
    }
    /**
     * 修改
     */
    public int updateTAB_SALES_THRESHOLD(TAB_SALES_THRESHOLD tab_sales_threshold) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.TAB_SALES_THRESHOLD.update",tab_sales_threshold);
    }
    /**
     * 批量修改
     */
    public int updateTAB_SALES_THRESHOLD(List<TAB_SALES_THRESHOLD> list) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.TAB_SALES_THRESHOLD.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteTAB_SALES_THRESHOLD(TAB_SALES_THRESHOLD tab_sales_threshold) throws Exception{
        return getSqlSession().delete("com.longti.upjc.entity.sporttery.TAB_SALES_THRESHOLD.delete",tab_sales_threshold);
    }


	
}