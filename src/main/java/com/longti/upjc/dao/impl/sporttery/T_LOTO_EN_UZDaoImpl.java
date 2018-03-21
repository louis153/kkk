package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.T_LOTO_EN_UZDao;
import com.longti.upjc.entity.sporttery.T_LOTO_EN_UZ;


/**
 * UZ话题竞猜信息daoImpl
 */
@Repository
public class T_LOTO_EN_UZDaoImpl extends BaseDaoImpl<T_LOTO_EN_UZ> implements T_LOTO_EN_UZDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_EN_UZ> selectT_LOTO_EN_UZList(T_LOTO_EN_UZ t_loto_en_uz) throws Exception{   	   	
        return findAllByKey(t_loto_en_uz,"selectT_LOTO_EN_UZList");
    }
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_EN_UZCount(T_LOTO_EN_UZ t_loto_en_uz) throws Exception{   	
        return getCountNum(t_loto_en_uz,"selectT_LOTO_EN_UZCount");
    }
    /**
     * 添加
     */
    public int insertT_LOTO_EN_UZ(T_LOTO_EN_UZ t_loto_en_uz) throws Exception{
        return save("save",t_loto_en_uz);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_EN_UZ(List<T_LOTO_EN_UZ> list) throws Exception{
        return save("saveList",list);
    }
    /**
     * 修改
     */
    public int updateT_LOTO_EN_UZ(T_LOTO_EN_UZ t_loto_en_uz) throws Exception{
        return update("update",t_loto_en_uz);
    }
    /**
     * 批量修改 
     */
    public int updateT_LOTO_EN_UZ(List<T_LOTO_EN_UZ> list) throws Exception{
        return update("updateList",list);
    }
    /**
     * 删除
     */
    public int deleteT_LOTO_EN_UZ(T_LOTO_EN_UZ t_loto_en_uz) throws Exception{
        return delete("delete",t_loto_en_uz);
    }


	
}