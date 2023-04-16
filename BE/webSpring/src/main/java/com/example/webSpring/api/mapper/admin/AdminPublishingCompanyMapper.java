package com.example.webSpring.api.mapper.admin;

import com.example.webSpring.api.config.MapStructConfiguration;
import com.example.webSpring.api.dto.admin.AdminPublishingCompanyDTO;
import com.example.webSpring.api.model.admin.AdminPublishingCompany;
import com.example.webSpring.api.vm.admin.AdminAddPublishingCompanyVM;
import com.example.webSpring.api.vm.admin.AdminPublishingCompanyVM;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapStructConfiguration.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AdminPublishingCompanyMapper {
    AdminPublishingCompanyMapper INSTANCE = Mappers.getMapper(AdminPublishingCompanyMapper.class);

    AdminPublishingCompanyDTO toDtoVM(AdminAddPublishingCompanyVM PublishingCompanyVM);

    AdminPublishingCompanyDTO toDto(AdminPublishingCompany PublishingCompany);

    AdminPublishingCompany toAddPublishingCompany(AdminAddPublishingCompanyVM PublishingCompanyVM);

    AdminPublishingCompany toPublishingCompany(AdminPublishingCompanyVM PublishingCompanyVM);
}
