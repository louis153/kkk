package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.T_ADVERT_RECORD;
import com.tengcai.vims.dao.sporttery.T_ADVERT_RECORDDao;


/**
 * 投注记录广告daoImpl
 */
@Repository
public class T_ADVERT_RECORDDaoImpl extends BaseDaoImpl<T_ADVERT_RECORD> implements T_ADVERT_RECORDDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_RECORD> selectT_ADVERT_RECORDList(T_ADVERT_RECORD t_advert_record) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.T_ADVERT_RECORD.selectT_ADVERT_RECORDList",t_advert_record);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_RECORDCount(T_ADVERT_RECORD t_advert_record) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.T_ADVERT_RECORD.selectT_ADVERT_RECORDCount",t_advert_record);
    }


	
}