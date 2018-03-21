package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.T_LOTO_EN_ETHDao;
import com.longti.upjc.entity.sporttery.T_LOTO_EN_ETH;
import com.longti.upjc.service.sporttery.T_LOTO_EN_ETHService;


/**
 * 进行中ETH话题竞猜信息serviceImpl
 */
@Service
public class T_LOTO_EN_ETHServiceImpl implements T_LOTO_EN_ETHService  {
	@Autowired
	private T_LOTO_EN_ETHDao T_LOTO_EN_ETHDao;
   
	/**
	 * 条件查询
	 */
    public List<T_LOTO_EN_ETH> selectT_LOTO_EN_ETHList(T_LOTO_EN_ETH t_loto_en_eth) throws Exception{
    	return T_LOTO_EN_ETHDao.selectT_LOTO_EN_ETHList(t_loto_en_eth);
    }
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_EN_ETHCount(T_LOTO_EN_ETH t_loto_en_eth) throws Exception{
    	return T_LOTO_EN_ETHDao.selectT_LOTO_EN_ETHCount(t_loto_en_eth);
    }
    /**
     * 添加
     */
    public int insertT_LOTO_EN_ETH(T_LOTO_EN_ETH t_loto_en_eth) throws Exception{
    	return T_LOTO_EN_ETHDao.insertT_LOTO_EN_ETH(t_loto_en_eth);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_EN_ETH(List<T_LOTO_EN_ETH> list) throws Exception{
    	return T_LOTO_EN_ETHDao.insertT_LOTO_EN_ETH(list);
    }
    /**
     * 修改
     */
    public int updateT_LOTO_EN_ETH(T_LOTO_EN_ETH t_loto_en_eth) throws Exception{
    	return T_LOTO_EN_ETHDao.updateT_LOTO_EN_ETH(t_loto_en_eth);
    }
    /**
     * 批量修改
     */
    public int updateT_LOTO_EN_ETH(List<T_LOTO_EN_ETH> list) throws Exception{
    	return T_LOTO_EN_ETHDao.updateT_LOTO_EN_ETH(list);
    }
    /**
     * 删除
     */
    public int deleteT_LOTO_EN_ETH(T_LOTO_EN_ETH t_loto_en_eth) throws Exception{
    	return T_LOTO_EN_ETHDao.deleteT_LOTO_EN_ETH(t_loto_en_eth);
    }



	
}