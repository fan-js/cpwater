package cn.ritac.cpwater.mybatis.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "cpwater_role_assets_fuction")
public class RoleAssetsFuction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 角色主键
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 资产操作主键
     */
    @Column(name = "assets_function_id")
    private Integer assetsFunctionId;

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
     * 获取角色主键
     *
     * @return role_id - 角色主键
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色主键
     *
     * @param roleId 角色主键
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取资产操作主键
     *
     * @return assets_function_id - 资产操作主键
     */
    public Integer getAssetsFunctionId() {
        return assetsFunctionId;
    }

    /**
     * 设置资产操作主键
     *
     * @param assetsFunctionId 资产操作主键
     */
    public void setAssetsFunctionId(Integer assetsFunctionId) {
        this.assetsFunctionId = assetsFunctionId;
    }
}