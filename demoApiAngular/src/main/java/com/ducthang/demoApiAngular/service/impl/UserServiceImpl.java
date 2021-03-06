package com.ducthang.demoApiAngular.service.impl;

import com.ducthang.demoApiAngular.model.Users;
import com.ducthang.demoApiAngular.repository.IUserRepository;
import com.ducthang.demoApiAngular.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<Users> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUserName(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Users save(Users users) {
        return userRepository.save(users);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        em.joinTransaction();
        userRepository.deleteById(id);
    }

    @Override
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<Users> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    @Override
    public List<Users> findUsersByIdIsNotLike(Long id) {
        return userRepository.findUsersByIdIsNotLike(id);
    }

    @Override
    public int countUsers() {
        return userRepository.countUsers();
    }

}
