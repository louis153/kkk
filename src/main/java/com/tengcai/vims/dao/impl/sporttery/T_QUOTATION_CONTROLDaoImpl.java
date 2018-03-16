package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;import com.tengcai.vims.entity.sporttery.T_QUOTATION_CONTROL;
import com.tengcai.vims.dao.sporttery.T_QUOTATION_CONTROLDao;


/**
 * 销售阀值daoImpl
 */
@Repository
public class T_QUOTATION_CONTROLDaoImpl extends BaseDaoImpl<T_QUOTATION_CONTROL> implements T_QUOTATION_CONTROLDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_QUOTATION_CONTROL> selectT_QUOTATION_CONTROLList(T_QUOTATION_CONTROL t_quotation_control) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.T_QUOTATION_CONTROL.selectT_QUOTATION_CONTROLList",t_quotation_control);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_QUOTATION_CONTROLCount(T_QUOTATION_CONTROL t_quotation_control) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.T_QUOTATION_CONTROL.selectT_QUOTATION_CONTROLCount",t_quotation_control);
    }


	
}