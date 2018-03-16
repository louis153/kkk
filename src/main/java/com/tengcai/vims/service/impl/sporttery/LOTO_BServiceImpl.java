package com.tengcai.vims.service.impl.sporttery;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.LOTO_B;
import com.tengcai.vims.dao.sporttery.LOTO_BDao;
import com.tengcai.vims.service.sporttery.LOTO_BService;


/**
 * 篮球彩票信息serviceImpl
 */
@Service
public class LOTO_BServiceImpl implements LOTO_BService  {
	@Autowired
	private LOTO_BDao lOTO_BDao;
   
    /**
	 * 条件查询
	 */
    public List<LOTO_B> selectLOTO_BList(LOTO_B loto_b) throws Exception{
        return lOTO_BDao.selectLOTO_BList(loto_b);
    }
    
    /**
     * 条件查询数量
     */
    public int selectLOTO_BCount(LOTO_B loto_b) throws Exception{
        return lOTO_BDao.selectLOTO_BCount(loto_b);
    }

    /**
     * 添加
     */
    public int insertLOTO_B(LOTO_B loto_b) throws Exception{
        return lOTO_BDao.insertLOTO_B(loto_b);
    }

    /**
     * 批量添加
     */
    public int insertLOTO_B(List<LOTO_B> list) throws Exception{
        return lOTO_BDao.insertLOTO_B(list);
    }

    /**
     * 修改
     */
    public int updateLOTO_B(LOTO_B loto_b) throws Exception{
        return lOTO_BDao.updateLOTO_B(loto_b);
    }
    
    /**
     * 批量修改
     */
    public int updateLOTO_B(List<LOTO_B> list) throws Exception{
        return lOTO_BDao.updateLOTO_B(list);
    }

    /**
     * 删除
     */
    public int deleteLOTO_B(LOTO_B loto_b) throws Exception{
        return lOTO_BDao.deleteLOTO_B(loto_b);
    }


	
}