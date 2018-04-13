package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.LOTO_ORDERDao;
import com.longti.upjc.entity.sporttery.LOTO_ORDER;


/**
 * daoImpl
 */
@Repository
public class LOTO_ORDERDaoImpl extends BaseDaoImpl<LOTO_ORDER> implements LOTO_ORDERDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<LOTO_ORDER> selectLOTO_ORDERList(LOTO_ORDER loto_order) throws Exception{
        return getSqlSession().selectList("com.longti.upjc.entity.sporttery.LOTO_ORDER.selectLOTO_ORDERList",loto_order);
    }
    
    /**
     * 条件查询数量
     */
    public int selectLOTO_ORDERCount(LOTO_ORDER loto_order) throws Exception{
        return getSqlSession().selectOne("com.longti.upjc.entity.sporttery.LOTO_ORDER.selectLOTO_ORDERCount",loto_order);
    }

    /**
     * 添加
     */
    public int insertLOTO_ORDER(LOTO_ORDER loto_order) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.LOTO_ORDER.save",loto_order);
    }
    /**
     * 批量添加
     */
    public int insertLOTO_ORDER(List<LOTO_ORDER> list) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.LOTO_ORDER.saveList",list);
    }
    /**
     * 修改
     */
    public int updateLOTO_ORDER(LOTO_ORDER loto_order) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.LOTO_ORDER.update",loto_order);
    }
    /**
     * 批量修改
     */
    public int updateLOTO_ORDER(List<LOTO_ORDER> list) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.LOTO_ORDER.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteLOTO_ORDER(LOTO_ORDER loto_order) throws Exception{
        return getSqlSession().delete("com.longti.upjc.entity.sporttery.LOTO_ORDER.delete",loto_order);
    }

	@Override
	public LOTO_ORDER selectOnceLim(LOTO_ORDER loto_order) {
		return getSqlSession().selectOne("com.longti.upjc.entity.sporttery.LOTO_ORDER.delete",loto_order);
	}


	
}