package cn.kunli.una.service.security;

import cn.kunli.una.annotation.LogAnnotation;
import cn.kunli.una.pojo.vo.SysLoginAccount;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysToken;
import cn.kunli.una.utils.redis.RedisUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class TokenService {

    @Value("${token.expire.seconds}")
    private Integer expireSeconds;
    @Autowired
    private RedisUtil<SysLoginAccount> redisUtil;
    @Value("${token.jwtSecret}")
    private String jwtSecret;

    private static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";

    //生成token
    @LogAnnotation(methodType = "log_operate_login")
    public SysToken saveToken(SysLoginAccountDetails obj){
        String token = UUID.randomUUID().toString().replace("-","");
        obj.setToken(token);
        //用户信息入缓存
        cacheLoginAccount(obj);
        //生成jwttoken
        String jwtToken = createJWTToken(obj);

        return new SysToken(jwtToken,obj.getLoginTime());
    };

    //刷新token
    public void refresh(SysLoginAccountDetails obj){
        cacheLoginAccount(obj);
    };

    //获取当前登录用户
    public SysLoginAccountDetails getLoginAccount(String jwtToken){
        String cacheTokenFromJWT = getCacheTokenFromJWT(jwtToken);
        if(StrUtil.isNotBlank(cacheTokenFromJWT)){
            SysLoginAccount sysLoginAccount = redisUtil.get(getTokenKey(cacheTokenFromJWT));
            if(sysLoginAccount!=null){
                SysLoginAccountDetails sysLoginAccountDetails = new SysLoginAccountDetails();

                BeanUtils.copyProperties(sysLoginAccount,sysLoginAccountDetails);
                return sysLoginAccountDetails;
            }
        }
        return null;
    };

    //删除token
    public boolean deleteToken(String jwtToken){
        String cacheTokenFromJWT = getCacheTokenFromJWT(jwtToken);
        SysLoginAccount sysLoginAccount = redisUtil.get(getTokenKey(cacheTokenFromJWT));
        if(sysLoginAccount !=null){
            //如果用户没过期，则注销
            redisUtil.del(getTokenKey(cacheTokenFromJWT));
            //退出日志

            return true;
        }
        return false;

    };

    //登录信息放入缓存
    private void cacheLoginAccount(SysLoginAccountDetails obj){
        obj.setLoginTime(System.currentTimeMillis());
        obj.setExpireTime(obj.getLoginTime()+expireSeconds*1000);

        SysLoginAccount sysLoginAccount = new SysLoginAccount();
        BeanUtils.copyProperties(obj,sysLoginAccount);
        redisUtil.set(getTokenKey(obj.getToken()),sysLoginAccount,expireSeconds);
    }

    //获取redis key
    private String getTokenKey(String token){
        return "tokens:"+token;
    }

    //生成jwt token
    private String createJWTToken(SysLoginAccountDetails obj){
        String jwtToken = JWT.create().withClaim(LOGIN_USER_KEY, obj.getToken()).sign(Algorithm.HMAC256(jwtSecret)).toString();
        return jwtToken;
    }

    //获取jwt token中的参数
    private String getCacheTokenFromJWT(String jwtToken){
        Map<String, Claim> claims = null;
        try {
            claims = JWT.require(Algorithm.HMAC256(jwtSecret)).build().verify(jwtToken).getClaims();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return claims.get(LOGIN_USER_KEY).asString();
    }
}
