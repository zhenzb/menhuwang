package com.handongkeji.util;

import com.handongkeji.util.manager.JwtUtils;
//import com.qingyun.service.MbUserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class DecryptToken {
    protected static final Log logger = LogFactory.getLog(DecryptToken.class);

//    @Autowired
//    private MbUserService mbUserService;

    /**
     * @param token
     * @return Long
     * @throws
     * @Title: decryptToken
     * @Author victor 2016年1月20日
     * @Description: 解密
     */
    public Map<String, Object> decryptToken(String token) {
        Map<String, Object> map = new HashMap<>();
//        String code = "0";
//        // 验证 token
//        Claims claims = JwtUtils.parse(token);
//        if (claims == null) {
//            logger.info("token无效");
//            code = "400";
//        }
//        Long uid = null;
//        MbUser user = null;
//        if (claims != null) {
//            uid = Long.valueOf(claims.get("userid").toString());
//            String logintime = claims.get("generateDate").toString();
//            try {
//                user = mbUserService.getUserByUserId(uid);
//                if (user != null) {
//                    //判断用户是否被下线
//                    if (user.getUsercurrentlogintime() != null) {// 判断用户是否是最新注册第一次登陆，第一次登陆没有上次登陆时间
//                        if (compareTimeAccurateSecond(Long.parseLong(logintime), user.getUsercurrentlogintime())) {
//                            // 时间戳校验成功
//                            code = "200";
//                        } else {
//                            // 校验失败 ，时间戳过期
//                            code = "201";
//                        }
//                    } else {
//                        code = "400";
//                    }
//                } else {// 找不到对应的用户ID
//                    code = "404";
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                code = "400";
//            }
//        }
//        map.put("uid", uid);
//        map.put("member", user);
//        map.put("code", code);
        return map;
    }

    public boolean compareTimeAccurateSecond(Long timestamp, Date datatime) {
        return (timestamp / 1000) == (datatime.getTime() / 1000);
    }
}
