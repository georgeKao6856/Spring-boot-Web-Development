package org.practice.week3day5georgekaoquizweb.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@WebFilter("/*")
@Component
public class LoginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            filterChain.doFilter(request, response);
        }else {
            // redirect back to the login page if user is not logged in
            response.sendRedirect("/login");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        System.out.println("Path: " + path);
        boolean flag = false;
        if("/login".equals(path) || "/WEB-INF/jsp/login.jsp".equals(path)){
            flag = true;
        }else if("/contactus".equals(path) || "/WEB-INF/jsp/contactus.jsp".equals(path)){
            flag = true;
        }else if (path.startsWith("/css/") || path.startsWith("/js/") || path.startsWith("/images/")) {
            flag = true;
        }else if("/register".equals(path)){
            flag = true;
        }else if(path.startsWith("/rs_user")){
            flag = true;
        }
//        else if("/home".equals(path) || "/WEB-INF/jsp/home.jsp".equals(path)){
//            flag = true;
//        }

        return flag;
    }
}
