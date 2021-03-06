package com.ducthang.demoApiAngular.controller;

import com.ducthang.demoApiAngular.dto.request.SignInForm;
import com.ducthang.demoApiAngular.dto.request.SignUpForm;
import com.ducthang.demoApiAngular.dto.response.JwtResponse;
import com.ducthang.demoApiAngular.dto.response.ResponseMessage;
import com.ducthang.demoApiAngular.model.Role;
import com.ducthang.demoApiAngular.model.RoleName;
import com.ducthang.demoApiAngular.model.Users;
import com.ducthang.demoApiAngular.security.jwt.JwtProvider;
import com.ducthang.demoApiAngular.security.userprinciple.UserPrinciple;
import com.ducthang.demoApiAngular.service.IRoleService;
import com.ducthang.demoApiAngular.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register (@Valid @RequestBody SignUpForm signUpForm) {
        if(userService.existsByUserName(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("username_existed"), HttpStatus.OK);
        }
        if(userService.existsByEmail(signUpForm.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("email_existed"), HttpStatus.OK);
        }
        if(signUpForm.getAvatar() == null || signUpForm.getAvatar().trim().isEmpty()){
            signUpForm.setAvatar("https://firebasestorage.googleapis.com/v0/b/casemd4-b5188.appspot.com/o/Avatar-Facebook-tr???ng.jpg?alt=media&token=e8460146-9763-4a7f-bb4e-56148b670434"); //Ch??? n??y l?? ???nh c???a user l??c ????ng k??, s??? g??n cho n?? 1 c??i m???c ?????nh
        }
        Users users = new Users(
          signUpForm.getName(),
          signUpForm.getUsername(),
          signUpForm.getEmail(),
          signUpForm.getAvatar(),
          passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "ADMIN" :
                    Role smRole = roleService.findByName(RoleName.ADMIN).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(smRole);
                    break;
                default:
                    Role usRole = roleService.findByName(RoleName.USER).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(usRole);
            }
        });
        users.setRoles(roles);
        userService.save(users);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        Users users = userService.findByUserName(userPrinciple.getUsername()).get();
        return ResponseEntity.ok(new JwtResponse(token, users));
    }
}
