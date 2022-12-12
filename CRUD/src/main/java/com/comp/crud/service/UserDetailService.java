package com.comp.crud.service;


import com.comp.crud.config.UserDetailsCon;
import com.comp.crud.entity.User;
import com.comp.crud.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.getUserByUserName(username);

        if(user==null) {

            throw new UsernameNotFoundException("Invalid credentials");

        }
        // TODO Auto-generated method stub
        return new UserDetailsCon(user);
    }
}
