package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.V_ORDER;
import com.tengcai.vims.dao.sporttery.V_ORDER_EDao;


/**
 * daoImpl
 */
@Repository
public class V_ORDER_EDaoImpl extends BaseDaoImpl<V_ORDER> implements V_ORDER_EDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<V_ORDER> selectV_ORDERList(V_ORDER v_order) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.V_ORDER_E.selectV_ORDERList",v_order);
    }
    
    /**
     * 条件查询数量
     */
    public int selectV_ORDERCount(V_ORDER v_order) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.V_ORDER_E.selectV_ORDERCount",v_order);
    }

    /**
     * 添加
     */
    public int insertV_ORDER(V_ORDER v_order) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.V_ORDER_E.save",v_order);
    }
    /**
     * 批量添加
     */
    public int insertV_ORDER(List<V_ORDER> list) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.V_ORDER_E.saveList",list);
    }
    /**
     * 修改
     */
    public int updateV_ORDER(V_ORDER v_order) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.V_ORDER_E.update",v_order);
    }
    /**
     * 批量修改
     */
    public int updateV_ORDER(List<V_ORDER> list) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.V_ORDER_E.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteV_ORDER(V_ORDER v_order) throws Exception{
        return getSqlSession().delete("com.tengcai.vims.entity.sporttery.V_ORDER_E.delete",v_order);
    }


	
}