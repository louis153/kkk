package com.longti.upjc.service.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.T_LOTO_EN_GTO;


/**
 * 进行中GTO话题竞猜信息service
 */
public interface T_LOTO_EN_GTOService {
	
   
	/**
	 * 条件查询
	 */
    public List<T_LOTO_EN_GTO> selectT_LOTO_EN_GTOList(T_LOTO_EN_GTO t_loto_en_gto) throws Exception;
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_EN_GTOCount(T_LOTO_EN_GTO t_loto_en_gto) throws Exception;
    /**
     * 添加
     */
    public int insertT_LOTO_EN_GTO(T_LOTO_EN_GTO t_loto_en_gto) throws Exception;
    /**
     * 批量添加
     */
    public int insertT_LOTO_EN_GTO(List<T_LOTO_EN_GTO> list) throws Exception;
    /**
     * 修改
     */
    public int updateT_LOTO_EN_GTO(T_LOTO_EN_GTO t_loto_en_gto) throws Exception;
    /**
     * 批量修改
     */
    public int updateT_LOTO_EN_GTO(List<T_LOTO_EN_GTO> list) throws Exception;
    /**
     * 删除
     */
    public int deleteT_LOTO_EN_GTO(T_LOTO_EN_GTO t_loto_en_gto) throws Exception;


	
}