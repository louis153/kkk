package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B;
import com.tengcai.vims.dao.sporttery.T_LOTO_SIS_BDao;
import com.tengcai.vims.service.sporttery.T_LOTO_SIS_BService;


/**
 * 蓝球统计serviceImpl
 */
@Service
public class T_LOTO_SIS_BServiceImpl implements T_LOTO_SIS_BService  {
	@Autowired
	private T_LOTO_SIS_BDao t_loto_sis_bDao;
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_SIS_B> selectT_LOTO_SIS_BList(T_LOTO_SIS_B t_loto_sis_b) throws Exception{
        return t_loto_sis_bDao.selectT_LOTO_SIS_BList(t_loto_sis_b);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_SIS_BCount(T_LOTO_SIS_B t_loto_sis_b) throws Exception{
        return t_loto_sis_bDao.selectT_LOTO_SIS_BCount(t_loto_sis_b);
    }

    /**
     * 添加
     */
    public int insertT_LOTO_SIS_B(T_LOTO_SIS_B t_loto_sis_b) throws Exception{
        return t_loto_sis_bDao.insertT_LOTO_SIS_B(t_loto_sis_b);
    }

    /**
     * 批量添加
     */
    public int insertT_LOTO_SIS_B(List<T_LOTO_SIS_B> list) throws Exception{
        return t_loto_sis_bDao.insertT_LOTO_SIS_B(list);
    }

    /**
     * 修改
     */
    public int updateT_LOTO_SIS_B(T_LOTO_SIS_B t_loto_sis_b) throws Exception{
        return t_loto_sis_bDao.updateT_LOTO_SIS_B(t_loto_sis_b);
    }
    
    /**
     * 批量修改
     */
    public int updateT_LOTO_SIS_B(List<T_LOTO_SIS_B> list) throws Exception{
        return t_loto_sis_bDao.updateT_LOTO_SIS_B(list);
    }

    /**
     * 删除
     */
    public int deleteT_LOTO_SIS_B(T_LOTO_SIS_B t_loto_sis_b) throws Exception{
        return t_loto_sis_bDao.deleteT_LOTO_SIS_B(t_loto_sis_b);
    }

	@Override
	public int saveSis(T_LOTO_SIS_B t_loto_sis_b) throws Exception {
		return t_loto_sis_bDao.saveSis(t_loto_sis_b);
	}


	
}