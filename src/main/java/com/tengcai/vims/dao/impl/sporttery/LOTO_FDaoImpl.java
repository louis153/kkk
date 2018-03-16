package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.LOTO_F;
import com.tengcai.vims.dao.sporttery.LOTO_FDao;


/**
 * 足球彩票信息daoImpl
 */
@Repository
public class LOTO_FDaoImpl extends BaseDaoImpl<LOTO_F> implements LOTO_FDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<LOTO_F> selectLOTO_FList(LOTO_F loto_f) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.LOTO_F.selectLOTO_FList",loto_f);
    }
    
    /**
     * 条件查询数量
     */
    public int selectLOTO_FCount(LOTO_F loto_f) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.LOTO_F.selectLOTO_FCount",loto_f);
    }

    /**
     * 添加
     */
    public int insertLOTO_F(LOTO_F loto_f) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.LOTO_F.save",loto_f);
    }
    /**
     * 批量添加
     */
    public int insertLOTO_F(List<LOTO_F> list) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.LOTO_F.saveList",list);
    }
    /**
     * 修改
     */
    public int updateLOTO_F(LOTO_F loto_f) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.LOTO_F.update",loto_f);
    }
    /**
     * 批量修改
     */
    public int updateLOTO_F(List<LOTO_F> list) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.LOTO_F.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteLOTO_F(LOTO_F loto_f) throws Exception{
        return getSqlSession().delete("com.tengcai.vims.entity.sporttery.LOTO_F.delete",loto_f);
    }


	
}