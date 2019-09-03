package cn.ritac.cpwater.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ritac.cpwater.mybatis.model.WorkingHour;
import cn.ritac.cpwater.web.dto.ContVoltnetPojo;
import tk.mybatis.mapper.common.Mapper;

public interface WorkingHourMapper extends Mapper<WorkingHour> {

	public List<ContVoltnetPojo> countVoltnetPropor();

	public void updateMinutesByYearMonth(@Param("minutesCount") Integer minutesCount,
			@Param("tmp_years") Integer tmp_years, @Param("tmp_months") Integer tmp_months);
}