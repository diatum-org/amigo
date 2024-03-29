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
 * Contact
 */
@Validated
public class Contact   {
  @JsonProperty("amigoId")
  private String amigoId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("logo")
  private String logo = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("node")
  private String node = null;

  @JsonProperty("registry")
  private String registry = null;

  @JsonProperty("revision")
  private Integer revision = null;

  @JsonProperty("version")
  private String version = null;

  @JsonProperty("handle")
  private String handle = null;

  @JsonProperty("available")
  private Boolean available = null;

  public Contact amigoId(String amigoId) {
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

  public Contact name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Contact description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Contact logo(String logo) {
    this.logo = logo;
    return this;
  }

  /**
   * Get logo
   * @return logo
  **/
  @ApiModelProperty(value = "")
  
    public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public Contact location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")
  
    public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Contact node(String node) {
    this.node = node;
    return this;
  }

  /**
   * Get node
   * @return node
  **/
  @ApiModelProperty(example = "https://node.coredb.org:9999/app", required = true, value = "")
      @NotNull

    public String getNode() {
    return node;
  }

  public void setNode(String node) {
    this.node = node;
  }

  public Contact registry(String registry) {
    this.registry = registry;
    return this;
  }

  /**
   * Get registry
   * @return registry
  **/
  @ApiModelProperty(example = "https://registry.coredb.org:8888/app", value = "")
  
    public String getRegistry() {
    return registry;
  }

  public void setRegistry(String registry) {
    this.registry = registry;
  }

  public Contact revision(Integer revision) {
    this.revision = revision;
    return this;
  }

  /**
   * Get revision
   * @return revision
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Integer getRevision() {
    return revision;
  }

  public void setRevision(Integer revision) {
    this.revision = revision;
  }

  public Contact version(String version) {
    this.version = version;
    return this;
  }

  /**
   * Get version
   * @return version
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public Contact handle(String handle) {
    this.handle = handle;
    return this;
  }

  /**
   * Get handle
   * @return handle
  **/
  @ApiModelProperty(value = "")
  
    public String getHandle() {
    return handle;
  }

  public void setHandle(String handle) {
    this.handle = handle;
  }

  public Contact available(Boolean available) {
    this.available = available;
    return this;
  }

  /**
   * Get available
   * @return available
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Boolean isAvailable() {
    return available;
  }

  public void setAvailable(Boolean available) {
    this.available = available;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contact contact = (Contact) o;
    return Objects.equals(this.amigoId, contact.amigoId) &&
        Objects.equals(this.name, contact.name) &&
        Objects.equals(this.description, contact.description) &&
        Objects.equals(this.logo, contact.logo) &&
        Objects.equals(this.location, contact.location) &&
        Objects.equals(this.node, contact.node) &&
        Objects.equals(this.registry, contact.registry) &&
        Objects.equals(this.revision, contact.revision) &&
        Objects.equals(this.version, contact.version) &&
        Objects.equals(this.handle, contact.handle) &&
        Objects.equals(this.available, contact.available);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amigoId, name, description, logo, location, node, registry, revision, version, handle, available);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Contact {\n");
    
    sb.append("    amigoId: ").append(toIndentedString(amigoId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    node: ").append(toIndentedString(node)).append("\n");
    sb.append("    registry: ").append(toIndentedString(registry)).append("\n");
    sb.append("    revision: ").append(toIndentedString(revision)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    handle: ").append(toIndentedString(handle)).append("\n");
    sb.append("    available: ").append(toIndentedString(available)).append("\n");
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

