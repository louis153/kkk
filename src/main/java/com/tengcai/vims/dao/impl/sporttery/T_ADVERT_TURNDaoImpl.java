package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.T_ADVERT_TURN;
import com.tengcai.vims.dao.sporttery.T_ADVERT_TURNDao;


/**
 * 首页轮播广告daoImpl
 */
@Repository
public class T_ADVERT_TURNDaoImpl extends BaseDaoImpl<T_ADVERT_TURN> implements T_ADVERT_TURNDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_TURN> selectT_ADVERT_TURNList(T_ADVERT_TURN t_advert_turn) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.T_ADVERT_TURN.selectT_ADVERT_TURNList",t_advert_turn);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_TURNCount(T_ADVERT_TURN t_advert_turn) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.T_ADVERT_TURN.selectT_ADVERT_TURNCount",t_advert_turn);
    }


	
}