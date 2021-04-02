package org.coredb.emigo.service.specification;

import java.io.*;
import java.text.*;
import java.util.*;
import java.time.Instant;
import java.sql.Timestamp;

import org.coredb.emigo.jpa.entity.Account;
import javax.persistence.criteria.*;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

public class AccountSpecificationSearchable implements Specification<Account> {
  private Boolean searchable;

  public AccountSpecificationSearchable(Boolean s) {
    this.searchable = s;
  }

  @Override
  public Predicate toPredicate (Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    Long cur = Instant.now().getEpochSecond() - (long)3600;
    Predicate search = builder.equal(root.<Boolean>get("searchable"), searchable);
    Predicate reported = builder.lessThan(root.<Long>get("reportTimestamp"), cur);
    return builder.and(search, reported);
  }
}

