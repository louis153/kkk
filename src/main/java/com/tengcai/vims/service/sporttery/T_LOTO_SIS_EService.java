package com.tengcai.vims.service.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_E;


/**
 * 电竞统计service
 */
public interface T_LOTO_SIS_EService {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_SIS_E> selectT_LOTO_SIS_EList(T_LOTO_SIS_E t_loto_sis_e) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_LOTO_SIS_ECount(T_LOTO_SIS_E t_loto_sis_e) throws Exception;

    /**
     * 添加
     */
    public int insertT_LOTO_SIS_E(T_LOTO_SIS_E t_loto_sis_e) throws Exception;

    /**
     * 批量添加
     */
    public int insertT_LOTO_SIS_E(List<T_LOTO_SIS_E> list) throws Exception;

    /**
     * 修改
     */
    public int updateT_LOTO_SIS_E(T_LOTO_SIS_E t_loto_sis_e) throws Exception;

    /**
     * 修改
     */
    public int updateT_LOTO_SIS_E(List<T_LOTO_SIS_E> list) throws Exception;

    /**
     * 删除
     */
    public int deleteT_LOTO_SIS_E(T_LOTO_SIS_E t_loto_sis_e) throws Exception;


	
}