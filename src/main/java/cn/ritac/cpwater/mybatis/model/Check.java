package cn.ritac.cpwater.mybatis.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author:FanJS
 * @Date:2019-9-5 18:14
 */

@Table(name = "cpwater_check")
public class Check implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")

    private Integer id;

    private String phone;

    private String type;

    @Column(name = "date_time")
    private String dateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
