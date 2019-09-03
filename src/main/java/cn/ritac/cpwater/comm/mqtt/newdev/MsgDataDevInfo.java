package cn.ritac.cpwater.comm.mqtt.newdev;

import java.sql.Date;

public class MsgDataDevInfo {
	private int slaveEntry;
	private int[] slaveIDs;
	private String softVer;
	private String hardwareVer;
	private Date time;
	private int firstServer;
	private int dataUpTime;
	private int signalQuality;
	private int keepAliveInterval;
	private int restartCount;
	private int connForm;
	private int firstLink;
	private float current;
	private int voltage;
	private Long workingHours;
	private Long networkinghours;

	public Long getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(Long workingHours) {
		this.workingHours = workingHours;
	}

	public Long getNetworkinghours() {
		return networkinghours;
	}

	public void setNetworkinghours(Long networkinghours) {
		this.networkinghours = networkinghours;
	}

	public int getSlaveEntry() {
		return slaveEntry;
	}

	public void setSlaveEntry(int slaveEntry) {
		this.slaveEntry = slaveEntry;
	}

	public int[] getSlaveIDs() {
		return slaveIDs;
	}

	public void setSlaveIDs(int[] slaveIDs) {
		this.slaveIDs = slaveIDs;
	}

	public String getSoftVer() {
		return softVer;
	}

	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}

	public String getHardwareVer() {
		return hardwareVer;
	}

	public void setHardwareVer(String hardwareVer) {
		this.hardwareVer = hardwareVer;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getFirstServer() {
		return firstServer;
	}

	public void setFirstServer(int firstServer) {
		this.firstServer = firstServer;
	}

	public int getDataUpTime() {
		return dataUpTime;
	}

	public void setDataUpTime(int dataUpTime) {
		this.dataUpTime = dataUpTime;
	}

	public int getSignalQuality() {
		return signalQuality;
	}

	public void setSignalQuality(int signalQuality) {
		this.signalQuality = signalQuality;
	}

	public int getKeepAliveInterval() {
		return keepAliveInterval;
	}

	public void setKeepAliveInterval(int keepAliveInterval) {
		this.keepAliveInterval = keepAliveInterval;
	}

	public int getRestartCount() {
		return restartCount;
	}

	public void setRestartCount(int restartCount) {
		this.restartCount = restartCount;
	}

	public int getConnForm() {
		return connForm;
	}

	public void setConnForm(int connForm) {
		this.connForm = connForm;
	}

	public int getFirstLink() {
		return firstLink;
	}

	public void setFirstLink(int firstLink) {
		this.firstLink = firstLink;
	}

	public float getCurrent() {
		return current;
	}

	public void setCurrent(float current) {
		this.current = current;
	}

	public int getVoltage() {
		return voltage;
	}

	public void setVoltage(int voltage) {
		this.voltage = voltage;
	}

}
