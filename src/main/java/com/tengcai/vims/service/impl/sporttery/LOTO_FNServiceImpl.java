package com.tengcai.vims.service.impl.sporttery;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengcai.vims.entity.sporttery.LOTO_F;
import com.tengcai.vims.dao.sporttery.LOTO_FNDao;
import com.tengcai.vims.service.sporttery.LOTO_FNService;


/**
 * 足球彩票信息（在售）serviceImpl
 */
@Service
public class LOTO_FNServiceImpl implements LOTO_FNService  {
	@Autowired
	private LOTO_FNDao lOTO_FNDao;
   
    /**
	 * 条件查询
	 */
    public List<LOTO_F> selectLOTO_FNList(LOTO_F loto_fn) throws Exception{
        return lOTO_FNDao.selectLOTO_FNList(loto_fn);
    }
    @Override
    public LOTO_F selectRemFN() throws Exception{
        return lOTO_FNDao.selectRemFN();
    }
    /**
     * 条件查询数量
     */
    public int selectLOTO_FNCount(LOTO_F loto_fn) throws Exception{
        return lOTO_FNDao.selectLOTO_FNCount(loto_fn);
    }

    /**
     * 添加
     */
    public int insertLOTO_FN(LOTO_F loto_fn) throws Exception{
        return lOTO_FNDao.insertLOTO_FN(loto_fn);
    }

    /**
     * 批量添加
     */
    public int insertLOTO_FN(List<LOTO_F> list) throws Exception{
        return lOTO_FNDao.insertLOTO_FN(list);
    }

    /**
     * 修改
     */
    public int updateLOTO_FN(LOTO_F loto_fn) throws Exception{
        return lOTO_FNDao.updateLOTO_FN(loto_fn);
    }
    
    /**
     * 批量修改
     */
    public int updateLOTO_FN(List<LOTO_F> list) throws Exception{
        return lOTO_FNDao.updateLOTO_FN(list);
    }

    /**
     * 删除
     */
    public int deleteLOTO_FN(LOTO_F loto_fn) throws Exception{
        return lOTO_FNDao.deleteLOTO_FN(loto_fn);
    }

	@Override
	public int insertAll(List<LOTO_F> list) throws Exception {
		if(list.isEmpty()==false){
			HashMap<String, LOTO_F> hF=new HashMap<String,LOTO_F>();
			for(LOTO_F f :list){
				hF.put(f.getIssue(), f);
			}
			
			lOTO_FNDao.clear();
			return lOTO_FNDao.updateLOTO_FN(list);
		}else{
			return 0;
		}
	}

	
	
}