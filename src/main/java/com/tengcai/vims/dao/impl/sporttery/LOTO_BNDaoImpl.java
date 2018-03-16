package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.LOTO_B;
import com.tengcai.vims.dao.sporttery.LOTO_BNDao;


/**
 * 篮球彩票信息（在售）daoImpl
 */
@Repository
public class LOTO_BNDaoImpl extends BaseDaoImpl<LOTO_B> implements LOTO_BNDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<LOTO_B> selectLOTO_BNList(LOTO_B loto_bn) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.LOTO_BN.selectLOTO_BNList",loto_bn);
    }
    
    /**
     * 条件查询数量
     */
    public int selectLOTO_BNCount(LOTO_B loto_bn) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.LOTO_BN.selectLOTO_BNCount",loto_bn);
    }

    /**
     * 添加
     */
    public int insertLOTO_BN(LOTO_B loto_bn) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.LOTO_BN.save",loto_bn);
    }
    /**
     * 批量添加
     */
    public int insertLOTO_BN(List<LOTO_B> list) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.LOTO_BN.saveList",list);
    }
    /**
     * 修改
     */
    public int updateLOTO_BN(LOTO_B loto_bn) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.LOTO_BN.update",loto_bn);
    }
    /**
     * 批量修改
     */
    public int updateLOTO_BN(List<LOTO_B> list) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.LOTO_BN.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteLOTO_BN(LOTO_B loto_bn) throws Exception{
        return getSqlSession().delete("com.tengcai.vims.entity.sporttery.LOTO_BN.delete",loto_bn);
    }

	@Override
	public int deleteAll() throws Exception {
		return getSqlSession().delete("com.tengcai.vims.entity.sporttery.LOTO_BN.deleteAll");
	}

	@Override
	 public LOTO_B selectRemBN() throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.LOTO_BN.selectRemBN");
    }
	
}