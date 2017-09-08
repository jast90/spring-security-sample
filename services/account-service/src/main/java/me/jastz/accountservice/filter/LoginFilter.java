package me.jastz.accountservice.filter;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhiwen on 2017/9/8.
 */
@WebFilter
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken)) {
            if (httpServletRequest.getServletPath().equals("/login")) {
                httpServletResponse.sendRedirect("/");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
