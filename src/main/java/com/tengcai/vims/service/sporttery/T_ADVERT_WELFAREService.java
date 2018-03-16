package com.tengcai.vims.service.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.T_ADVERT_WELFARE;


/**
 * 首页福利社service
 */
public interface T_ADVERT_WELFAREService {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_WELFARE> selectT_ADVERT_WELFAREList(T_ADVERT_WELFARE t_advert_welfare) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_WELFARECount(T_ADVERT_WELFARE t_advert_welfare) throws Exception;


	
}