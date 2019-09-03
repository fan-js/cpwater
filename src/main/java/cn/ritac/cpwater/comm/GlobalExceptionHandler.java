package cn.ritac.cpwater.comm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

@ControllerAdvice
public class GlobalExceptionHandler {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	public BaseReturnModel returnLogic;

	@ExceptionHandler(IllegalStateException.class)
	public void showTwo() {
		logger.warn("+++++ 数据推送失败，浏览器已关闭 ++++ ");
	}

	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public void showOne() {
		logger.warn("+++++ 启用转换器成功 ++++ ");
	}

	@ExceptionHandler(IOException.class)
	public void manyIO() {
		logger.warn("++++++++ 操作频繁，主机已拒绝当前请求。+++++++ ");
	}

	@ExceptionHandler(AsyncRequestTimeoutException.class)
	public void handleTimeout() {
		logger.warn("++++++++ 数据推送暂停,无客户端连接。++++++++ ");
	}

	@ExceptionHandler(value = Exception.class)
	public String defaultErrorHandler(HttpServletRequest req, Exception e) {
		String msg = e.getMessage();
		int code = 206;
		String resMsg = e.getMessage();
		if (!StringUtils.isEmpty(msg)) {
			String[] msgs = msg.split("-");
			if (msgs.length == 2) {
				resMsg = msgs[0];
				code = Integer.parseInt(msgs[1]);
			}
		}
		e.printStackTrace();
		return returnLogic.resultErrorJsonString(code, resMsg);

	}

	/**
	 * 设置日期传输格式
	 * @param webDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), false));
	}

}
