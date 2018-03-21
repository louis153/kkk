package com.longti.upjc.service.sporttery;
import java.util.List;
import com.longti.upjc.entity.sporttery.T_USER;


/**
 * service
 */
public interface T_USERService {
	
   
    /**
	 * 条件查询
	 */
    public List<T_USER> selectT_USERList(T_USER t_user) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_USERCount(T_USER t_user) throws Exception;

    /**
     * 添加
     */
    public int insertT_USER(T_USER t_user) throws Exception;

    /**
     * 批量添加
     */
    public int insertT_USER(List<T_USER> list) throws Exception;

    /**
     * 修改
     */
    public int updateT_USER(T_USER t_user) throws Exception;

    /**
     * 修改
     */
    public int updateT_USER(List<T_USER> list) throws Exception;

    /**
     * 删除
     */
    public int deleteT_USER(T_USER t_user) throws Exception;


	
}