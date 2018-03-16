package com.tengcai.vims.service.impl.sporttery;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.LOTO_F;
import com.tengcai.vims.dao.sporttery.LOTO_FDao;
import com.tengcai.vims.service.sporttery.LOTO_FService;


/**
 * 足球彩票信息serviceImpl
 */
@Service
public class LOTO_FServiceImpl implements LOTO_FService  {
	@Autowired
	private LOTO_FDao lOTO_FDao;
   
    /**
	 * 条件查询
	 */
    public List<LOTO_F> selectLOTO_FList(LOTO_F loto_f) throws Exception{
        return lOTO_FDao.selectLOTO_FList(loto_f);
    }
    
    /**
     * 条件查询数量
     */
    public int selectLOTO_FCount(LOTO_F loto_f) throws Exception{
        return lOTO_FDao.selectLOTO_FCount(loto_f);
    }

    /**
     * 添加
     */
    public int insertLOTO_F(LOTO_F loto_f) throws Exception{
        return lOTO_FDao.insertLOTO_F(loto_f);
    }

    /**
     * 批量添加
     */
    public int insertLOTO_F(List<LOTO_F> list) throws Exception{
        return lOTO_FDao.insertLOTO_F(list);
    }

    /**
     * 修改
     */
    public int updateLOTO_F(LOTO_F loto_f) throws Exception{
        return lOTO_FDao.updateLOTO_F(loto_f);
    }
    
    /**
     * 批量修改
     */
    public int updateLOTO_F(List<LOTO_F> list) throws Exception{
        return lOTO_FDao.updateLOTO_F(list);
    }

    /**
     * 删除
     */
    public int deleteLOTO_F(LOTO_F loto_f) throws Exception{
        return lOTO_FDao.deleteLOTO_F(loto_f);
    }


	
}