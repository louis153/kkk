package com.longti.upjc.service.sporttery;
import java.util.List;

import com.longti.upjc.entity.sporttery.TAB_INVITATION_BIND;


/**
 * 绑定邀请码设置service
 */
public interface TAB_INVITATION_BINDService {
	
   
	/**
	 * 条件查询
	 */
    public List<TAB_INVITATION_BIND> selectTAB_INVITATION_BINDList(TAB_INVITATION_BIND tab_invitation_bind) throws Exception;
    /**
     * 条件查询数量
     */
    public int selectTAB_INVITATION_BINDCount(TAB_INVITATION_BIND tab_invitation_bind) throws Exception;
    /**
     * 添加
     */
    public int insertTAB_INVITATION_BIND(TAB_INVITATION_BIND tab_invitation_bind) throws Exception;
    /**
     * 批量添加
     */
    public int insertTAB_INVITATION_BIND(List<TAB_INVITATION_BIND> list) throws Exception;
    /**
     * 修改
     */
    public int updateTAB_INVITATION_BIND(TAB_INVITATION_BIND tab_invitation_bind) throws Exception;
    /**
     * 批量修改
     */
    public int updateTAB_INVITATION_BIND(List<TAB_INVITATION_BIND> list) throws Exception;
    /**
     * 删除
     */
    public int deleteTAB_INVITATION_BIND(TAB_INVITATION_BIND tab_invitation_bind) throws Exception;


	
}