package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.LOTO_B;
import com.tengcai.vims.dao.sporttery.LOTO_BDao;


/**
 * 篮球彩票信息daoImpl
 */
@Repository
public class LOTO_BDaoImpl extends BaseDaoImpl<LOTO_B> implements LOTO_BDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<LOTO_B> selectLOTO_BList(LOTO_B loto_b) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.LOTO_B.selectLOTO_BList",loto_b);
    }
    
    /**
     * 条件查询数量
     */
    public int selectLOTO_BCount(LOTO_B loto_b) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.LOTO_B.selectLOTO_BCount",loto_b);
    }

    /**
     * 添加
     */
    public int insertLOTO_B(LOTO_B loto_b) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.LOTO_B.save",loto_b);
    }
    /**
     * 批量添加
     */
    public int insertLOTO_B(List<LOTO_B> list) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.LOTO_B.saveList",list);
    }
    /**
     * 修改
     */
    public int updateLOTO_B(LOTO_B loto_b) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.LOTO_B.update",loto_b);
    }
    /**
     * 批量修改
     */
    public int updateLOTO_B(List<LOTO_B> list) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.LOTO_B.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteLOTO_B(LOTO_B loto_b) throws Exception{
        return getSqlSession().delete("com.tengcai.vims.entity.sporttery.LOTO_B.delete",loto_b);
    }


	
}