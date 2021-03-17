package org.coredb.emigo.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.coredb.emigo.model.NodeConnection;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AmigoLogin
 */
@Validated
public class AmigoLogin   {
  @JsonProperty("token")
  private String token = null;

  @JsonProperty("account")
  private NodeConnection account = null;

  @JsonProperty("service")
  private NodeConnection service = null;

  public AmigoLogin token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Get token
   * @return token
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public AmigoLogin account(NodeConnection account) {
    this.account = account;
    return this;
  }

  /**
   * Get account
   * @return account
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public NodeConnection getAccount() {
    return account;
  }

  public void setAccount(NodeConnection account) {
    this.account = account;
  }

  public AmigoLogin service(NodeConnection service) {
    this.service = service;
    return this;
  }

  /**
   * Get service
   * @return service
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public NodeConnection getService() {
    return service;
  }

  public void setService(NodeConnection service) {
    this.service = service;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AmigoLogin amigoLogin = (AmigoLogin) o;
    return Objects.equals(this.token, amigoLogin.token) &&
        Objects.equals(this.account, amigoLogin.account) &&
        Objects.equals(this.service, amigoLogin.service);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, account, service);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AmigoLogin {\n");
    
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    account: ").append(toIndentedString(account)).append("\n");
    sb.append("    service: ").append(toIndentedString(service)).append("\n");
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

