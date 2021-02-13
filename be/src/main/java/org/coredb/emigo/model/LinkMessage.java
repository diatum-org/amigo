package org.coredb.emigo.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.coredb.emigo.model.EmigoMessage;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LinkMessage
 */
@Validated
public class LinkMessage   {
  @JsonProperty("emigo")
  private EmigoMessage emigo = null;

  @JsonProperty("signature")
  private String signature = null;

  @JsonProperty("create")
  private String create = null;

  @JsonProperty("attach")
  private String attach = null;

  public LinkMessage emigo(EmigoMessage emigo) {
    this.emigo = emigo;
    return this;
  }

  /**
   * Get emigo
   * @return emigo
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public EmigoMessage getEmigo() {
    return emigo;
  }

  public void setEmigo(EmigoMessage emigo) {
    this.emigo = emigo;
  }

  public LinkMessage signature(String signature) {
    this.signature = signature;
    return this;
  }

  /**
   * Get signature
   * @return signature
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public LinkMessage create(String create) {
    this.create = create;
    return this;
  }

  /**
   * Get create
   * @return create
  **/
  @ApiModelProperty(value = "")

  public String getCreate() {
    return create;
  }

  public void setCreate(String create) {
    this.create = create;
  }

  public LinkMessage attach(String attach) {
    this.attach = attach;
    return this;
  }

  /**
   * Get attach
   * @return attach
  **/
  @ApiModelProperty(value = "")

  public String getAttach() {
    return attach;
  }

  public void setAttach(String attach) {
    this.attach = attach;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LinkMessage linkMessage = (LinkMessage) o;
    return Objects.equals(this.emigo, linkMessage.emigo) &&
        Objects.equals(this.signature, linkMessage.signature) &&
        Objects.equals(this.create, linkMessage.create) &&
        Objects.equals(this.attach, linkMessage.attach);
  }

  @Override
  public int hashCode() {
    return Objects.hash(emigo, signature, create, attach);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LinkMessage {\n");
    
    sb.append("    emigo: ").append(toIndentedString(emigo)).append("\n");
    sb.append("    signature: ").append(toIndentedString(signature)).append("\n");
    sb.append("    create: ").append(toIndentedString(create)).append("\n");
    sb.append("    attach: ").append(toIndentedString(attach)).append("\n");
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

