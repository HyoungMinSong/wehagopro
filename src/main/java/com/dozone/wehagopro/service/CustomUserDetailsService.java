package com.dozone.wehagopro.service;

import com.dozone.wehagopro.config.SecurityUser;
import com.dozone.wehagopro.domain.UserDTO;
import com.dozone.wehagopro.repository.mybatis.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<UserDTO> optional = userMapper.findByUserId(userId);
        if(!optional.isPresent()) {
            throw new UsernameNotFoundException("사용자가 없습니다.");
        } else {
            UserDTO userDto = optional.get();
            return new SecurityUser(userDto);
        }
    }

}