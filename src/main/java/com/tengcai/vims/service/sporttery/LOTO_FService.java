package com.tengcai.vims.service.sporttery;
import java.util.List;

import com.tengcai.vims.entity.sporttery.LOTO_F;


/**
 * 足球彩票信息service
 */
public interface LOTO_FService {
	
   
    /**
	 * 条件查询
	 */
    public List<LOTO_F> selectLOTO_FList(LOTO_F loto_f) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectLOTO_FCount(LOTO_F loto_f) throws Exception;

    /**
     * 添加
     */
    public int insertLOTO_F(LOTO_F loto_f) throws Exception;

    /**
     * 批量添加
     */
    public int insertLOTO_F(List<LOTO_F> list) throws Exception;

    /**
     * 修改
     */
    public int updateLOTO_F(LOTO_F loto_f) throws Exception;

    /**
     * 修改
     */
    public int updateLOTO_F(List<LOTO_F> list) throws Exception;

    /**
     * 删除
     */
    public int deleteLOTO_F(LOTO_F loto_f) throws Exception;


	
}