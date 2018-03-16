package com.tengcai.vims.dao.sporttery;
import java.util.List;

import com.tengcai.vims.entity.sporttery.T_LOTO_E;


/**
 * 电竞彩票信息dao
 */
public interface LOTO_EDao {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_E> selectLOTO_EList(T_LOTO_E loto_b) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectLOTO_ECount(T_LOTO_E loto_b) throws Exception;

    /**
     * 添加
     */
    public int insertLOTO_E(T_LOTO_E loto_e) throws Exception;
    /**
     * 批量添加
     */
    public int insertLOTO_E(List<T_LOTO_E> list) throws Exception;
    /**
     * 修改
     */
    public int updateLOTO_E(T_LOTO_E loto_e) throws Exception;
    /**
     * 批量修改
     */
    public int updateLOTO_E(List<T_LOTO_E> list) throws Exception;
    /**
     * 删除
     */
    public int deleteLOTO_E(T_LOTO_E loto_e) throws Exception;
    
    


	
}