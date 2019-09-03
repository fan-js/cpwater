package cn.ritac.cpwater.comm.jobs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import cn.ritac.cpwater.service.DevicesService;

@Component
@EnableScheduling
public class DevicesOnLineJobs {

	@Autowired
	private DevicesService devicesService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 更新总时长
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void cronJob() {
		devicesService.updateHoursAll();
		logger.info(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + " >>更新总时长....");
	}

	/**
	 * 更新工作时长
	 */
	 @Scheduled(cron = "0 0 0/1 * * ?")
//	@Scheduled(cron = "0 0/10 * * * ?")
	public void lengthOfTimeJob() {
//		devicesService.updateHoursAndLenthOfTime();
		logger.info(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + " >>更新工作时长....");
	}

}
