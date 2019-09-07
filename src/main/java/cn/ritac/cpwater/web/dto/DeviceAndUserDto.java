package cn.ritac.cpwater.web.dto;


/**
 * @Author:FanJSde
 * @Date:2019-8-30 11:23
 */
public class DeviceAndUserDto{

    private int deviceNum;
    private int userId;
    private String userPhone;

    public int getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(int deviceNum) {
        this.deviceNum = deviceNum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
