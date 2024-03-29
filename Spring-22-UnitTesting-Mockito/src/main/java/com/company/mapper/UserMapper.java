package com.company.mapper;
import com.company.dto.UserDTO;
import com.company.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;
    public UserMapper(ModelMapper modelMapper1){
        this.modelMapper = modelMapper1;
    }

    public User convertToEntity(UserDTO dto){
        return modelMapper.map(dto, User.class);
    }

    public UserDTO convertToDto(User entity){
        return modelMapper.map(entity, UserDTO.class);
    }



}
