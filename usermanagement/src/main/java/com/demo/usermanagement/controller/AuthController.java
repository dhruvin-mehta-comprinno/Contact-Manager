package com.demo.usermanagement.controller;

import com.demo.usermanagement.config.MySecurityUtils;
import com.demo.usermanagement.config.UserDetailsCon;
import com.demo.usermanagement.entity.User;
import com.demo.usermanagement.repo.UserRepo;
import com.demo.usermanagement.service.UserDetailService;
import com.demo.usermanagement.tokens.MessageResponse;
import com.demo.usermanagement.tokens.TokenReq;
import com.demo.usermanagement.tokens.TokenRes;
import com.demo.usermanagement.tokens.TokenSignupReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@Api(value="auth-controller")
public class AuthController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private MySecurityUtils mySecurityUtils;


    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Existing user login using username and password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Logged-in"),
            @ApiResponse(code = 404, message = "Not found - The product was not found")
    })
    @ApiOperation(value = "User login", notes = "Existing user can Login using username ane password")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody TokenReq loginRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = mySecurityUtils.generateJwtToken(authentication);
        UserDetailsCon userDetailService1 = (UserDetailsCon) authentication.getPrincipal();
        return ResponseEntity.ok(new TokenRes(jwt,
                userDetailService1.user.getUid(),
                userDetailService1.user.getUsername(),
                userDetailService1.user.getName()
                ));
    }

    @PostMapping("/register")
    @Operation(summary = "User Registration", description = "New users have to register themself")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Registered"),
            @ApiResponse(code = 404, message = "Not found - The product was not found")
    })
    public ResponseEntity<?> registerUser(@Valid @RequestBody TokenSignupReq tokenSignupReq) {
        if (userRepo.existsByUsername(tokenSignupReq.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));

        }

        User user = new User(tokenSignupReq.getUsername(), tokenSignupReq.getName(), tokenSignupReq.getPassword());
        userRepo.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered"));

    }
//    @GetMapping("/currentUser")
//    public User getCurrentUser(Principal principal) {
//        return ((User)this.userDetailService.loadUserByUsername(principal.getName()));
//    }

}
