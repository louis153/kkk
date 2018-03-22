package com.longti.upjc.service.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.T_USER_INVCODE;


/**
 * 用户绑定邀请码信息service
 */
public interface T_USER_INVCODEService {
	
   
	/**
	 * 条件查询
	 */
    public List<T_USER_INVCODE> selectT_USER_INVCODEList(T_USER_INVCODE t_user_invcode) throws Exception;
    /**
     * 条件查询数量
     */
    public int selectT_USER_INVCODECount(T_USER_INVCODE t_user_invcode) throws Exception;
    /**
     * 添加
     */
    public int insertT_USER_INVCODE(T_USER_INVCODE t_user_invcode) throws Exception;
    /**
     * 批量添加
     */
    public int insertT_USER_INVCODE(List<T_USER_INVCODE> list) throws Exception;
    /**
     * 修改
     */
    public int updateT_USER_INVCODE(T_USER_INVCODE t_user_invcode) throws Exception;
    /**
     * 批量修改
     */
    public int updateT_USER_INVCODE(List<T_USER_INVCODE> list) throws Exception;
    /**
     * 删除
     */
    public int deleteT_USER_INVCODE(T_USER_INVCODE t_user_invcode) throws Exception;


	
}