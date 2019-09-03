package cn.ritac.cpwater.service;

import java.io.Serializable;
import java.util.List;


import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.common.Mapper;



public interface BaseService<T,I extends Serializable> {

	abstract void setInit(Mapper<T> mapper);
	
	
    void save(T pojo);
    void update(T pojo);
    void delete(I[] ids);
    T findById(I id);
    
    T find(T pojo);
    
    
    I count(T pojo);
    
    List<T> findList(T pojo);
    List<T> findAll();
    PageInfo<T> page(Integer pageNumber, Integer pageSize, T pojo);
    PageInfo<T> page(Integer pageNumber, Integer pageSize);



}