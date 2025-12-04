package com.intern.digitallendingsystem.mapper;

import com.intern.digitallendingsystem.dto.CustomerDto;
import com.intern.digitallendingsystem.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(source ="bankId.id", target ="bankId")
    CustomerDto toDto(Customer Customer);

    @Mapping(source ="bankId", target ="bankId.id")
    Customer toEntity(CustomerDto customerDto);

    @Mapping(source ="bankId", target ="bankId.id")
    Customer update(CustomerDto customerDto, @MappingTarget Customer customer);
}
