package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.T_ADVERT_REDPACK;
import com.tengcai.vims.dao.sporttery.T_ADVERT_REDPACKDao;
import com.tengcai.vims.service.sporttery.T_ADVERT_REDPACKService;


/**
 * 红包雨serviceImpl
 */
@Service
public class T_ADVERT_REDPACKServiceImpl implements T_ADVERT_REDPACKService  {
	@Autowired
	private T_ADVERT_REDPACKDao t_advert_redpackDao;
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_REDPACK> selectT_ADVERT_REDPACKList(T_ADVERT_REDPACK t_advert_redpack) throws Exception{
        return t_advert_redpackDao.selectT_ADVERT_REDPACKList(t_advert_redpack);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_REDPACKCount(T_ADVERT_REDPACK t_advert_redpack) throws Exception{
        return t_advert_redpackDao.selectT_ADVERT_REDPACKCount(t_advert_redpack);
    }


	
}