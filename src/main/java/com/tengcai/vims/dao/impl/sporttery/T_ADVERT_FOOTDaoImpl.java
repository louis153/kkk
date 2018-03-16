package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.T_ADVERT_FOOT;
import com.tengcai.vims.dao.sporttery.T_ADVERT_FOOTDao;


/**
 * 列表底部广告daoImpl
 */
@Repository
public class T_ADVERT_FOOTDaoImpl extends BaseDaoImpl<T_ADVERT_FOOT> implements T_ADVERT_FOOTDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_FOOT> selectT_ADVERT_FOOTList(T_ADVERT_FOOT t_advert_foot) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.T_ADVERT_FOOT.selectT_ADVERT_FOOTList",t_advert_foot);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_FOOTCount(T_ADVERT_FOOT t_advert_foot) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.T_ADVERT_FOOT.selectT_ADVERT_FOOTCount",t_advert_foot);
    }


	
}