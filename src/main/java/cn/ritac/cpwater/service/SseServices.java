/*
 *
 *
 */
package cn.ritac.cpwater.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> SseServices
 *<br><b>CreatTime:</b> 2019年5月17日下午3:33:23
 */
public interface SseServices extends BaseService<SseEmitter, Integer> {
	
	public void register(final SseEmitter emitter);

	public void sendMsg(String key , Object value);

}
