package org.coredb.emigo.service.specification;

import java.io.*;
import java.text.*;
import java.util.*;
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
    return builder.equal(root.<Account>get("searchable"), searchable);
  }
}

