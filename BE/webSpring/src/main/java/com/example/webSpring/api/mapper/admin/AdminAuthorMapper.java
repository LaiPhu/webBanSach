package com.example.webSpring.api.mapper.admin;

import com.example.webSpring.api.config.MapStructConfiguration;
import com.example.webSpring.api.dto.admin.AdminAuthorDTO;
import com.example.webSpring.api.model.admin.AdminAuthor;
import com.example.webSpring.api.vm.admin.AdminAddAuthorVM;
import com.example.webSpring.api.vm.admin.AdminAuthorVM;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapStructConfiguration.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AdminAuthorMapper {
    AdminAuthorMapper INSTANCE = Mappers.getMapper(AdminAuthorMapper.class);

    AdminAuthorDTO toDtoVM(AdminAddAuthorVM AuthorVM);

    AdminAuthorDTO toDto(AdminAuthor Author);

    AdminAuthor toAddAuthor(AdminAddAuthorVM AuthorVM);

    AdminAuthor toAuthor(AdminAuthorVM AuthorVM);
}
