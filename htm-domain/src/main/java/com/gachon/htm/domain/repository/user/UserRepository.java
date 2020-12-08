package com.gachon.htm.domain.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.gachon.htm.domain.model.Test;
import com.gachon.htm.domain.model.User;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {
}
