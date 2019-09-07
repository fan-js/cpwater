package cn.ritac.cpwater.mybatis.mapper;

import java.util.Date;
import java.util.List;

import cn.ritac.cpwater.web.dto.DevicesDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import cn.ritac.cpwater.mybatis.model.Devices;
import cn.ritac.cpwater.mybatis.model.DevicesState;
import cn.ritac.cpwater.web.dto.EventDto;
import cn.ritac.cpwater.web.dto.convert.EventVO;
import cn.ritac.cpwater.web.dto.convert.ProporVO;
import tk.mybatis.mapper.common.Mapper;

public interface DevicesMapper extends Mapper<Devices> {

	public Devices getDevice(@Param("deviceNum") String deviceNum, @Param("key") String key);

	public void saveStatusOnLine(@Param("device_id") String device_id, @Param("slaveId") String slaveId,
			@Param("nowTime") Date nowTime);

	public void saveStatusUnOnLine(@Param("device_id") String device_id, @Param("slaveId") String slaveId,
			@Param("nowTime") Date nowTime);

	public DevicesState getUserDevicesState(int uid);

	public void delBindUserDev(int device_id);

	public List<Devices> findDeviceOfGroup(int groupId);

	public List<Devices> findDeviceOfRole(@Param("groupId") int groupId, @Param("roleId") int roleId);

	public List<ProporVO> deviceProporList();

	public List<ProporVO> eventProporList();

	public List<EventVO> evenList(EventDto eventDto);

	@Select("SELECT * FROM cpwater_devices d WHERE d.id not in ( SELECT gd.device_id FROM cpwater_group_device gd )")
	@ResultMap("BaseResultMap")
	public List<Devices> findAllNotInGroup();

	public List<DevicesDto> findDeviceByCondition();

	public List<DevicesDto> findDeviceByUser(@Param("telePhone") String telePhone);

	public List<Devices> getDevicesNoPage();

}