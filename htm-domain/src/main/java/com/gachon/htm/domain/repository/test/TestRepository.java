package com.gachon.htm.domain.repository.test;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gachon.htm.domain.model.test.Test;

@Repository
public interface TestRepository extends CrudRepository<Test, Long>, TestRepositoryCustom {
}
