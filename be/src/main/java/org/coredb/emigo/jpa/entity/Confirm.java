package org.coredb.emigo.jpa.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.coredb.emigo.jpa.entity.Account;

@Entity
@Table(name = "confirm", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class Confirm implements Serializable {
  private Integer id;
  private Account account;
  private String token;
  private String email;
  private String phone;
  private Long issued;  
  private Long expires;

  public Confirm() {}

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, nullable = false)
  public Integer getId() {
    return this.id;
  }
  public void setId(Integer value) {
    this.id = value;
  }

  @ManyToOne
  @JoinColumn(name = "account_id")
  public Account getAccount() {
    return this.account;
  }
  public void setAccount(Account value) {
    this.account = value;
  }

  public String getToken() {
    return this.token;
  }
  public void setToken(String value) {
    this.token = value;
  }

  public String getEmail() {
    return this.email;
  }
  public void setEmail(String value) {
    this.email = value;
  }

  public String getPhone() {
    return this.phone;
  }
  public void setPhone(String value) {
    this.phone = value;
  }

  public Long getIssued() {
    return this.issued;
  }
  public void setIssued(Long value) {
    this.issued = value;
  }

  public Long getExpires() {
    return this.expires;
  }
  public void setExpires(Long value) {
    this.expires = value;
  }
}

