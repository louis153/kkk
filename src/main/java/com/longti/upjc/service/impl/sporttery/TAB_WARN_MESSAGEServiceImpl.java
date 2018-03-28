package com.longti.upjc.service.impl.sporttery;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.longti.upjc.entity.sporttery.TAB_WARN_MESSAGE;
import com.longti.upjc.entity.sporttery.TAB_WARN_RECEIVE;
import com.longti.upjc.entity.sporttery.TAB_WARN_SETTING;
import com.longti.upjc.dao.sporttery.TAB_WARN_MESSAGEDao;
import com.longti.upjc.dao.sporttery.TAB_WARN_RECEIVEDao;
import com.longti.upjc.dao.sporttery.TAB_WARN_SETTINGDao;
import com.longti.upjc.service.sporttery.TAB_WARN_MESSAGEService;


/**
 * 告警消息列表serviceImpl
 */
@Service
public class TAB_WARN_MESSAGEServiceImpl implements TAB_WARN_MESSAGEService  {
	@Autowired
	private TAB_WARN_MESSAGEDao tab_warn_messageDao;
	@Autowired
	private TAB_WARN_RECEIVEDao tab_warn_receiveDao;
	@Autowired
	private TAB_WARN_SETTINGDao tab_warn_settingDao;
   
	/**
	 * 条件查询
	 */
    public List<TAB_WARN_MESSAGE> selectTAB_WARN_MESSAGEList(TAB_WARN_MESSAGE tab_warn_message) throws Exception{
        return tab_warn_messageDao.selectTAB_WARN_MESSAGEList(tab_warn_message);
    }

    /**
     * 添加
     */
    public int insertTAB_WARN_MESSAGE(TAB_WARN_MESSAGE tab_warn_message) throws Exception{
    	
        return tab_warn_messageDao.insertTAB_WARN_MESSAGE(tab_warn_message);
    }
    
    @Override
    public List<TAB_WARN_RECEIVE> selectTAB_WARN_RECEIVEList() throws Exception{
    	TAB_WARN_RECEIVE tab_warn_receive=new TAB_WARN_RECEIVE();
    	tab_warn_receive.setAvailable(1);
    	return tab_warn_receiveDao.selectTAB_WARN_RECEIVEList(tab_warn_receive);
    }

    @Override
    public TAB_WARN_SETTING selectTAB_WARN_SETTING() throws Exception{
    	TAB_WARN_SETTING tab_warn_setting=new TAB_WARN_SETTING();
    	List<TAB_WARN_SETTING> lSettings=tab_warn_settingDao.selectTAB_WARN_SETTINGList(tab_warn_setting);
    	if(lSettings.isEmpty()){
    		return null;
    	}else{
    		return lSettings.get(0);
    	}
    }

	
}