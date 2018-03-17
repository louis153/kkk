package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.T_CONTROLDao;
import com.longti.upjc.entity.sporttery.T_CONTROL;


/**
 * 阀值管理daoImpl
 */
@Repository
public class T_CONTROLDaoImpl extends BaseDaoImpl<T_CONTROL> implements T_CONTROLDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_CONTROL> selectT_CONTROLList(T_CONTROL t_control) throws Exception{
        return getSqlSession().selectList("com.longti.upjc.entity.sporttery.T_CONTROL.selectT_CONTROLList",t_control);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_CONTROLCount(T_CONTROL t_control) throws Exception{
        return getSqlSession().selectOne("com.longti.upjc.entity.sporttery.T_CONTROL.selectT_CONTROLCount",t_control);
    }

    /**
     * 添加
     */
    public int insertT_CONTROL(T_CONTROL t_control) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.T_CONTROL.save",t_control);
    }
    /**
     * 批量添加
     */
    public int insertT_CONTROL(List<T_CONTROL> list) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.T_CONTROL.saveList",list);
    }
    /**
     * 修改
     */
    public int updateT_CONTROL(T_CONTROL t_control) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.T_CONTROL.update",t_control);
    }
    /**
     * 批量修改
     */
    public int updateT_CONTROL(List<T_CONTROL> list) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.T_CONTROL.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteT_CONTROL(T_CONTROL t_control) throws Exception{
        return getSqlSession().delete("com.longti.upjc.entity.sporttery.T_CONTROL.delete",t_control);
    }


	
}