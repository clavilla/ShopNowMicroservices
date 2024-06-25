package com.shopnow.user.service;


import com.shopnow.user.exception.EmailAlreadyExistsException;
import com.shopnow.user.mapper.UserMapper;
import com.shopnow.user.model.dto.UserRequestDto;
import com.shopnow.user.model.dto.UserResponseDto;
import com.shopnow.user.model.entity.UserEntity;
import com.shopnow.user.repository.UserRepository;
import com.shopnow.user.utils.JwtUtils;
import com.shopnow.user.utils.ServiceUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;

    public UserService(UserRepository userRepository, UserMapper userMapper, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtUtils = jwtUtils;
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        UserEntity userEntity = userMapper.convertToEntity(userRequestDto);

        if (!ServiceUtils.isValidPassword(userEntity.getPassword())) {
            throw new IllegalArgumentException("Password does not meet the requirements");
        }

        if (!ServiceUtils.isValidEmail(userEntity.getEmail())) {
            throw new IllegalArgumentException("Email is not valid");
        }

        if (userRepository.findByEmail(userEntity.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("The email already exist in database");
        }
        userEntity.setCreated(LocalDate.now());
        userEntity.setModified(LocalDate.now());
        userEntity.setLastLogin(LocalDate.now());
        userEntity.setPassword(ServiceUtils.encodePassword(userEntity.getPassword()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity.getEmail(), userEntity.getPassword());
        userEntity.setToken(jwtUtils.createToken(authentication));
        userEntity.setEnabled(true);
        return userMapper.convertToDto(userRepository.save(userEntity));
    }


}
