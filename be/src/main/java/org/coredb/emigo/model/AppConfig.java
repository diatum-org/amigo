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
 * AppConfig
 */
@Validated
public class AppConfig   {
  @JsonProperty("region")
  private String region = null;

  @JsonProperty("sourceEmail")
  private String sourceEmail = null;

  @JsonProperty("accessId")
  private String accessId = null;

  @JsonProperty("accessKey")
  private String accessKey = null;

  @JsonProperty("consoleToken")
  private String consoleToken = null;

  @JsonProperty("appNode")
  private String appNode = null;

  @JsonProperty("appEmigo")
  private String appEmigo = null;

  @JsonProperty("appToken")
  private String appToken = null;

  @JsonProperty("clusterUrl")
  private String clusterUrl = null;

  @JsonProperty("clusterToken")
  private String clusterToken = null;

  @JsonProperty("slotToken")
  private String slotToken = null;

  @JsonProperty("statToken")
  private String statToken = null;

  public AppConfig region(String region) {
    this.region = region;
    return this;
  }

  /**
   * Get region
   * @return region
  **/
  @ApiModelProperty(value = "")
  
    public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public AppConfig sourceEmail(String sourceEmail) {
    this.sourceEmail = sourceEmail;
    return this;
  }

  /**
   * Get sourceEmail
   * @return sourceEmail
  **/
  @ApiModelProperty(value = "")
  
    public String getSourceEmail() {
    return sourceEmail;
  }

  public void setSourceEmail(String sourceEmail) {
    this.sourceEmail = sourceEmail;
  }

  public AppConfig accessId(String accessId) {
    this.accessId = accessId;
    return this;
  }

  /**
   * Get accessId
   * @return accessId
  **/
  @ApiModelProperty(value = "")
  
    public String getAccessId() {
    return accessId;
  }

  public void setAccessId(String accessId) {
    this.accessId = accessId;
  }

  public AppConfig accessKey(String accessKey) {
    this.accessKey = accessKey;
    return this;
  }

  /**
   * Get accessKey
   * @return accessKey
  **/
  @ApiModelProperty(value = "")
  
    public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public AppConfig consoleToken(String consoleToken) {
    this.consoleToken = consoleToken;
    return this;
  }

  /**
   * Get consoleToken
   * @return consoleToken
  **/
  @ApiModelProperty(value = "")
  
    public String getConsoleToken() {
    return consoleToken;
  }

  public void setConsoleToken(String consoleToken) {
    this.consoleToken = consoleToken;
  }

  public AppConfig appNode(String appNode) {
    this.appNode = appNode;
    return this;
  }

  /**
   * Get appNode
   * @return appNode
  **/
  @ApiModelProperty(value = "")
  
    public String getAppNode() {
    return appNode;
  }

  public void setAppNode(String appNode) {
    this.appNode = appNode;
  }

  public AppConfig appEmigo(String appEmigo) {
    this.appEmigo = appEmigo;
    return this;
  }

  /**
   * Get appEmigo
   * @return appEmigo
  **/
  @ApiModelProperty(value = "")
  
    public String getAppEmigo() {
    return appEmigo;
  }

  public void setAppEmigo(String appEmigo) {
    this.appEmigo = appEmigo;
  }

  public AppConfig appToken(String appToken) {
    this.appToken = appToken;
    return this;
  }

  /**
   * Get appToken
   * @return appToken
  **/
  @ApiModelProperty(value = "")
  
    public String getAppToken() {
    return appToken;
  }

  public void setAppToken(String appToken) {
    this.appToken = appToken;
  }

  public AppConfig clusterUrl(String clusterUrl) {
    this.clusterUrl = clusterUrl;
    return this;
  }

  /**
   * Get clusterUrl
   * @return clusterUrl
  **/
  @ApiModelProperty(value = "")
  
    public String getClusterUrl() {
    return clusterUrl;
  }

  public void setClusterUrl(String clusterUrl) {
    this.clusterUrl = clusterUrl;
  }

  public AppConfig clusterToken(String clusterToken) {
    this.clusterToken = clusterToken;
    return this;
  }

  /**
   * Get clusterToken
   * @return clusterToken
  **/
  @ApiModelProperty(value = "")
  
    public String getClusterToken() {
    return clusterToken;
  }

  public void setClusterToken(String clusterToken) {
    this.clusterToken = clusterToken;
  }

  public AppConfig slotToken(String slotToken) {
    this.slotToken = slotToken;
    return this;
  }

  /**
   * Get slotToken
   * @return slotToken
  **/
  @ApiModelProperty(value = "")
  
    public String getSlotToken() {
    return slotToken;
  }

  public void setSlotToken(String slotToken) {
    this.slotToken = slotToken;
  }

  public AppConfig statToken(String statToken) {
    this.statToken = statToken;
    return this;
  }

  /**
   * Get statToken
   * @return statToken
  **/
  @ApiModelProperty(value = "")
  
    public String getStatToken() {
    return statToken;
  }

  public void setStatToken(String statToken) {
    this.statToken = statToken;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppConfig appConfig = (AppConfig) o;
    return Objects.equals(this.region, appConfig.region) &&
        Objects.equals(this.sourceEmail, appConfig.sourceEmail) &&
        Objects.equals(this.accessId, appConfig.accessId) &&
        Objects.equals(this.accessKey, appConfig.accessKey) &&
        Objects.equals(this.consoleToken, appConfig.consoleToken) &&
        Objects.equals(this.appNode, appConfig.appNode) &&
        Objects.equals(this.appEmigo, appConfig.appEmigo) &&
        Objects.equals(this.appToken, appConfig.appToken) &&
        Objects.equals(this.clusterUrl, appConfig.clusterUrl) &&
        Objects.equals(this.clusterToken, appConfig.clusterToken) &&
        Objects.equals(this.slotToken, appConfig.slotToken) &&
        Objects.equals(this.statToken, appConfig.statToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(region, sourceEmail, accessId, accessKey, consoleToken, appNode, appEmigo, appToken, clusterUrl, clusterToken, slotToken, statToken);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppConfig {\n");
    
    sb.append("    region: ").append(toIndentedString(region)).append("\n");
    sb.append("    sourceEmail: ").append(toIndentedString(sourceEmail)).append("\n");
    sb.append("    accessId: ").append(toIndentedString(accessId)).append("\n");
    sb.append("    accessKey: ").append(toIndentedString(accessKey)).append("\n");
    sb.append("    consoleToken: ").append(toIndentedString(consoleToken)).append("\n");
    sb.append("    appNode: ").append(toIndentedString(appNode)).append("\n");
    sb.append("    appEmigo: ").append(toIndentedString(appEmigo)).append("\n");
    sb.append("    appToken: ").append(toIndentedString(appToken)).append("\n");
    sb.append("    clusterUrl: ").append(toIndentedString(clusterUrl)).append("\n");
    sb.append("    clusterToken: ").append(toIndentedString(clusterToken)).append("\n");
    sb.append("    slotToken: ").append(toIndentedString(slotToken)).append("\n");
    sb.append("    statToken: ").append(toIndentedString(statToken)).append("\n");
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

