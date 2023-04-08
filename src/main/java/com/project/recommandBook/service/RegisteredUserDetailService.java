package com.project.recommandBook.service;

import com.project.recommandBook.entity.UserInfo;
import com.project.recommandBook.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RegisteredUserDetailService implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> res = userInfoRepository.findByUserId(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        if (!res.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        if (res != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority(res.get().getUserRole().toString()));
        }

        return new User(res.get().getUserId(), res.get().getPw(), grantedAuthorities);
    }
}
