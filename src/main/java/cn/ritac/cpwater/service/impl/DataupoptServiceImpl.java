/*
 *
 *
 */
package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ritac.cpwater.mybatis.mapper.DataupoptMapper;
import cn.ritac.cpwater.mybatis.model.Dataupopt;
import cn.ritac.cpwater.service.DataupoptService;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> DataupoptServiceImpl
 *<br><b>CreatTime:</b> 2019年4月30日上午10:48:20
 */

@Service
public class DataupoptServiceImpl extends BaseServiceImpl<Dataupopt> implements DataupoptService {

	@Autowired
	private DataupoptMapper dataupoptMapper;

	@Autowired
	public void setInit() {
		super.setInit(dataupoptMapper);
	}

}
