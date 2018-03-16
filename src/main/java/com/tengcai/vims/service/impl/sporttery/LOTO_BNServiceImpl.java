package com.tengcai.vims.service.impl.sporttery;
import java.util.HashMap;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.LOTO_B;
import com.tengcai.vims.dao.sporttery.LOTO_BNDao;
import com.tengcai.vims.service.sporttery.LOTO_BNService;


/**
 * 篮球彩票信息（在售）serviceImpl
 */
@Service
public class LOTO_BNServiceImpl implements LOTO_BNService  {
	protected final transient static org.slf4j.Logger logger = LoggerFactory.getLogger(LOTO_BNServiceImpl.class);
	@Autowired
	private LOTO_BNDao lOTO_BNDao;
   
    /**
	 * 条件查询
	 */
    public List<LOTO_B> selectLOTO_BNList(LOTO_B loto_bn) throws Exception{
        return lOTO_BNDao.selectLOTO_BNList(loto_bn);
    }
    @Override
    public LOTO_B selectRemBN() throws Exception{
        return lOTO_BNDao.selectRemBN();
    }
    /**
     * 条件查询数量
     */
    public int selectLOTO_BNCount(LOTO_B loto_bn) throws Exception{
        return lOTO_BNDao.selectLOTO_BNCount(loto_bn);
    }

    /**
     * 添加
     */
    public int insertLOTO_BN(LOTO_B loto_bn) throws Exception{
        return lOTO_BNDao.insertLOTO_BN(loto_bn);
    }

    /**
     * 批量添加
     */
    public int insertLOTO_BN(List<LOTO_B> list) throws Exception{
        return lOTO_BNDao.insertLOTO_BN(list);
    }

    /**
     * 修改
     */
    public int updateLOTO_BN(LOTO_B loto_bn) throws Exception{
        return lOTO_BNDao.updateLOTO_BN(loto_bn);
    }
    
    /**
     * 批量修改
     */
    public int updateLOTO_BN(List<LOTO_B> list) throws Exception{
        return lOTO_BNDao.updateLOTO_BN(list);
    }

    /**
     * 删除
     */
    public int deleteLOTO_BN(LOTO_B loto_bn) throws Exception{
        return lOTO_BNDao.deleteLOTO_BN(loto_bn);
    }

	@Override
	public int insertAll(List<LOTO_B> list) throws Exception {
		HashMap<String, LOTO_B> hB=new HashMap<String,LOTO_B>();
		for(LOTO_B b :list){
			hB.put(b.getIssue(), b);
		}
		
		lOTO_BNDao.deleteAll();
		lOTO_BNDao.updateLOTO_BN(list);
		return list.size();
	}

	
	
}