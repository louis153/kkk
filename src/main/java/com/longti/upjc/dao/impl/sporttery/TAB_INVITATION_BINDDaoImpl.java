package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.TAB_INVITATION_BINDDao;
import com.longti.upjc.entity.sporttery.TAB_INVITATION_BIND;


/**
 * 绑定邀请码设置daoImpl
 */
@Repository
public class TAB_INVITATION_BINDDaoImpl extends BaseDaoImpl<TAB_INVITATION_BIND> implements TAB_INVITATION_BINDDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<TAB_INVITATION_BIND> selectTAB_INVITATION_BINDList(TAB_INVITATION_BIND tab_invitation_bind) throws Exception{   	   	
        return findAllByKey(tab_invitation_bind,"selectTAB_INVITATION_BINDList");
    }
    /**
     * 条件查询数量
     */
    public int selectTAB_INVITATION_BINDCount(TAB_INVITATION_BIND tab_invitation_bind) throws Exception{   	
        return getCountNum(tab_invitation_bind,"selectTAB_INVITATION_BINDCount");
    }
    /**
     * 添加
     */
    public int insertTAB_INVITATION_BIND(TAB_INVITATION_BIND tab_invitation_bind) throws Exception{
        return save("save",tab_invitation_bind);
    }
    /**
     * 批量添加
     */
    public int insertTAB_INVITATION_BIND(List<TAB_INVITATION_BIND> list) throws Exception{
        return save("saveList",list);
    }
    /**
     * 修改
     */
    public int updateTAB_INVITATION_BIND(TAB_INVITATION_BIND tab_invitation_bind) throws Exception{
        return update("update",tab_invitation_bind);
    }
    /**
     * 批量修改 
     */
    public int updateTAB_INVITATION_BIND(List<TAB_INVITATION_BIND> list) throws Exception{
        return update("updateList",list);
    }
    /**
     * 删除
     */
    public int deleteTAB_INVITATION_BIND(TAB_INVITATION_BIND tab_invitation_bind) throws Exception{
        return delete("delete",tab_invitation_bind);
    }


	
}