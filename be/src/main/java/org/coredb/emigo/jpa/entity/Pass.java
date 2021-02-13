package org.coredb.emigo.jpa.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.coredb.emigo.jpa.entity.Account;

@Entity
@Table(name = "pass", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class Pass implements Serializable {
  private Integer id;
  private Account parent;
  private Account child;
  private String token;
  private Long issued;  
  private Long expires;

  public Pass() {}

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
  @JoinColumn(name = "parent_id")
  public Account getParent() {
    return this.parent;
  }
  public void setParent(Account value) {
    this.parent = value;
  }

  @ManyToOne
  @JoinColumn(name = "child_id")
  public Account getChild() {
    return this.child;
  }
  public void setChild(Account value) {
    this.child = value;
  }

  public String getToken() {
    return this.token;
  }
  public void setToken(String value) {
    this.token = value;
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
