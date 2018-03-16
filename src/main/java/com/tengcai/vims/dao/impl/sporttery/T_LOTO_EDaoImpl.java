package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.T_LOTO_E;
import com.tengcai.vims.dao.sporttery.T_LOTO_EDao;


/**
 * 电子竞猜比赛信息daoImpl
 */
@Repository
public class T_LOTO_EDaoImpl extends BaseDaoImpl<T_LOTO_E> implements T_LOTO_EDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_E> selectT_LOTO_EList(T_LOTO_E t_loto_e) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.T_LOTO_E.selectT_LOTO_EList",t_loto_e);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_ECount(T_LOTO_E t_loto_e) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.T_LOTO_E.selectT_LOTO_ECount",t_loto_e);
    }

    /**
     * 添加
     */
    public int insertT_LOTO_E(T_LOTO_E t_loto_e) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.T_LOTO_E.save",t_loto_e);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_E(List<T_LOTO_E> list) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.T_LOTO_E.saveList",list);
    }
    /**
     * 修改
     */
    public int updateT_LOTO_E(T_LOTO_E t_loto_e) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.T_LOTO_E.update",t_loto_e);
    }
    /**
     * 批量修改
     */
    public int updateT_LOTO_E(List<T_LOTO_E> list) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.T_LOTO_E.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteT_LOTO_E(T_LOTO_E t_loto_e) throws Exception{
        return getSqlSession().delete("com.tengcai.vims.entity.sporttery.T_LOTO_E.delete",t_loto_e);
    }


	
}