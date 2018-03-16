package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.T_QUOTATION_CONTROL;
import com.tengcai.vims.dao.sporttery.T_QUOTATION_CONTROLDao;
import com.tengcai.vims.service.sporttery.T_QUOTATION_CONTROLService;


/**
 * 销售阀值serviceImpl
 */
@Service
public class T_QUOTATION_CONTROLServiceImpl implements T_QUOTATION_CONTROLService  {
	@Autowired
	private T_QUOTATION_CONTROLDao t_quotation_controlDao;
   
    /**
	 * 条件查询
	 */
    public List<T_QUOTATION_CONTROL> selectT_QUOTATION_CONTROLList(T_QUOTATION_CONTROL t_quotation_control) throws Exception{
        return t_quotation_controlDao.selectT_QUOTATION_CONTROLList(t_quotation_control);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_QUOTATION_CONTROLCount(T_QUOTATION_CONTROL t_quotation_control) throws Exception{
        return t_quotation_controlDao.selectT_QUOTATION_CONTROLCount(t_quotation_control);
    }


	
}