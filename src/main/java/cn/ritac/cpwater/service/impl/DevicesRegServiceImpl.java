package cn.ritac.cpwater.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ritac.cpwater.mybatis.mapper.DevicesRegMapper;

import cn.ritac.cpwater.mybatis.model.DevicesReg;

import cn.ritac.cpwater.service.DevicesRegService;

@Service
public class DevicesRegServiceImpl  extends BaseServiceImpl<DevicesReg> implements DevicesRegService{

	@Autowired
	private DevicesRegMapper devicesRegMapper;
	@Autowired
	public void setInit() {
		super.setInit(devicesRegMapper);
	}


	@Override
	public void insert(DevicesReg devicesReg) {
		System.out.printf("ceshi0000000000000000000000000000000000000000000");

	}
}

