package org.coredb.emigo.jpa.repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;
import java.util.Set;

import org.coredb.emigo.jpa.entity.Pass;

public interface PassRepository extends JpaRepository<Pass, Integer> {
  Pass findOneByToken(String token);
}


