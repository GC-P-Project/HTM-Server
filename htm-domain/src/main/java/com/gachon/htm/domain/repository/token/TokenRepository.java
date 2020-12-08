package com.gachon.htm.domain.repository.token;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.gachon.htm.domain.model.Token;

public interface TokenRepository extends CrudRepository<Token, Long>, TokenRepositoryCustom {

    Optional<Token> findByToken(String token);

    void deleteByUserId(long userId);
}
