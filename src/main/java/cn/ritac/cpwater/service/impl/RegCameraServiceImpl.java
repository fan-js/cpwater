/*
 *
 *
 */
package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ritac.cpwater.mybatis.mapper.RegCameraMapper;
import cn.ritac.cpwater.mybatis.model.RegCamera;
import cn.ritac.cpwater.service.RegCameraService;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> RegCameraServiceImpl
 *<br><b>CreatTime:</b> 2019年5月6日下午3:59:55
 */

@Service
public class RegCameraServiceImpl extends BaseServiceImpl<RegCamera> implements RegCameraService {

	@Autowired
	RegCameraMapper cameraMapper;

	@Autowired
	public void setInit() {
		super.setInit(cameraMapper);
	}

}
