package com.tengcai.vims.service.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.T_QUOTATION_CONTROL;


/**
 * 销售阀值service
 */
public interface T_QUOTATION_CONTROLService {
	
   
    /**
	 * 条件查询
	 */
    public List<T_QUOTATION_CONTROL> selectT_QUOTATION_CONTROLList(T_QUOTATION_CONTROL t_quotation_control) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_QUOTATION_CONTROLCount(T_QUOTATION_CONTROL t_quotation_control) throws Exception;


	
}