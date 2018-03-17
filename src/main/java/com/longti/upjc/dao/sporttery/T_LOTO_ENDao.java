package com.longti.upjc.dao.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.T_LOTO_E;


/**
 * 在售的比赛, 比赛开始后删除此表中的数据dao
 */
public interface T_LOTO_ENDao {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_E> selectT_LOTO_ENList(T_LOTO_E t_loto_en) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_LOTO_ENCount(T_LOTO_E t_loto_en) throws Exception;

    /**
     * 添加
     */
    public int insertT_LOTO_EN(T_LOTO_E t_loto_en) throws Exception;
    /**
     * 批量添加
     */
    public int insertT_LOTO_EN(List<T_LOTO_E> list) throws Exception;
    /**
     * 修改
     */
    public int updateT_LOTO_EN(T_LOTO_E t_loto_en) throws Exception;
    /**
     * 批量修改
     */
    public int updateT_LOTO_EN(List<T_LOTO_E> list) throws Exception;
    /**
     * 删除
     */
    public int deleteT_LOTO_EN(T_LOTO_E t_loto_en) throws Exception;

	public T_LOTO_E selectRemEN() throws Exception;
    
    


	
}