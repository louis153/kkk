package com.longti.upjc.service.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.T_LOTO_SIS_E_GTO;


/**
 * GTO竞猜话题统计service
 */
public interface T_LOTO_SIS_E_GTOService {
	
   
	/**
	 * 条件查询
	 */
    public List<T_LOTO_SIS_E_GTO> selectT_LOTO_SIS_E_GTOList(T_LOTO_SIS_E_GTO t_loto_sis_e_gto) throws Exception;
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_SIS_E_GTOCount(T_LOTO_SIS_E_GTO t_loto_sis_e_gto) throws Exception;
    /**
     * 添加
     */
    public int insertT_LOTO_SIS_E_GTO(T_LOTO_SIS_E_GTO t_loto_sis_e_gto) throws Exception;
    /**
     * 批量添加
     */
    public int insertT_LOTO_SIS_E_GTO(List<T_LOTO_SIS_E_GTO> list) throws Exception;
    /**
     * 修改
     */
    public int updateT_LOTO_SIS_E_GTO(T_LOTO_SIS_E_GTO t_loto_sis_e_gto) throws Exception;
    /**
     * 批量修改
     */
    public int updateT_LOTO_SIS_E_GTO(List<T_LOTO_SIS_E_GTO> list) throws Exception;
    /**
     * 删除
     */
    public int deleteT_LOTO_SIS_E_GTO(T_LOTO_SIS_E_GTO t_loto_sis_e_gto) throws Exception;


	
}