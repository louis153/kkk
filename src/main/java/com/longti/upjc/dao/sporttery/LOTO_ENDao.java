package com.longti.upjc.dao.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.T_LOTO_E;


/**
 * 电竞彩票信息（在售）dao
 */
public interface LOTO_ENDao {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_E> selectLOTO_ENList(T_LOTO_E loto_en) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectLOTO_ENCount(T_LOTO_E loto_en) throws Exception;

    /**
     * 添加
     */
    public int insertLOTO_EN(T_LOTO_E loto_en) throws Exception;
    /**
     * 批量添加
     */
    public int insertLOTO_EN(List<T_LOTO_E> list) throws Exception;
    /**
     * 修改
     */
    public int updateLOTO_EN(T_LOTO_E loto_en) throws Exception;
    /**
     * 批量修改
     */
    public int updateLOTO_EN(List<T_LOTO_E> list) throws Exception;
    /**
     * 删除
     */
    public int deleteLOTO_EN(T_LOTO_E loto_en) throws Exception;
    /**
     * 全部删除
     */
    public int deleteAll() throws Exception;

	public T_LOTO_E selectRemEN() throws Exception;    
    
    

	
}