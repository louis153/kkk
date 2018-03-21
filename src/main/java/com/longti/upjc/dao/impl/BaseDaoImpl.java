package com.longti.upjc.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import com.longti.upjc.dao.BaseDao;
import com.longti.upjc.util.PageBean;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	@Resource
	private SqlSession sqlSession;
	
	protected SqlSession getSqlSession() {
		return sqlSession;
	}
	
	protected Class<T> entityClass;
	
	public BaseDaoImpl(){
		ParameterizedType pt = (ParameterizedType) getClass()
				.getGenericSuperclass();
		entityClass = (Class<T>) pt.getActualTypeArguments()[0];
	}

   /**
    * 删除
    * @param id	pk
    */
	public int delete(Serializable id) {
		return sqlSession.delete(entityClass.getName() + ".delete", id);
	}
	
   /**
    * 删除
    * @param id 
    */
   public int delete(String id){
	   return sqlSession.delete(entityClass.getName() + ".delete", id);
   }
   
   /**
    * 删除
    * @param id 
    */
   public int delete(String id, String methodName){
	   return sqlSession.delete(entityClass.getName() + "." + methodName, id);
   }

   /**
    * 多条件删除
    * @param entity
    */
   public int delete(T entity){
	   return sqlSession.delete(entityClass.getName() + ".deleteMaybeMoreCondition", entity);
   }
   
   /**
    * 多条件删除
    * @param methodName	方法名
    * @param params
    */
   public int delete(String methodName, Map<String, Object> params){
	   return sqlSession.delete(entityClass.getName() + "." + methodName, params);
   }
	
   /**
    * 批量删除
    * @param ids
    */
   public int deleteBatch(List<?> ids){
	   return sqlSession.delete(entityClass.getName() + ".deleteBatch", ids);
   }
   
   /**
    * 批量删除
    * @param ids
    */
   public int deleteBatch(List<?> ids, String methodName){
	   return sqlSession.delete(entityClass.getName() + "." + methodName, ids);
   }
   
   /**
    * 删除对象
    * @param methodName	方法名
    * @param object
    */
   public int delete(String methodName, Object object){
	   return sqlSession.delete(entityClass.getName() + "." + methodName, object);
   }

   /**
    * 修改
    * @param entity
    */
	public int update(T entity) {
		return sqlSession.update(entity.getClass().getName() + ".update", entity);
	}
	
   /**
    * 修改
    * @param methodName		方法名
    * @param paramString	一个字符串参数
    * @return void
    * @throws
    */
   public int update(String methodName, String paramString){
	   return sqlSession.update(entityClass.getName() + "." + methodName, paramString);
   }

   /**
    * 修改
    * @param methodName 方法名
    * @param params 	参数类型为map
    */
   public int update(String methodName, Map<String, Object> params){
	   return sqlSession.update(entityClass.getName() + "." + methodName, params);
   }
	
   /**
    * 修改
    * @param methodName 方法名
    * @param object 	对象
    */
   public int update(String methodName, Object object){
	   return sqlSession.update(entityClass.getName() + "." + methodName, object);
   }
   
   /**
    * 查询 按ID
    * @param pk
    */
	public T findById(Serializable id) {
		return sqlSession.selectOne(entityClass.getName() + ".findById", id);
	}

	/**
	 * 增加
	 * @param entity
	 */
	public void save(T entity) {
		sqlSession.insert(entity.getClass().getName() + ".add", entity);
	}
	
	/**
	 * 增加
	 * @param object
	 */
	public void saveEntity(String methodName, Object object){
		sqlSession.insert(entityClass.getName() + "." + methodName, object);
	}
	
	/**
	 * 增加
	 * @param entity
	 */
	@Override
	public void save(String methodName, Map<String, Object> params) {
		sqlSession.insert(entityClass.getName() + "." + methodName, params);
	}

	/**
	 * 增加
	 * @param object
	 */
	public int save(String methodName, Object object){
		return  sqlSession.insert(entityClass.getName() + "." + methodName, object);
	}
	
   /**
    * 通过一个参数取到对应的对象
    * @param entityClass
    * @param maps
    * @param methodName
    * @return
    */
	public T findByParam(Map<String, Object> params,String methodName) {
		return sqlSession.selectOne(entityClass.getName() + "." + methodName, params);
	}
	
	
   /**
    * 查询全部信息
    * @return
    */
	public List<T> findAll() {
		return sqlSession.selectList(entityClass.getName() + ".findAll");
	}

   /**
    * 根据单个条件 查询所有内容
    * @param parameter
    * @param methodName
    * @return
    */
   public List<T> findAllByKey(Object parameter, String methodName){
	   return sqlSession.selectList(entityClass.getName() + "." + methodName, parameter);
   }
   
   /**
    * 根据多个条件 查询所有内容
    * @param maps
    * @param methodName
    * @return
    */
	public List<T> findAllByKey(Map<String, Object> params,String methodName) {
		return sqlSession.selectList(entityClass.getName() + "." + methodName, params);
	}
	
   /**
    * PageBean分页
    * @param entityClass
    * @param pageNo	      页数
    * @param pageSize 条数
    * @param params	      参数(可以为空)
    * @param methodName  方法名.如(findByPage)
    * @return
    */
   public PageBean<T> findByPageBean(int pageNo, int pageSize, Map<String, Object> params,String methodName){
	   PageBean<T> pageBean = new PageBean<T>();
		// 判断总条数是否带参数查询
		boolean boo = true;
		// 如果没有参数或者不包括值与键的关系
		if (params == null) {
			params = new HashMap<String, Object>();
			// 不带参数查询总条数
			boo = false;
		}
		// 如果页数小于1的情况
		if (pageNo < 1) {
			pageNo = 1;
		}
		// 开始条数
		params.put("firstResult", (pageNo-1)*pageSize);
		// 最大条数
		//params.put("maxResults", pageNo*pageSize);
		params.put("maxResults", pageSize);

		// 当前页码
		pageBean.setPage(pageNo);
		// 每页多少条数据
		pageBean.setPageSize(pageSize);
		
		List<T> pageList = sqlSession.selectList(entityClass.getName() + "." + methodName, params);
		// 每页数据
		pageBean.setData(pageList);
		// 总条数
		int totalNum = getTotalNum(params, methodName, boo);
		// 总页数
		pageBean.setTotalNums(totalNum);
		// 总多少条数据
		pageBean.setTotalPage(totalNum % pageSize == 0 ? totalNum / pageSize
				: totalNum / pageSize + 1);
		// 实际多少条数据
		pageBean.setActualPageSize(pageList.size());
		
	   return pageBean;
   }


	/**
	 * 总条数
	 * @param entityClass
	 * @param params	参数
	 * @param methodName   分页方法名+Total
	 * @return
	 */
	protected int getTotalNum(Map<String, Object> params, String methodName, boolean boo) {
		int totalNum = 0;
		if(boo){
			totalNum = sqlSession.selectOne(entityClass.getName() + "." + methodName + "Total", params);
		}else{
			totalNum = sqlSession.selectOne(entityClass.getName() + "." + methodName + "Total");
		}
		
		return totalNum;
	}
	
   /**
    * 判断某个值是否存在
    * @param entityClass
    * @param maps
    * @param methodName
    * @return
    */
	public int isExist(Map<String, Object> params, String methodName) {
		int count = 0;
		count = sqlSession.selectOne(entityClass.getName() + "." + methodName, params);
		return count;
	}
	
	/**
	 * 返回个数,count(*), max()
	 * @param params		参数
	 * @param methodName	方法名
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int getCountNum(Map<String, Object> params, String methodName){
		int count = 0;
		count = sqlSession.selectOne(entityClass.getName() + "." + methodName, params);
		return count;
	}
	
	/**
	 * 返回个数,count(*)
	 * @param params		参数
	 * @param methodName	方法名
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int getCountNum(Object parameter, String methodName){
		int count = 0;
		count = sqlSession.selectOne(entityClass.getName() + "." + methodName, parameter);
		return count;
	}
}
