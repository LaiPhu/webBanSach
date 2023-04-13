package com.example.webSpring.api.mapper.admin;

import com.example.webSpring.api.config.MapStructConfiguration;
import com.example.webSpring.api.dto.admin.AdminBookDTO;
import com.example.webSpring.api.model.admin.AdminBook;
import com.example.webSpring.api.vm.admin.AdminAddBookVM;
import com.example.webSpring.api.vm.admin.AdminBookVM;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapStructConfiguration.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AdminBookMapper {
    AdminBookMapper INSTANCE = Mappers.getMapper(AdminBookMapper.class);

    AdminBookDTO toDtoVM(AdminAddBookVM bookVM);

    AdminBookDTO toDto(AdminBook book);

    AdminBook toAddBook(AdminAddBookVM bookVM);

    AdminBook toBook(AdminBookVM bookVM);
}
