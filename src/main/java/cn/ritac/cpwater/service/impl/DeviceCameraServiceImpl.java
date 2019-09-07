package cn.ritac.cpwater.service.impl;

import cn.ritac.cpwater.mybatis.mapper.CheckMapper;
import cn.ritac.cpwater.mybatis.mapper.DeviceCameraMapper;
import cn.ritac.cpwater.mybatis.model.Check;
import cn.ritac.cpwater.mybatis.model.DeviceCamera;
import cn.ritac.cpwater.service.CheckService;
import cn.ritac.cpwater.service.DeviceCameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:FanJS
 * @Date:2019-9-6 19:23
 */

@Service
public class DeviceCameraServiceImpl extends BaseServiceImpl<DeviceCamera> implements DeviceCameraService {

    @Autowired
    private DeviceCameraMapper deviceCameraMapper;
    @Autowired
    public void setInit() {
        super.setInit(deviceCameraMapper);
    }

}
