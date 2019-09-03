package cn.ritac.cpwater.comm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;


@Component
public class BaseReturnModel {

	/***
	 * 接口返回
	 * 
	 * @param msg    message
	 * @param status 状态码
	 * @param data   返回数据
	 * @return
	 */
//刷数据给接口
	public String resultJsonString(int status, String msg, Object data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("msg", msg);
		map.put("data", data);
		return JSONObject.toJSONString(map, SerializerFeature.WriteNonStringValueAsString, SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteMapNullValue);
	}

//刷通知给接口
	public String resultJson(int status, String msg) {
		JSONObject result = new JSONObject();
		result.put("status", status);
		result.put("msg", msg);
		return JSONObject.toJSONString(result, SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse);
	}

//刷错误给接口
	public String resultErrorJsonString(int status, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("msg", msg);
		return JSONObject.toJSONString(map, SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse);
	}

//刷登录给接口
	public String resultJsonLogin(int status, String msg, String token) {
		JSONObject result = new JSONObject();
		result.put("status", status);
		result.put("msg", msg);
		result.put("token", token);
		return JSONObject.toJSONString(result, SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse);
	}

	/**
	 * 接口成功返回方法
	 * 
	 * @param data 返回业务数据
	 * @return
	 */
//	public String resultSuccess(Object data) {
//		return resultJson(true, "接口调用成功", data);
//	}
//
	public String resultSuccess() {
		return resultJson(200, "操作成功，设备已删除！");
	}

	public String defalutValidate(BindingResult result) {
		// JSONObject errorMsg = new JSONObject();
		// errorMsg.put("code", !result.hasErrors());

		StringBuffer sBuffer = new StringBuffer();

		result.getAllErrors().forEach(k -> {
			sBuffer.append(k.getDefaultMessage());
		});
		return sBuffer.toString();
		// errorMsg.put("msg", sBuffer.toString());
		// errorMsg.put("data", null);
		// return
		// JSONObject.toJSONString(errorMsg,SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero,SerializerFeature.WriteNullBooleanAsFalse);
	}

	/**
	 * 接口失败返回方法
	 * 
	 * @param code 错误或成功代码
	 * @param msg  错误提示
	 * @return
	 */
	/*
	 * public String resultError(Boolean code, String msg) { return resultJson(code,
	 * msg, null); }
	 */

	/**
	 * 接口失败返回方法
	 * 
	 * @param msg 自定义错误提示
	 * @return
	 */
	/*
	 * public String resultError(String msg) { return resultJson(false, msg, null);
	 * }
	 */

	/**
	 * 接口失败返回方法
	 * 
	 * @return
	 */
	/*
	 * public String resultError(Boolean code) { String msg = ""; if (code) { msg =
	 * "接口调用成功"; } else { msg = "接口调用失败"; } return resultJson(code, msg, null); }
	 */

	/**
	 * 接口失败返回方法
	 * 
	 * @param code 错误码 101 :参数为空,未查询到信息 102 :必填参数为空 103:方法内部错误 404:服务器错误
	 *             401:用户不存在，请求失败 1000:用户名错误 1001:用户密码错误 1002:用户名或密码错误
	 * @return
	 */
	/*
	 * public String resultError(int code) { String msg = ""; switch (code) { case
	 * 101: msg = "参数为空,未查询到信息"; break; case 102: msg = "必填参数为空"; break; case 103:
	 * msg = "方法内部错误"; break; case 404: msg = "服务器错误"; break; case 401: msg =
	 * "用户不存在，请求失败"; break; case 1000: msg = "用户名错误"; break; case 1001: msg =
	 * "用户密码错误"; break; case 1002: msg = "用户名或密码错误"; break; default: msg = "";
	 * break; } return resultJson(false, msg, null); }
	 */
}