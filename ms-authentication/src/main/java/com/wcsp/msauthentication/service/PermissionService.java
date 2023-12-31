package com.wcsp.msauthentication.service;

import com.wcsp.msauthentication.model.Permission;
import com.wcsp.msauthentication.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PermissionService {

  private final PermissionRepository permissionRepository;

  public Flux<Permission> findAll() {
    return permissionRepository.findAll();
  }

  public Mono<Permission> findByUriAndRol(String uri, String rol) {
    return permissionRepository.findByUriAndRolesContaining(uri, rol);
  }

}
