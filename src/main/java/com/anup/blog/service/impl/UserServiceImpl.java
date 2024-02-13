package com.anup.blog.service.impl;

import com.anup.blog.payload.ResetPasswordDto;
import com.anup.blog.payload.UserDto;
import com.anup.blog.repository.RoleRepository;
import com.anup.blog.repository.UserRepository;
import com.anup.blog.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Override
    public String register(UserDto userDto) {

        return null;
    }

    @Override
    public List<UserDto> read() {
        return null;
    }

    @Override
    public String update(UserDto user) {
        return null;
    }

    @Override
    public String verifyOtp(String otp) {
        return null;
    }

    @Override
    public String userLogin(String email, String password) {
        return null;
    }

    @Override
    public String forgotPassword(String email) {
        return null;
    }

    @Override
    public String changePassword(ResetPasswordDto resetPassDto) {
        return null;
    }
}
