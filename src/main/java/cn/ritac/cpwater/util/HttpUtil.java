/*
 *
 *
 */
package cn.ritac.cpwater.util;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> HttpUtil
 *<br><b>CreatTime:</b> 2019年5月10日上午10:51:27
 */
public class HttpUtil {

	/**
	 * 发送http post请求 , 可以自定义请求地址及参数; 放开注释可以实现获取请求后返回参数模式
	 * @param url 请求地址
	 * @param msgData 传递参数
	 */
	public static void sendPost(String url, String msgData) {
		// String response = null;
		try {
			CloseableHttpClient httpclient = null;
			CloseableHttpResponse httpresponse = null;
			try {
				httpclient = HttpClients.createDefault();
				HttpPost httpPost = new HttpPost(url);
				// StringEntity stringentity = new StringEntity(msgData,
				// ContentType.create("text/json", "UTF-8"));
				StringEntity stringentity = new StringEntity(msgData, ContentType.create("text/event-stream", "UTF-8"));
				httpPost.setEntity(stringentity);
				httpresponse = httpclient.execute(httpPost);
				// response = EntityUtils.toString(httpresponse.getEntity());
			} finally {
				if (httpclient != null) {
					httpclient.close();
				}
				if (httpresponse != null) {
					httpresponse.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return response;
	}


	/**
	 * 发送http get请求 , 可以自定义请求地址及参数
	 * @param url 请求地址
	 * @param param 传递参数
	 */
	public static void sendGet(String url, String param) {
		try {
			CloseableHttpClient httpclient = null;
			CloseableHttpResponse httpresponse = null;
			try {
				httpclient = HttpClients.createDefault();
				HttpGet httpGet = new HttpGet(url + "?params=" + param);
				httpclient.execute(httpGet);
			} finally {
				if (httpclient != null) {
					httpclient.close();
				}
				if (httpresponse != null) {
					httpresponse.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
