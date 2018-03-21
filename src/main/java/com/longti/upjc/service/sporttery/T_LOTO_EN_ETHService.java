package com.longti.upjc.service.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.T_LOTO_EN_ETH;


/**
 * 进行中ETH话题竞猜信息service
 */
public interface T_LOTO_EN_ETHService {
	
   
	/**
	 * 条件查询
	 */
    public List<T_LOTO_EN_ETH> selectT_LOTO_EN_ETHList(T_LOTO_EN_ETH t_loto_en_eth) throws Exception;
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_EN_ETHCount(T_LOTO_EN_ETH t_loto_en_eth) throws Exception;
    /**
     * 添加
     */
    public int insertT_LOTO_EN_ETH(T_LOTO_EN_ETH t_loto_en_eth) throws Exception;
    /**
     * 批量添加
     */
    public int insertT_LOTO_EN_ETH(List<T_LOTO_EN_ETH> list) throws Exception;
    /**
     * 修改
     */
    public int updateT_LOTO_EN_ETH(T_LOTO_EN_ETH t_loto_en_eth) throws Exception;
    /**
     * 批量修改
     */
    public int updateT_LOTO_EN_ETH(List<T_LOTO_EN_ETH> list) throws Exception;
    /**
     * 删除
     */
    public int deleteT_LOTO_EN_ETH(T_LOTO_EN_ETH t_loto_en_eth) throws Exception;


	
}