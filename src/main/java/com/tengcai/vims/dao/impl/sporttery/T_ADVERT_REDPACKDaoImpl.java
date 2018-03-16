package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;import com.tengcai.vims.entity.sporttery.T_ADVERT_REDPACK;
import com.tengcai.vims.dao.sporttery.T_ADVERT_REDPACKDao;


/**
 * 红包雨daoImpl
 */
@Repository
public class T_ADVERT_REDPACKDaoImpl extends BaseDaoImpl<T_ADVERT_REDPACK> implements T_ADVERT_REDPACKDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_REDPACK> selectT_ADVERT_REDPACKList(T_ADVERT_REDPACK t_advert_redpack) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.T_ADVERT_REDPACK.selectT_ADVERT_REDPACKList",t_advert_redpack);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_REDPACKCount(T_ADVERT_REDPACK t_advert_redpack) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.T_ADVERT_REDPACK.selectT_ADVERT_REDPACKCount",t_advert_redpack);
    }


	
}