package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.T_LOTO_E;
import com.tengcai.vims.dao.sporttery.LOTO_EDao;


/**
 * 篮球彩票信息daoImpl
 */
@Repository
public class LOTO_EDaoImpl extends BaseDaoImpl<T_LOTO_E> implements LOTO_EDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_E> selectLOTO_EList(T_LOTO_E loto_e) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.LOTO_E.selectLOTO_EList",loto_e);
    }
    
    /**
     * 条件查询数量
     */
    public int selectLOTO_ECount(T_LOTO_E loto_e) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.LOTO_E.selectLOTO_ECount",loto_e);
    }

    /**
     * 添加
     */
    public int insertLOTO_E(T_LOTO_E loto_e) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.LOTO_E.save",loto_e);
    }
    /**
     * 批量添加
     */
    public int insertLOTO_E(List<T_LOTO_E> list) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.LOTO_E.saveList",list);
    }
    /**
     * 修改
     */
    public int updateLOTO_E(T_LOTO_E loto_e) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.LOTO_E.update",loto_e);
    }
    /**
     * 批量修改
     */
    public int updateLOTO_E(List<T_LOTO_E> list) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.LOTO_E.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteLOTO_E(T_LOTO_E loto_e) throws Exception{
        return getSqlSession().delete("com.tengcai.vims.entity.sporttery.LOTO_E.delete",loto_e);
    }


	
}