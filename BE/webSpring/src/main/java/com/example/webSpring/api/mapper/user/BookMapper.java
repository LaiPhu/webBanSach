package com.example.webSpring.api.mapper.user;

import com.example.webSpring.api.config.MapStructConfiguration;
import com.example.webSpring.api.dto.user.BookDTO;
import com.example.webSpring.api.model.user.Book;
import com.example.webSpring.api.vm.user.AddBookVM;
import com.example.webSpring.api.vm.user.BookVM;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapStructConfiguration.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO toDtoVM(BookVM bookVM);

    BookDTO toDto(Book book);
}
