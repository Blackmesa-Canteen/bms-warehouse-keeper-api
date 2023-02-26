package io.bms.bmswk.mapper;

import io.bms.bmswk.model.dto.PermissionDTO;
import io.bms.bmswk.model.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper interface
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<PermissionDTO> getPermissionsByRoleName(@Param("roleName") String roleName);

    List<PermissionDTO> getPermissionsByRoleId(@Param("roleId") Integer roleId);

}
