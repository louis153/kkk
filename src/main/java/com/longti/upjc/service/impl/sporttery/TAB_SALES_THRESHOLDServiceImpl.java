package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.longti.upjc.entity.sporttery.TAB_SALES_THRESHOLD;
import com.longti.upjc.dao.sporttery.TAB_SALES_THRESHOLDDao;
import com.longti.upjc.service.sporttery.TAB_SALES_THRESHOLDService;


/**
 * 销售阈值serviceImpl
 */
@Service
public class TAB_SALES_THRESHOLDServiceImpl implements TAB_SALES_THRESHOLDService  {
	@Autowired
	private TAB_SALES_THRESHOLDDao tab_sales_thresholdDao;
   
    /**
	 * 条件查询
	 */
    public List<TAB_SALES_THRESHOLD> selectTAB_SALES_THRESHOLDList(TAB_SALES_THRESHOLD tab_sales_threshold) throws Exception{
        return tab_sales_thresholdDao.selectTAB_SALES_THRESHOLDList(tab_sales_threshold);
    }
    
    /**
     * 条件查询数量
     */
    public int selectTAB_SALES_THRESHOLDCount(TAB_SALES_THRESHOLD tab_sales_threshold) throws Exception{
        return tab_sales_thresholdDao.selectTAB_SALES_THRESHOLDCount(tab_sales_threshold);
    }

    /**
     * 添加
     */
    public int insertTAB_SALES_THRESHOLD(TAB_SALES_THRESHOLD tab_sales_threshold) throws Exception{
        return tab_sales_thresholdDao.insertTAB_SALES_THRESHOLD(tab_sales_threshold);
    }

    /**
     * 批量添加
     */
    public int insertTAB_SALES_THRESHOLD(List<TAB_SALES_THRESHOLD> list) throws Exception{
        return tab_sales_thresholdDao.insertTAB_SALES_THRESHOLD(list);
    }

    /**
     * 修改
     */
    public int updateTAB_SALES_THRESHOLD(TAB_SALES_THRESHOLD tab_sales_threshold) throws Exception{
        return tab_sales_thresholdDao.updateTAB_SALES_THRESHOLD(tab_sales_threshold);
    }
    
    /**
     * 批量修改
     */
    public int updateTAB_SALES_THRESHOLD(List<TAB_SALES_THRESHOLD> list) throws Exception{
        return tab_sales_thresholdDao.updateTAB_SALES_THRESHOLD(list);
    }

    /**
     * 删除
     */
    public int deleteTAB_SALES_THRESHOLD(TAB_SALES_THRESHOLD tab_sales_threshold) throws Exception{
        return tab_sales_thresholdDao.deleteTAB_SALES_THRESHOLD(tab_sales_threshold);
    }


	
}