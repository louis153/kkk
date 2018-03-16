package com.tengcai.vims.service.sporttery;
import java.util.List;
import com.tengcai.vims.entity.sporttery.USER_DAYWIN;


/**
 * service
 */
public interface USER_DAYWINService {
	
   
    /**
	 * 条件查询
	 */
    public List<USER_DAYWIN> selectUSER_DAYWINList(USER_DAYWIN user_daywin) throws Exception;

    /**
     * 条件查询数量
     */
    public int selectUSER_DAYWINCount(USER_DAYWIN user_daywin) throws Exception;

    /**
     * 添加
     */
    public int insertUSER_DAYWIN(USER_DAYWIN user_daywin) throws Exception;

    /**
     * 批量添加
     */
    public int insertUSER_DAYWIN(List<USER_DAYWIN> list) throws Exception;

    /**
     * 修改
     */
    public int updateUSER_DAYWIN(USER_DAYWIN user_daywin) throws Exception;

    /**
     * 修改
     */
    public int updateUSER_DAYWIN(List<USER_DAYWIN> list) throws Exception;

    /**
     * 删除
     */
    public int deleteUSER_DAYWIN(USER_DAYWIN user_daywin) throws Exception;

    public int teInsert();
	public int teDelete();
	
}