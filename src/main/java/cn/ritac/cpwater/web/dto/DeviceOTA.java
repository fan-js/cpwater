package cn.ritac.cpwater.web.dto;

import java.util.Date;

/**
 * @Author:FanJS
 * @Date:2019-9-6 18:36
 */
public class DeviceOTA {
/**
 * 主键自增长id
 * */
    private Integer id;
    /**
     * ota升级包路径
     * */
    private String otaUrl;
    /**
     * 加载路径
     * */
    private String uploadPath;
    /**
     * 版本
     * */
    private String otaVersion;
    /**
     * 文件名称
     * */
    private String otaName;
    /**
     * 文件大小
     * */
    private String otaSize;
    /**
     * MD5校验码
     * */
    private String otaCode;
    /**
     * 升级包所实用的产品类型
     * */
    private String otaProductId;
    /**
     * 升级包所适用的设备id
     * */
    private String otaDeviceId;
    /**
     *
     * */
    private Date uploadTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOtaUrl() {
        return otaUrl;
    }

    public void setOtaUrl(String otaUrl) {
        this.otaUrl = otaUrl;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getOtaVersion() {
        return otaVersion;
    }

    public void setOtaVersion(String otaVersion) {
        this.otaVersion = otaVersion;
    }

    public String getOtaName() {
        return otaName;
    }

    public void setOtaName(String otaName) {
        this.otaName = otaName;
    }

    public String getOtaSize() {
        return otaSize;
    }

    public void setOtaSize(String otaSize) {
        this.otaSize = otaSize;
    }

    public String getOtaCode() {
        return otaCode;
    }

    public void setOtaCode(String otaCode) {
        this.otaCode = otaCode;
    }

    public String getOtaProductId() {
        return otaProductId;
    }

    public void setOtaProductId(String otaProductId) {
        this.otaProductId = otaProductId;
    }

    public String getOtaDeviceId() {
        return otaDeviceId;
    }

    public void setOtaDeviceId(String otaDeviceId) {
        this.otaDeviceId = otaDeviceId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date upload_time) {
        this.uploadTime = upload_time;
    }
}
