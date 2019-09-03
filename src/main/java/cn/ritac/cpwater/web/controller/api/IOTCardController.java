/*
 *
 *
 */
package cn.ritac.cpwater.web.controller.api;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import cn.ritac.cpwater.mybatis.model.Devices;
import cn.ritac.cpwater.mybatis.model.IOTCard;
import cn.ritac.cpwater.service.DevicesService;
import cn.ritac.cpwater.service.IOTCardService;
import cn.ritac.cpwater.web.dto.IOTCardDto;

/**
 *<b>Description:</b><br>
 * @author admin
 * @version V1.0<br>
 * @Note
 *<b>ProjectName:</b> cpwater
 *<br><b>ClassName:</b> IOTCardController
 *<br><b>CreatTime:</b> 2019年4月29日下午4:07:44
 */

@RestController
@RequestMapping(value = "/card")
public class IOTCardController extends BaseController {

	@Autowired
	private IOTCardService cardService;

	@Autowired
	private DevicesService devicesService;

	/**
	 * 物联卡列表
	 * @param cardDto
	 * @return cardList
	 */
	@GetMapping("/findCard")
	public String findCard(IOTCardDto cardDto) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(cardDto)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误！");
		}
		Integer pageIndex = cardDto.getPageIndex();
		Integer pageSize = cardDto.getPageSize();
		IOTCard card = new IOTCard();
		BeanUtils.copyProperties(cardDto, card);
		if (!StringUtils.isEmpty(cardDto.getDeviceNum()))
			card.setDeviceNum(cardDto.getDeviceNum());
		if (!StringUtils.isEmpty(cardDto.getCardNum()))
			card.setCardNum(cardDto.getCardNum());
		PageInfo<IOTCard> cardList = cardService.page(pageIndex, pageSize, card);
		return returnLogic.resultJsonString(200, "查询成功", cardList);
	}

	@PostMapping("/savaCard")
	public String savaCard(@RequestBody IOTCardDto cardDto) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(cardDto)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误！");
		}

		Devices dev = new Devices();
		dev.setDeviceNum(cardDto.getDeviceNum());
		Devices isExitsDev = devicesService.find(dev);
		if (StringUtils.isEmpty(isExitsDev)) {
			return returnLogic.resultErrorJsonString(206, "输入设备编号不存在！");
		}

		IOTCard card = new IOTCard();
		card.setCardNum(cardDto.getCardNum());
		IOTCard isExitsCard = cardService.find(card);
		if (!StringUtils.isEmpty(isExitsCard)) {
			return returnLogic.resultErrorJsonString(206, "输入卡号已存在！");
		}
		BeanUtils.copyProperties(cardDto, card);
		cardService.save(card);
		return returnLogic.resultJson(200, "添加成功。");
	}

	@PutMapping("/updateCard")
	public String updateCard(@RequestBody IOTCardDto cardDto) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(cardDto)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误！");
		}
		Devices dev = new Devices();
		dev.setDeviceNum(cardDto.getDeviceNum());
		Devices isExitsDev = devicesService.find(dev);
		if (StringUtils.isEmpty(isExitsDev)) {
			return returnLogic.resultErrorJsonString(206, "输入设备编号不存在！");
		}
		IOTCard card = new IOTCard();
		card.setCardNum(cardDto.getCardNum());
		IOTCard isExitsCard = cardService.find(card);
		if (!StringUtils.isEmpty(isExitsCard)) {
			return returnLogic.resultErrorJsonString(206, "输入卡号已存在！");
		}
		BeanUtils.copyProperties(cardDto, card);
		cardService.update(card);
		return returnLogic.resultJson(200, "修改成功。");
	}

	/**
	 * 按主键删除卡
	 * @param id
	 * @return msg
	 */
	@DeleteMapping("/deleteCard")
	public String deleteCard(int id) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return returnLogic.resultErrorJsonString(401, "请先登录！");
		}
		if (StringUtils.isEmpty(id)) {
			return returnLogic.resultErrorJsonString(206, "输入参数有误！");
		}
		cardService.delete(new Integer[] { id });
		return returnLogic.resultJson(200, "删除成功。");
	}

}
