package com.intern.digitallendingsystem.mapper;

import com.intern.digitallendingsystem.dto.BankUserDto;
import com.intern.digitallendingsystem.enums.UserRole;
import com.intern.digitallendingsystem.model.BankUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BankUserMapper {

    @Mapping(source = "bankId.id", target = "bankId")
    BankUserDto toDto(BankUser bankUser);

    @Mapping(source = "bankId", target = "bankId.id")
    @Mapping(source = "role", target = "role", qualifiedByName = "mapRole")
    BankUser toEntity(BankUserDto bankUserDto);

    @Mapping(source = "bankId", target = "bankId.id")
    @Mapping(source = "role", target = "role", qualifiedByName = "mapRole")
    void update(BankUserDto bankUserDto, @MappingTarget BankUser bankUser);

    @Named("mapRole")
    default UserRole mapRole(String role){
        if (role == null) return null;
        return UserRole.valueOf(role.toUpperCase().trim());
    }


}