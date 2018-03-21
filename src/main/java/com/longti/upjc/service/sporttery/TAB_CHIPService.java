package com.longti.upjc.service.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.TAB_CHIP;


/**
 * 投注底注信息service
 */
public interface TAB_CHIPService {
	
   
	/**
	 * 条件查询
	 */
    public List<TAB_CHIP> selectTAB_CHIPList(TAB_CHIP tab_chip) throws Exception;
    /**
     * 条件查询数量
     */
    public int selectTAB_CHIPCount(TAB_CHIP tab_chip) throws Exception;
    /**
     * 添加
     */
    public int insertTAB_CHIP(TAB_CHIP tab_chip) throws Exception;
    /**
     * 批量添加
     */
    public int insertTAB_CHIP(List<TAB_CHIP> list) throws Exception;
    /**
     * 修改
     */
    public int updateTAB_CHIP(TAB_CHIP tab_chip) throws Exception;
    /**
     * 批量修改
     */
    public int updateTAB_CHIP(List<TAB_CHIP> list) throws Exception;
    /**
     * 删除
     */
    public int deleteTAB_CHIP(TAB_CHIP tab_chip) throws Exception;


	
}