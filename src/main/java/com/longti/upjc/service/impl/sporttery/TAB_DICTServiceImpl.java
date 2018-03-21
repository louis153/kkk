package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.TAB_DICTDao;
import com.longti.upjc.entity.sporttery.TAB_DICT;
import com.longti.upjc.service.sporttery.TAB_DICTService;


/**
 * 奖励机制信息serviceImpl
 */
@Service
public class TAB_DICTServiceImpl implements TAB_DICTService  {
	@Autowired
	private TAB_DICTDao TAB_DICTDao;
   
	/**
	 * 条件查询
	 */
    public List<TAB_DICT> selectTAB_DICTList(TAB_DICT tab_dict) throws Exception{
    	return TAB_DICTDao.selectTAB_DICTList(tab_dict);
    }
    /**
     * 条件查询数量
     */
    public int selectTAB_DICTCount(TAB_DICT tab_dict) throws Exception{
    	return TAB_DICTDao.selectTAB_DICTCount(tab_dict);
    }
    /**
     * 添加
     */
    public int insertTAB_DICT(TAB_DICT tab_dict) throws Exception{
    	return TAB_DICTDao.insertTAB_DICT(tab_dict);
    }
    /**
     * 批量添加
     */
    public int insertTAB_DICT(List<TAB_DICT> list) throws Exception{
    	return TAB_DICTDao.insertTAB_DICT(list);
    }
    /**
     * 修改
     */
    public int updateTAB_DICT(TAB_DICT tab_dict) throws Exception{
    	return TAB_DICTDao.updateTAB_DICT(tab_dict);
    }
    /**
     * 批量修改
     */
    public int updateTAB_DICT(List<TAB_DICT> list) throws Exception{
    	return TAB_DICTDao.updateTAB_DICT(list);
    }
    /**
     * 删除
     */
    public int deleteTAB_DICT(TAB_DICT tab_dict) throws Exception{
    	return TAB_DICTDao.deleteTAB_DICT(tab_dict);
    }


	
}