package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.T_ADVERT_FLOAT;
import com.tengcai.vims.dao.sporttery.T_ADVERT_FLOATDao;


/**
 * 首页浮动广告daoImpl
 */
@Repository
public class T_ADVERT_FLOATDaoImpl extends BaseDaoImpl<T_ADVERT_FLOAT> implements T_ADVERT_FLOATDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_FLOAT> selectT_ADVERT_FLOATList(T_ADVERT_FLOAT t_advert_float) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.T_ADVERT_FLOAT.selectT_ADVERT_FLOATList",t_advert_float);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_FLOATCount(T_ADVERT_FLOAT t_advert_float) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.T_ADVERT_FLOAT.selectT_ADVERT_FLOATCount",t_advert_float);
    }


	
}