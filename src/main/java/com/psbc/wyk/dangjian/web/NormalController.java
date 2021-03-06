package com.psbc.wyk.dangjian.web;

import com.psbc.wyk.dangjian.common.responsecode.CodeLogin;
import com.psbc.wyk.dangjian.common.responsecode.RestResponse;
import com.psbc.wyk.dangjian.common.util.Assert;
import com.psbc.wyk.dangjian.interfaces.UserService;
import com.psbc.wyk.dangjian.interfaces.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyk on 2019/02/28
 */
@RestController
@RequestMapping("/normal")
public class NormalController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public RestResponse login(@RequestBody UserDTO userDTO){
        Assert.notNull(userDTO.getPassword(),CodeLogin.USER_PHONE_EMPTY_EXCEPTION);
        Assert.notNull(userDTO.getPassword(),CodeLogin.USER_PASSWORD_EMPTY_EXCEPTION);
        return RestResponse.ok(userService.checkUser(userDTO.getPhone(),userDTO.getPassword()));
    }
}
