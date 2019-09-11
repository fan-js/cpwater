package cn.ritac.cpwater.service;

import cn.ritac.cpwater.mybatis.model.DevicesOta;
import cn.ritac.cpwater.mybatis.model.Users;
import cn.ritac.cpwater.web.dto.DeviceOTA;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.provider.base.BaseUpdateProvider;

/**
 * @Author:FanJS
 * @Date:2019-9-9 14:02
 */

@RegisterMapper
public interface DevicesOTAService extends BaseService<DevicesOta, Integer> {



}
