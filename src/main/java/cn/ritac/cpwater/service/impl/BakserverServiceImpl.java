/*
 *
 *
 */
package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ritac.cpwater.mybatis.mapper.BakserveMapper;
import cn.ritac.cpwater.mybatis.model.Bakserve;
import cn.ritac.cpwater.service.BakserverService;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> BakserverServiceImpl
 *<br><b>CreatTime:</b> 2019年4月30日上午10:45:26
 */

@Service
public class BakserverServiceImpl extends BaseServiceImpl<Bakserve> implements BakserverService {

	@Autowired
	private BakserveMapper bakserveMapper;

	@Autowired
	public void setInit() {
		super.setInit(bakserveMapper);
	}

}
