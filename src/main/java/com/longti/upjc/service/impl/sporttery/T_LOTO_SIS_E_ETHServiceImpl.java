package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.T_LOTO_SIS_E_ETHDao;
import com.longti.upjc.entity.sporttery.T_LOTO_SIS_E_ETH;
import com.longti.upjc.service.sporttery.T_LOTO_SIS_E_ETHService;


/**
 * ETH话题竞猜统计serviceImpl
 */
@Service
public class T_LOTO_SIS_E_ETHServiceImpl implements T_LOTO_SIS_E_ETHService  {
	@Autowired
	private T_LOTO_SIS_E_ETHDao T_LOTO_SIS_E_ETHDao;
   
	/**
	 * 条件查询
	 */
    public List<T_LOTO_SIS_E_ETH> selectT_LOTO_SIS_E_ETHList(T_LOTO_SIS_E_ETH t_loto_sis_e_eth) throws Exception{
    	return T_LOTO_SIS_E_ETHDao.selectT_LOTO_SIS_E_ETHList(t_loto_sis_e_eth);
    }
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_SIS_E_ETHCount(T_LOTO_SIS_E_ETH t_loto_sis_e_eth) throws Exception{
    	return T_LOTO_SIS_E_ETHDao.selectT_LOTO_SIS_E_ETHCount(t_loto_sis_e_eth);
    }
    /**
     * 添加
     */
    public int insertT_LOTO_SIS_E_ETH(T_LOTO_SIS_E_ETH t_loto_sis_e_eth) throws Exception{
    	return T_LOTO_SIS_E_ETHDao.insertT_LOTO_SIS_E_ETH(t_loto_sis_e_eth);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_SIS_E_ETH(List<T_LOTO_SIS_E_ETH> list) throws Exception{
    	return T_LOTO_SIS_E_ETHDao.insertT_LOTO_SIS_E_ETH(list);
    }
    /**
     * 修改
     */
    public int updateT_LOTO_SIS_E_ETH(T_LOTO_SIS_E_ETH t_loto_sis_e_eth) throws Exception{
    	return T_LOTO_SIS_E_ETHDao.updateT_LOTO_SIS_E_ETH(t_loto_sis_e_eth);
    }
    /**
     * 批量修改
     */
    public int updateT_LOTO_SIS_E_ETH(List<T_LOTO_SIS_E_ETH> list) throws Exception{
    	return T_LOTO_SIS_E_ETHDao.updateT_LOTO_SIS_E_ETH(list);
    }
    /**
     * 删除
     */
    public int deleteT_LOTO_SIS_E_ETH(T_LOTO_SIS_E_ETH t_loto_sis_e_eth) throws Exception{
    	return T_LOTO_SIS_E_ETHDao.updateT_LOTO_SIS_E_ETH(t_loto_sis_e_eth);
    }



	
}