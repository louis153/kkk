package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.T_ADVERT_TURN;
import com.tengcai.vims.dao.sporttery.T_ADVERT_TURNDao;
import com.tengcai.vims.service.sporttery.T_ADVERT_TURNService;


/**
 * 首页轮播广告serviceImpl
 */
@Service
public class T_ADVERT_TURNServiceImpl implements T_ADVERT_TURNService  {
	@Autowired
	private T_ADVERT_TURNDao t_advert_turnDao;
   
    /**
	 * 条件查询
	 */
    public List<T_ADVERT_TURN> selectT_ADVERT_TURNList(T_ADVERT_TURN t_advert_turn) throws Exception{
        return t_advert_turnDao.selectT_ADVERT_TURNList(t_advert_turn);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_ADVERT_TURNCount(T_ADVERT_TURN t_advert_turn) throws Exception{
        return t_advert_turnDao.selectT_ADVERT_TURNCount(t_advert_turn);
    }


	
}