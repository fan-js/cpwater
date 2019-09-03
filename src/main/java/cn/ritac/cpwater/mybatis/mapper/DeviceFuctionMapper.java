package cn.ritac.cpwater.mybatis.mapper;

import java.util.List;

import cn.ritac.cpwater.mybatis.model.DeviceFuction;
import tk.mybatis.mapper.common.Mapper;

public interface DeviceFuctionMapper extends Mapper<DeviceFuction> {

	public List<DeviceFuction> findDeviceFunctionByRoleId(String phone);
}