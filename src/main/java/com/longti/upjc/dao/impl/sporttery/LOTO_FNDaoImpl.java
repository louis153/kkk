package com.longti.upjc.dao.impl.sporttery;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.LOTO_FNDao;
import com.longti.upjc.entity.sporttery.LOTO_F;


/**
 * 足球彩票信息（在售）daoImpl
 */
@Repository
public class LOTO_FNDaoImpl extends BaseDaoImpl<LOTO_F> implements LOTO_FNDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<LOTO_F> selectLOTO_FNList(LOTO_F loto_fn) throws Exception{
        return getSqlSession().selectList("com.longti.upjc.entity.sporttery.LOTO_FN.selectLOTO_FNList",loto_fn);
    }
    
    /**
     * 条件查询数量
     */
    public int selectLOTO_FNCount(LOTO_F loto_fn) throws Exception{
        return getSqlSession().selectOne("com.longti.upjc.entity.sporttery.LOTO_FN.selectLOTO_FNCount",loto_fn);
    }

    /**
     * 添加
     */
    public int insertLOTO_FN(LOTO_F loto_fn) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.LOTO_FN.save",loto_fn);
    }
    /**
     * 批量添加
     */
    public int insertLOTO_FN(List<LOTO_F> list) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.LOTO_FN.saveList",list);
    }
    /**
     * 修改
     */
    public int updateLOTO_FN(LOTO_F loto_fn) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.LOTO_FN.update",loto_fn);
    }
    /**
     * 批量修改
     */
    public int updateLOTO_FN(List<LOTO_F> list) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.LOTO_FN.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteLOTO_FN(LOTO_F loto_fn) throws Exception{
        return getSqlSession().delete("com.longti.upjc.entity.sporttery.LOTO_FN.delete",loto_fn);
    }

	@Override
	public int clear() throws Exception {
		return getSqlSession().delete("com.longti.upjc.entity.sporttery.LOTO_FN.clear");
	}
	@Override
	 public LOTO_F selectRemFN() throws Exception{
       return getSqlSession().selectOne("com.longti.upjc.entity.sporttery.LOTO_FN.selectRemFN");
   }

	
}