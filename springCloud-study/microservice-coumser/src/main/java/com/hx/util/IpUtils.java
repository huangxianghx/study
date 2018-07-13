package com.hx.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class IpUtils {
    protected static final Logger log = LoggerFactory.getLogger(IpUtils.class);
    /**
     * 获取用户真实 ip
     *
     * @param request
     * @return
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        String ip = getIpFromHeader(request);
        return  getFirstIp(ip);
    }

    /**
     * 从请求头中获取 ip 字段
     * @param request
     * @return
     */
    private static String getIpFromHeader(HttpServletRequest request){
        String ipHeader = "X-Real-IP";
        if(!StringUtils.isEmpty(ipHeader)){
            String ip = request.getHeader(ipHeader);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return  ip;
            }
            log.warn("从 {} 取 ip 失败:{},返回默认 ip:{},url:{}",ipHeader,ip,request.getRemoteAddr(),request.getRequestURI());
        }
        return request.getRemoteAddr();
    }

    /**
     * 对于X-Forwarded-For 可能有多个 ip,取第一个,使用,号分隔
     * @return
     */
    private  static String getFirstIp( String ip ){
        if(StringUtils.isEmpty(ip)){
            return ip;
        }
        int signIndex = ip.indexOf(",");
        if(signIndex<0){
            return ip;
        }
        return ip.substring(0,signIndex).trim();
    }


    /**
     *  在 Aws 中取相应头的 ip,由于在 ELB后面,取相应 ip 可能有多个,需要最最后的 ip 字段。使用,号分隔
     * @return
     */
    private static  String getLastIp(String ip) {
        if (StringUtils.isEmpty(ip)) {
            return ip;
        }
        int signIndex = ip.lastIndexOf(",");
        if (signIndex < 0) {
            return ip;
        }
        return StringUtils.trimWhitespace(ip.substring(signIndex + 1));
    }
}
