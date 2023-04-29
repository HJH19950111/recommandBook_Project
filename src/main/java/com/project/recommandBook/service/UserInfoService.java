package com.project.recommandBook.service;

import com.project.recommandBook.dto.UserInfoDto;
import com.project.recommandBook.entity.Board;
import com.project.recommandBook.entity.UserInfo;
import com.project.recommandBook.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void join(UserInfoDto userInfoDto) {
        userInfoDto.setPw(bCryptPasswordEncoder.encode(userInfoDto.getPw()));
        UserInfo userInfo = userInfoDto.toEntity();

        Optional<UserInfo> res = userInfoRepository.findByUserId(userInfo.getUserId());

        if (!res.isPresent()) {
            this.userInfoRepository.save(userInfo);
        } else {
            return;
        }
    }

    public UserInfo login(String id, String pw) {
        Optional<UserInfo> res = userInfoRepository.findByUserId(id);

        return res.filter(m -> m.getPw().equals(pw)).orElse(null);
    }

    @Transactional
    public void updatePoint(String id, int point) {
        Optional<UserInfo> res = userInfoRepository.findByUserId(id);
        res.get().addPoint(point);
    }
}
