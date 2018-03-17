package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.T_LOTO_SIS_FDao;
import com.longti.upjc.entity.sporttery.T_LOTO_SIS_F;
import com.longti.upjc.service.sporttery.T_LOTO_SIS_FService;


/**
 * 足球统计serviceImpl
 */
@Service
public class T_LOTO_SIS_FServiceImpl implements T_LOTO_SIS_FService  {
	@Autowired
	private T_LOTO_SIS_FDao t_loto_sis_fDao;
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_SIS_F> selectT_LOTO_SIS_FList(T_LOTO_SIS_F t_loto_sis_f) throws Exception{
        return t_loto_sis_fDao.selectT_LOTO_SIS_FList(t_loto_sis_f);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_SIS_FCount(T_LOTO_SIS_F t_loto_sis_f) throws Exception{
        return t_loto_sis_fDao.selectT_LOTO_SIS_FCount(t_loto_sis_f);
    }

    /**
     * 添加
     */
    public int insertT_LOTO_SIS_F(T_LOTO_SIS_F t_loto_sis_f) throws Exception{
        return t_loto_sis_fDao.insertT_LOTO_SIS_F(t_loto_sis_f);
    }
    
    /**
     * 按统计方式添加
     */
    public int saveSisT_LOTO_SIS_F(T_LOTO_SIS_F t_loto_sis_f) throws Exception{
    	return t_loto_sis_fDao.saveSisT_LOTO_SIS_F(t_loto_sis_f);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_SIS_F(List<T_LOTO_SIS_F> list) throws Exception{
        return t_loto_sis_fDao.insertT_LOTO_SIS_F(list);
    }

    /**
     * 修改
     */
    public int updateT_LOTO_SIS_F(T_LOTO_SIS_F t_loto_sis_f) throws Exception{
        return t_loto_sis_fDao.updateT_LOTO_SIS_F(t_loto_sis_f);
    }
    
    /**
     * 批量修改
     */
    public int updateT_LOTO_SIS_F(List<T_LOTO_SIS_F> list) throws Exception{
        return t_loto_sis_fDao.updateT_LOTO_SIS_F(list);
    }

    /**
     * 删除
     */
    public int deleteT_LOTO_SIS_F(T_LOTO_SIS_F t_loto_sis_f) throws Exception{
        return t_loto_sis_fDao.deleteT_LOTO_SIS_F(t_loto_sis_f);
    }


	
}