package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.T_QUOTATION_CONTROLDao;
import com.longti.upjc.entity.sporttery.T_QUOTATION_CONTROL;


/**
 * 销售阀值daoImpl
 */
@Repository
public class T_QUOTATION_CONTROLDaoImpl extends BaseDaoImpl<T_QUOTATION_CONTROL> implements T_QUOTATION_CONTROLDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_QUOTATION_CONTROL> selectT_QUOTATION_CONTROLList(T_QUOTATION_CONTROL t_quotation_control) throws Exception{
        return getSqlSession().selectList("com.longti.upjc.entity.sporttery.T_QUOTATION_CONTROL.selectT_QUOTATION_CONTROLList",t_quotation_control);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_QUOTATION_CONTROLCount(T_QUOTATION_CONTROL t_quotation_control) throws Exception{
        return getSqlSession().selectOne("com.longti.upjc.entity.sporttery.T_QUOTATION_CONTROL.selectT_QUOTATION_CONTROLCount",t_quotation_control);
    }


	
}