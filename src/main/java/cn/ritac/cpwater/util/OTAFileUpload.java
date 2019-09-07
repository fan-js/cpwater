//package cn.ritac.cpwater.util;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import cn.ritac.cpwater.web.controller.api.BaseController;
//import cn.ritac.cpwater.web.dto.DeviceOTA;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//import org.apache.tomcat.util.http.fileupload.FileUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping(value = "/OTA")
//public class OTAFileUpload extends BaseController {
//	@Autowired
//	private DevicesOTAService deviceOTAService;
//
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	/**
//	 * 实现文件上传
//	 */
//	@RequestMapping("/fileUpload")
//	@ResponseBody
//	@Transactional(readOnly = false)
//	public String fileUpload(@RequestParam("fileOTA") MultipartFile file, @RequestParam("filePath") String filePath,
//			@RequestParam("deviceIds") List<String> deviceIds, HttpServletRequest request) {
//		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			return returnLogic.resultErrorJsonString(401, "请先登录！");
//		}
//				if (file.isEmpty()) {
//					return returnLogic.resultErrorJsonString(404,"请选择要上传的文件!");
//				}
//				String productId = "";
//				String version = "";
//				//获取当前应用目录
//				String filePathTmp = request.getSession().getServletContext().getRealPath("/");
//				StringBuffer tmpPath = new StringBuffer();
//				//当前目录新建目录
//				tmpPath = tmpPath.append(filePathTmp).append("upload/");
//				//目录如果不存在进行创建
//				File dir = new File(tmpPath.toString());
//				dir.setWritable(true, false);
//				if (!dir.exists()) {
//					dir.mkdir();
//				}
//				// 文件名
//				String fileName = file.getOriginalFilename();
//
//				StringBuffer path = new StringBuffer();
//							 path = path.append(tmpPath).append(fileName);
//				File tempFile = null;
//				// save to the /upload path
//				try {
//					tempFile = new File(path.toString());
//					FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
//					// md5校验码
//					String md5 = MD5CheckFile.getFileMD5String(tempFile);
//					File del = new File(tempFile.toURI());
//					del.delete();
////					System.out.println(md5);
//
//					if (fileName.contains("_")) {
//						// 产品类型
//						productId = fileName.split("_")[0];
//						int beginindex = fileName.lastIndexOf("_");
//						int endindex = fileName.lastIndexOf(".");
//						// 版本
//						version = fileName.substring(beginindex + 1, endindex);
//					}
//					// 文件大小
//					long fileSize = file.getSize();
//
//					// 设备下载固件路径
//					String downPath = "http://www.ritac.cn/OTA/";
//
//					// 文件存储路径 需要设置Linux当前目录权限 如： sudo chmod -R 777 /www/wwwroot/www.ritac.cn/OTA
//					String filePathFxd = "/www/wwwroot/www.ritac.cn/OTA/";
//					//win 路径测试，正式打包需要注释，同时释放前一行代码
////					String filePathFxd = "D:/www/wwwroot/www.ritac.cn/OTA/";
//
//					StringBuffer fileTmpPath = new StringBuffer();
//								 fileTmpPath = fileTmpPath.append(filePathFxd).append(filePath).append("/");
//					//文件写入路径，存进数据库
//					StringBuffer uploadPath = new StringBuffer();
//								 uploadPath = uploadPath.append(fileTmpPath).append(fileName);
//					// 存库，下载路径
//					StringBuffer pathUrl = new StringBuffer();
//								 pathUrl = pathUrl.append(downPath).append(filePath).append("/").append(fileName);
//
//					// 封装ota对象
//					DeviceOTA otaInfo = deviceOTAService.findOTAByMd5(md5);
//					if (otaInfo == null) {
//
//						File dest_p = new File(fileTmpPath.toString());
//						dest_p.setWritable(true, false);
//						if (!dest_p.exists()) { // 判断文件父目录是否存在
//							dest_p.mkdirs();
//						}
//						// 保存文件
//						String destPath = fileTmpPath.append(fileName).toString();
//						File dest = new File(destPath);
//						file.transferTo(dest);
//
//						DeviceOTA ota = new DeviceOTA();
//						ota.setOtaCode(md5);
//						StringBuilder builder = new StringBuilder();
//						for (String str : deviceIds) {
//							builder.append(str + ":");
//						}
//						ota.setOtaDeviceid(builder.toString().substring(0, builder.length() - 1));
//						ota.setOtaName(fileName);
//						ota.setOtaProductid(productId);
//						ota.setOtaSize(fileSize + "");
//						ota.setUploadPath(uploadPath.toString());
//						ota.setOtaUrl(pathUrl.toString());
//						ota.setOtaVersion(version);
//						ota.setUploadTime(new Date());
//						deviceOTAService.save(ota);
//
//					} else {
//						return returnLogic.resultErrorJsonString("OTA升级包已存在,无需重复上传:", 404, false);
//					}
//					logger.info("OTA升级包上传成功---:");
//					return returnLogic.resultJsonString("OTA升级包上传成功", 200, true, null);
//				} catch (IllegalStateException e) {
//					e.printStackTrace();
//					logger.error("OTA升级包上传失败:" + e.getMessage() + ";请稍后重试！");
//					return returnLogic.resultErrorJsonString("OTA升级包上传失败:" + e.getMessage(), 404, false);
//				} catch (IOException e) {
//					e.printStackTrace();
//					logger.error("控制命令下发出错:" + e.getMessage() + ";请稍后重试！");
//					return returnLogic.resultErrorJsonString("OTA升级包上传失败:" + e.getMessage(), 404, false);
//				}
//
//
//
//	}
//
//	/**
//	 * 实现文件删除
//	 */
//	@RequestMapping("/fileDel")
//	@ResponseBody
//	@Transactional(readOnly = false)
//	public String fileDel(@RequestBody Map<String, Object> params, HttpServletRequest request) {
//		Object otaIds = params.get("otaIds");
//		List<Integer> otaList = (List<Integer>) otaIds;
//		for (Integer id : otaList) {
//			// 根据主键查询ota
//			DeviceOTA ota = deviceOTAService.findById_int(id);
//			if (ota != null) {
//
//				//获取当前应用目录
//				String filePathTmp = request.getSession().getServletContext().getRealPath("/");
//				StringBuffer tmpPath = new StringBuffer();
//				//当前目录新建目录
//				tmpPath = tmpPath.append(filePathTmp).append("upload/");
//				String tp = tmpPath.append(ota.getOtaName()).toString();
//				File tmdel = new File(tp);
//					 tmdel.delete();
//				// 删除文件夹内文件
//				String path = ota.getUploadPath();
//				File del = new File(path);
//					 del.delete();
//				// 删除数据库信息
//				deviceOTAService.deleteObject(ota);
//			}
//		}
//		return returnLogic.resultJsonString("OTA升级包删除成功", 200, true, null);
//	}
//
//	/**
//	 * 获取安装包列表
//	 */
//	@RequestMapping("/fileList")
//	@ResponseBody
//	@Transactional(readOnly = false)
//	public String fileList(@RequestBody Map<String, String> params) {
//		String productId = params.get("productId");
//		String deviceId = params.get("deviceId");
//		// 根据产品类型获取所有OTA升级包
//		DeviceOTA ota = new DeviceOTA();
//		ota.setOtaProductid(productId);
//		List<DeviceOTA> otaList = deviceOTAService.findList(ota);
//		List<DeviceOTA> otaInfoList = new ArrayList<>();
//		if (!otaList.isEmpty()) {
//			for (DeviceOTA dota : otaList) {
//				String deviceIds = dota.getOtaDeviceid();
//				if (!SpringStringUtils.isEmpty(deviceIds)) {
//					if (!SpringStringUtils.isEmpty(deviceId)) {
//						if (deviceIds.contains(deviceId)) {
//							otaInfoList.add(dota);
//						}
//					} else {
//						return returnLogic.resultJsonString("OTA列表查询成功！", 200, true, otaList);
//					}
//				}
//			}
//			if (!otaInfoList.isEmpty()) {
//				return returnLogic.resultJsonString("OTA列表查询成功！", 200, true, otaInfoList);
//			} else {
//				return returnLogic.resultErrorJsonString("该设备暂未有升级包！！", 404, false);
//			}
//
//		} else {
//			return returnLogic.resultErrorJsonString("该设备类型暂未有升级包！", 404, false);
//		}
//
//	}
//}
