package cn.ritac.cpwater.service.impl;

import cn.ritac.cpwater.mybatis.mapper.DeviceAndUserMapper;
import cn.ritac.cpwater.mybatis.mapper.DevicesDoMapper;
import cn.ritac.cpwater.mybatis.model.DeviceAndUser;
import cn.ritac.cpwater.mybatis.model.DevicesDo;
import cn.ritac.cpwater.service.DeviceAndUserService;
import cn.ritac.cpwater.service.DevicesDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:FanJS
 * @Date:2019-8-30 9:27
 */
@Service
public class DeviceAndUserServiceImpl extends BaseServiceImpl<DeviceAndUser> implements DeviceAndUserService {
    @Autowired
    private DeviceAndUserMapper deviceAndUserMapper;
    @Autowired
    public void setInit() {
        super.setInit(deviceAndUserMapper);
    }

}
