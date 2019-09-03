package cn.ritac.cpwater.service.impl;

import java.util.List;




import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ritac.cpwater.service.BaseService;
import tk.mybatis.mapper.common.Mapper;


public class BaseServiceImpl<T> implements BaseService<T, Integer> {

	private Mapper<T> baseMapper;

	@Override
	public void save(T pojo) {
		
		baseMapper.insert(pojo);
	}

	@Override
	public void update(T pojo) {
		baseMapper.updateByPrimaryKey(pojo);
	}

	@Override
	public void delete(Integer[] ids) {
		for (Integer id : ids) {
			baseMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public T findById(Integer id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<T> findList(T pojo) {
		return baseMapper.select(pojo);
	}

	@Override
	public List<T> findAll() {
		return baseMapper.selectAll();
	}

	@Override
	public PageInfo<T> page(Integer pageNumber, Integer pageSize, T pojo) {
		PageHelper.startPage(pageNumber, pageSize);
		return new PageInfo<>(baseMapper.select(pojo));
	}

	@Override
	public PageInfo<T> page(Integer pageNumber, Integer pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<T> list=baseMapper.selectAll();
		
		return new PageInfo<>(list);
	}

	@Override
	public void setInit(Mapper<T> mapper) {
		baseMapper = mapper;

	}

	@Override
	public T find(T pojo) {
		
		return baseMapper.selectOne(pojo);
		
	
	}

	@Override
	public Integer count(T pojo) {
		return baseMapper.selectCount(pojo);
	}

}
