package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.V_SALEDAY;
import com.tengcai.vims.dao.sporttery.V_SALEDAYDao;
import com.tengcai.vims.service.sporttery.V_SALEDAYService;


/**
 * VIEWserviceImpl
 */
@Service
public class V_SALEDAYServiceImpl implements V_SALEDAYService  {
	@Autowired
	private V_SALEDAYDao v_SALEDAYDao;
   
    /**
	 * 条件查询
	 */
    public List<V_SALEDAY> selectV_SALEDAYList(V_SALEDAY v_saleday) throws Exception{
    	List<V_SALEDAY> lst=v_SALEDAYDao.selectV_SALEDAYList(v_saleday);
    	int delPos=-1;
    	for(int i=0;i<lst.size();i++){
    		V_SALEDAY vSaleday=lst.get(i);
    		if(vSaleday.getRem_count()>0){
    			vSaleday.setMatch_count(vSaleday.getMatch_count()-1);
    			if(vSaleday.getMatch_count()==0){
    				delPos=i;
    			}
    			break;
    		}
    	}
    	if(delPos>=0)
    		lst.remove(delPos);
        return lst;
    }
    
    

	
}