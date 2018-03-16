package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.T_ADVERT_RECORD;
import com.tengcai.vims.dao.sporttery.T_ADVERT_RECORDDao;
import com.tengcai.vims.service.sporttery.T_ADVERT_RECORDService;


/**
 * 投注记录广告serviceImpl
 */
@Service
public class T_ADVERT_RECORDServiceImpl implements T_ADVERT_RECORDService  {
	@Autowired
	private T_ADVERT_RECORDDao t_advert_recordDao;
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_RECORD> selectT_ADVERT_RECORDList(T_ADVERT_RECORD t_advert_record) throws Exception{
        return t_advert_recordDao.selectT_ADVERT_RECORDList(t_advert_record);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_RECORDCount(T_ADVERT_RECORD t_advert_record) throws Exception{
        return t_advert_recordDao.selectT_ADVERT_RECORDCount(t_advert_record);
    }


	
}