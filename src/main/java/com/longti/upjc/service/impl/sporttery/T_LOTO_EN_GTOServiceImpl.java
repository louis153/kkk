package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.T_LOTO_EN_GTODao;
import com.longti.upjc.entity.sporttery.T_LOTO_EN_GTO;
import com.longti.upjc.service.sporttery.T_LOTO_EN_GTOService;


/**
 * 进行中GTO话题竞猜信息serviceImpl
 */
@Service
public class T_LOTO_EN_GTOServiceImpl implements T_LOTO_EN_GTOService  {
	@Autowired
	private T_LOTO_EN_GTODao T_LOTO_EN_GTODao;
   
	/**
	 * 条件查询
	 */
    public List<T_LOTO_EN_GTO> selectT_LOTO_EN_GTOList(T_LOTO_EN_GTO t_loto_en_gto) throws Exception{
    	return T_LOTO_EN_GTODao.selectT_LOTO_EN_GTOList(t_loto_en_gto);
    }
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_EN_GTOCount(T_LOTO_EN_GTO t_loto_en_gto) throws Exception{
    	return T_LOTO_EN_GTODao.selectT_LOTO_EN_GTOCount(t_loto_en_gto);
    }
    /**
     * 添加
     */
    public int insertT_LOTO_EN_GTO(T_LOTO_EN_GTO t_loto_en_gto) throws Exception{
    	return T_LOTO_EN_GTODao.insertT_LOTO_EN_GTO(t_loto_en_gto);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_EN_GTO(List<T_LOTO_EN_GTO> list) throws Exception{
    	return T_LOTO_EN_GTODao.insertT_LOTO_EN_GTO(list);
    }
    /**
     * 修改
     */
    public int updateT_LOTO_EN_GTO(T_LOTO_EN_GTO t_loto_en_gto) throws Exception{
    	return T_LOTO_EN_GTODao.updateT_LOTO_EN_GTO(t_loto_en_gto);
    }
    /**
     * 批量修改
     */
    public int updateT_LOTO_EN_GTO(List<T_LOTO_EN_GTO> list) throws Exception{
    	return T_LOTO_EN_GTODao.updateT_LOTO_EN_GTO(list);
    }
    /**
     * 删除
     */
    public int deleteT_LOTO_EN_GTO(T_LOTO_EN_GTO t_loto_en_gto) throws Exception{
    	return T_LOTO_EN_GTODao.updateT_LOTO_EN_GTO(t_loto_en_gto);
    }



	
}