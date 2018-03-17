package com.longti.upjc.service.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.T_CONTROL;


/**
 * 阀值管理service
 */
public interface T_CONTROLService {
	
   
    /**
	 * 条件查询
	 */
    public List<T_CONTROL> selectT_CONTROLList(T_CONTROL t_control) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectT_CONTROLCount(T_CONTROL t_control) throws Exception;

    /**
     * 添加
     */
    public int insertT_CONTROL(T_CONTROL t_control) throws Exception;

    /**
     * 批量添加
     */
    public int insertT_CONTROL(List<T_CONTROL> list) throws Exception;

    /**
     * 修改
     */
    public int updateT_CONTROL(T_CONTROL t_control) throws Exception;

    /**
     * 修改
     */
    public int updateT_CONTROL(List<T_CONTROL> list) throws Exception;

    /**
     * 删除
     */
    public int deleteT_CONTROL(T_CONTROL t_control) throws Exception;


	
}