package com.tengcai.vims.service.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.T_ADVERT_POP;


/**
 * 首页弹层广告service
 */
public interface T_ADVERT_POPService {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_POP> selectT_ADVERT_POPList(T_ADVERT_POP t_advert_pop) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_POPCount(T_ADVERT_POP t_advert_pop) throws Exception;


	
}