package cn.ritac.cpwater.comm;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;


public class MessageGenerate {
	private static String _platformName = "【范金帅】";

	private static Dictionary<MessageTemplateTypeEnum, String> _dicContent = new Hashtable<MessageTemplateTypeEnum, String>();

	static {
		_dicContent.put(MessageTemplateTypeEnum.RegisterTemplate, "您的验证码是:%s");
		_dicContent.put(MessageTemplateTypeEnum.ResetTemplate, "您的验证码是:%s");
		_dicContent.put(MessageTemplateTypeEnum.OrderMsgTemplate, "您有新的工单:%s");
		_dicContent.put(MessageTemplateTypeEnum.YiChang,"检测到系统异常，请及时处理！");

	}

	public static String CreateRandom() {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += random.nextInt(10);
		}
		return result;

	}


	
}
