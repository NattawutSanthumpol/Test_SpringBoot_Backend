package com.santhumpol.backend_spring_boot.mapper;

import com.santhumpol.backend_spring_boot.entity.User;
import com.santhumpol.backend_spring_boot.model.RegisterRespone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RegisterRespone toRegisterRespone(User user);


}
