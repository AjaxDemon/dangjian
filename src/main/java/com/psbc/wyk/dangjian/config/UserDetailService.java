//package com.psbc.wyk.dangjian.config;
//
//import com.psbc.wyk.dangjian.interfaces.UserService;
//import com.psbc.wyk.dangjian.interfaces.dto.UserDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.psbc.wyk.dangjian.common.Constant.TYPE_ADMIN;
//import static com.psbc.wyk.dangjian.common.Constant.TYPE_NORMAL;
//import static com.psbc.wyk.dangjian.common.Constant.USER_TYPE_ADMIN;
//
///**
// * @author wyk on 2019/02/28
// */
//@Component
//public class UserDetailService implements UserDetailsService {
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//
//        UserDTO userDTO = userService.findUser(s);
//        List<GrantedAuthority> list = new ArrayList<>();
//        if (userDTO==null) {
//            return null;
//        }
//        if (USER_TYPE_ADMIN.equals(userDTO.getType())) {
//            list.add(new SimpleGrantedAuthority(TYPE_ADMIN));
//        } else {
//            list.add(new SimpleGrantedAuthority(TYPE_NORMAL));
//        }
//        return new User(userDTO.getPhone(),userDTO.getPassword(),list);
//    }
//
//
//
//}
