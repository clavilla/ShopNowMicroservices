package com.shopnow.user.mapper;


import com.shopnow.user.model.dto.UserRequestDto;
import com.shopnow.user.model.dto.UserResponseDto;
import com.shopnow.user.model.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserEntity convertToEntity(UserRequestDto userRequestDto) {
        return modelMapper.map(userRequestDto, UserEntity.class);
    }

    public UserResponseDto convertToDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserResponseDto.class);
    }

    public List<UserResponseDto> convertToListDto(List<UserEntity> userEntityList) {
        return userEntityList.stream().map(this::convertToDto).toList();
    }
}
