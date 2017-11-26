package com.ace.controller;
import com.ace.entity.SysUser;
import com.ace.dao.AuthService;
import com.ace.service.JwtAuthenticationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@Api(value = "用户controller", description = "用户操作", tags = {"用户操作接口"})
@RestController
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;
    @ApiOperation("登录")
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            String username,String password
    ) throws AuthenticationException{
        //  @RequestBody JwtAuthenticationRequest authenticationRequest
        final String token = authService.login(username,password);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }
    @ApiOperation("注册")
    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public SysUser register(@RequestBody SysUser addedUser) throws AuthenticationException{
        return authService.register(addedUser);
    }
}