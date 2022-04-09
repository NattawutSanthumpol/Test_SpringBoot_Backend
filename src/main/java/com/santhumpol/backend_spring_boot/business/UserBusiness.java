package com.santhumpol.backend_spring_boot.business;

import com.santhumpol.backend_spring_boot.entity.User;
import com.santhumpol.backend_spring_boot.exception.BaseException;
import com.santhumpol.backend_spring_boot.exception.FileException;
import com.santhumpol.backend_spring_boot.exception.UserException;
import com.santhumpol.backend_spring_boot.mapper.UserMapper;
import com.santhumpol.backend_spring_boot.model.LoginRequest;
import com.santhumpol.backend_spring_boot.model.RegisterRequest;
import com.santhumpol.backend_spring_boot.model.RegisterRespone;
import com.santhumpol.backend_spring_boot.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserBusiness {
    private final UserService userService;

    private final UserMapper userMapper;

    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    //กำหนดการ Upfile
    public static String uploadProfilePicture(MultipartFile file) throws FileException {
        //validate file
        if (file == null) {
            //throw Error
            throw FileException.fileNull();
        }

        //validate size
        if (file.getSize() > 1048576 * 2) {
            //throw Error
            throw FileException.fileMaxSize();
        }

        //validate type
        String contentType = file.getContentType();
        if (contentType == null) {
            //throw error
            throw FileException.unsupported();
        }

        List<String> supportedType = Arrays.asList("image/jpeg", "image/png");
        if (!supportedType.contains(contentType)) {
            //throw error(unsupport)
            throw FileException.unsupported();
        }

        // TODO: upload file File Storage (AWS S3,Etc...)
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";

    }
    // login
    public String login(LoginRequest request) throws BaseException{
        // validate request

        // verify database
        Optional<User> opt = userService.findByEmail(request.getEmail());
        if(opt.isEmpty()){
            // throw login fail, email not found
            throw UserException.loginFailEmailNotFound();
        }

        User user = opt.get();
        if(!userService.matchPassword(request.getPassword(),user.getPassword())){
            // throw login fail, password incorrect
            throw  UserException.loginFailPasswordIncorrect();
        }

        // TODO: generate JWT
        String token = "JWT TO DO";

        return token;
    }

    // เพิ่มข้อมูล User
    public RegisterRespone register(RegisterRequest request) throws BaseException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());

        // TODO: mapper
        return userMapper.toRegisterRespone(user);

    }
}
