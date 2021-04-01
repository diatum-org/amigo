package org.coredb.emigo.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AmigoEntry
 */
@Validated


public class AmigoEntry   {
  @JsonProperty("amigoId")
  private String amigoId = null;

  @JsonProperty("registry")
  private String registry = null;

  @JsonProperty("flagCount")
  private Integer flagCount = null;

  @JsonProperty("blocked")
  private Boolean blocked = null;

  public AmigoEntry amigoId(String amigoId) {
    this.amigoId = amigoId;
    return this;
  }

  /**
   * Get amigoId
   * @return amigoId
   **/
      @NotNull

    public String getAmigoId() {
    return amigoId;
  }

  public void setAmigoId(String amigoId) {
    this.amigoId = amigoId;
  }

  public AmigoEntry registry(String registry) {
    this.registry = registry;
    return this;
  }

  /**
   * Get registry
   * @return registry
   **/
      @NotNull

    public String getRegistry() {
    return registry;
  }

  public void setRegistry(String registry) {
    this.registry = registry;
  }

  public AmigoEntry flagCount(Integer flagCount) {
    this.flagCount = flagCount;
    return this;
  }

  /**
   * Get flagCount
   * @return flagCount
   **/
      @NotNull

    @Valid
    public Integer getFlagCount() {
    return flagCount;
  }

  public void setFlagCount(Integer flagCount) {
    this.flagCount = flagCount;
  }

  public AmigoEntry blocked(Boolean blocked) {
    this.blocked = blocked;
    return this;
  }

  /**
   * Get blocked
   * @return blocked
   **/
      @NotNull

    public Boolean isBlocked() {
    return blocked;
  }

  public void setBlocked(Boolean blocked) {
    this.blocked = blocked;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AmigoEntry amigoEntry = (AmigoEntry) o;
    return Objects.equals(this.amigoId, amigoEntry.amigoId) &&
        Objects.equals(this.registry, amigoEntry.registry) &&
        Objects.equals(this.flagCount, amigoEntry.flagCount) &&
        Objects.equals(this.blocked, amigoEntry.blocked);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amigoId, registry, flagCount, blocked);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AmigoEntry {\n");
    
    sb.append("    amigoId: ").append(toIndentedString(amigoId)).append("\n");
    sb.append("    registry: ").append(toIndentedString(registry)).append("\n");
    sb.append("    flagCount: ").append(toIndentedString(flagCount)).append("\n");
    sb.append("    blocked: ").append(toIndentedString(blocked)).append("\n");
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

