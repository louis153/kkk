package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.TAB_INVITATION_BINDDao;
import com.longti.upjc.entity.sporttery.TAB_INVITATION_BIND;
import com.longti.upjc.service.sporttery.TAB_INVITATION_BINDService;


/**
 * 绑定邀请码设置serviceImpl
 */
@Service
public class TAB_INVITATION_BINDServiceImpl implements TAB_INVITATION_BINDService  {
	@Autowired
	private TAB_INVITATION_BINDDao TAB_INVITATION_BINDDao;
   
	/**
	 * 条件查询
	 */
    public List<TAB_INVITATION_BIND> selectTAB_INVITATION_BINDList(TAB_INVITATION_BIND tab_invitation_bind) throws Exception{
    	return TAB_INVITATION_BINDDao.selectTAB_INVITATION_BINDList(tab_invitation_bind);
    }
    /**
     * 条件查询数量
     */
    public int selectTAB_INVITATION_BINDCount(TAB_INVITATION_BIND tab_invitation_bind) throws Exception{
    	return TAB_INVITATION_BINDDao.selectTAB_INVITATION_BINDCount(tab_invitation_bind);
    }
    /**
     * 添加
     */
    public int insertTAB_INVITATION_BIND(TAB_INVITATION_BIND tab_invitation_bind) throws Exception{
    	return TAB_INVITATION_BINDDao.insertTAB_INVITATION_BIND(tab_invitation_bind);
    }
    /**
     * 批量添加
     */
    public int insertTAB_INVITATION_BIND(List<TAB_INVITATION_BIND> list) throws Exception{
    	return TAB_INVITATION_BINDDao.insertTAB_INVITATION_BIND(list);
    }
    /**
     * 修改
     */
    public int updateTAB_INVITATION_BIND(TAB_INVITATION_BIND tab_invitation_bind) throws Exception{
    	return TAB_INVITATION_BINDDao.updateTAB_INVITATION_BIND(tab_invitation_bind);
    }
    /**
     * 批量修改
     */
    public int updateTAB_INVITATION_BIND(List<TAB_INVITATION_BIND> list) throws Exception{
    	return TAB_INVITATION_BINDDao.updateTAB_INVITATION_BIND(list);
    }
    /**
     * 删除
     */
    public int deleteTAB_INVITATION_BIND(TAB_INVITATION_BIND tab_invitation_bind) throws Exception{
    	return TAB_INVITATION_BINDDao.deleteTAB_INVITATION_BIND(tab_invitation_bind);
    }


	
}