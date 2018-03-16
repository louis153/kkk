package com.tengcai.vims.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.tengcai.vims.util.PageBean;


public interface BaseDao<T> {
	/**
	 * 增加
	 * @param entity
	 */
   public void save(T entity);
	/**
	 * 增加
	 * @param entity
	 */
   public void save(String methodName, Map<String, Object> params);
	/**
	 * 增加
	 * @param object
	 */
	public void saveEntity(String methodName, Object object);
   /**
    * 删除
    * @param id pk
    */
   public int delete(Serializable id);
   /**
    * 删除
    * @param id
    */
   public int delete(String id);
   /**
    * 删除
    * @param id 
    */
   public int delete(String id, String methodName);
   /**
    * 多条件删除
    * @param entity
    */
   public int delete(T entity);
   /**
    * 多条件删除
    * @param methodName	方法名
    * @param params
    */
   public int delete(String methodName, Map<String, Object> params);
   /**
    * 批量删除
    * @param ids
    */
   public int deleteBatch(List<?> ids);
   /**
    * 批量删除
    * @param ids
    */
   public int deleteBatch(List<?> ids, String methodName);
   /**
    * 修改
    * @param entity
    */
   public int update(T entity);
   
   /**
    * 修改
    * @param methodName		方法名
    * @param paramString	一个字符串参数
    * @return void
    * @throws
    */
   public int update(String methodName, String paramString);
   
   /**
    * 修改
    * @param methodName 方法名
    * @param params 	参数类型为map
    */
   public int update(String methodName, Map<String, Object> params);
   
   /**
    * 查询 按ID
    * @param pk
    */
   public T findById(Serializable id);
   /**
    * 查询全部信息
    * @return
    */
   public List<T> findAll();
   /**
    * 通过一个参数取到对应的对象
    * @param entityClass
    * @param maps
    * @param methodName
    * @return
    */
   public T findByParam(Map<String, Object> params,String methodName);
   /**
    * 根据单个条件 查询所有内容
    * @param parameter
    * @param methodName
    * @return
    */
   public List<T> findAllByKey(Object parameter, String methodName);
   /**
    * 根据多个条件 查询所有内容
    * @param maps
    * @param methodName
    * @return
    */
   public List<T> findAllByKey(Map<String, Object> params,String methodName);
   /**
    * PageBean分页
    * @param entityClass
    * @param pageNo	      页数
    * @param pageSize 条数
    * @param params	      参数(可以为空)
    * @param methodName  方法名.如(findByPage)
    * @return
    */
   public PageBean<T> findByPageBean(int pageNo, int pageSize, Map<String, Object> params,String methodName);
   /**
    * 判断某个值是否存在
    * @param entityClass
    * @param maps
    * @param methodName
    * @return
    */
	public int isExist(Map<String, Object> params, String methodName);
	
	/**
	 * 返回个数,count(*)
	 * @param params		参数
	 * @param methodName	方法名
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int getCountNum(Map<String, Object> params, String methodName);
	
	/**
	 * 返回个数,count(*)
	 * @param params		参数
	 * @param methodName	方法名
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int getCountNum(Object parameter, String methodName);
}
