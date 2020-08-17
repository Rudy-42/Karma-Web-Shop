package com.codemart.karmawebshop.service.impl;

import com.codemart.karmawebshop.entity.Cart;
import com.codemart.karmawebshop.entity.User;
import com.codemart.karmawebshop.repository.UserRepository;
import com.codemart.karmawebshop.service.CartService;
import com.codemart.karmawebshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartService cartService;


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
        cartService.createCart(new Cart(user, true));
    }


    public boolean exists(User user) {
        return userRepository.existsByUsername(user.getUsername());
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(s);
        if (user != null)
            return new MyUserDetailsService(user);
        else{
            throw new UsernameNotFoundException("Username not found");
        }
    }
}
