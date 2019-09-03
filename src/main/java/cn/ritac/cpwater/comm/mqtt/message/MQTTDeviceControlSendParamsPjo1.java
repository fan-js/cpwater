package cn.ritac.cpwater.comm.mqtt.message;

/**
 * @Author:FanJS
 * @Date:2019-8-21 15:04
 */
public class MQTTDeviceControlSendParamsPjo1 {

    public int addr;
    public int isDown;

    public int getAddr() {
        return addr;
    }

    public void setAddr(int addr) {
        this.addr = addr;
    }

    public int getIsDown() {
        return isDown;
    }

    public void setIsDown(int isDown) {
        this.isDown = isDown;
    }
}
