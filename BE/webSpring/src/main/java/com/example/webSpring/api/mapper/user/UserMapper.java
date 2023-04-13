package com.example.webSpring.api.mapper.user;

import com.example.webSpring.api.config.MapStructConfiguration;
import com.example.webSpring.api.model.user.User;
import com.example.webSpring.api.vm.user.AccountVM;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapStructConfiguration.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
