package org.coredb.emigo.jpa.repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;
import java.util.Set;

import org.coredb.emigo.jpa.entity.Confirm;

public interface ConfirmRepository extends JpaRepository<Confirm, Integer> {
  Confirm findOneByToken(String token);
}


