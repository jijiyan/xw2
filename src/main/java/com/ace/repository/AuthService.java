package com.ace.repository;

import com.ace.entity.user.SysUser;

public interface AuthService {
    SysUser register(SysUser userToAdd);

    SysUser login(String username, String password);

    String refresh(String oldToken);
}
