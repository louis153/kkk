package com.tengcai.vims.service.sporttery;
import java.util.List;

import com.tengcai.vims.entity.sporttery.LOTO_B;


/**
 * 篮球彩票信息service
 */
public interface LOTO_BService {
	
   
    /**
	 * 条件查询
	 */
    public List<LOTO_B> selectLOTO_BList(LOTO_B loto_b) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectLOTO_BCount(LOTO_B loto_b) throws Exception;

    /**
     * 添加
     */
    public int insertLOTO_B(LOTO_B loto_b) throws Exception;

    /**
     * 批量添加
     */
    public int insertLOTO_B(List<LOTO_B> list) throws Exception;

    /**
     * 修改
     */
    public int updateLOTO_B(LOTO_B loto_b) throws Exception;

    /**
     * 修改
     */
    public int updateLOTO_B(List<LOTO_B> list) throws Exception;

    /**
     * 删除
     */
    public int deleteLOTO_B(LOTO_B loto_b) throws Exception;


	
}