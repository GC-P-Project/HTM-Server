package com.gachon.htm.domain.repository.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.gachon.htm.domain.model.User;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByEmailAndPassword(String email, String password);
}
