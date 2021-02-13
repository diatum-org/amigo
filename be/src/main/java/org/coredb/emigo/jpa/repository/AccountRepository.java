package org.coredb.emigo.jpa.repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;
import java.util.Set;

import org.coredb.emigo.jpa.entity.Account;

public interface AccountRepository 
    extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
  List<Account> findAll();
  Account findOneByLoginToken(String token);
  Account findOneByEmailAddress(String email);
  Account findOneByEmailAddressAndConfirmedEmail(String email, Boolean flag);
  Account findFirstByEmailAddressOrderByIdDesc(String email);
  Account findOneByPhoneNumber(String phone);
  Account findOneByPhoneNumberAndConfirmedPhone(String phone, Boolean flag);
  Account findOneByResetToken(String token);
  Account findFirstByPhoneNumberOrderByIdDesc(String phone);
  Account findOneByEmigoId(String id);
  long count();
}


