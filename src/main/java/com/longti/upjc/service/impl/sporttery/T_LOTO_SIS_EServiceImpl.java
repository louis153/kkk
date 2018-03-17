package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.T_LOTO_SIS_EDao;
import com.longti.upjc.entity.sporttery.T_LOTO_SIS_E;
import com.longti.upjc.service.sporttery.T_LOTO_SIS_EService;


/**
 * 电竞统计serviceImpl
 */
@Service
public class T_LOTO_SIS_EServiceImpl implements T_LOTO_SIS_EService  {
	@Autowired
	private T_LOTO_SIS_EDao t_loto_sis_eDao;
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_SIS_E> selectT_LOTO_SIS_EList(T_LOTO_SIS_E t_loto_sis_e) throws Exception{
        return t_loto_sis_eDao.selectT_LOTO_SIS_EList(t_loto_sis_e);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_SIS_ECount(T_LOTO_SIS_E t_loto_sis_e) throws Exception{
        return t_loto_sis_eDao.selectT_LOTO_SIS_ECount(t_loto_sis_e);
    }

    /**
     * 添加
     */
    public int insertT_LOTO_SIS_E(T_LOTO_SIS_E t_loto_sis_e) throws Exception{
        return t_loto_sis_eDao.insertT_LOTO_SIS_E(t_loto_sis_e);
    }

    /**
     * 批量添加
     */
    public int insertT_LOTO_SIS_E(List<T_LOTO_SIS_E> list) throws Exception{
        return t_loto_sis_eDao.insertT_LOTO_SIS_E(list);
    }

    /**
     * 修改
     */
    public int updateT_LOTO_SIS_E(T_LOTO_SIS_E t_loto_sis_e) throws Exception{
        return t_loto_sis_eDao.updateT_LOTO_SIS_E(t_loto_sis_e);
    }
    
    /**
     * 批量修改
     */
    public int updateT_LOTO_SIS_E(List<T_LOTO_SIS_E> list) throws Exception{
        return t_loto_sis_eDao.updateT_LOTO_SIS_E(list);
    }

    /**
     * 删除
     */
    public int deleteT_LOTO_SIS_E(T_LOTO_SIS_E t_loto_sis_e) throws Exception{
        return t_loto_sis_eDao.deleteT_LOTO_SIS_E(t_loto_sis_e);
    }


	
}