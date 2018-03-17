package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.V_ORDERDao;
import com.longti.upjc.entity.sporttery.V_ORDER;


/**
 * daoImpl
 */
@Repository
public class V_ORDERDaoImpl extends BaseDaoImpl<V_ORDER> implements V_ORDERDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<V_ORDER> selectV_ORDERList(V_ORDER v_order) throws Exception{
        return getSqlSession().selectList("com.longti.upjc.entity.sporttery.V_ORDER.selectV_ORDERList",v_order);
    }
    
    /**
     * 条件查询数量
     */
    public int selectV_ORDERCount(V_ORDER v_order) throws Exception{
        return getSqlSession().selectOne("com.longti.upjc.entity.sporttery.V_ORDER.selectV_ORDERCount",v_order);
    }

    /**
     * 添加
     */
    public int insertV_ORDER(V_ORDER v_order) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.V_ORDER.save",v_order);
    }
    /**
     * 批量添加
     */
    public int insertV_ORDER(List<V_ORDER> list) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.V_ORDER.saveList",list);
    }
    /**
     * 修改
     */
    public int updateV_ORDER(V_ORDER v_order) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.V_ORDER.update",v_order);
    }
    /**
     * 批量修改
     */
    public int updateV_ORDER(List<V_ORDER> list) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.V_ORDER.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteV_ORDER(V_ORDER v_order) throws Exception{
        return getSqlSession().delete("com.longti.upjc.entity.sporttery.V_ORDER.delete",v_order);
    }


	
}