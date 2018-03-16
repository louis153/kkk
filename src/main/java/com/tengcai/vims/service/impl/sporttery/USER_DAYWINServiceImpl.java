package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.USER_DAYWIN;
import com.tengcai.vims.dao.sporttery.USER_DAYWINDao;
import com.tengcai.vims.service.sporttery.USER_DAYWINService;


/**
 * serviceImpl
 */
@Service
public class USER_DAYWINServiceImpl implements USER_DAYWINService  {
	@Autowired
	private USER_DAYWINDao uSER_DAYWINDao;
   
    /**
	 * 条件查询
	 */
    public List<USER_DAYWIN> selectUSER_DAYWINList(USER_DAYWIN user_daywin) throws Exception{
        return uSER_DAYWINDao.selectUSER_DAYWINList(user_daywin);
    }
    
    /**
     * 条件查询数量
     */
    public int selectUSER_DAYWINCount(USER_DAYWIN user_daywin) throws Exception{
        return uSER_DAYWINDao.selectUSER_DAYWINCount(user_daywin);
    }

    /**
     * 添加
     */
    public int insertUSER_DAYWIN(USER_DAYWIN user_daywin) throws Exception{
        return uSER_DAYWINDao.insertUSER_DAYWIN(user_daywin);
    }

    /**
     * 批量添加
     */
    public int insertUSER_DAYWIN(List<USER_DAYWIN> list) throws Exception{
        return uSER_DAYWINDao.insertUSER_DAYWIN(list);
    }

    /**
     * 修改
     */
    public int updateUSER_DAYWIN(USER_DAYWIN user_daywin) throws Exception{
        return uSER_DAYWINDao.updateUSER_DAYWIN(user_daywin);
    }
    
    /**
     * 批量修改
     */
    public int updateUSER_DAYWIN(List<USER_DAYWIN> list) throws Exception{
        return uSER_DAYWINDao.updateUSER_DAYWIN(list);
    }

    /**
     * 删除
     */
    public int deleteUSER_DAYWIN(USER_DAYWIN user_daywin) throws Exception{
        return uSER_DAYWINDao.deleteUSER_DAYWIN(user_daywin);
    }

    public int teInsert(){
    	return uSER_DAYWINDao.teInsert();
    };
	public int teDelete(){
		return uSER_DAYWINDao.teDelete();
	};
	
}