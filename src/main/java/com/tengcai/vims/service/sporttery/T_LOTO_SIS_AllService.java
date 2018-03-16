package com.tengcai.vims.service.sporttery;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_E;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_F;


/**
 * 蓝球统计service
 */
public interface T_LOTO_SIS_AllService {   
    
    /**
     * 按统计方式添加
     */
    public int saveFootSis(T_LOTO_SIS_F t_loto_sis_f) throws Exception;
    public int saveBasketSis(T_LOTO_SIS_B t_loto_sis_b) throws Exception;
    public int saveDjSis(T_LOTO_SIS_E t_loto_sis_e) throws Exception;    
 
   
}