package com.santhumpol.backend_spring_boot.api;

import com.santhumpol.backend_spring_boot.business.UserBusiness;
import com.santhumpol.backend_spring_boot.exception.BaseException;
import com.santhumpol.backend_spring_boot.model.LoginRequest;
import com.santhumpol.backend_spring_boot.model.RegisterRequest;
import com.santhumpol.backend_spring_boot.model.RegisterRespone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserApi {

    // METHOD: 1 Field Injection
//    @Autowired
//    private TestBusiness business;

    // METHOD: 2 Constructor Injection
    private final UserBusiness business;

    public UserApi(UserBusiness business) {
        this.business = business;
    }


    //POST
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) throws BaseException {
        String response = business.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterRespone> register(@RequestBody RegisterRequest request) throws BaseException {
        RegisterRespone response = business.register(request);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/upload-profile")
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseException {
        String response = business.uploadProfilePicture(file);
        return ResponseEntity.ok(response);
    }
}
