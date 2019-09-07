/*
 *
 *
 */
package cn.ritac.cpwater.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.ritac.cpwater.service.SseServices;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> SseServicesImp
 *<br><b>CreatTime:</b> 2019年5月28日上午9:54:26
 */
@Service
public class SseServicesImp extends BaseServiceImpl<SseEmitter> implements SseServices {
	private Logger logger = LoggerFactory.getLogger(SseServicesImp.class);
	private static List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

	/*
	 * 1. 提供SseEmitter对象注册验证功能
	 */
	public void register(final SseEmitter emitter) {
		// 判断当前SseEmitter是否已反馈,如果是则移除集合内当前对象
		emitter.onCompletion(() -> {
			removeEmitter(emitter);
		});
		// 判断当前SseEmitter是否超时,如果是则移除集合内当前对象
		emitter.onTimeout(() -> {
			removeEmitter(emitter);
		});
		// 验证通过后把传入的SseEmitter对象放入集合
		emitters.add(emitter);
	}

	/*
	 * 2. 移除集合内所有SseEmitter对象
	 */
	private void removeEmitter(final SseEmitter emitter) {
		emitters.remove(emitter);
	}

	/*
	 * 3. 通过SseEmitter对象发送信息
	 */
	@Async
	public void sendMsg(String key, Object value) {
		// 存放无效的SseEmitter对象,下边集中销毁
		List<SseEmitter> deadEmitters = new ArrayList<SseEmitter>();
		// 给集合内所有SseEitter对象发送信息
		emitters.forEach(emitter -> {
			// 事件对象
			SseEventBuilder sseEventBuilder = SseEmitter.event();
			logger.info("发送数据：--->" + value);
			// 添加event
			sseEventBuilder.name(key);
			// 加载数据
			sseEventBuilder.data(JSONObject.toJSONString(value, SerializerFeature.WriteDateUseDateFormat));
			try {
				// 发送
				emitter.send(sseEventBuilder);
				System.out.printf("sseEventBuilder.toString()"+sseEventBuilder.toString());
			} catch (IOException e) {
				deadEmitters.add(emitter);
			}
			/*保持连接，多次发送，超时断开*/
			// emitter.complete();
		});
		// 遍历结束,从集合内清掉无效对象
		emitters.removeAll(deadEmitters);
	}
}
