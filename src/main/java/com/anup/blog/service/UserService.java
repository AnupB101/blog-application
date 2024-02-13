package com.anup.blog.service;

import com.anup.blog.payload.ResetPasswordDto;
import com.anup.blog.payload.UserDto;

import java.util.List;

public interface UserService {
    String register(UserDto userDto);

    List<UserDto> read();

    String update(UserDto user);

    public String verifyOtp(String otp);

    public String userLogin(String email, String password);

    public String forgotPassword(String email);

    public String changePassword(ResetPasswordDto resetPassDto);
}
