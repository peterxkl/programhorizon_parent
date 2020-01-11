package com.dillon.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DillonXie
 * @version 1.0
 * @date 1/8/2020 8:12 PM
 */
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if (request.getMethod().equals("OPTIONS")) {     //?????
            return null;
        }
        String url = request.getRequestURL().toString();
        if (url.indexOf("/admin/login") > 0) {
            return null;  //表示是登录请求
        }
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if ("admin".equals(claims.get("roles"))) {
                requestContext.addZuulRequestHeader("Authorization", authorization);
                return null;
            }
        }
        requestContext.setSendZuulResponse(false);  //终止运行
        requestContext.setResponseStatusCode(401);
        requestContext.setResponseBody("无权访问");
        return null;
    }
}
