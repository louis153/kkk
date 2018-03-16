package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.V_PRECENT_B;
import com.tengcai.vims.dao.sporttery.V_PRECENT_BDao;
import com.tengcai.vims.service.sporttery.V_PRECENT_BService;


/**
 * VIEWserviceImpl
 */
@Service
public class V_PRECENT_BServiceImpl implements V_PRECENT_BService  {
	@Autowired
	private V_PRECENT_BDao v_PRECENT_BDao;
   
    /**
	 * 条件查询
	 */
    public List<V_PRECENT_B> selectV_PRECENT_BList(V_PRECENT_B v_precent_b) throws Exception{
        return v_PRECENT_BDao.selectV_PRECENT_BList(v_precent_b);
    }
    
    /**
     * 条件查询数量
     */
    public int selectV_PRECENT_BCount(V_PRECENT_B v_precent_b) throws Exception{
        return v_PRECENT_BDao.selectV_PRECENT_BCount(v_precent_b);
    }


}