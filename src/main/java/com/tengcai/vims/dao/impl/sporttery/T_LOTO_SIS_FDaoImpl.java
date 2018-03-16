package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_F;
import com.tengcai.vims.dao.sporttery.T_LOTO_SIS_FDao;


/**
 * 足球统计daoImpl
 */
@Repository
public class T_LOTO_SIS_FDaoImpl extends BaseDaoImpl<T_LOTO_SIS_F> implements T_LOTO_SIS_FDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_SIS_F> selectT_LOTO_SIS_FList(T_LOTO_SIS_F t_loto_sis_f) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.T_LOTO_SIS_F.selectT_LOTO_SIS_FList",t_loto_sis_f);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_SIS_FCount(T_LOTO_SIS_F t_loto_sis_f) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.T_LOTO_SIS_F.selectT_LOTO_SIS_FCount",t_loto_sis_f);
    }

    /**
     * 添加
     */
    public int insertT_LOTO_SIS_F(T_LOTO_SIS_F t_loto_sis_f) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.T_LOTO_SIS_F.save",t_loto_sis_f);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_SIS_F(List<T_LOTO_SIS_F> list) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.T_LOTO_SIS_F.saveList",list);
    }
    /**
     * 修改
     */
    public int updateT_LOTO_SIS_F(T_LOTO_SIS_F t_loto_sis_f) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.T_LOTO_SIS_F.update",t_loto_sis_f);
    }
    /**
     * 批量修改
     */
    public int updateT_LOTO_SIS_F(List<T_LOTO_SIS_F> list) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.T_LOTO_SIS_F.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteT_LOTO_SIS_F(T_LOTO_SIS_F t_loto_sis_f) throws Exception{
        return getSqlSession().delete("com.tengcai.vims.entity.sporttery.T_LOTO_SIS_F.delete",t_loto_sis_f);
    }

	@Override
	public int saveSisT_LOTO_SIS_F(T_LOTO_SIS_F t_loto_sis_f) throws Exception {
		return getSqlSession().insert("com.tengcai.vims.entity.sporttery.T_LOTO_SIS_F.save_sis",t_loto_sis_f);
	}

}