package cn.ritac.cpwater.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ritac.cpwater.mybatis.model.Admin;
import tk.mybatis.mapper.common.Mapper;

public interface AdminMapper extends Mapper<Admin> {

	public List<Admin> findListByObj(@Param("userPhone") String userPhone, @Param("adminName") String adminName,
			@Param("adminDescript") String adminDescript);
}