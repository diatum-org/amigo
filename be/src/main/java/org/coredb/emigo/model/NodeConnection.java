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
 * NodeConnection
 */
@Validated
public class NodeConnection   {
  @JsonProperty("amigoId")
  private String amigoId = null;

  @JsonProperty("node")
  private String node = null;

  @JsonProperty("handle")
  private String handle = null;

  @JsonProperty("registry")
  private String registry = null;

  @JsonProperty("token")
  private String token = null;

  public NodeConnection amigoId(String amigoId) {
    this.amigoId = amigoId;
    return this;
  }

  /**
   * Get amigoId
   * @return amigoId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getAmigoId() {
    return amigoId;
  }

  public void setAmigoId(String amigoId) {
    this.amigoId = amigoId;
  }

  public NodeConnection node(String node) {
    this.node = node;
    return this;
  }

  /**
   * Get node
   * @return node
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getNode() {
    return node;
  }

  public void setNode(String node) {
    this.node = node;
  }

  public NodeConnection handle(String handle) {
    this.handle = handle;
    return this;
  }

  /**
   * Get handle
   * @return handle
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getHandle() {
    return handle;
  }

  public void setHandle(String handle) {
    this.handle = handle;
  }

  public NodeConnection registry(String registry) {
    this.registry = registry;
    return this;
  }

  /**
   * Get registry
   * @return registry
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getRegistry() {
    return registry;
  }

  public void setRegistry(String registry) {
    this.registry = registry;
  }

  public NodeConnection token(String token) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NodeConnection nodeConnection = (NodeConnection) o;
    return Objects.equals(this.amigoId, nodeConnection.amigoId) &&
        Objects.equals(this.node, nodeConnection.node) &&
        Objects.equals(this.handle, nodeConnection.handle) &&
        Objects.equals(this.registry, nodeConnection.registry) &&
        Objects.equals(this.token, nodeConnection.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amigoId, node, handle, registry, token);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NodeConnection {\n");
    
    sb.append("    amigoId: ").append(toIndentedString(amigoId)).append("\n");
    sb.append("    node: ").append(toIndentedString(node)).append("\n");
    sb.append("    handle: ").append(toIndentedString(handle)).append("\n");
    sb.append("    registry: ").append(toIndentedString(registry)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
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

