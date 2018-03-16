package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.T_ADVERT_FLOAT;
import com.tengcai.vims.dao.sporttery.T_ADVERT_FLOATDao;
import com.tengcai.vims.service.sporttery.T_ADVERT_FLOATService;


/**
 * 首页浮动广告serviceImpl
 */
@Service
public class T_ADVERT_FLOATServiceImpl implements T_ADVERT_FLOATService  {
	@Autowired
	private T_ADVERT_FLOATDao t_advert_floatDao;
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_FLOAT> selectT_ADVERT_FLOATList(T_ADVERT_FLOAT t_advert_float) throws Exception{
        return t_advert_floatDao.selectT_ADVERT_FLOATList(t_advert_float);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_FLOATCount(T_ADVERT_FLOAT t_advert_float) throws Exception{
        return t_advert_floatDao.selectT_ADVERT_FLOATCount(t_advert_float);
    }


	
}