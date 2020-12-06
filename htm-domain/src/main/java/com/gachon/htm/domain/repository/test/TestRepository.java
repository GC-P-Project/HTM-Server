package com.gachon.htm.domain.repository.test;

import org.springframework.data.repository.CrudRepository;

import com.gachon.htm.domain.model.Test;

public interface TestRepository extends CrudRepository<Test, Long>, TestRepositoryCustom {
}
