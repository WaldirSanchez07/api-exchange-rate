package com.wcsp.msauthentication.repository;

import com.wcsp.msauthentication.model.Permission;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PermissionRepository extends R2dbcRepository<Permission, Integer> {

//  Mono<Permission> findByUriAndRol(String rol);

  Mono<Permission> findByUriAndRolesContaining(String uri, String rol);

}
