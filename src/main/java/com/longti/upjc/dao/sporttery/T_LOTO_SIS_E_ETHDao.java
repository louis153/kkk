package com.longti.upjc.dao.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.T_LOTO_SIS_E_ETH;


/**
 *ETH话题竞猜统计dao
 */
public interface T_LOTO_SIS_E_ETHDao {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_SIS_E_ETH> selectT_LOTO_SIS_E_ETHList(T_LOTO_SIS_E_ETH t_loto_sis_e_eth) throws Exception;
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_SIS_E_ETHCount(T_LOTO_SIS_E_ETH t_loto_sis_e_eth) throws Exception;
    /**
     * 添加
     */
    public int insertT_LOTO_SIS_E_ETH(T_LOTO_SIS_E_ETH t_loto_sis_e_eth) throws Exception;
    /**
     * 批量添加
     */
    public int insertT_LOTO_SIS_E_ETH(List<T_LOTO_SIS_E_ETH> list) throws Exception;
    /**
     * 修改
     */
    public int updateT_LOTO_SIS_E_ETH(T_LOTO_SIS_E_ETH t_loto_sis_e_eth) throws Exception;
    /**
     * 批量修改
     */
    public int updateT_LOTO_SIS_E_ETH(List<T_LOTO_SIS_E_ETH> list) throws Exception;
    /**
     * 删除
     */
    public int deleteT_LOTO_SIS_E_ETH(T_LOTO_SIS_E_ETH t_loto_sis_e_eth) throws Exception;
    
    


	
}