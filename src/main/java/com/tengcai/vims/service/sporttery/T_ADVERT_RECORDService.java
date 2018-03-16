package com.tengcai.vims.service.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.T_ADVERT_RECORD;


/**
 * 投注记录广告service
 */
public interface T_ADVERT_RECORDService {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_RECORD> selectT_ADVERT_RECORDList(T_ADVERT_RECORD t_advert_record) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_RECORDCount(T_ADVERT_RECORD t_advert_record) throws Exception;


	
}