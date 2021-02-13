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

public class AccountSpecificationMaxAltitude implements Specification<Account> {
  private Float latitude;

  public AccountSpecificationMaxAltitude(Float l) {
    this.latitude = l;
  }

  @Override
  public Predicate toPredicate (Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    return builder.lessThanOrEqualTo(root.<Float>get("gpsAltitude"), latitude);
  }
}

