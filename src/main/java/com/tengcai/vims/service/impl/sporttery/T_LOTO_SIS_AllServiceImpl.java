package com.tengcai.vims.service.impl.sporttery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_E;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_F;
import com.tengcai.vims.dao.sporttery.T_LOTO_SIS_BDao;
import com.tengcai.vims.dao.sporttery.T_LOTO_SIS_EDao;
import com.tengcai.vims.dao.sporttery.T_LOTO_SIS_FDao;
import com.tengcai.vims.service.sporttery.T_LOTO_SIS_AllService;


/**
 * 蓝球统计serviceImpl
 */
@Service
public class T_LOTO_SIS_AllServiceImpl implements T_LOTO_SIS_AllService  {
	@Autowired
	private T_LOTO_SIS_BDao t_loto_sis_bDao;
    @Autowired
    private T_LOTO_SIS_FDao t_loto_sis_fDao;
    @Autowired
    private T_LOTO_SIS_EDao t_loto_sis_eDao;
	@Override
	public int saveFootSis(T_LOTO_SIS_F t_loto_sis_f) throws Exception {
		t_loto_sis_fDao.saveSisT_LOTO_SIS_F(t_loto_sis_f);
		return 0;
	}
	@Override
	public int saveBasketSis(T_LOTO_SIS_B t_loto_sis_b) throws Exception {
		t_loto_sis_bDao.saveSis(t_loto_sis_b);
		return 0;
	}
	@Override
	public int saveDjSis(T_LOTO_SIS_E t_loto_sis_e) throws Exception {
		return t_loto_sis_eDao.saveSis(t_loto_sis_e);
	}
    
    
	


	
}