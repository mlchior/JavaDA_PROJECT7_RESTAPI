package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void delete(Integer id);

    public User getUserById(Integer id);

    public User saveUser(User user);

    public User updateUser(Integer id, User user);

    public User getUserByUsername(String username);

}
