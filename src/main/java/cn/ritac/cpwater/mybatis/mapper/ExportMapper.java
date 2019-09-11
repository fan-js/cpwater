package cn.ritac.cpwater.mybatis.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ritac.cpwater.web.dto.export.DeviceCountVo;
import cn.ritac.cpwater.web.dto.export.DeviceGroupCountVo;
import cn.ritac.cpwater.web.dto.export.DeviceGroupPerVo;
import cn.ritac.cpwater.web.dto.export.DeviceMonthElectricVo;
import cn.ritac.cpwater.web.dto.export.DeviceMonthNetVo;
import cn.ritac.cpwater.web.dto.export.GroupWringVo;
import cn.ritac.cpwater.web.dto.export.OrderGroupPerVo;
import tk.mybatis.mapper.common.Mapper;

public interface ExportMapper extends Mapper<GroupWringVo> {

	public List<GroupWringVo> export_groupWing(@Param("startTime") Date startTime, @Param("endTime")Date endTime);

	public List<DeviceCountVo> export_deviceCount(@Param("phone") String phone);

	public List<DeviceGroupCountVo> export_deviceGroupCount(@Param("startTime") Date startTime, @Param("endTime")Date endTime);

	public List<DeviceGroupPerVo> export_deviceGroupPer(@Param("startTime")Date startTime, @Param("endTime") Date endTime);

	public List<DeviceMonthElectricVo> export_deviceMonthElectric(@Param("startTime")Date startTime, @Param("endTime")Date endTime);

	public List<DeviceMonthNetVo> export_deviceMonthNet(@Param("startTime")Date startTime, @Param("endTime")Date endTime);

	public List<OrderGroupPerVo> export_orderGroupPer(@Param("startTime")Date startTime, @Param("endTime")Date endTime);

}