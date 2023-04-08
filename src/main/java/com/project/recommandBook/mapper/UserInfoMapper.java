package com.project.recommandBook.mapper;


import com.project.recommandBook.dto.UserInfoDto;
import com.project.recommandBook.entity.UserInfo;

public class UserInfoMapper {

    public static UserInfoDto convertToDto(UserInfo userInfo) {
        UserInfoDto userInfoDto = new UserInfoDto();

        userInfoDto.setUserId(userInfo.getUserId());
        userInfoDto.setPw(userInfo.getPw());
        userInfoDto.setName(userInfo.getName());
        userInfoDto.setPhoneNumber(userInfo.getPhoneNumber());
        userInfoDto.setCreatedAt(userInfo.getCreatedAt());
        userInfoDto.setUserRole(userInfo.getUserRole());

        return userInfoDto;
    }

//    public static UserInfo convertToModel(UserInfoDto userInfoDto) {
//        UserInfo userInfo = new UserInfo();
//
//        userInfo.setUserId(userInfoDto.getUserId());
//        userInfo.setPassword(userInfoDto.getPassword());
//        userInfo.setName(userInfoDto.getName());
//        userInfo.setPhoneNumber(userInfoDto.getPhoneNumber());
//        userInfo.setCreatedAt(userInfoDto.getCreatedAt());
//
//        return userInfo;
//    }
}
