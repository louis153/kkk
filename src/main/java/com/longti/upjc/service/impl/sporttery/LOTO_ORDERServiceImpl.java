package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.LOTO_ORDERDao;
import com.longti.upjc.entity.sporttery.LOTO_ORDER;
import com.longti.upjc.service.sporttery.LOTO_ORDERService;
import com.longti.upjc.util.StringUtil;


/**
 * serviceImpl
 */
@Service
public class LOTO_ORDERServiceImpl implements LOTO_ORDERService  {
	@Autowired
	private LOTO_ORDERDao lOTO_ORDERDao;
   
    /**
	 * 条件查询
	 */
    public List<LOTO_ORDER> selectLOTO_ORDERList(LOTO_ORDER loto_order) throws Exception{
        return lOTO_ORDERDao.selectLOTO_ORDERList(loto_order);
    }
    
    /**
     * 条件查询数量
     */
    public int selectLOTO_ORDERCount(LOTO_ORDER loto_order) throws Exception{
        return lOTO_ORDERDao.selectLOTO_ORDERCount(loto_order);
    }

    /**
     * 添加
     */
    public int insertLOTO_ORDER(LOTO_ORDER loto_order) throws Exception{
        return lOTO_ORDERDao.insertLOTO_ORDER(loto_order);
    }

    /**
     * 批量添加
     */
    public int insertLOTO_ORDER(List<LOTO_ORDER> list) throws Exception{
        return lOTO_ORDERDao.insertLOTO_ORDER(list);
    }

    /**
     * 修改
     */
    public int updateLOTO_ORDER(LOTO_ORDER loto_order) throws Exception{
        return lOTO_ORDERDao.updateLOTO_ORDER(loto_order);
    }
    
    /**
     * 批量修改
     */
    public int updateLOTO_ORDER(List<LOTO_ORDER> list) throws Exception{
        return lOTO_ORDERDao.updateLOTO_ORDER(list);
    }

    /**
     * 删除
     */
    public int deleteLOTO_ORDER(LOTO_ORDER loto_order) throws Exception{
        return lOTO_ORDERDao.deleteLOTO_ORDER(loto_order);
    }

	@Override
	public Long selectOnceLim(String user_pin,String issue,String electronic_code) {
		LOTO_ORDER loto_order=new LOTO_ORDER();
		loto_order.setIssue(issue);
		loto_order.setUser_pin(user_pin);
		loto_order.setElectronic_code(electronic_code.toUpperCase());
		loto_order= lOTO_ORDERDao.selectOnceLim(loto_order);
		if(loto_order!=null){
		return (StringUtil.ifnull(loto_order.getBet_fee(),0L));
		}else{
			return 0L;
		}
	}


	
}