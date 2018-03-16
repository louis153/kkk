package com.tengcai.vims.dao.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.V_SALEDAY;


/**
 * VIEWdao
 */
public interface V_SALEDAYDao {
	
   
    /**
	 * 条件查询
	 */
    public List<V_SALEDAY> selectV_SALEDAYList(V_SALEDAY v_saleday) throws Exception;

    

	
}