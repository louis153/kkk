package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.T_LOTO_INVCODEDao;
import com.longti.upjc.entity.sporttery.T_LOTO_INVCODE;
import com.longti.upjc.service.sporttery.T_LOTO_INVCODEService;


/**
 * 用户绑定邀请码信息serviceImpl
 */
@Service
public class T_LOTO_INVCODEServiceImpl implements T_LOTO_INVCODEService  {
	@Autowired
	private T_LOTO_INVCODEDao T_LOTO_INVCODEDao;
   
	/**
	 * 条件查询
	 */
    public List<T_LOTO_INVCODE> selectT_LOTO_INVCODEList(T_LOTO_INVCODE t_loto_invcode) throws Exception{
    	return T_LOTO_INVCODEDao.selectT_LOTO_INVCODEList(t_loto_invcode);
    }
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_INVCODECount(T_LOTO_INVCODE t_loto_invcode) throws Exception{
    	return T_LOTO_INVCODEDao.selectT_LOTO_INVCODECount(t_loto_invcode);
    }
    /**
     * 添加
     */
    public int insertT_LOTO_INVCODE(T_LOTO_INVCODE t_loto_invcode) throws Exception{
    	return T_LOTO_INVCODEDao.insertT_LOTO_INVCODE(t_loto_invcode);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_INVCODE(List<T_LOTO_INVCODE> list) throws Exception{
    	return T_LOTO_INVCODEDao.insertT_LOTO_INVCODE(list);
    }
    /**
     * 修改
     */
    public int updateT_LOTO_INVCODE(T_LOTO_INVCODE t_loto_invcode) throws Exception{
    	return T_LOTO_INVCODEDao.updateT_LOTO_INVCODE(t_loto_invcode);
    }
    /**
     * 批量修改
     */
    public int updateT_LOTO_INVCODE(List<T_LOTO_INVCODE> list) throws Exception{
    	return T_LOTO_INVCODEDao.updateT_LOTO_INVCODE(list);
    }
    /**
     * 删除
     */
    public int deleteT_LOTO_INVCODE(T_LOTO_INVCODE t_loto_invcode) throws Exception{
    	return T_LOTO_INVCODEDao.deleteT_LOTO_INVCODE(t_loto_invcode);
    }


	
}