package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.T_LOTO_E;
import com.tengcai.vims.dao.sporttery.T_LOTO_ENDao;


/**
 * 在售的比赛, 比赛开始后删除此表中的数据daoImpl
 */
@Repository
public class T_LOTO_ENDaoImpl extends BaseDaoImpl<T_LOTO_E> implements T_LOTO_ENDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_E> selectT_LOTO_ENList(T_LOTO_E t_loto_en) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.T_LOTO_EN.selectT_LOTO_ENList",t_loto_en);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_ENCount(T_LOTO_E t_loto_en) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.T_LOTO_EN.selectT_LOTO_ENCount",t_loto_en);
    }

    /**
     * 添加
     */
    public int insertT_LOTO_EN(T_LOTO_E t_loto_en) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.T_LOTO_EN.save",t_loto_en);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_EN(List<T_LOTO_E> list) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.T_LOTO_EN.saveList",list);
    }
    /**
     * 修改
     */
    public int updateT_LOTO_EN(T_LOTO_E t_loto_en) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.T_LOTO_EN.update",t_loto_en);
    }
    /**
     * 批量修改
     */
    public int updateT_LOTO_EN(List<T_LOTO_E> list) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.T_LOTO_EN.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteT_LOTO_EN(T_LOTO_E t_loto_en) throws Exception{
        return getSqlSession().delete("com.tengcai.vims.entity.sporttery.T_LOTO_EN.delete",t_loto_en);
    }
    @Override
    public T_LOTO_E selectRemEN() throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.T_LOTO_EN.selectRemEN");
    }
	
}