package com.longti.upjc.service.impl.sporttery;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.T_INVITATION_AWARDDao;
import com.longti.upjc.entity.sporttery.T_INVITATION_AWARD;
import com.longti.upjc.service.sporttery.T_INVITATION_AWARDService;


/**
 * 绑定奖励serviceImpl
 */
@Service
public class T_INVITATION_AWARDServiceImpl implements T_INVITATION_AWARDService  {
	@Autowired
	private T_INVITATION_AWARDDao t_invitation_awardDDao;
	
	/**
	 * 统计查询
	 */
	public List<T_INVITATION_AWARD> sumT_INVITATION_AWARD(T_INVITATION_AWARD t_invitation_award) throws Exception{
    	return t_invitation_awardDDao.sumT_INVITATION_AWARD(t_invitation_award);
    }
	/**
	 * 条件查询
	 */
    public List<T_INVITATION_AWARD> selectT_INVITATION_AWARDList(T_INVITATION_AWARD t_invitation_award) throws Exception{
    	return t_invitation_awardDDao.selectT_INVITATION_AWARDList(t_invitation_award);
    }
    /**
     * 条件查询数量
     */
    public int selectT_INVITATION_AWARDCount(T_INVITATION_AWARD t_invitation_award) throws Exception{
    	return t_invitation_awardDDao.selectT_INVITATION_AWARDCount(t_invitation_award);
    }
    /**
     * 添加
     */
    public int insertT_INVITATION_AWARD(T_INVITATION_AWARD t_invitation_award) throws Exception{
    	return t_invitation_awardDDao.insertT_INVITATION_AWARD(t_invitation_award);
    }
    /**
     * 批量添加
     */
    public int insertT_INVITATION_AWARD(List<T_INVITATION_AWARD> list) throws Exception{
    	return t_invitation_awardDDao.insertT_INVITATION_AWARD(list);
    }
    /**
     * 修改
     */
    public int updateT_INVITATION_AWARD(T_INVITATION_AWARD t_invitation_award,String gto) throws Exception{
    	return t_invitation_awardDDao.updateT_INVITATION_AWARD(t_invitation_award);
    }
    /**
     * 批量修改
     */
    public int updateT_INVITATION_AWARD(List<T_INVITATION_AWARD> list) throws Exception{
    	return t_invitation_awardDDao.updateT_INVITATION_AWARD(list);
    }
    /**
     * 删除
     */
    public int deleteT_INVITATION_AWARD(T_INVITATION_AWARD t_invitation_award) throws Exception{
    	return t_invitation_awardDDao.deleteT_INVITATION_AWARD(t_invitation_award);
    }


	
}