package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.TAB_CHIPDao;
import com.longti.upjc.entity.sporttery.TAB_CHIP;
import com.longti.upjc.service.sporttery.TAB_CHIPService;


/**
 * 投注底注信息serviceImpl
 */
@Service
public class TAB_CHIPServiceImpl implements TAB_CHIPService  {
	@Autowired
	private TAB_CHIPDao TAB_CHIPDao;
   
	/**
	 * 条件查询
	 */
    public List<TAB_CHIP> selectTAB_CHIPList(TAB_CHIP tab_chip) throws Exception{
    	return TAB_CHIPDao.selectTAB_CHIPList(tab_chip);
    }
    /**
     * 条件查询数量
     */
    public int selectTAB_CHIPCount(TAB_CHIP tab_chip) throws Exception{
    	return TAB_CHIPDao.selectTAB_CHIPCount(tab_chip);
    }
    /**
     * 添加
     */
    public int insertTAB_CHIP(TAB_CHIP tab_chip) throws Exception{
    	return TAB_CHIPDao.insertTAB_CHIP(tab_chip);
    }
    /**
     * 批量添加
     */
    public int insertTAB_CHIP(List<TAB_CHIP> list) throws Exception{
    	return TAB_CHIPDao.insertTAB_CHIP(list);
    }
    /**
     * 修改
     */
    public int updateTAB_CHIP(TAB_CHIP tab_chip) throws Exception{
    	return TAB_CHIPDao.updateTAB_CHIP(tab_chip);
    }
    /**
     * 批量修改
     */
    public int updateTAB_CHIP(List<TAB_CHIP> list) throws Exception{
    	return TAB_CHIPDao.updateTAB_CHIP(list);
    }
    /**
     * 删除
     */
    public int deleteTAB_CHIP(TAB_CHIP tab_chip) throws Exception{
    	return TAB_CHIPDao.deleteTAB_CHIP(tab_chip);
    }


	
}