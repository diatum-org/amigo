package org.coredb.emigo.jpa.repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;
import java.util.Set;

import org.coredb.emigo.jpa.entity.ServerConfig;

public interface ServerConfigRepository extends JpaRepository<ServerConfig, Integer> {
  List<ServerConfig> findAll();
  List<ServerConfig> findByConfigId(String id);
  ServerConfig findOneByConfigId(String id);
  Long deleteByConfigId(String id);
}

