package cn.ritac.cpwater.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class MD5CheckFile {

	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };
	protected static MessageDigest messagedigest = null;
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			System.err.println(MD5CheckFile.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
			nsaex.printStackTrace();
		}
	}

	public static String getFileMD5String(File file) throws IOException {
		@SuppressWarnings("resource")
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		messagedigest.update(byteBuffer);
		return bufferToHex(messagedigest.digest());
	}

	public static String getMD5String(String s) {
		return getMD5String(s.getBytes());
	}

	public static String getMD5String(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	/**
	 *
	 *进行密码校验
	 * Method： checkPassword（String password, String md5PwdStr）
	 * @param  password ：
	 * 			明文，页面接受参数
	 *@param   md5PwdStr ：
	 *			密文，数据库存放数据
	 * @return  
	 *	true -- 验证通过 ； false -- 验证失败
	 * */
	public static boolean checkPassword(String password, String md5PwdStr) {
		String s = getMD5String(password);
		System.out.println("s:   " + s + "    md5pwdstr:    " + md5PwdStr);
		return s.equals(md5PwdStr);
	}

	/**
	 * 获取唯一UUID字符串
	 * @param isReplace   true 开启字符替换；false 不开起（默认项）
	 * @param isUpperCase true字母 转大写，默认为小写
	 * @param replaceChar 被替换字符 <若开启替换>
	 * @param replaceWithChar 替换成字符 <若开启替换>
	 * @return 操作后结果字符串
	 * see：<UUIDStrCode(true, true, "-", "0")>
	 */
	public static String UUIDStrCode(boolean isReplace, boolean isUpperCase, String replaceChar,
			String replaceWithChar) {
		String UUID_Str = UUID.randomUUID().toString().toLowerCase();
		if (isReplace) {
			UUID_Str = UUID_Str.replace(replaceChar, replaceWithChar);
		}
		if (isUpperCase) {
			UUID_Str = UUID_Str.toUpperCase();
		}
		return UUID_Str;
	}

	/**
	 * 获取指定长度数字串，可指定开头
	 * @param fixedPart 开头固定部分，单个数字或字母
	 * @param codeLength 生成串长度，长度不能小于12
	 * @return
	 */
	public static String codeAnyLength(String fixedPart, int codeLength) {
		int fixedPartLength = fixedPart.length();
		if (fixedPartLength > 1) {
			fixedPart = fixedPart.substring(0, 1);
		}
		if (codeLength < 12) {
			codeLength = 12;
		}
		codeLength -= 1;
		String code = UUIDStrCode(false, false, null, null);
		int hashCode = code.hashCode();
		if (hashCode < 0) {
			hashCode = -hashCode;
		}
		code = String.format("%0" + codeLength + "d", hashCode);
		return new StringBuffer().append(fixedPart).append(code).toString();
	}

}