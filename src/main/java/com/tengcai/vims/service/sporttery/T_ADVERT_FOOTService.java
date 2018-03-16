package com.tengcai.vims.service.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.T_ADVERT_FOOT;


/**
 * 列表底部广告service
 */
public interface T_ADVERT_FOOTService {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_FOOT> selectT_ADVERT_FOOTList(T_ADVERT_FOOT t_advert_foot) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_FOOTCount(T_ADVERT_FOOT t_advert_foot) throws Exception;


	
}