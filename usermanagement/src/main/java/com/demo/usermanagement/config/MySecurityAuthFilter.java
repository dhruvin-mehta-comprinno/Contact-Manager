package com.demo.usermanagement.config;

import com.demo.usermanagement.service.UserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MySecurityAuthFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    MySecurityUtils jwtUtils;
    private static final Logger logger = LoggerFactory.getLogger(MySecurityAuthFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        catch (Exception e){
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
    }

//    @Autowired
//    private UserDetailService userDetailService;
//    @Autowired
//    MySecurityUtils mySecurityUtils;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        final String requestTokenHeader = request.getHeader("Authorization");
//        System.out.println(requestTokenHeader);
//        String username=null;
//        String jwtToken=null;
//        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
//            jwtToken=requestTokenHeader.substring(7);
//            try {
//                username=this.mySecurityUtils.extractUsername(jwtToken);
//            }catch(ExpiredJwtException e) {
//                e.printStackTrace();
//                System.out.println("jwt token has expired");
//            }catch(Exception e) {
//                e.printStackTrace();
//                System.out.println("jerror");
//            }
//        }
//        else {
//            System.out.println("Invalid Token");
//        }
//        // TODO Auto-generated method stub
//        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
//            final UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
//            if(this.mySecurityUtils.validateToken(jwtToken, userDetails)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//        else {
//            System.out.println("Token is not valid");
//        }
//        filterChain.doFilter(request, response);
//    }

