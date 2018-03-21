package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.entity.sporttery.T_USER;
import com.longti.upjc.dao.sporttery.T_USERDao;


/**
 * daoImpl
 */
@Repository
public class T_USERDaoImpl extends BaseDaoImpl<T_USER> implements T_USERDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_USER> selectT_USERList(T_USER t_user) throws Exception{
        return getSqlSession().selectList("com.longti.upjc.entity.sporttery.T_USER.selectT_USERList",t_user);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_USERCount(T_USER t_user) throws Exception{
        return getSqlSession().selectOne("com.longti.upjc.entity.sporttery.T_USER.selectT_USERCount",t_user);
    }

    /**
     * 添加
     */
    public int insertT_USER(T_USER t_user) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.T_USER.save",t_user);
    }
    /**
     * 批量添加
     */
    public int insertT_USER(List<T_USER> list) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.T_USER.saveList",list);
    }
    /**
     * 修改
     */
    public int updateT_USER(T_USER t_user) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.T_USER.update",t_user);
    }
    /**
     * 批量修改
     */
    public int updateT_USER(List<T_USER> list) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.T_USER.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteT_USER(T_USER t_user) throws Exception{
        return getSqlSession().delete("com.longti.upjc.entity.sporttery.T_USER.delete",t_user);
    }


	
}