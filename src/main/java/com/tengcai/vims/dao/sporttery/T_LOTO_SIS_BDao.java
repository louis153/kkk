package com.tengcai.vims.dao.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B;


/**
 * 蓝球统计dao
 */
public interface T_LOTO_SIS_BDao {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_SIS_B> selectT_LOTO_SIS_BList(T_LOTO_SIS_B t_loto_sis_b) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_LOTO_SIS_BCount(T_LOTO_SIS_B t_loto_sis_b) throws Exception;

    /**
     * 添加
     */
    public int insertT_LOTO_SIS_B(T_LOTO_SIS_B t_loto_sis_b) throws Exception;
    
    /**
     * 按统计方式添加
     */
    public int saveSis(T_LOTO_SIS_B t_loto_sis_b) throws Exception;
    /**
     * 批量添加
     */
    public int insertT_LOTO_SIS_B(List<T_LOTO_SIS_B> list) throws Exception;
    /**
     * 修改
     */
    public int updateT_LOTO_SIS_B(T_LOTO_SIS_B t_loto_sis_b) throws Exception;
    /**
     * 批量修改
     */
    public int updateT_LOTO_SIS_B(List<T_LOTO_SIS_B> list) throws Exception;
    /**
     * 删除
     */
    public int deleteT_LOTO_SIS_B(T_LOTO_SIS_B t_loto_sis_b) throws Exception;
    
   

	
}