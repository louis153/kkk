package com.longti.upjc.dao.impl.sporttery;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.LOTO_EDao;
import com.longti.upjc.entity.sporttery.T_LOTO_E;


/**
 * 篮球彩票信息daoImpl
 */
@Repository
public class LOTO_EDaoImpl extends BaseDaoImpl<T_LOTO_E> implements LOTO_EDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_E> selectLOTO_EList(T_LOTO_E loto_e) throws Exception{
        return getSqlSession().selectList("com.longti.upjc.entity.sporttery.LOTO_E.selectLOTO_EList",loto_e);
    }
    
    /**
     * 条件查询数量
     */
    public int selectLOTO_ECount(T_LOTO_E loto_e) throws Exception{
        return getSqlSession().selectOne("com.longti.upjc.entity.sporttery.LOTO_E.selectLOTO_ECount",loto_e);
    }

    /**
     * 添加
     */
    public int insertLOTO_E(T_LOTO_E loto_e) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.LOTO_E.save",loto_e);
    }
    /**
     * 批量添加
     */
    public int insertLOTO_E(List<T_LOTO_E> list) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.LOTO_E.saveList",list);
    }
    /**
     * 修改
     */
    public int updateLOTO_E(T_LOTO_E loto_e) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.LOTO_E.update",loto_e);
    }
    /**
     * 批量修改
     */
    public int updateLOTO_E(List<T_LOTO_E> list) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.LOTO_E.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteLOTO_E(T_LOTO_E loto_e) throws Exception{
        return getSqlSession().delete("com.longti.upjc.entity.sporttery.LOTO_E.delete",loto_e);
    }


	
}