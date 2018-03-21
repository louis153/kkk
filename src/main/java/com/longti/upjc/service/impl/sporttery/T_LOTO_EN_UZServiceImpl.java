package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.T_LOTO_EN_UZDao;
import com.longti.upjc.entity.sporttery.T_LOTO_EN_UZ;
import com.longti.upjc.service.sporttery.T_LOTO_EN_UZService;


/**
 * UZ话题竞猜信息serviceImpl
 */
@Service
public class T_LOTO_EN_UZServiceImpl implements T_LOTO_EN_UZService  {
	@Autowired
	private T_LOTO_EN_UZDao T_LOTO_EN_UZDao;
   
	/**
	 * 条件查询
	 */
    public List<T_LOTO_EN_UZ> selectT_LOTO_EN_UZList(T_LOTO_EN_UZ t_loto_en_uz) throws Exception{
    	return T_LOTO_EN_UZDao.selectT_LOTO_EN_UZList(t_loto_en_uz);
    }
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_EN_UZCount(T_LOTO_EN_UZ t_loto_en_uz) throws Exception{
    	return T_LOTO_EN_UZDao.selectT_LOTO_EN_UZCount(t_loto_en_uz);
    }
    /**
     * 添加
     */
    public int insertT_LOTO_EN_UZ(T_LOTO_EN_UZ t_loto_en_uz) throws Exception{
    	return T_LOTO_EN_UZDao.insertT_LOTO_EN_UZ(t_loto_en_uz);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_EN_UZ(List<T_LOTO_EN_UZ> list) throws Exception{
    	return T_LOTO_EN_UZDao.insertT_LOTO_EN_UZ(list);
    }
    /**
     * 修改
     */
    public int updateT_LOTO_EN_UZ(T_LOTO_EN_UZ t_loto_en_uz) throws Exception{
    	return T_LOTO_EN_UZDao.updateT_LOTO_EN_UZ(t_loto_en_uz);
    }
    /**
     * 批量修改
     */
    public int updateT_LOTO_EN_UZ(List<T_LOTO_EN_UZ> list) throws Exception{
    	return T_LOTO_EN_UZDao.updateT_LOTO_EN_UZ(list);
    }
    /**
     * 删除
     */
    public int deleteT_LOTO_EN_UZ(T_LOTO_EN_UZ t_loto_en_uz) throws Exception{
    	return T_LOTO_EN_UZDao.deleteT_LOTO_EN_UZ(t_loto_en_uz);
    }


	
}