package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.T_LOTO_EN_GTODao;
import com.longti.upjc.entity.sporttery.T_LOTO_EN_GTO;


/**
 * GTO话题竞猜信息daoImpl
 */
@Repository
public class T_LOTO_EN_GTODaoImpl extends BaseDaoImpl<T_LOTO_EN_GTO> implements T_LOTO_EN_GTODao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_EN_GTO> selectT_LOTO_EN_GTOList(T_LOTO_EN_GTO t_loto_en_gto) throws Exception{   	   	
        return findAllByKey(t_loto_en_gto,"selectT_LOTO_EN_GTOList");
    }
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_EN_GTOCount(T_LOTO_EN_GTO t_loto_en_gto) throws Exception{   	
        return getCountNum(t_loto_en_gto,"selectT_LOTO_EN_GTOCount");
    }
    /**
     * 添加
     */
    public int insertT_LOTO_EN_GTO(T_LOTO_EN_GTO t_loto_en_gto) throws Exception{
        return save("save",t_loto_en_gto);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_EN_GTO(List<T_LOTO_EN_GTO> list) throws Exception{
        return save("saveList",list);
    }
    /**
     * 修改
     */
    public int updateT_LOTO_EN_GTO(T_LOTO_EN_GTO t_loto_en_gto) throws Exception{
        return update("update",t_loto_en_gto);
    }
    /**
     * 批量修改 
     */
    public int updateT_LOTO_EN_GTO(List<T_LOTO_EN_GTO> list) throws Exception{
        return update("updateList",list);
    }
    /**
     * 删除
     */
    public int deleteT_LOTO_EN_GTO(T_LOTO_EN_GTO t_loto_en_gto) throws Exception{
        return delete("delete",t_loto_en_gto);
    }


	
}