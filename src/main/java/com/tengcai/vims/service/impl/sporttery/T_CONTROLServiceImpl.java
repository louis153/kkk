package com.tengcai.vims.service.impl.sporttery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengcai.vims.entity.sporttery.T_CONTROL;
import com.tengcai.vims.dao.sporttery.T_CONTROLDao;
import com.tengcai.vims.service.sporttery.T_CONTROLService;


/**
 * 阀值管理serviceImpl
 */
@Service
public class T_CONTROLServiceImpl implements T_CONTROLService  {
	@Autowired
	private T_CONTROLDao t_CONTROLDao;
   
    /**
	 * 条件查询
	 */
    public List<T_CONTROL> selectT_CONTROLList(T_CONTROL t_control) throws Exception{
        return t_CONTROLDao.selectT_CONTROLList(t_control);
    }
    
    /**
     * 条件查询数量
     */
    public int selectT_CONTROLCount(T_CONTROL t_control) throws Exception{
        return t_CONTROLDao.selectT_CONTROLCount(t_control);
    }

    /**
     * 添加
     */
    public int insertT_CONTROL(T_CONTROL t_control) throws Exception{
        return t_CONTROLDao.insertT_CONTROL(t_control);
    }

    /**
     * 批量添加
     */
    public int insertT_CONTROL(List<T_CONTROL> list) throws Exception{
        return t_CONTROLDao.insertT_CONTROL(list);
    }

    /**
     * 修改
     */
    public int updateT_CONTROL(T_CONTROL t_control) throws Exception{
        return t_CONTROLDao.updateT_CONTROL(t_control);
    }
    
    /**
     * 批量修改
     */
    public int updateT_CONTROL(List<T_CONTROL> list) throws Exception{
        return t_CONTROLDao.updateT_CONTROL(list);
    }

    /**
     * 删除
     */
    public int deleteT_CONTROL(T_CONTROL t_control) throws Exception{
        return t_CONTROLDao.deleteT_CONTROL(t_control);
    }


	
}