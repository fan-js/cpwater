package cn.ritac.cpwater.comm.mqtt.newdev;

public class StatusDevInfo {

	private int slaveEntry;
	private int[] slaveIDs;

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

}
