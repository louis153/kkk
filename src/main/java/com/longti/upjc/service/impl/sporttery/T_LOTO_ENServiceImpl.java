package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.dao.sporttery.T_LOTO_ENDao;
import com.longti.upjc.entity.sporttery.T_LOTO_E;
import com.longti.upjc.service.sporttery.T_LOTO_ENService;


/**
 * 在售的比赛, 比赛开始后删除此表中的数据serviceImpl
 */
@Service
public class T_LOTO_ENServiceImpl implements T_LOTO_ENService  {
	@Autowired
	private T_LOTO_ENDao t_loto_enDao;
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_E> selectT_LOTO_ENList(T_LOTO_E t_loto_en) throws Exception{
        return t_loto_enDao.selectT_LOTO_ENList(t_loto_en);
    }
    public T_LOTO_E selectRemEN() throws Exception{
        return t_loto_enDao.selectRemEN();
    }
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_ENCount(T_LOTO_E t_loto_en) throws Exception{
        return t_loto_enDao.selectT_LOTO_ENCount(t_loto_en);
    }

    /**
     * 添加
     */
    public int insertT_LOTO_EN(T_LOTO_E t_loto_en) throws Exception{
        return t_loto_enDao.insertT_LOTO_EN(t_loto_en);
    }

    /**
     * 批量添加
     */
    public int insertT_LOTO_EN(List<T_LOTO_E> list) throws Exception{
        return t_loto_enDao.insertT_LOTO_EN(list);
    }

    /**
     * 修改
     */
    public int updateT_LOTO_EN(T_LOTO_E t_loto_en) throws Exception{
        return t_loto_enDao.updateT_LOTO_EN(t_loto_en);
    }
    
    /**
     * 批量修改
     */
    public int updateT_LOTO_EN(List<T_LOTO_E> list) throws Exception{
        return t_loto_enDao.updateT_LOTO_EN(list);
    }

    /**
     * 删除
     */
    public int deleteT_LOTO_EN(T_LOTO_E t_loto_en) throws Exception{
        return t_loto_enDao.deleteT_LOTO_EN(t_loto_en);
    }


	
}