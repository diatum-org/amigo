package org.coredb.emigo.jpa.repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;
import java.util.Set;

import org.coredb.emigo.jpa.entity.Account;

public interface AccountRepository 
    extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
  List<Account> findAll();
  Account findOneByLoginToken(String token);
  Account findOneByEmigoId(String id);
  List<Account> findByReportCountGreaterThanOrderByReportCountDesc(Integer count);
  long count();
}


