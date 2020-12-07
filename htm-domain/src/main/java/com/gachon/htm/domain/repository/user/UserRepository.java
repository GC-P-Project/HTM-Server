package com.gachon.htm.domain.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.gachon.htm.domain.model.Test;

public interface UserRepository extends CrudRepository<Test, Long>, UserRepositoryCustom {
}
