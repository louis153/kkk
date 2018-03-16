package com.tengcai.vims.dao.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.V_ORDER;


/**
 * dao
 */
public interface V_ORDERDao {
	
   
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
     * 批量添加
     */
    public int insertV_ORDER(List<V_ORDER> list) throws Exception;
    /**
     * 修改
     */
    public int updateV_ORDER(V_ORDER v_order) throws Exception;
    /**
     * 批量修改
     */
    public int updateV_ORDER(List<V_ORDER> list) throws Exception;
    /**
     * 删除
     */
    public int deleteV_ORDER(V_ORDER v_order) throws Exception;
    
    
   

	
}