/*
 *
 *
 */
package cn.ritac.cpwater.web.controller.sse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import cn.ritac.cpwater.service.SseServices;

import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.Random;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> SseController
 *<br><b>CreatTime:</b> 2019年5月28日上午9:54:38
 */
@RequestMapping("/sendMsg")
@RestController
public class SseController {

	@Autowired
	SseServices sseServicesImp;

	/**
	 * 接受客户端请求,保持连接
	 * @return SseEmitter对象,提前返回
	 */
	@GetMapping(value = "/push", produces = "text/event-stream;charset=UTF-8")
	public SseEmitter push() {
		/*
		 * 1. 客户端接入,创建SseEmitter对象
		 * 2. 把对象传入service的register方法进行验证
		 */
		SseEmitter emitter = new SseEmitter(600000L);
		// 验证新对象
		sseServicesImp.register(emitter);
		return emitter;
	}

	/*
	 * 1. 发送HTTP请求调用action方法
	 * 2. 在mqtt实现类中调用消息service发送方法
	 */
	@GetMapping(value = "/callback")
	public void callBack(String devNum) {
		// 1. 先调用mqtt回去设备数据方法
		String msgDate = "模拟数据 : " + devNum;
		// 2. 调用消息方法把获取的封装数据发送
		sseServicesImp.sendMsg("test", msgDate);

	}
}
