package com.theduckfood.filter;

import com.theduckfood.repositories.UserAccountRepository;
import com.theduckfood.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

@Component
public class SimpleFilter implements Filter {
    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (req.getRequestURI().equals("/api/users/login") || req.getRequestURI().equals("/api/users/register")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (!isValidJWTToken(req.getHeader("Authorization"))) {
            res.setStatus(401);
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            PrintWriter out = res.getWriter();
            out.print("{\"error\": true, \"message\": \"JWT Token không hợp lệ\"}");
            out.flush();
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isValidJWTToken(String authorizationToken) {
        Map<String, Object> payload = JWTUtil.getPayloadFromJWTToken(authorizationToken);
        if (payload == null)
            return false;

        if (((Date) (payload.get("expiration"))).before(new Date()))
            return false;

        return userAccountRepository.findUserAccountByEmail(payload.get("email").toString()) != null;
    }
}
