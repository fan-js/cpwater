package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "cpwater_reg_camera")
public class RegCamera implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 设备主键
     */
    @Column(name = "device_id")
    private Integer deviceId;

    /**
     * 摄相机序列号
     */
    private String sn;

    /**
     * 摄像机型号
     */
    private String model;

    /**
     * 摄像机MAC
     */
    private String mac;

    /**
     * 摄像机IP地址
     */
    private String ip;

    /**
     *  状态 online-在线 ;offline-离线
     */
    private String state;
    
    private String account;
    
    private String password;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取设备主键
     *
     * @return device_id - 设备主键
     */
    public Integer getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备主键
     *
     * @param deviceId 设备主键
     */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取摄相机序列号
     *
     * @return sn - 摄相机序列号
     */
    public String getSn() {
        return sn;
    }

    /**
     * 设置摄相机序列号
     *
     * @param sn 摄相机序列号
     */
    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    /**
     * 获取摄像机型号
     *
     * @return model - 摄像机型号
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置摄像机型号
     *
     * @param model 摄像机型号
     */
    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    /**
     * 获取摄像机MAC
     *
     * @return mac - 摄像机MAC
     */
    public String getMac() {
        return mac;
    }

    /**
     * 设置摄像机MAC
     *
     * @param mac 摄像机MAC
     */
    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    /**
     * 获取摄像机IP地址
     *
     * @return ip - 摄像机IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置摄像机IP地址
     *
     * @param ip 摄像机IP地址
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取 状态 online-在线 ;offline-离线
     *
     * @return state -  状态 online-在线 ;offline-离线
     */
    public String getState() {
        return state;
    }

    /**
     * 设置 状态 online-在线 ;offline-离线
     *
     * @param state  状态 online-在线 ;offline-离线
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}