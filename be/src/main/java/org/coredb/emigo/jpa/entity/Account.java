package org.coredb.emigo.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.coredb.emigo.model.Amigo;
import org.coredb.emigo.model.Contact;
import org.coredb.emigo.model.UserEntry;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class Account extends Contact implements Serializable {
  private Integer id;
  private Boolean gps;
  private Integer profileRevision;
  private Float gpsLongitude;
  private Float gpsLatitude;
  private Float gpsAltitude;
  private Long gpsTimestamp;
  private Boolean searchable;
  private String loginToken;
  private String accountToken;
  private String serviceToken;
  private Boolean enabled;
  private Long createTimestamp;
  
  public Account() { 
    this.profileRevision = 0;
  }

  public Account(Amigo emigo, String login, String account, String service, Long timestamp) {
    update(emigo, login, account, service, timestamp);
  }

  @Transient
  public void update(Amigo emigo, String login, String account, String service, Long timestamp) {
    super.setAmigoId(emigo.getAmigoId());
    super.setName(emigo.getName());
    super.setDescription(emigo.getDescription());
    super.setLogo(emigo.getLogo());
    super.setLocation(emigo.getLocation());
    super.setNode(emigo.getNode());
    super.setVersion(emigo.getVersion());
    super.setAvailable(false);
    // registry and handle only set in registry response
    super.setRevision(0);
    this.gps = false;
    this.searchable = false;
    this.enabled = true;
    this.loginToken = login;
    this.accountToken = account;
    this.serviceToken = service;
    this.profileRevision = 0;
    this.createTimestamp = timestamp;
  }

  @Transient
  public void update(Amigo emigo) {
    if(emigo.getRevision() > super.getRevision()) {
      super.setAmigoId(emigo.getAmigoId());
      super.setName(emigo.getName());
      super.setDescription(emigo.getDescription());
      super.setLogo(emigo.getLogo());
      super.setLocation(emigo.getLocation());
      super.setNode(emigo.getNode());
      super.setVersion(emigo.getVersion());
      super.setRevision(emigo.getRevision());
      super.setHandle(emigo.getHandle());
      super.setRegistry(emigo.getRegistry());
    }
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, nullable = false)
  @JsonIgnore
  public Integer getId() {
    return this.id;
  }
  public void setId(Integer value) {
    this.id = value;
  }

  @JsonIgnore
  public String getEmigoId() {
    return super.getAmigoId();
  }
  public void setEmigoId(String value) {
    super.setAmigoId(value);
  }

  @JsonIgnore
  public Long getCreateTimestamp() {
    return this.createTimestamp;
  }
  public void setCreateTimestamp(Long value) {
    this.createTimestamp = value;
  }

  @JsonIgnore
  public String getHandle() {
    return super.getHandle();
  }
  public void setHandle(String value) {
    super.setHandle(value);
  }

  @JsonIgnore
  public String getName() {
    return super.getName();
  }
  public void setName(String value) {
    super.setName(value);
  }

  @JsonIgnore
  public String getDescription() {
    return super.getDescription();
  }
  public void setDescription(String value) {
    super.setDescription(value);
  }

  @JsonIgnore
  public String getLocation() {
    return super.getLocation();
  }
  public void setLocation(String value) {
    super.setLocation(value);
  }

  @JsonIgnore
  public String getLogo() {
    return super.getLogo();
  }
  public void setLogo(String value) {
    super.setLogo(value);
  }

  @JsonIgnore
  public Integer getRevision() {
    return super.getRevision();
  }
  public void setRevision(Integer value) {
    super.setRevision(value);
  }

  @JsonIgnore
  public String getRegistry() {
    return super.getRegistry();
  }
  public void setRegistry(String value) {
    super.setRegistry(value);
  }

  @JsonIgnore
  public String getNode() {
    return super.getNode();
  }
  public void setNode(String value) {
    super.setNode(value);
  }

  @JsonIgnore
  public String getVersion() {
    return super.getVersion();
  }
  public void setVersion(String value) {
    super.setVersion(value);
  }

  @JsonIgnore
  public Boolean getGps() {
    return this.gps;
  }
  public void setGps(Boolean value) {
    this.gps = value;
  }

  @JsonIgnore
  public Float getGpsLongitude() {
    return this.gpsLongitude;
  }
  public void setGpsLongitude(Float value) {
    this.gpsLongitude = value;
  }

  @JsonIgnore
  public Float getGpsLatitude() {
    return this.gpsLatitude;
  }
  public void setGpsLatitude(Float value) {
    this.gpsLatitude = value;
  }

  @JsonIgnore
  public Float getGpsAltitude() {
    return this.gpsAltitude;
  }
  public void setGpsAltitude(Float value) {
    this.gpsAltitude = value;
  }

  @JsonIgnore
  public Long getGpsTimestamp() {
    return this.gpsTimestamp;
  }
  public void setGpsTimestamp(Long value) {
    this.gpsTimestamp = value;
  }

  @JsonIgnore
  public Boolean getAvailable() {
    return super.isAvailable();
  }
  public void setAvailable(Boolean value) {
    super.setAvailable(value);
  }

  @JsonIgnore
  public Boolean getSearchable() {
    return this.searchable;
  }
  public void setSearchable(Boolean value) {
    this.searchable = value;
  }

  @JsonIgnore
  public Boolean getEnabled() {
    return this.enabled;
  }
  public void setEnabled(Boolean value) {
    this.enabled = value;
  }

  @JsonIgnore
  public String getLoginToken() {
    return this.loginToken;
  }
  public void setLoginToken(String value) {
    this.loginToken = value;
  }

  @JsonIgnore
  public String getAccountToken() {
    return this.accountToken;
  }
  public void setAccountToken(String value) {
    this.accountToken = value;
  }

  @JsonIgnore
  public String getServiceToken() {
    return this.serviceToken;
  }
  public void setServiceToken(String value) {
    this.serviceToken = value;
  }

  @JsonIgnore
  public Integer getProfileRevision() {
    return this.profileRevision;
  }
  public void setProfileRevision(Integer value) {
    this.profileRevision = value;
  }

}

