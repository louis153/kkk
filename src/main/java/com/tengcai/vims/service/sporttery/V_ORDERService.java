package com.tengcai.vims.service.sporttery;
import java.util.HashMap;
import java.util.List;
import com.tengcai.vims.entity.sporttery.LOTO_ORDER;
import com.tengcai.vims.entity.sporttery.T_QUOTATION_CONTROL;
import com.tengcai.vims.entity.sporttery.V_ORDER;


/**
 * service
 */
public interface V_ORDERService {
	
   
    /**
	 * 条件查询
	 */
    public List<V_ORDER> selectV_ORDERList(V_ORDER v_order) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectV_ORDERCount(V_ORDER v_order) throws Exception;

    /**
     * 添加
     */
    public int insertV_ORDER(V_ORDER v_order) throws Exception;
    /**
     * 添加(带事物处理）
     */
    public int insertV_ORDER(V_ORDER v_order,List<LOTO_ORDER> lst_loto_order,HashMap<String, Boolean> canChangeOdd,List<String> canBet, T_QUOTATION_CONTROL qutation) throws Exception;

    /**
     * 批量添加
     */
    public int insertV_ORDER(List<V_ORDER> list) throws Exception;

    /**
     * 修改
     */
    public int updateV_ORDER(V_ORDER v_order) throws Exception;

    /**
     * 修改
     */
    public int updateV_ORDER(List<V_ORDER> list) throws Exception;

    /**
     * 删除
     */
    public int deleteV_ORDER(V_ORDER v_order) throws Exception;

	void updateDPs(String issume, HashMap<String, Boolean> canChangeOdd, List<String> canBet) throws Exception;
    
	
}