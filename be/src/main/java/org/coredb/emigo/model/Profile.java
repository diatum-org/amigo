package org.coredb.emigo.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Profile
 */
@Validated
public class Profile   {
  @JsonProperty("revision")
  private Integer revision = null;

  @JsonProperty("phoneNumber")
  private String phoneNumber = null;

  @JsonProperty("confirmedPhone")
  private Boolean confirmedPhone = null;

  @JsonProperty("emailAddress")
  private String emailAddress = null;

  @JsonProperty("confirmedEmail")
  private Boolean confirmedEmail = null;

  @JsonProperty("searchable")
  private Boolean searchable = null;

  @JsonProperty("available")
  private Boolean available = null;

  @JsonProperty("gps")
  private Boolean gps = null;

  @JsonProperty("gpsTimestamp")
  private Long gpsTimestamp = null;

  @JsonProperty("gpsLongitude")
  private Float gpsLongitude = null;

  @JsonProperty("gpsLatitude")
  private Float gpsLatitude = null;

  public Profile revision(Integer revision) {
    this.revision = revision;
    return this;
  }

  /**
   * Get revision
   * @return revision
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Integer getRevision() {
    return revision;
  }

  public void setRevision(Integer revision) {
    this.revision = revision;
  }

  public Profile phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
  **/
  @ApiModelProperty(value = "")
  
    public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Profile confirmedPhone(Boolean confirmedPhone) {
    this.confirmedPhone = confirmedPhone;
    return this;
  }

  /**
   * Get confirmedPhone
   * @return confirmedPhone
  **/
  @ApiModelProperty(value = "")
  
    public Boolean isConfirmedPhone() {
    return confirmedPhone;
  }

  public void setConfirmedPhone(Boolean confirmedPhone) {
    this.confirmedPhone = confirmedPhone;
  }

  public Profile emailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  /**
   * Get emailAddress
   * @return emailAddress
  **/
  @ApiModelProperty(value = "")
  
    public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public Profile confirmedEmail(Boolean confirmedEmail) {
    this.confirmedEmail = confirmedEmail;
    return this;
  }

  /**
   * Get confirmedEmail
   * @return confirmedEmail
  **/
  @ApiModelProperty(value = "")
  
    public Boolean isConfirmedEmail() {
    return confirmedEmail;
  }

  public void setConfirmedEmail(Boolean confirmedEmail) {
    this.confirmedEmail = confirmedEmail;
  }

  public Profile searchable(Boolean searchable) {
    this.searchable = searchable;
    return this;
  }

  /**
   * Get searchable
   * @return searchable
  **/
  @ApiModelProperty(value = "")
  
    public Boolean isSearchable() {
    return searchable;
  }

  public void setSearchable(Boolean searchable) {
    this.searchable = searchable;
  }

  public Profile available(Boolean available) {
    this.available = available;
    return this;
  }

  /**
   * Get available
   * @return available
  **/
  @ApiModelProperty(value = "")
  
    public Boolean isAvailable() {
    return available;
  }

  public void setAvailable(Boolean available) {
    this.available = available;
  }

  public Profile gps(Boolean gps) {
    this.gps = gps;
    return this;
  }

  /**
   * Get gps
   * @return gps
  **/
  @ApiModelProperty(value = "")
  
    public Boolean isGps() {
    return gps;
  }

  public void setGps(Boolean gps) {
    this.gps = gps;
  }

  public Profile gpsTimestamp(Long gpsTimestamp) {
    this.gpsTimestamp = gpsTimestamp;
    return this;
  }

  /**
   * Get gpsTimestamp
   * @return gpsTimestamp
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Long getGpsTimestamp() {
    return gpsTimestamp;
  }

  public void setGpsTimestamp(Long gpsTimestamp) {
    this.gpsTimestamp = gpsTimestamp;
  }

  public Profile gpsLongitude(Float gpsLongitude) {
    this.gpsLongitude = gpsLongitude;
    return this;
  }

  /**
   * Get gpsLongitude
   * @return gpsLongitude
  **/
  @ApiModelProperty(value = "")
  
    public Float getGpsLongitude() {
    return gpsLongitude;
  }

  public void setGpsLongitude(Float gpsLongitude) {
    this.gpsLongitude = gpsLongitude;
  }

  public Profile gpsLatitude(Float gpsLatitude) {
    this.gpsLatitude = gpsLatitude;
    return this;
  }

  /**
   * Get gpsLatitude
   * @return gpsLatitude
  **/
  @ApiModelProperty(value = "")
  
    public Float getGpsLatitude() {
    return gpsLatitude;
  }

  public void setGpsLatitude(Float gpsLatitude) {
    this.gpsLatitude = gpsLatitude;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profile profile = (Profile) o;
    return Objects.equals(this.revision, profile.revision) &&
        Objects.equals(this.phoneNumber, profile.phoneNumber) &&
        Objects.equals(this.confirmedPhone, profile.confirmedPhone) &&
        Objects.equals(this.emailAddress, profile.emailAddress) &&
        Objects.equals(this.confirmedEmail, profile.confirmedEmail) &&
        Objects.equals(this.searchable, profile.searchable) &&
        Objects.equals(this.available, profile.available) &&
        Objects.equals(this.gps, profile.gps) &&
        Objects.equals(this.gpsTimestamp, profile.gpsTimestamp) &&
        Objects.equals(this.gpsLongitude, profile.gpsLongitude) &&
        Objects.equals(this.gpsLatitude, profile.gpsLatitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(revision, phoneNumber, confirmedPhone, emailAddress, confirmedEmail, searchable, available, gps, gpsTimestamp, gpsLongitude, gpsLatitude);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Profile {\n");
    
    sb.append("    revision: ").append(toIndentedString(revision)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    confirmedPhone: ").append(toIndentedString(confirmedPhone)).append("\n");
    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
    sb.append("    confirmedEmail: ").append(toIndentedString(confirmedEmail)).append("\n");
    sb.append("    searchable: ").append(toIndentedString(searchable)).append("\n");
    sb.append("    available: ").append(toIndentedString(available)).append("\n");
    sb.append("    gps: ").append(toIndentedString(gps)).append("\n");
    sb.append("    gpsTimestamp: ").append(toIndentedString(gpsTimestamp)).append("\n");
    sb.append("    gpsLongitude: ").append(toIndentedString(gpsLongitude)).append("\n");
    sb.append("    gpsLatitude: ").append(toIndentedString(gpsLatitude)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

