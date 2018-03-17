package com.longti.upjc.dao.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.LOTO_F;


/**
 * 足球彩票信息（在售）dao
 */
public interface LOTO_FNDao {
	
   
    /**
	 * 条件查询
	 */
    public List<LOTO_F> selectLOTO_FNList(LOTO_F loto_fn) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectLOTO_FNCount(LOTO_F loto_fn) throws Exception;

    /**
     * 添加
     */
    public int insertLOTO_FN(LOTO_F loto_fn) throws Exception;
    /**
     * 批量添加
     */
    public int insertLOTO_FN(List<LOTO_F> list) throws Exception;
    /**
     * 修改
     */
    public int updateLOTO_FN(LOTO_F loto_fn) throws Exception;
    /**
     * 批量修改
     */
    public int updateLOTO_FN(List<LOTO_F> list) throws Exception;
    /**
     * 删除
     */
    public int deleteLOTO_FN(LOTO_F loto_fn) throws Exception;
    /**
     * 删除全部
     */
    public int clear() throws Exception;

	public LOTO_F selectRemFN() throws Exception;    
    


	
}