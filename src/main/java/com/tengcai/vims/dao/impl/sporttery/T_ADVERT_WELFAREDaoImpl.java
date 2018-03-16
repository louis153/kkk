package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.T_ADVERT_WELFARE;
import com.tengcai.vims.dao.sporttery.T_ADVERT_WELFAREDao;


/**
 * 首页福利社daoImpl
 */
@Repository
public class T_ADVERT_WELFAREDaoImpl extends BaseDaoImpl<T_ADVERT_WELFARE> implements T_ADVERT_WELFAREDao  {
	   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_WELFARE> selectT_ADVERT_WELFAREList(T_ADVERT_WELFARE t_advert_welfare) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.T_ADVERT_WELFARE.selectT_ADVERT_WELFAREList",t_advert_welfare);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_WELFARECount(T_ADVERT_WELFARE t_advert_welfare) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.T_ADVERT_WELFARE.selectT_ADVERT_WELFARECount",t_advert_welfare);
    }


	
}