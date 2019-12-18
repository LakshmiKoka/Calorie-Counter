package com.example.calorie.counter.services;

import com.example.calorie.counter.entity.User;
import com.example.calorie.counter.model.CalorieCounterUser;
import com.example.calorie.counter.repository.UserDao;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.logging.Logger;

@Service("UserDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userDao.findByUsername(userId);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        return new CalorieCounterUser(user);
    }

}
