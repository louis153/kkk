package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.T_ADVERT_FOOT;
import com.tengcai.vims.dao.sporttery.T_ADVERT_FOOTDao;
import com.tengcai.vims.service.sporttery.T_ADVERT_FOOTService;


/**
 * 列表底部广告serviceImpl
 */
@Service
public class T_ADVERT_FOOTServiceImpl implements T_ADVERT_FOOTService  {
	@Autowired
	private T_ADVERT_FOOTDao t_advert_footDao;
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_FOOT> selectT_ADVERT_FOOTList(T_ADVERT_FOOT t_advert_foot) throws Exception{
        return t_advert_footDao.selectT_ADVERT_FOOTList(t_advert_foot);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_FOOTCount(T_ADVERT_FOOT t_advert_foot) throws Exception{
        return t_advert_footDao.selectT_ADVERT_FOOTCount(t_advert_foot);
    }


	
}