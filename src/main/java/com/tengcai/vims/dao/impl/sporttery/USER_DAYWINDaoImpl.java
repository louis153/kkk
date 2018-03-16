package com.tengcai.vims.dao.impl.sporttery;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.tengcai.vims.dao.impl.BaseDaoImpl;
import com.tengcai.vims.entity.sporttery.USER_DAYWIN;
import com.tengcai.vims.dao.sporttery.USER_DAYWINDao;


/**
 * daoImpl
 */
@Repository
public class USER_DAYWINDaoImpl extends BaseDaoImpl<USER_DAYWIN> implements USER_DAYWINDao  {
	
   
    /**
	 * 条件查询
	 */
    public List<USER_DAYWIN> selectUSER_DAYWINList(USER_DAYWIN user_daywin) throws Exception{
        return getSqlSession().selectList("com.tengcai.vims.entity.sporttery.USER_DAYWIN.selectUSER_DAYWINList",user_daywin);
    }
    
    /**
     * 条件查询数量
     */
    public int selectUSER_DAYWINCount(USER_DAYWIN user_daywin) throws Exception{
        return getSqlSession().selectOne("com.tengcai.vims.entity.sporttery.USER_DAYWIN.selectUSER_DAYWINCount",user_daywin);
    }

    /**
     * 添加
     */
    public int insertUSER_DAYWIN(USER_DAYWIN user_daywin) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.USER_DAYWIN.save",user_daywin);
    }
    /**
     * 批量添加
     */
    public int insertUSER_DAYWIN(List<USER_DAYWIN> list) throws Exception{
        return getSqlSession().insert("com.tengcai.vims.entity.sporttery.USER_DAYWIN.saveList",list);
    }
    /**
     * 修改
     */
    public int updateUSER_DAYWIN(USER_DAYWIN user_daywin) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.USER_DAYWIN.update",user_daywin);
    }
    /**
     * 批量修改
     */
    public int updateUSER_DAYWIN(List<USER_DAYWIN> list) throws Exception{
        return getSqlSession().update("com.tengcai.vims.entity.sporttery.USER_DAYWIN.updateList",list);
    }
    /**
     * 删除
     */
    public int deleteUSER_DAYWIN(USER_DAYWIN user_daywin) throws Exception{
        return getSqlSession().delete("com.tengcai.vims.entity.sporttery.USER_DAYWIN.delete",user_daywin);
    }

	@Override
	public int teInsert() {
		
		return getSqlSession().insert("com.tengcai.vims.entity.sporttery.USER_DAYWIN.teInsert");
	}

	@Override
	public int teDelete() {		
		return getSqlSession().delete("com.tengcai.vims.entity.sporttery.USER_DAYWIN.teDelete");
	}


	
}