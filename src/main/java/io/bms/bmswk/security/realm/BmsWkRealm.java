package io.bms.bmswk.security.realm;

import io.bms.bmswk.exception.AuthException;
import io.bms.bmswk.model.dto.PermissionDTO;
import io.bms.bmswk.model.entity.Role;
import io.bms.bmswk.model.entity.User;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.security.model.AuthToken;
import io.bms.bmswk.security.service.IPermissionService;
import io.bms.bmswk.security.service.IRoleService;
import io.bms.bmswk.security.util.ITokenHelper;
import io.bms.bmswk.security.util.JwtTokenHelper;
import io.bms.bmswk.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  shiro auth realm
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-27 14:54
 */
public class BmsWkRealm extends AuthorizingRealm {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private ITokenHelper tokenHelper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AuthToken;
    }

    /**
     * authentication
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String tokenStr= (String) authenticationToken.getCredentials();
        LOGGER.debug(String.format("doGetAuthenticationInfo: credentials: %s", tokenStr));
        // validate auth token
        try {
            AuthToken authToken = tokenHelper.validateAndDecodeTokenStr(tokenStr);
            return new SimpleAuthenticationInfo(authToken.getUserPk(), tokenStr, SecurityConstant.REALM_NAME_BMS_WK);

        } catch (AuthException e) {
            throw new AuthenticationException(e.getMessage(), e);
        }
    }

    /**
     * authorization
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Integer userId = principalCollection.oneByType(Integer.class);
        LOGGER.debug(String.format("doGetAuthorizationInfo: principal user pk id: %s", userId));

        User userInfo = userService.getById(userId);

        // get role
        Integer roleId = userInfo.getRoleId();
        Role role = roleService.getById(roleId);
        LOGGER.debug(String.format("doGetAuthorizationInfo: user role name: %s", role.getName()));

        // get permission info based on role
        List<PermissionDTO> permissionDTOS = permissionService.getPermissionListByRoleId(roleId);
        Set<String> permissionNameSet = new HashSet<>();
        permissionDTOS.forEach(permissionDTO -> {
            permissionNameSet.add(permissionDTO.getPermissionName());
        });

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole(role.getName());
        authorizationInfo.addStringPermissions(permissionNameSet);

        return authorizationInfo;
    }
}
