package cn.lee.web.service;

import cn.lee.web.config.Session;
import cn.lee.web.util.JwtUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.utils.$;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.security.SignatureException;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service
@RequiredArgsConstructor
public class AppTokenService {

    private final RedisService redisService;
    /**
     * 失效时间,1天
     */
    private final static long TOKEN_TIME_OUT = 7 * 24 * 60 * 60 * 1000;

    private final static String PREFIX = "TOKEN_";

    public String createOrUpdateToken(Long custId, Long accountId) {
        String token = createJwtToken(custId, accountId);
        String key = StringUtils.join(PREFIX, custId, "_", accountId);
        if (null != redisService.getObject(key)) {
            redisService.del(key);
        }
        redisService.setObject(key, token, TOKEN_TIME_OUT, TimeUnit.MILLISECONDS);
        return token;
    }

    public void deleteToken(Long custId, Long accountId) {
        String key = StringUtils.join(PREFIX, custId, "_", accountId);
        if (null != redisService.getObject(key)) {
            redisService.del(key);
        }
    }

    private String createJwtToken(Long custId, Long accountId) {
        Session session = new Session(custId, accountId);
        return JwtUtils.createJWT("appToken", $.toJson(session), TOKEN_TIME_OUT);
    }

    /**
     * 解密token
     *
     * @param token
     * @return
     */
    public Session decodeToken(String token) throws Exception  {
        try {
            Claims claims = JwtUtils.parseJWT(token);
            String body = claims.getSubject();
            Session session = $.readJson(body, new TypeReference<Session>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
            if (session != null) {
                //从redis去验证是否存在不同的token，如果存在则抛出token实效
                String key = StringUtils.join(PREFIX, session.getCustId(), "_", session.getAccountId());
                String cacheToken = redisService.getObject(key);
                if (StringUtils.isBlank(cacheToken)) {
                    throw new Exception("登录过期，请重新登陆！");
                }
                if (!token.equalsIgnoreCase(cacheToken)) {
                    throw new Exception("您的账号已经在其他设备登录，请重新登录！");
                }
            }
            return session;
        } catch (ExpiredJwtException | SignatureException ex) {
            throw new Exception("token无效，请重新获取");
        }
    }
}