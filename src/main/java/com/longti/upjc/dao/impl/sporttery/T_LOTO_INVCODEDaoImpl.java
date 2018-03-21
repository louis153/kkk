package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.T_LOTO_INVCODEDao;
import com.longti.upjc.entity.sporttery.T_LOTO_INVCODE;


/**
 * 用户绑定邀请码信息daoImpl
 */
@Repository
public class T_LOTO_INVCODEDaoImpl extends BaseDaoImpl<T_LOTO_INVCODE> implements T_LOTO_INVCODEDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_INVCODE> selectT_LOTO_INVCODEList(T_LOTO_INVCODE t_loto_invcode) throws Exception{   	   	
        return findAllByKey(t_loto_invcode,"selectT_LOTO_INVCODEList");
    }
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_INVCODECount(T_LOTO_INVCODE t_loto_invcode) throws Exception{   	
        return getCountNum(t_loto_invcode,"selectT_LOTO_INVCODECount");
    }
    /**
     * 添加
     */
    public int insertT_LOTO_INVCODE(T_LOTO_INVCODE t_loto_invcode) throws Exception{
        return save("save",t_loto_invcode);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_INVCODE(List<T_LOTO_INVCODE> list) throws Exception{
        return save("saveList",list);
    }
    /**
     * 修改
     */
    public int updateT_LOTO_INVCODE(T_LOTO_INVCODE t_loto_invcode) throws Exception{
        return update("update",t_loto_invcode);
    }
    /**
     * 批量修改 
     */
    public int updateT_LOTO_INVCODE(List<T_LOTO_INVCODE> list) throws Exception{
        return update("updateList",list);
    }
    /**
     * 删除
     */
    public int deleteT_LOTO_INVCODE(T_LOTO_INVCODE t_loto_invcode) throws Exception{
        return delete("delete",t_loto_invcode);
    }


	
}