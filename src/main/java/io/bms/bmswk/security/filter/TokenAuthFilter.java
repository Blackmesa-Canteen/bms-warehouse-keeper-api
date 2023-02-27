package io.bms.bmswk.security.filter;

import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.security.model.AuthToken;
import io.bms.bmswk.security.util.TokenStrUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * request filter for shiro framework
 * </p>
 *
 * @author 996Worker
 * @since 2023-02-27 16:08
 */
public class TokenAuthFilter extends BasicHttpAuthenticationFilter {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    /**
     * check is login request
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String tokenStr = req.getHeader(SecurityConstant.AUTH_HEADER_NAME);
        return StringUtils.isNotEmpty(tokenStr);
    }

    /**
     * perform login
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String tokenStrOriginal = req.getHeader(SecurityConstant.AUTH_HEADER_NAME);

        // remove bearer prefix
        String tokenStr = TokenStrUtils.removeTokenPrefix(tokenStrOriginal);

        LOGGER.debug(String.format("Received header token: %s", tokenStr));

        //gen token obj
        AuthToken authToken = new AuthToken(tokenStr);

        // exe the realm auth logic
        getSubject(request, response).login(authToken);

        return true;
    }

    /**
     * Show 401 response
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            // if token header exists
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                redirect403(request, response);
            }
        } else {
            // if missing token, still reject!
            redirect403(request, response);
            return false;
        }
        return true;
    }

    /**
     * CORS for all frontend url
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * redirect to 403 route
     */
    private void redirect403(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            httpServletResponse.sendRedirect("/403");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
