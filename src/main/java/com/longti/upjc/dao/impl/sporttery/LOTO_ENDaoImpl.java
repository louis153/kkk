package com.longti.upjc.dao.impl.sporttery;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.LOTO_ENDao;
import com.longti.upjc.entity.sporttery.T_LOTO_E;


/**
 * 篮球彩票信息（在售）daoImpl
 */
@Repository
public class LOTO_ENDaoImpl extends BaseDaoImpl<T_LOTO_E> implements LOTO_ENDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_E> selectLOTO_ENList(T_LOTO_E loto_en) throws Exception{
        return getSqlSession().selectList("com.longti.upjc.entity.sporttery.T_LOTO_EN.selectT_LOTO_ENList",loto_en);
    }
  
    /**
     * 条件查询数量
     */
    public int selectLOTO_ENCount(T_LOTO_E loto_en) throws Exception{
        return getSqlSession().selectOne("com.longti.upjc.entity.sporttery.T_LOTO_EN.selectT_LOTO_ENCount",loto_en);
    }

    /**
     * 添加
     */
    public int insertLOTO_EN(T_LOTO_E loto_en) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.T_LOTO_EN.save",loto_en);
    }
    /**
     * 批量添加
     */
    public int insertLOTO_EN(List<T_LOTO_E> list) throws Exception{
        return getSqlSession().insert("com.longti.upjc.entity.sporttery.T_LOTO_EN.saveList",list);
    }
    /**
     * 修改
     */
    public int updateLOTO_EN(T_LOTO_E loto_bn) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.T_LOTO_EN.update",loto_bn);
    }
    /**
     * 批量修改
     */
    public int updateLOTO_EN(List<T_LOTO_E> list) throws Exception{
        return getSqlSession().update("com.longti.upjc.entity.sporttery.T_LOTO_EN.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteLOTO_EN(T_LOTO_E loto_en) throws Exception{
        return getSqlSession().delete("com.longti.upjc.entity.sporttery.T_LOTO_EN.delete",loto_en);
    }

	@Override
	public int deleteAll() throws Exception {
		return getSqlSession().delete("com.longti.upjc.entity.sporttery.T_LOTO_EN.deleteAll");
	}
	@Override
	 public T_LOTO_E selectRemEN() throws Exception{
      return getSqlSession().selectOne("com.longti.upjc.entity.sporttery.T_LOTO_EN.selectRemEN");
  }

	
}