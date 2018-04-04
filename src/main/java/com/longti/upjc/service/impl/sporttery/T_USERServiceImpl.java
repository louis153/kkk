package com.longti.upjc.service.impl.sporttery;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longti.upjc.entity.sporttery.TAB_INVITATION_BIND;
import com.longti.upjc.entity.sporttery.T_INVITATION_AWARD;
import com.longti.upjc.entity.sporttery.T_USER;
import com.longti.upjc.entity.sporttery.T_USER_INVCODE;
import com.longti.upjc.dao.sporttery.TAB_INVITATION_BINDDao;
import com.longti.upjc.dao.sporttery.T_INVITATION_AWARDDao;
import com.longti.upjc.dao.sporttery.T_USERDao;
import com.longti.upjc.dao.sporttery.T_USER_INVCODEDao;
import com.longti.upjc.service.sporttery.T_USERService;
import com.longti.upjc.util.StringUtil;


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
    @Autowired
    private T_INVITATION_AWARDDao t_INVITATION_AWARDDao;
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
	public void saveInvitationBind(String userPin,String orderId) throws Exception {
		TAB_INVITATION_BIND tab_invitation_bind=new TAB_INVITATION_BIND();		
		tab_invitation_bind.setBe_bind_available(1);
		List<TAB_INVITATION_BIND> lBinds=tab_INVITATION_BINDDao.selectTAB_INVITATION_BINDList(tab_invitation_bind);
		T_USER owner_user=new T_USER();
		owner_user.setUser_pin(userPin);
		List<T_USER> ownerUserList=t_userDao.selectT_USERList(owner_user);
		if(ownerUserList.size()>0){
			owner_user=ownerUserList.get(0);
			owner_user.setBet_times(StringUtil.ifnull(owner_user.getBet_times(),0)+1);
			t_userDao.updateT_USER(owner_user);
		}
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
				if(owner_user.getBet_times()<4){
				    BigDecimal win_fee=null;
					switch(owner_user.getBet_times()){
					case 0:
						win_fee= tab_invitation_bind.getBe_bind_fee_one();
						break;
					case 1:
						win_fee= tab_invitation_bind.getBe_bind_fee_two();
						break;
					case 2:
						win_fee=tab_invitation_bind.getBe_bind_fee_three();
						break;
					case 3:
						win_fee=tab_invitation_bind.getBe_bind_fee_four();
					}		
					t_user.setAward_gto(t_user.getAward_gto().add(win_fee));
					t_userDao.updateT_USER(t_user);
					T_INVITATION_AWARD t_invitation_award=new T_INVITATION_AWARD();
					t_invitation_award.setContributor(owner_user.getUser_pin());
					t_invitation_award.setCreate_time(new Date());
					t_invitation_award.setId("BE"+orderId);
					t_invitation_award.setElectronic_code("GTO");
					t_invitation_award.setNick_name(t_user.getNick_name());
					t_invitation_award.setType("0");
					t_invitation_award.setUser_pin(t_user.getUser_pin());
					t_invitation_award.setWin_fee(win_fee.toString());;
					t_INVITATION_AWARDDao.insertT_INVITATION_AWARD(t_invitation_award);
				}
			}
		}
	}	
}