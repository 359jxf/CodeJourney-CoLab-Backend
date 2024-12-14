package com.tongji.codejourneycolab.codejourneycolabbackend.service;

import com.tongji.codejourneycolab.codejourneycolabbackend.dto.UserInfoDto;
import com.tongji.codejourneycolab.codejourneycolabbackend.exception.InvalidCredentialsException;
import com.tongji.codejourneycolab.codejourneycolabbackend.exception.InvalidRegisterException;
import com.tongji.codejourneycolab.codejourneycolabbackend.exception.IdentityAlreadyExistsException;

public interface AccountService {
    String login(String username, String password) throws IdentityAlreadyExistsException;

    void register(String username, String password, String email) throws IdentityAlreadyExistsException, InvalidRegisterException;

    UserInfoDto getUserInfoById(Integer id) throws InvalidCredentialsException;

    void editUserInfoById(Integer id, UserInfoDto userInfoDto) throws InvalidCredentialsException;

    void logout(String token);

}
