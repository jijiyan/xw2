package com.ace.common.model;

import lombok.Data;

/**
 * @author bamboo
 */
@Data
public class LoginPara {
    private String clientId;
    private String userName;
    private String password;
    private String captchaCode;
    private String captchaValue;

}
