package cn.ritac.cpwater.web.dto;


/**
 * @Author:FanJSde
 * @Date:2019-8-30 11:23
 */
public class DeviceAndUserDto{

    private int deviceId;
    private int userId;
    private String userPhone;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
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
