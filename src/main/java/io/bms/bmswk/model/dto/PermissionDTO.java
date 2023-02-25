package io.bms.bmswk.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 *  permission DTO
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-25 20:48
 */
public class PermissionDTO {

    @TableField("permission_id")
    private Integer permissionId;
    @TableField("permission_name")
    private String permissionName;
}
