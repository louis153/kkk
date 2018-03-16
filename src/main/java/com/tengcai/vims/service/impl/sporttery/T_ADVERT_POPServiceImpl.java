package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.T_ADVERT_POP;
import com.tengcai.vims.dao.sporttery.T_ADVERT_POPDao;
import com.tengcai.vims.service.sporttery.T_ADVERT_POPService;


/**
 * 首页弹层广告serviceImpl
 */
@Service
public class T_ADVERT_POPServiceImpl implements T_ADVERT_POPService  {
	@Autowired
	private T_ADVERT_POPDao t_advert_popDao;
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_POP> selectT_ADVERT_POPList(T_ADVERT_POP t_advert_pop) throws Exception{
        return t_advert_popDao.selectT_ADVERT_POPList(t_advert_pop);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_POPCount(T_ADVERT_POP t_advert_pop) throws Exception{
        return t_advert_popDao.selectT_ADVERT_POPCount(t_advert_pop);
    }


	
}