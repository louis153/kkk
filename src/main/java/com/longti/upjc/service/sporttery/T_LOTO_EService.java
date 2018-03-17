package com.longti.upjc.service.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.T_LOTO_E;


/**
 * 电子竞猜比赛信息service
 */
public interface T_LOTO_EService {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_E> selectT_LOTO_EList(T_LOTO_E t_loto_e) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_LOTO_ECount(T_LOTO_E t_loto_e) throws Exception;

    /**
     * 添加
     */
    public int insertT_LOTO_E(T_LOTO_E t_loto_e) throws Exception;

    /**
     * 批量添加
     */
    public int insertT_LOTO_E(List<T_LOTO_E> list) throws Exception;

    /**
     * 修改
     */
    public int updateT_LOTO_E(T_LOTO_E t_loto_e) throws Exception;

    /**
     * 修改
     */
    public int updateT_LOTO_E(List<T_LOTO_E> list) throws Exception;

    /**
     * 删除
     */
    public int deleteT_LOTO_E(T_LOTO_E t_loto_e) throws Exception;


	
}