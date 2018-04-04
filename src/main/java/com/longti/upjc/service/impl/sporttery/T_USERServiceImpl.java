package com.longti.upjc.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.entity.sporttery.TAB_INVITATION_BIND;
import com.longti.upjc.entity.sporttery.T_USER;
import com.longti.upjc.entity.sporttery.T_USER_INVCODE;
import com.longti.upjc.dao.sporttery.TAB_INVITATION_BINDDao;
import com.longti.upjc.dao.sporttery.T_USERDao;
import com.longti.upjc.dao.sporttery.T_USER_INVCODEDao;
import com.longti.upjc.service.sporttery.T_USERService;


/**
 * serviceImpl
 */
@Service
public class T_USERServiceImpl implements T_USERService  {
	@Autowired
	private T_USERDao t_userDao;
    @Autowired
    private TAB_INVITATION_BINDDao tab_INVITATION_BINDDao;
    @Autowired
    private T_USER_INVCODEDao t_USER_INVCODEDao;
    /**
	 * 条件查询
	 */
    public List<T_USER> selectT_USERList(T_USER t_user) throws Exception{
        return t_userDao.selectT_USERList(t_user);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_USERCount(T_USER t_user) throws Exception{
        return t_userDao.selectT_USERCount(t_user);
    }

    /**
     * 添加
     */
    public int insertT_USER(T_USER t_user) throws Exception{
        return t_userDao.insertT_USER(t_user);
    }

    /**
     * 批量添加
     */
    public int insertT_USER(List<T_USER> list) throws Exception{
        return t_userDao.insertT_USER(list);
    }

    /**
     * 修改
     */
    public int updateT_USER(T_USER t_user) throws Exception{
        return t_userDao.updateT_USER(t_user);
    }
    
    /**
     * 批量修改
     */
    public int updateT_USER(List<T_USER> list) throws Exception{
        return t_userDao.updateT_USER(list);
    }

    /**
     * 删除
     */
    public int deleteT_USER(T_USER t_user) throws Exception{
        return t_userDao.deleteT_USER(t_user);
    }

	@Override
	public void invitation_BIND(String userPin) throws Exception {
		TAB_INVITATION_BIND tab_invitation_bind=new TAB_INVITATION_BIND();		
		tab_invitation_bind.setBe_bind_available(1);
		List<TAB_INVITATION_BIND> lBinds=tab_INVITATION_BINDDao.selectTAB_INVITATION_BINDList(tab_invitation_bind);
		if(lBinds.size()>0){
			tab_invitation_bind=lBinds.get(0);
			T_USER_INVCODE t_user_invcode=new T_USER_INVCODE();
			t_user_invcode.setUser_pin(userPin);
			t_user_invcode.setIs_bind(1);
			List<T_USER_INVCODE> lInvcodes = t_USER_INVCODEDao.selectT_USER_INVCODEList(t_user_invcode);
			if(lInvcodes.isEmpty()){
				return;
			}else{
				t_user_invcode=lInvcodes.get(0);
			}
			T_USER t_user=new T_USER();
			t_user.setUser_pin(t_user_invcode.getBind_user_pin());
			List<T_USER> lUsers= t_userDao.selectT_USERList(t_user);
			if(lUsers.size()>0){
				t_user=lUsers.get(0);
				if(t_user.getBet_times()<4){
					switch(t_user.getBet_times()){
					case 0:
						t_user.setAward_gto(t_user.getAward_gto().add(tab_invitation_bind.getBe_bind_fee_one()));
						break;
					case 1:
						t_user.setAward_gto(t_user.getAward_gto().add(tab_invitation_bind.getBe_bind_fee_two()));
						break;
					case 2:
						t_user.setAward_gto(t_user.getAward_gto().add(tab_invitation_bind.getBe_bind_fee_three()));
						break;
					case 3:
						t_user.setAward_gto(t_user.getAward_gto().add(tab_invitation_bind.getBe_bind_fee_four()));
					}
										
					t_user.setBet_times(t_user.getBet_times()+1);
					t_userDao.updateT_USER(t_user);
					
				}
			}
		}
	}	
}