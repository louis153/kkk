package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.longti.upjc.entity.sporttery.T_LOTO_E;
import com.longti.upjc.dao.sporttery.T_LOTO_EDao;
import com.longti.upjc.service.sporttery.T_LOTO_EService;


/**
 * 话题信息serviceImpl
 */
@Service
public class T_LOTO_EServiceImpl implements T_LOTO_EService  {
	@Autowired
	private T_LOTO_EDao t_loto_eDao;
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_E> selectT_LOTO_EList(T_LOTO_E t_loto_e) throws Exception{
        return t_loto_eDao.selectT_LOTO_EList(t_loto_e);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_ECount(T_LOTO_E t_loto_e) throws Exception{
        return t_loto_eDao.selectT_LOTO_ECount(t_loto_e);
    }

    /**
     * 添加
     */
    public int insertT_LOTO_E(T_LOTO_E t_loto_e) throws Exception{
        return t_loto_eDao.insertT_LOTO_E(t_loto_e);
    }

    /**
     * 批量添加
     */
    public int insertT_LOTO_E(List<T_LOTO_E> list) throws Exception{
        return t_loto_eDao.insertT_LOTO_E(list);
    }

    /**
     * 修改
     */
    public int updateT_LOTO_E(T_LOTO_E t_loto_e) throws Exception{
        return t_loto_eDao.updateT_LOTO_E(t_loto_e);
    }
    
    /**
     * 批量修改
     */
    public int updateT_LOTO_E(List<T_LOTO_E> list) throws Exception{
        return t_loto_eDao.updateT_LOTO_E(list);
    }

    /**
     * 删除
     */
    public int deleteT_LOTO_E(T_LOTO_E t_loto_e) throws Exception{
        return t_loto_eDao.deleteT_LOTO_E(t_loto_e);
    }


	
}