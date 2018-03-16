package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.T_ADVERT_BANNER;
import com.tengcai.vims.dao.sporttery.T_ADVERT_BANNERDao;


/**
 * 首页轮播广告daoImpl
 */
@Repository
public class T_ADVERT_BANNERDaoImpl extends BaseDaoImpl<T_ADVERT_BANNER> implements T_ADVERT_BANNERDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_BANNER> selectT_ADVERT_BANNERList(T_ADVERT_BANNER t_advert_banner) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.T_ADVERT_BANNER.selectT_ADVERT_BANNERList",t_advert_banner);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_BANNERCount(T_ADVERT_BANNER t_advert_banner) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.T_ADVERT_BANNER.selectT_ADVERT_BANNERCount",t_advert_banner);
    }


	
}