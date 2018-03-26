package com.longti.upjc.dao.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.T_INVITATION_AWARD;



/**
 * 邀请码奖励dao
 */
public interface T_INVITATION_AWARDDao {
	
	
	/**
	 * 统计查询
	 */
	public List<T_INVITATION_AWARD> sumT_INVITATION_AWARD(T_INVITATION_AWARD t_invitation_award) throws Exception;
    /**
	 * 条件查询
	 */
    public List<T_INVITATION_AWARD> selectT_INVITATION_AWARDList(T_INVITATION_AWARD t_invitation_award) throws Exception;
    /**
     * 条件查询数量
     */
    public int selectT_INVITATION_AWARDCount(T_INVITATION_AWARD t_invitation_award) throws Exception;
    /**
     * 添加
     */
    public int insertT_INVITATION_AWARD(T_INVITATION_AWARD t_invitation_award) throws Exception;
    /**
     * 批量添加s
     */
    public int insertT_INVITATION_AWARD(List<T_INVITATION_AWARD> list) throws Exception;
    /**
     * 修改
     */
    public int updateT_INVITATION_AWARD(T_INVITATION_AWARD t_invitation_award) throws Exception;
    /**
     * 批量修改
     */
    public int updateT_INVITATION_AWARD(List<T_INVITATION_AWARD> list) throws Exception;
    /**
     * 删除
     */
    public int deleteT_INVITATION_AWARD(T_INVITATION_AWARD t_invitation_award) throws Exception;
    
    


	
}