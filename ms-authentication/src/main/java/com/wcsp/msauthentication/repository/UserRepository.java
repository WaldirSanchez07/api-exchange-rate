package com.wcsp.msauthentication.repository;

import com.wcsp.msauthentication.model.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<User, Integer> {

  Mono<User> findByUsername(String username);

}
