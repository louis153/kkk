package com.longti.upjc.dao.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.T_LOTO_EN_UZ;


/**
 * 进行中UZ话题竞猜信息dao
 */
public interface T_LOTO_EN_UZDao {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_EN_UZ> selectT_LOTO_EN_UZList(T_LOTO_EN_UZ t_loto_en_uz) throws Exception;
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_EN_UZCount(T_LOTO_EN_UZ t_loto_en_uz) throws Exception;
    /**
     * 添加
     */
    public int insertT_LOTO_EN_UZ(T_LOTO_EN_UZ t_loto_en_uz) throws Exception;
    /**
     * 批量添加
     */
    public int insertT_LOTO_EN_UZ(List<T_LOTO_EN_UZ> list) throws Exception;
    /**
     * 修改
     */
    public int updateT_LOTO_EN_UZ(T_LOTO_EN_UZ t_loto_en_uz) throws Exception;
    /**
     * 批量修改
     */
    public int updateT_LOTO_EN_UZ(List<T_LOTO_EN_UZ> list) throws Exception;
    /**
     * 删除
     */
    public int deleteT_LOTO_EN_UZ(T_LOTO_EN_UZ t_loto_en_uz) throws Exception;
    
    


	
}