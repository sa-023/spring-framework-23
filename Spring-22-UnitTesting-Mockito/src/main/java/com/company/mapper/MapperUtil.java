package com.company.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class MapperUtil {
    private final ModelMapper modelMapper;
    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> T convert(Object objectToBeConverted, T conertedObject){
        return modelMapper.map(objectToBeConverted, (Type) conertedObject.getClass());
    }


//    public <T> T convertToEntity(Object objectToBeConverted, T conertedObject){
//        return modelMapper.map(objectToBeConverted, (Type) conertedObject.getClass());
//
//    }
//    public <T> T convertToDTO(Object objectToBeConverted, T conertedObject){
//        return modelMapper.map(objectToBeConverted, (Type) conertedObject.getClass());
//
//    }




}
