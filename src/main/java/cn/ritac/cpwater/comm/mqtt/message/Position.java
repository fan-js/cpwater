package cn.ritac.cpwater.comm.mqtt.message;

public class Position {
	//经度
	private String longitude;
	//维度
	private String dimension;
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	
}
