package com.longti.upjc.dao.impl.sporttery;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.T_INVITATION_AWARDDao;
import com.longti.upjc.entity.sporttery.T_INVITATION_AWARD;


/**
 * 邀请奖励daoImpl
 */
@Repository
public class T_INVITATION_AWARDDaoImpl extends BaseDaoImpl<T_INVITATION_AWARD> implements T_INVITATION_AWARDDao  {
	
	/**
	 * 统计查询
	 */
    public List<T_INVITATION_AWARD> sumT_INVITATION_AWARD(T_INVITATION_AWARD t_invitation_award) throws Exception{
    	return findAllByKey(t_invitation_award,"sumT_INVITATION_AWARD");
    }
    /**
	 * 条件查询
	 */
    public List<T_INVITATION_AWARD> selectT_INVITATION_AWARDList(T_INVITATION_AWARD t_invitation_award) throws Exception{   	   	
        return findAllByKey(t_invitation_award,"selectT_INVITATION_AWARDList");
    }
    /**
     * 条件查询数量
     */
    public int selectT_INVITATION_AWARDCount(T_INVITATION_AWARD t_invitation_award) throws Exception{   	
        return getCountNum(t_invitation_award,"selectT_INVITATION_AWARDCount");
    }
    /**
     * 添加
     */
    public int insertT_INVITATION_AWARD(T_INVITATION_AWARD t_invitation_award) throws Exception{
        return save("save",t_invitation_award);
    }
    /**
     * 批量添加
     */
    public int insertT_INVITATION_AWARD(List<T_INVITATION_AWARD> list) throws Exception{
        return save("saveList",list);
    }
    /**
     * 修改
     */
    public int updateT_INVITATION_AWARD(T_INVITATION_AWARD t_invitation_award) throws Exception{
        return update("update",t_invitation_award);
    }
    /**
     * 批量修改 
     */
    public int updateT_INVITATION_AWARD(List<T_INVITATION_AWARD> list) throws Exception{
        return update("updateList",list);
    }
    /**
     * 删除
     */
    public int deleteT_INVITATION_AWARD(T_INVITATION_AWARD t_invitation_award) throws Exception{
        return delete("delete",t_invitation_award);
    }


	
}