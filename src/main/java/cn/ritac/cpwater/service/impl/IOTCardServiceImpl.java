/*
 *
 *
 */
package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ritac.cpwater.mybatis.mapper.IOTCardMapper;
import cn.ritac.cpwater.mybatis.model.IOTCard;
import cn.ritac.cpwater.service.IOTCardService;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> IOTCardServiceImpl
 *<br><b>CreatTime:</b> 2019年4月29日下午4:11:43
 */

@Service
public class IOTCardServiceImpl extends BaseServiceImpl<IOTCard> implements IOTCardService {
	@Autowired
	private IOTCardMapper cardMapper;

	@Autowired
	public void setInit() {
		super.setInit(cardMapper);
	}
}
