package io.bms.bmswk.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.bms.bmswk.security.constant.SecurityConstant;
import io.bms.bmswk.exception.AuthException;
import io.bms.bmswk.exception.NotImplementedException;
import io.bms.bmswk.security.model.AuthToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 996Worker
 * @author https://www.cnblogs.com/yelanggu/p/10320739.html
 * @since 2023-02-23 16:36
 */
@Component
public class JwtTokenHelper implements ITokenHelper {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Override
    public AuthToken validateAndDecodeTokenStr(String token) throws AuthException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SecurityConstant.JWT_KEY);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(SecurityConstant.JWT_ISSUER)
                    .build();

            // validate and parse token
            // check: issuer and expiration
            DecodedJWT jwt = verifier.verify(token);

            // build token dto
            AuthToken res = new AuthToken();
            res.setToken(token);
            res.setUserPk(jwt.getClaim(SecurityConstant.JWT_CLAIM_USER_PRIMARY_KEY).asInt());
            res.setLoginId(jwt.getClaim(SecurityConstant.JWT_CLAIM_LOGIN_ID).asString());
            res.setUserName(jwt.getClaim(SecurityConstant.JWT_CLAIM_USERNAME).asString());
            res.setRoleId(jwt.getClaim(SecurityConstant.JWT_CLAIM_ROLE_ID).asInt());
            res.setIssuedDate(jwt.getIssuedAt());
            res.setExpirationDate(jwt.getExpiresAt());

            return res;
        } catch (JWTDecodeException e) {
            LOGGER.error(e);
            throw new AuthException("token decode error");
        } catch (JWTVerificationException e) {
            LOGGER.error(e);
            throw new AuthException("token invalid");
        } catch (Exception e) {
            LOGGER.error(e);
            throw new AuthException("Exception in validate and decode token str");
        }
    }

    @Override
    public AuthToken decodeTokenStr(String token) throws AuthException {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            // build token dto
            AuthToken res = new AuthToken();
            res.setToken(token);
            res.setUserPk(decodedJWT.getClaim(SecurityConstant.JWT_CLAIM_USER_PRIMARY_KEY).asInt());
            res.setLoginId(decodedJWT.getClaim(SecurityConstant.JWT_CLAIM_LOGIN_ID).asString());
            res.setUserName(decodedJWT.getClaim(SecurityConstant.JWT_CLAIM_USERNAME).asString());
            res.setRoleId(decodedJWT.getClaim(SecurityConstant.JWT_CLAIM_ROLE_ID).asInt());
            res.setIssuedDate(decodedJWT.getIssuedAt());
            res.setExpirationDate(decodedJWT.getExpiresAt());

            return res;
        } catch (JWTDecodeException e) {
            LOGGER.error(e);
            throw new AuthException("token decode error");
        } catch (Exception e) {
            LOGGER.error(e);
            throw new AuthException("token unknown decode error");
        }
    }

    @Override
    public String genTokenStr(Integer userPk, String loginId, String userName, Integer roleId) throws AuthException {
        try {
            // now date
            Date now = new Date();
            // now in miliseconds
            long nowMs = now.getTime();

            Date expireDate = new Date(nowMs + SecurityConstant.JWT_VALIDITY_MS);
            Algorithm algorithm = Algorithm.HMAC256(SecurityConstant.JWT_KEY);
            return JWT.create()
                    .withClaim(SecurityConstant.JWT_CLAIM_USER_PRIMARY_KEY, userPk)
                    .withClaim(SecurityConstant.JWT_CLAIM_LOGIN_ID, loginId)
                    .withClaim(SecurityConstant.JWT_CLAIM_USERNAME, userName)
                    .withClaim(SecurityConstant.JWT_CLAIM_ROLE_ID, roleId)
                    .withExpiresAt(expireDate)
                    .withIssuedAt(now)
                    .withIssuer(SecurityConstant.JWT_ISSUER)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e);
            throw new AuthException("gen jwt token error");
        }
    }

    @Override
    @Deprecated
    public boolean validateTokenStr(String token) throws AuthException {
        throw new NotImplementedException();
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(SecurityConstant.JWT_KEY);
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .build();
//            DecodedJWT jwt = verifier.verify(token);
//            return true;
//        } catch (JWTVerificationException e) {
//            return false;
//        } catch (Exception e) {
//            throw new AuthException("validate Token error");
//        }
    }
}
