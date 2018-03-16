package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.V_PRECENT_F;
import com.tengcai.vims.dao.sporttery.V_PRECENT_FDao;
import com.tengcai.vims.service.sporttery.V_PRECENT_FService;


/**
 * VIEWserviceImpl
 */
@Service
public class V_PRECENT_FServiceImpl implements V_PRECENT_FService  {
	@Autowired
	private V_PRECENT_FDao v_PRECENT_FDao;
   
    /**
	 * 条件查询
	 */
    public List<V_PRECENT_F> selectV_PRECENT_FList(V_PRECENT_F v_precent_f) throws Exception{
        return v_PRECENT_FDao.selectV_PRECENT_FList(v_precent_f);
    }
    
    /**
     * 条件查询数量
     */
    public int selectV_PRECENT_FCount(V_PRECENT_F v_precent_f) throws Exception{
        return v_PRECENT_FDao.selectV_PRECENT_FCount(v_precent_f);
    }


	
}