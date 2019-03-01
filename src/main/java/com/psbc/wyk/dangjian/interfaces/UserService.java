package com.psbc.wyk.dangjian.interfaces;

import com.psbc.wyk.dangjian.dao.dos.UserDO;
import com.psbc.wyk.dangjian.interfaces.base.BaseService;
import com.psbc.wyk.dangjian.interfaces.dto.UserDTO;

import java.util.Map;

/**
 * @author wyk on 2019/02/27
 */
public interface UserService extends BaseService<UserDO> {

    public String checkUser(String phone, String password);

    public UserDTO findUser(String phone);
}
