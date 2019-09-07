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
    private String ota_url;
    /**
     * 加载路径
     * */
    private String uploadPath;
    /**
     * 版本
     * */
    private String ota_version;
    /**
     * 文件名称
     * */
    private String ota_name;
    /**
     * 文件大小
     * */
    private String ota_size;
    /**
     * MD5校验码
     * */
    private String ota_code;
    /**
     * 升级包所实用的产品类型
     * */
    private String ota_productId;
    /**
     * 升级包所适用的设备id
     * */
    private String ota_deviceId;
    /**
     *
     * */
    private Date upload_time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOta_url() {
        return ota_url;
    }

    public void setOta_url(String ota_url) {
        this.ota_url = ota_url;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getOta_version() {
        return ota_version;
    }

    public void setOta_version(String ota_version) {
        this.ota_version = ota_version;
    }

    public String getOta_name() {
        return ota_name;
    }

    public void setOta_name(String ota_name) {
        this.ota_name = ota_name;
    }

    public String getOta_size() {
        return ota_size;
    }

    public void setOta_size(String ota_size) {
        this.ota_size = ota_size;
    }

    public String getOta_code() {
        return ota_code;
    }

    public void setOta_code(String ota_code) {
        this.ota_code = ota_code;
    }

    public String getOta_productId() {
        return ota_productId;
    }

    public void setOta_productId(String ota_productId) {
        this.ota_productId = ota_productId;
    }

    public String getOta_deviceId() {
        return ota_deviceId;
    }

    public void setOta_deviceId(String ota_deviceId) {
        this.ota_deviceId = ota_deviceId;
    }

    public Date getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Date upload_time) {
        this.upload_time = upload_time;
    }
}
