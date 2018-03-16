package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.T_ADVERT_WELFARE;
import com.tengcai.vims.dao.sporttery.T_ADVERT_WELFAREDao;
import com.tengcai.vims.service.sporttery.T_ADVERT_WELFAREService;


/**
 * 首页福利社serviceImpl
 */
@Service
public class T_ADVERT_WELFAREServiceImpl implements T_ADVERT_WELFAREService  {
	@Autowired
	private T_ADVERT_WELFAREDao t_advert_welfareDao;
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_WELFARE> selectT_ADVERT_WELFAREList(T_ADVERT_WELFARE t_advert_welfare) throws Exception{
        return t_advert_welfareDao.selectT_ADVERT_WELFAREList(t_advert_welfare);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_WELFARECount(T_ADVERT_WELFARE t_advert_welfare) throws Exception{
        return t_advert_welfareDao.selectT_ADVERT_WELFARECount(t_advert_welfare);
    }


	
}