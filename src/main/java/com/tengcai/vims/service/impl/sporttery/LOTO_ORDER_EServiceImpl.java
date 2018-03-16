package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.LOTO_ORDER;
import com.tengcai.vims.dao.sporttery.LOTO_ORDER_EDao;
import com.tengcai.vims.service.sporttery.LOTO_ORDER_EService;


/**
 * serviceImpl
 */
@Service
public class LOTO_ORDER_EServiceImpl implements LOTO_ORDER_EService  {
	@Autowired
	private LOTO_ORDER_EDao lOTO_ORDER_EDao;
   
    /**
	 * 条件查询
	 */
    public List<LOTO_ORDER> selectLOTO_ORDERList(LOTO_ORDER loto_order) throws Exception{
        return lOTO_ORDER_EDao.selectLOTO_ORDERList(loto_order);
    }
    
    /**
     * 条件查询数量
     */
    public int selectLOTO_ORDERCount(LOTO_ORDER loto_order) throws Exception{
        return lOTO_ORDER_EDao.selectLOTO_ORDERCount(loto_order);
    }

    /**
     * 添加
     */
    public int insertLOTO_ORDER(LOTO_ORDER loto_order) throws Exception{
        return lOTO_ORDER_EDao.insertLOTO_ORDER(loto_order);
    }

    /**
     * 批量添加
     */
    public int insertLOTO_ORDER(List<LOTO_ORDER> list) throws Exception{
        return lOTO_ORDER_EDao.insertLOTO_ORDER(list);
    }

    /**
     * 修改
     */
    public int updateLOTO_ORDER(LOTO_ORDER loto_order) throws Exception{
        return lOTO_ORDER_EDao.updateLOTO_ORDER(loto_order);
    }
    
    /**
     * 批量修改
     */
    public int updateLOTO_ORDER(List<LOTO_ORDER> list) throws Exception{
        return lOTO_ORDER_EDao.updateLOTO_ORDER(list);
    }

    /**
     * 删除
     */
    public int deleteLOTO_ORDER(LOTO_ORDER loto_order) throws Exception{
        return lOTO_ORDER_EDao.deleteLOTO_ORDER(loto_order);
    }


	
}