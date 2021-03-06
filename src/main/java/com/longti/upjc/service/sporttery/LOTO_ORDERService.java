package com.longti.upjc.service.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.LOTO_ORDER;


/**
 * service
 */
public interface LOTO_ORDERService {
	
   
    /**
	 * 条件查询
	 */
    public List<LOTO_ORDER> selectLOTO_ORDERList(LOTO_ORDER loto_order) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectLOTO_ORDERCount(LOTO_ORDER loto_order) throws Exception;

    /**
     * 添加
     */
    public int insertLOTO_ORDER(LOTO_ORDER loto_order) throws Exception;

    /**
     * 批量添加
     */
    public int insertLOTO_ORDER(List<LOTO_ORDER> list) throws Exception;

    /**
     * 修改
     */
    public int updateLOTO_ORDER(LOTO_ORDER loto_order) throws Exception;

    /**
     * 修改
     */
    public int updateLOTO_ORDER(List<LOTO_ORDER> list) throws Exception;

    /**
     * 删除
     */
    public int deleteLOTO_ORDER(LOTO_ORDER loto_order) throws Exception;


    public Long selectOnceLim(String user_pin,String issue,String electronic_code);
}