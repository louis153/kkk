package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.T_LOTO_SIS_E_GTODao;
import com.longti.upjc.entity.sporttery.T_LOTO_SIS_E_GTO;
import com.longti.upjc.service.sporttery.T_LOTO_SIS_E_GTOService;


/**
 *GTO话题竞猜统计serviceImpl
 */
@Service
public class T_LOTO_SIS_E_GTOServiceImpl implements T_LOTO_SIS_E_GTOService  {
	@Autowired
	private T_LOTO_SIS_E_GTODao T_LOTO_SIS_E_GTODao;
   
	/**
	 * 条件查询
	 */
    public List<T_LOTO_SIS_E_GTO> selectT_LOTO_SIS_E_GTOList(T_LOTO_SIS_E_GTO t_loto_sis_e_gto) throws Exception{
    	return T_LOTO_SIS_E_GTODao.selectT_LOTO_SIS_E_GTOList(t_loto_sis_e_gto);
    }
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_SIS_E_GTOCount(T_LOTO_SIS_E_GTO t_loto_sis_e_gto) throws Exception{
    	return T_LOTO_SIS_E_GTODao.selectT_LOTO_SIS_E_GTOCount(t_loto_sis_e_gto);
    }
    /**
     * 添加
     */
    public int insertT_LOTO_SIS_E_GTO(T_LOTO_SIS_E_GTO t_loto_sis_e_gto) throws Exception{
    	return T_LOTO_SIS_E_GTODao.insertT_LOTO_SIS_E_GTO(t_loto_sis_e_gto);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_SIS_E_GTO(List<T_LOTO_SIS_E_GTO> list) throws Exception{
    	return T_LOTO_SIS_E_GTODao.insertT_LOTO_SIS_E_GTO(list);
    }
    /**
     * 修改
     */
    public int updateT_LOTO_SIS_E_GTO(T_LOTO_SIS_E_GTO t_loto_sis_e_gto) throws Exception{
    	return T_LOTO_SIS_E_GTODao.updateT_LOTO_SIS_E_GTO(t_loto_sis_e_gto);
    }
    /**
     * 批量修改
     */
    public int updateT_LOTO_SIS_E_GTO(List<T_LOTO_SIS_E_GTO> list) throws Exception{
    	return T_LOTO_SIS_E_GTODao.updateT_LOTO_SIS_E_GTO(list);
    }
    /**
     * 删除
     */
    public int deleteT_LOTO_SIS_E_GTO(T_LOTO_SIS_E_GTO t_loto_sis_e_gto) throws Exception{
    	return T_LOTO_SIS_E_GTODao.updateT_LOTO_SIS_E_GTO(t_loto_sis_e_gto);
    }



	
}