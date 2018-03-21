package com.longti.upjc.service.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.T_LOTO_SIS_E_UZ;


/**
 * UZ竞猜话题统计service
 */
public interface T_LOTO_SIS_E_UZService {
	
   
	/**
	 * 条件查询
	 */
    public List<T_LOTO_SIS_E_UZ> selectT_LOTO_SIS_E_UZList(T_LOTO_SIS_E_UZ t_loto_sis_e_uz) throws Exception;
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_SIS_E_UZCount(T_LOTO_SIS_E_UZ t_loto_sis_e_uz) throws Exception;
    /**
     * 添加
     */
    public int insertT_LOTO_SIS_E_UZ(T_LOTO_SIS_E_UZ t_loto_sis_e_uz) throws Exception;
    /**
     * 批量添加
     */
    public int insertT_LOTO_SIS_E_UZ(List<T_LOTO_SIS_E_UZ> list) throws Exception;
    /**
     * 修改
     */
    public int updateT_LOTO_SIS_E_UZ(T_LOTO_SIS_E_UZ t_loto_sis_e_uz) throws Exception;
    /**
     * 批量修改
     */
    public int updateT_LOTO_SIS_E_UZ(List<T_LOTO_SIS_E_UZ> list) throws Exception;
    /**
     * 删除
     */
    public int deleteT_LOTO_SIS_E_UZ(T_LOTO_SIS_E_UZ t_loto_sis_e_uz) throws Exception;


	
}