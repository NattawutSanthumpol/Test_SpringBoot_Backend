package com.santhumpol.backend_spring_boot;

import com.santhumpol.backend_spring_boot.entity.User;
import com.santhumpol.backend_spring_boot.exception.BaseException;
import com.santhumpol.backend_spring_boot.exception.UserException;
import com.santhumpol.backend_spring_boot.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUserService {

    @Autowired
    private UserService userService;

    @Order(1)
    @Test
    void testCreate() throws BaseException {
       User user =  userService.create(
               TestCreateData.email,
               TestCreateData.password,
               TestCreateData.name
        );

       // check not null
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());

        // check equals
        Assertions.assertEquals(TestCreateData.email,user.getEmail());
        boolean isMatched = userService.matchPassword(TestCreateData.password,user.getPassword());
        Assertions.assertTrue(isMatched);
        Assertions.assertEquals(TestCreateData.name,user.getName());

    }

    @Order(2)
    @Test
    void testUpdate() throws UserException {
    Optional<User> opt = userService.findByEmail(TestCreateData.email);
    Assertions.assertTrue(opt.isPresent());

    User user = opt.get();
    User updateUser = userService.updateName(user.getId(),TestUpdateData.name);

    Assertions.assertNotNull(updateUser);
    Assertions.assertEquals(TestUpdateData.name,updateUser.getName());
    }

    @Order(3)
    @Test
    void testDelete(){
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();
        userService.deleteById(user.getId());

        Optional<User> optDelete = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(optDelete.isEmpty());
    }

    interface TestCreateData{
        String email = "nattawut.santhumpol@hotmail.com";
        String password = "1234";
        String name = "Nattawut Santhumpol";
        String civil_id = "1234";
    }

    interface TestUpdateData{
        String name = "Nattawut";
    }
}
