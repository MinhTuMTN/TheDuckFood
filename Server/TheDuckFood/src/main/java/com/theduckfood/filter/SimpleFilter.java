package com.theduckfood.filter;

import com.theduckfood.repositories.StoreAccountRepository;
import com.theduckfood.repositories.UserAccountRepository;
import com.theduckfood.util.Constants;
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

    @Autowired
    StoreAccountRepository storeAccountRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (req.getRequestURI().equals("/api/users/login")
                || req.getRequestURI().equals("/api/users/register")
                || req.getRequestURI().equals("/api/merchant/login")
                || req.getRequestURI().contains("/api/file/image")
        ) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String role = "user";

        if (req.getRequestURI().contains("/api/merchant"))
            role = "store";

        if (!isValidJWTToken(req.getHeader("Authorization"), role)) {
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




    private boolean isValidJWTToken(String authorizationToken, String role) {
        Map<String, Object> payload = JWTUtil.getPayloadFromJWTToken(authorizationToken);
        if (payload == null)
            return false;

        if (((Date) (payload.get("expiration"))).before(new Date()))
            return false;

        if (!payload.get("role").equals(role))
            return false;
        if (role.equals("user"))
            return userAccountRepository.findUserAccountsByEmailAndStatus(
                    payload.get("email").toString(),
                    Constants.USER_ACCOUNT_STATUS_ACTIVATED) != null;
        else if (role.equals("store")) {
            return storeAccountRepository.findStoreAccountByEmailAndStatusNotContaining(
                    payload.get("email").toString(),
                    Constants.STORE_STATUS_DELETED
            ) != null;
        }
        return false;
    }
}
