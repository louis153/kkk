package com.longti.upjc.service.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.V_SALEDAY;


/**
 * VIEWservice
 */
public interface V_SALEDAYService {
	
   
    /**
	 * 条件查询
	 */
    public List<V_SALEDAY> selectV_SALEDAYList(V_SALEDAY v_saleday) throws Exception;

    	
}