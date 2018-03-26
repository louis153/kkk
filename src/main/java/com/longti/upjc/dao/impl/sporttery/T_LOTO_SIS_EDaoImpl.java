package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.entity.sporttery.T_LOTO_SIS_E;
import com.longti.upjc.dao.sporttery.T_LOTO_SIS_EDao;


/**
 * 电竞统计daoImpl
 */
@Repository
public class T_LOTO_SIS_EDaoImpl extends BaseDaoImpl<T_LOTO_SIS_E> implements T_LOTO_SIS_EDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_SIS_E> selectT_LOTO_SIS_EList(T_LOTO_SIS_E t_loto_sis_e) throws Exception{
        return getSqlSession().selectList("com.longti.upjc.entity.sporttery.T_LOTO_SIS_E.selectT_LOTO_SIS_EList",t_loto_sis_e);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_SIS_ECount(T_LOTO_SIS_E t_loto_sis_e) throws Exception{
        return getSqlSession().selectOne("com.longti.upjc.entity.sporttery.T_LOTO_SIS_E.selectT_LOTO_SIS_ECount",t_loto_sis_e);
    }

    /**
     * 添加
     */
    public int insertT_LOTO_SIS_E(T_LOTO_SIS_E t_loto_sis_e) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.T_LOTO_SIS_E.save",t_loto_sis_e);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_SIS_E(List<T_LOTO_SIS_E> list) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.T_LOTO_SIS_E.saveList",list);
    }
    /**
     * 修改
     */
    public int updateT_LOTO_SIS_E(T_LOTO_SIS_E t_loto_sis_e) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.T_LOTO_SIS_E.update",t_loto_sis_e);
    }
    /**
     * 批量修改
     */
    public int updateT_LOTO_SIS_E(List<T_LOTO_SIS_E> list) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.T_LOTO_SIS_E.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteT_LOTO_SIS_E(T_LOTO_SIS_E t_loto_sis_e) throws Exception{
        return getSqlSession().delete("com.longti.upjc.entity.sporttery.T_LOTO_SIS_E.delete",t_loto_sis_e);
    }

	@Override
	public int saveSis(T_LOTO_SIS_E t_loto_sis_e) {
		return getSqlSession().insert("com.longti.upjc.entity.sporttery.T_LOTO_SIS_E.save_sis",t_loto_sis_e);
	}


	
}