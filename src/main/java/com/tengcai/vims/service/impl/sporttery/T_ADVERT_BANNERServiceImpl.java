package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.T_ADVERT_BANNER;
import com.tengcai.vims.dao.sporttery.T_ADVERT_BANNERDao;
import com.tengcai.vims.service.sporttery.T_ADVERT_BANNERService;


/**
 * 首页轮播广告serviceImpl
 */
@Service
public class T_ADVERT_BANNERServiceImpl implements T_ADVERT_BANNERService  {
	@Autowired
	private T_ADVERT_BANNERDao t_advert_bannerDao;
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_BANNER> selectT_ADVERT_BANNERList(T_ADVERT_BANNER t_advert_banner) throws Exception{
        return t_advert_bannerDao.selectT_ADVERT_BANNERList(t_advert_banner);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_BANNERCount(T_ADVERT_BANNER t_advert_banner) throws Exception{
        return t_advert_bannerDao.selectT_ADVERT_BANNERCount(t_advert_banner);
    }


	
}