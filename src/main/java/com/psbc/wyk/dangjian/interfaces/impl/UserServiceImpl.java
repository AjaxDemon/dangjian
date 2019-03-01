package com.psbc.wyk.dangjian.interfaces.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.psbc.wyk.dangjian.common.util.JwtUtil;
import com.psbc.wyk.dangjian.common.util.Md5Encoder;
import com.psbc.wyk.dangjian.dao.dos.UserDO;
import com.psbc.wyk.dangjian.dao.repo.UserMapper;
import com.psbc.wyk.dangjian.interfaces.UserService;


import com.psbc.wyk.dangjian.interfaces.base.BaseServiceImpl;
import com.psbc.wyk.dangjian.interfaces.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import static com.psbc.wyk.dangjian.common.Constant.TYPE_ADMIN;
import static com.psbc.wyk.dangjian.common.Constant.TYPE_NORMAL;
import static com.psbc.wyk.dangjian.common.Constant.USER_TYPE_ADMIN;

/**
 * @author wyk on 2019/02/28
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserDO> implements UserService {


    @Override
    public String checkUser(String phone, String password) {
        UserDO userDO = new UserDO();
        userDO.setPhone(phone);
        userDO.setPassword(Md5Encoder.md5Encode(password, null));
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>(userDO);
        UserDO findUserDO = this.selectOne(queryWrapper);
        if (findUserDO == null) {
            return "";
        }
        String message = findUserDO.getId() + "#" + findUserDO.getPhone();
        String role;
        if (USER_TYPE_ADMIN.equals(findUserDO.getType())) {
            role = TYPE_ADMIN;
        } else {
            role = TYPE_NORMAL;
        }
        return JwtUtil.generateToken(message, JwtUtil.LOGIN_TOKEN, role, JwtUtil.ONE_HANDRUM_EXPIRATIONTIME);
    }

    @Override
    public UserDTO findUser(String phone) {
        UserDO userDO = new UserDO();
        userDO.setPhone(phone);
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>(userDO);
        UserDTO userDTO = new UserDTO();
        UserDO findUserDO = this.selectOne(queryWrapper);
        if (findUserDO == null) {
            return null;
        } else {
            BeanUtils.copyProperties(findUserDO, userDTO);
        }
        return userDTO;
    }
}
