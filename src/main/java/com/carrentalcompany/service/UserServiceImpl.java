package com.carrentalcompany.service;

import com.carrentalcompany.dao.UserDao;
import com.carrentalcompany.model.User;
import com.carrentalcompany.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger logger = Logger.getLogger("UserServiceImpl");

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userDao.findUserById(userId);
        logger.info("load user by userId: " + user.getUserId());
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        logger.info("load user by userId SUCCEED: " + user.getUserId());
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        logger.info("AUTHORITY SUCCEED: " + user.getUserId());
        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), authorities);
    }

    @Override
    public Result register(User user, String password2) {
        Result result = new Result();
        result.setSuccess(false);
        try {
            if ("".equals(user.getUserId())) {
                result.setMsg("userId cannot be null");
                return result;
            }
            if ("".equals(user.getPassword())) {
                result.setMsg("password cannot be null");
                return result;
            }
            if (!password2.equals(user.getPassword())) {
                result.setMsg("please double check the input password");
                return result;
            }
            User existUser = userDao.findUserById(user.getUserId());
            if (existUser != null) {
                result.setMsg("user existed");
                return result;
            } else {
                userDao.register(user);
                result.setMsg("register succeed");
                result.setSuccess(true);
                result.setData(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
