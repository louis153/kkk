package com.longti.upjc.service.impl.sporttery;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.T_USERDao;
import com.longti.upjc.dao.sporttery.T_USER_INVCODEDao;
import com.longti.upjc.entity.sporttery.T_USER;
import com.longti.upjc.entity.sporttery.T_USER_INVCODE;
import com.longti.upjc.service.sporttery.T_USER_INVCODEService;


/**
 * 用户绑定邀请码信息serviceImpl
 */
@Service
public class T_USER_INVCODEServiceImpl implements T_USER_INVCODEService  {
	@Autowired
	private T_USER_INVCODEDao T_USER_INVCODEDao;
	@Autowired
	private T_USERDao t_userDao;
	
	/**
	 * 条件查询
	 */
    public List<T_USER_INVCODE> selectT_USER_INVCODEList(T_USER_INVCODE t_user_invcode) throws Exception{
    	return T_USER_INVCODEDao.selectT_USER_INVCODEList(t_user_invcode);
    }
    /**
     * 条件查询数量
     */
    public int selectT_USER_INVCODECount(T_USER_INVCODE t_user_invcode) throws Exception{
    	return T_USER_INVCODEDao.selectT_USER_INVCODECount(t_user_invcode);
    }
    /**
     * 添加
     */
    public int insertT_USER_INVCODE(T_USER_INVCODE t_user_invcode) throws Exception{
    	return T_USER_INVCODEDao.insertT_USER_INVCODE(t_user_invcode);
    }
    /**
     * 批量添加
     */
    public int insertT_USER_INVCODE(List<T_USER_INVCODE> list) throws Exception{
    	return T_USER_INVCODEDao.insertT_USER_INVCODE(list);
    }
    /**
     * 修改
     */
    public int updateT_USER_INVCODE(T_USER_INVCODE t_user_invcode,String gto) throws Exception{
    	T_USER t_user = new T_USER();
		t_user.setUser_pin(t_user_invcode.getUser_pin());
		t_user = t_userDao.selectT_USERList(t_user).get(0);
		t_user.setAward_gto(new BigDecimal(gto));
		t_userDao.updateT_USER(t_user);//更新奖励GTO
    	return T_USER_INVCODEDao.updateT_USER_INVCODE(t_user_invcode);
    }
    /**
     * 批量修改
     */
    public int updateT_USER_INVCODE(List<T_USER_INVCODE> list) throws Exception{
    	return T_USER_INVCODEDao.updateT_USER_INVCODE(list);
    }
    /**
     * 删除
     */
    public int deleteT_USER_INVCODE(T_USER_INVCODE t_user_invcode) throws Exception{
    	return T_USER_INVCODEDao.deleteT_USER_INVCODE(t_user_invcode);
    }


	
}