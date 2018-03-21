package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.longti.upjc.entity.sporttery.T_USER;
import com.longti.upjc.dao.sporttery.T_USERDao;
import com.longti.upjc.service.sporttery.T_USERService;


/**
 * serviceImpl
 */
@Service
public class T_USERServiceImpl implements T_USERService  {
	@Autowired
	private T_USERDao t_userDao;
   
    /**
	 * 条件查询
	 */
    public List<T_USER> selectT_USERList(T_USER t_user) throws Exception{
        return t_userDao.selectT_USERList(t_user);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_USERCount(T_USER t_user) throws Exception{
        return t_userDao.selectT_USERCount(t_user);
    }

    /**
     * 添加
     */
    public int insertT_USER(T_USER t_user) throws Exception{
        return t_userDao.insertT_USER(t_user);
    }

    /**
     * 批量添加
     */
    public int insertT_USER(List<T_USER> list) throws Exception{
        return t_userDao.insertT_USER(list);
    }

    /**
     * 修改
     */
    public int updateT_USER(T_USER t_user) throws Exception{
        return t_userDao.updateT_USER(t_user);
    }
    
    /**
     * 批量修改
     */
    public int updateT_USER(List<T_USER> list) throws Exception{
        return t_userDao.updateT_USER(list);
    }

    /**
     * 删除
     */
    public int deleteT_USER(T_USER t_user) throws Exception{
        return t_userDao.deleteT_USER(t_user);
    }


	
}