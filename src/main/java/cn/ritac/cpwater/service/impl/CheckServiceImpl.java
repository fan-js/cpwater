package cn.ritac.cpwater.service.impl;

import cn.ritac.cpwater.mybatis.mapper.CheckMapper;
import cn.ritac.cpwater.mybatis.mapper.DeviceAndUserMapper;
import cn.ritac.cpwater.mybatis.model.Check;
import cn.ritac.cpwater.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:FanJS
 * @Date:2019-9-6 8:26
 */

@Service
public class CheckServiceImpl extends BaseServiceImpl<Check> implements CheckService {

    @Autowired
    private CheckMapper checkMapper;
    @Autowired
    public void setInit() {
        super.setInit(checkMapper);
    }
}
