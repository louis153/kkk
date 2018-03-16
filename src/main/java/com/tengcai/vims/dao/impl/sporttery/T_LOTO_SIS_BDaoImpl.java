package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B;
import com.tengcai.vims.dao.sporttery.T_LOTO_SIS_BDao;


/**
 * 蓝球统计daoImpl
 */
@Repository
public class T_LOTO_SIS_BDaoImpl extends BaseDaoImpl<T_LOTO_SIS_B> implements T_LOTO_SIS_BDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_SIS_B> selectT_LOTO_SIS_BList(T_LOTO_SIS_B t_loto_sis_b) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B.selectT_LOTO_SIS_BList",t_loto_sis_b);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_SIS_BCount(T_LOTO_SIS_B t_loto_sis_b) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B.selectT_LOTO_SIS_BCount",t_loto_sis_b);
    }

    /**
     * 添加
     */
    public int insertT_LOTO_SIS_B(T_LOTO_SIS_B t_loto_sis_b) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B.save",t_loto_sis_b);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_SIS_B(List<T_LOTO_SIS_B> list) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B.saveList",list);
    }
    /**
     * 修改
     */
    public int updateT_LOTO_SIS_B(T_LOTO_SIS_B t_loto_sis_b) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B.update",t_loto_sis_b);
    }
    /**
     * 批量修改
     */
    public int updateT_LOTO_SIS_B(List<T_LOTO_SIS_B> list) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteT_LOTO_SIS_B(T_LOTO_SIS_B t_loto_sis_b) throws Exception{
    	
        return getSqlSession().delete("com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B.delete",t_loto_sis_b);
    }

	@Override
	public int saveSis(T_LOTO_SIS_B t_loto_sis_b) throws Exception {		
		
		return getSqlSession().delete("com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B.save_sis",t_loto_sis_b);
		
	}

	


	
}