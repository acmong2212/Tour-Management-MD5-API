package com.ducthang.demoApiAngular.controller;

import com.ducthang.demoApiAngular.model.Users;
import com.ducthang.demoApiAngular.security.jwt.JwtProvider;
import com.ducthang.demoApiAngular.service.IRoleService;
import com.ducthang.demoApiAngular.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UsersController {
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

    @GetMapping
    public ResponseEntity<Page<Users>> show(@RequestParam(defaultValue = "0") int page) {
        return new ResponseEntity<>(userService.findAll(PageRequest.of(page, 3)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<Users> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> edit(@PathVariable Long id, @RequestBody Users users) {
        Optional<Users> oldUser = userService.findById(id);
        if (users.getPassword() == null || users.getAvatar() == null) {
            users.setPassword(oldUser.get().getPassword());
        }
        if(users.getAvatar() == null) {
            users.setAvatar(oldUser.get().getAvatar());
        }
        users.setId(id);
        users.setRoles(oldUser.get().getRoles());
        userService.save(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/countUsers")
    public int countUsers() {
        return userService.countUsers();
    }

}
