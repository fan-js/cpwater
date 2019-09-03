package cn.ritac.cpwater.comm.mqtt.newdev;

public class MsgDataKernelInfo {

	private int cpuTemper;
	private int ramProportion;
	private int romProportion;
	private int cpuProportion;

	public int getCpuTemper() {
		return cpuTemper;
	}

	public void setCpuTemper(int cpuTemper) {
		this.cpuTemper = cpuTemper;
	}

	public int getRamProportion() {
		return ramProportion;
	}

	public void setRamProportion(int ramProportion) {
		this.ramProportion = ramProportion;
	}

	public int getRomProportion() {
		return romProportion;
	}

	public void setRomProportion(int romProportion) {
		this.romProportion = romProportion;
	}

	public int getCpuProportion() {
		return cpuProportion;
	}

	public void setCpuProportion(int cpuProportion) {
		this.cpuProportion = cpuProportion;
	}

}
