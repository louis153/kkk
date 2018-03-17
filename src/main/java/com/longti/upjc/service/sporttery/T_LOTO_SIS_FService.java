package com.longti.upjc.service.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.T_LOTO_SIS_F;


/**
 * 足球统计service
 */
public interface T_LOTO_SIS_FService {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_SIS_F> selectT_LOTO_SIS_FList(T_LOTO_SIS_F t_loto_sis_f) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_LOTO_SIS_FCount(T_LOTO_SIS_F t_loto_sis_f) throws Exception;

    /**
     * 添加
     */
    public int insertT_LOTO_SIS_F(T_LOTO_SIS_F t_loto_sis_f) throws Exception;

    /**
     * 按统计方式添加
     */
    public int saveSisT_LOTO_SIS_F(T_LOTO_SIS_F t_loto_sis_f) throws Exception;
    /**
     * 批量添加
     */
    public int insertT_LOTO_SIS_F(List<T_LOTO_SIS_F> list) throws Exception;

    /**
     * 修改
     */
    public int updateT_LOTO_SIS_F(T_LOTO_SIS_F t_loto_sis_f) throws Exception;

    /**
     * 修改
     */
    public int updateT_LOTO_SIS_F(List<T_LOTO_SIS_F> list) throws Exception;

    /**
     * 删除
     */
    public int deleteT_LOTO_SIS_F(T_LOTO_SIS_F t_loto_sis_f) throws Exception;


	
}