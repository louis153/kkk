package com.longti.upjc.dao.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.T_LOTO_INVCODE;


/**
 * 用户绑定邀请码信息dao
 */
public interface T_LOTO_INVCODEDao {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_INVCODE> selectT_LOTO_INVCODEList(T_LOTO_INVCODE t_loto_invcode) throws Exception;
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_INVCODECount(T_LOTO_INVCODE t_loto_invcode) throws Exception;
    /**
     * 添加
     */
    public int insertT_LOTO_INVCODE(T_LOTO_INVCODE t_loto_invcode) throws Exception;
    /**
     * 批量添加s
     */
    public int insertT_LOTO_INVCODE(List<T_LOTO_INVCODE> list) throws Exception;
    /**
     * 修改
     */
    public int updateT_LOTO_INVCODE(T_LOTO_INVCODE t_loto_invcode) throws Exception;
    /**
     * 批量修改
     */
    public int updateT_LOTO_INVCODE(List<T_LOTO_INVCODE> list) throws Exception;
    /**
     * 删除
     */
    public int deleteT_LOTO_INVCODE(T_LOTO_INVCODE t_loto_invcode) throws Exception;
    
    


	
}