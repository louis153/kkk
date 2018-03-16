package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.T_ADVERT_POP;
import com.tengcai.vims.dao.sporttery.T_ADVERT_POPDao;


/**
 * 首页弹层广告daoImpl
 */
@Repository
public class T_ADVERT_POPDaoImpl extends BaseDaoImpl<T_ADVERT_POP> implements T_ADVERT_POPDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_POP> selectT_ADVERT_POPList(T_ADVERT_POP t_advert_pop) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.T_ADVERT_POP.selectT_ADVERT_POPList",t_advert_pop);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_POPCount(T_ADVERT_POP t_advert_pop) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.T_ADVERT_POP.selectT_ADVERT_POPCount",t_advert_pop);
    }


	
}