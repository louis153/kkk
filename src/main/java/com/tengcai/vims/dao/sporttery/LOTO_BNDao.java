package com.tengcai.vims.dao.sporttery;
import java.util.List;

import com.tengcai.vims.entity.sporttery.LOTO_B;


/**
 * 篮球彩票信息（在售）dao
 */
public interface LOTO_BNDao {
	
   
    /**
	 * 条件查询
	 */
    public List<LOTO_B> selectLOTO_BNList(LOTO_B loto_bn) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectLOTO_BNCount(LOTO_B loto_bn) throws Exception;

    /**
     * 添加
     */
    public int insertLOTO_BN(LOTO_B loto_bn) throws Exception;
    /**
     * 批量添加
     */
    public int insertLOTO_BN(List<LOTO_B> list) throws Exception;
    /**
     * 修改
     */
    public int updateLOTO_BN(LOTO_B loto_bn) throws Exception;
    /**
     * 批量修改
     */
    public int updateLOTO_BN(List<LOTO_B> list) throws Exception;
    /**
     * 删除
     */
    public int deleteLOTO_BN(LOTO_B loto_bn) throws Exception;
    /**
     * 全部删除
     */
    public int deleteAll() throws Exception;

	public LOTO_B selectRemBN() throws Exception;    
    
    

	
}