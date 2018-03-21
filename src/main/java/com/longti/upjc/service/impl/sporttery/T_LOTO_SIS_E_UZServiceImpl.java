package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.T_LOTO_SIS_E_UZDao;
import com.longti.upjc.entity.sporttery.T_LOTO_SIS_E_UZ;
import com.longti.upjc.service.sporttery.T_LOTO_SIS_E_UZService;


/**
 * UZ话题竞猜统计serviceImpl
 */
@Service
public class T_LOTO_SIS_E_UZServiceImpl implements T_LOTO_SIS_E_UZService  {
	@Autowired
	private T_LOTO_SIS_E_UZDao T_LOTO_SIS_E_UZDao;
   
	/**
	 * 条件查询
	 */
    public List<T_LOTO_SIS_E_UZ> selectT_LOTO_SIS_E_UZList(T_LOTO_SIS_E_UZ t_loto_sis_e_uz) throws Exception{
    	return T_LOTO_SIS_E_UZDao.selectT_LOTO_SIS_E_UZList(t_loto_sis_e_uz);
    }
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_SIS_E_UZCount(T_LOTO_SIS_E_UZ t_loto_sis_e_uz) throws Exception{
    	return T_LOTO_SIS_E_UZDao.selectT_LOTO_SIS_E_UZCount(t_loto_sis_e_uz);
    }
    /**
     * 添加
     */
    public int insertT_LOTO_SIS_E_UZ(T_LOTO_SIS_E_UZ t_loto_sis_e_uz) throws Exception{
    	return T_LOTO_SIS_E_UZDao.insertT_LOTO_SIS_E_UZ(t_loto_sis_e_uz);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_SIS_E_UZ(List<T_LOTO_SIS_E_UZ> list) throws Exception{
    	return T_LOTO_SIS_E_UZDao.insertT_LOTO_SIS_E_UZ(list);
    }
    /**
     * 修改
     */
    public int updateT_LOTO_SIS_E_UZ(T_LOTO_SIS_E_UZ t_loto_sis_e_uz) throws Exception{
    	return T_LOTO_SIS_E_UZDao.updateT_LOTO_SIS_E_UZ(t_loto_sis_e_uz);
    }
    /**
     * 批量修改
     */
    public int updateT_LOTO_SIS_E_UZ(List<T_LOTO_SIS_E_UZ> list) throws Exception{
    	return T_LOTO_SIS_E_UZDao.updateT_LOTO_SIS_E_UZ(list);
    }
    /**
     * 删除
     */
    public int deleteT_LOTO_SIS_E_UZ(T_LOTO_SIS_E_UZ t_loto_sis_e_uz) throws Exception{
    	return T_LOTO_SIS_E_UZDao.updateT_LOTO_SIS_E_UZ(t_loto_sis_e_uz);
    }



	
}