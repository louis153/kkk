package com.longti.upjc.dao.sporttery;
import java.util.List;
import com.longti.upjc.entity.sporttery.TAB_SALES_THRESHOLD;


/**
 * 销售阈值dao
 */
public interface TAB_SALES_THRESHOLDDao {
	
   
    /**
	 * 条件查询
	 */
    public List<TAB_SALES_THRESHOLD> selectTAB_SALES_THRESHOLDList(TAB_SALES_THRESHOLD tab_sales_threshold) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectTAB_SALES_THRESHOLDCount(TAB_SALES_THRESHOLD tab_sales_threshold) throws Exception;

    /**
     * 添加
     */
    public int insertTAB_SALES_THRESHOLD(TAB_SALES_THRESHOLD tab_sales_threshold) throws Exception;
    /**
     * 批量添加
     */
    public int insertTAB_SALES_THRESHOLD(List<TAB_SALES_THRESHOLD> list) throws Exception;
    /**
     * 修改
     */
    public int updateTAB_SALES_THRESHOLD(TAB_SALES_THRESHOLD tab_sales_threshold) throws Exception;
    /**
     * 批量修改
     */
    public int updateTAB_SALES_THRESHOLD(List<TAB_SALES_THRESHOLD> list) throws Exception;
    /**
     * 删除
     */
    public int deleteTAB_SALES_THRESHOLD(TAB_SALES_THRESHOLD tab_sales_threshold) throws Exception;
    
    


	
}