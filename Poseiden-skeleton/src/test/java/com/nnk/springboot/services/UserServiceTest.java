package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setUsername("User1");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setFullname("User One");
        user.setRole("ROLE_USER");

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1);

        assertNotNull(foundUser);
        assertEquals(user.getUsername(), foundUser.getUsername());
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("User1");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setFullname("User One");
        user.setRole("ROLE_USER");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
        assertEquals(user.getUsername(), savedUser.getUsername());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUsername("User1");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setFullname("User One");
        user.setRole("ROLE_USER");

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.updateUser(1, user);

        assertNotNull(updatedUser);
        assertEquals(user.getUsername(), updatedUser.getUsername());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setUsername("User1");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setFullname("User One");
        user.setRole("ROLE_USER");

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(anyInt());

        userService.delete(1);

        verify(userRepository, times(1)).deleteById(anyInt());
    }

    @Test
    public void testGetUserByUsername() {
        User user = new User();
        user.setUsername("User1");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setFullname("User One");
        user.setRole("ROLE_USER");

        when(userRepository.findByUsername(anyString())).thenReturn(user);

        User foundUser = userService.getUserByUsername("User1");

        assertNotNull(foundUser);
        assertEquals(user.getUsername(), foundUser.getUsername());
    }
}
