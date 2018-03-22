package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.T_USER_INVCODEDao;
import com.longti.upjc.entity.sporttery.T_USER_INVCODE;


/**
 * 用户绑定邀请码信息daoImpl
 */
@Repository
public class T_USER_INVCODEDaoImpl extends BaseDaoImpl<T_USER_INVCODE> implements T_USER_INVCODEDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_USER_INVCODE> selectT_USER_INVCODEList(T_USER_INVCODE t_user_invcode) throws Exception{   	   	
        return findAllByKey(t_user_invcode,"selectT_USER_INVCODEList");
    }
    /**
     * 条件查询数量
     */
    public int selectT_USER_INVCODECount(T_USER_INVCODE t_user_invcode) throws Exception{   	
        return getCountNum(t_user_invcode,"selectT_USER_INVCODECount");
    }
    /**
     * 添加
     */
    public int insertT_USER_INVCODE(T_USER_INVCODE t_user_invcode) throws Exception{
        return save("save",t_user_invcode);
    }
    /**
     * 批量添加
     */
    public int insertT_USER_INVCODE(List<T_USER_INVCODE> list) throws Exception{
        return save("saveList",list);
    }
    /**
     * 修改
     */
    public int updateT_USER_INVCODE(T_USER_INVCODE t_user_invcode) throws Exception{
        return update("update",t_user_invcode);
    }
    /**
     * 批量修改 
     */
    public int updateT_USER_INVCODE(List<T_USER_INVCODE> list) throws Exception{
        return update("updateList",list);
    }
    /**
     * 删除
     */
    public int deleteT_USER_INVCODE(T_USER_INVCODE t_user_invcode) throws Exception{
        return delete("delete",t_user_invcode);
    }


	
}