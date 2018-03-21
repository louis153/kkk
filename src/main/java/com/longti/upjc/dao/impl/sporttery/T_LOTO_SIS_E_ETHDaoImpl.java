package com.longti.upjc.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.longti.upjc.dao.impl.BaseDaoImpl;
import com.longti.upjc.dao.sporttery.T_LOTO_SIS_E_ETHDao;
import com.longti.upjc.entity.sporttery.T_LOTO_SIS_E_ETH;


/**
 * ETH话题竞猜统计daoImpl
 */
@Repository
public class T_LOTO_SIS_E_ETHDaoImpl extends BaseDaoImpl<T_LOTO_SIS_E_ETH> implements T_LOTO_SIS_E_ETHDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_LOTO_SIS_E_ETH> selectT_LOTO_SIS_E_ETHList(T_LOTO_SIS_E_ETH t_loto_sis_e_eth) throws Exception{   	   	
        return findAllByKey(t_loto_sis_e_eth,"selectT_LOTO_SIS_E_ETHList");
    }
    /**
     * 条件查询数量
     */
    public int selectT_LOTO_SIS_E_ETHCount(T_LOTO_SIS_E_ETH t_loto_sis_e_eth) throws Exception{   	
        return getCountNum(t_loto_sis_e_eth,"selectT_LOTO_SIS_E_ETHCount");
    }
    /**
     * 添加
     */
    public int insertT_LOTO_SIS_E_ETH(T_LOTO_SIS_E_ETH t_loto_sis_e_eth) throws Exception{
        return save("save",t_loto_sis_e_eth);
    }
    /**
     * 批量添加
     */
    public int insertT_LOTO_SIS_E_ETH(List<T_LOTO_SIS_E_ETH> list) throws Exception{
        return save("saveList",list);
    }
    /**
     * 修改
     */
    public int updateT_LOTO_SIS_E_ETH(T_LOTO_SIS_E_ETH t_loto_sis_e_eth) throws Exception{
        return update("update",t_loto_sis_e_eth);
    }
    /**
     * 批量修改 
     */
    public int updateT_LOTO_SIS_E_ETH(List<T_LOTO_SIS_E_ETH> list) throws Exception{
        return update("updateList",list);
    }
    /**
     * 删除
     */
    public int deleteT_LOTO_SIS_E_ETH(T_LOTO_SIS_E_ETH t_loto_sis_e_eth) throws Exception{
        return delete("delete",t_loto_sis_e_eth);
    }


	
}