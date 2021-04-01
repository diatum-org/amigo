/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.13).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package org.coredb.emigo.api;

import org.coredb.emigo.model.Amigo;
import org.coredb.emigo.model.AmigoLogin;
import org.coredb.emigo.model.Result;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@Api(value = "accounts", description = "the accounts API")
public interface AccountsApi {


    @ApiOperation(value = "", nickname = "attachAccount", notes = "Create new amigo account and in turn attach an existing db account", response = AmigoLogin.class, tags={ "accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "account created", response = AmigoLogin.class),
        @ApiResponse(code = 401, message = "invalid password"),
        @ApiResponse(code = 406, message = "account limit reached"),
        @ApiResponse(code = 500, message = "internal server error") })
    @RequestMapping(value = "/accounts/attached",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<AmigoLogin> attachAccount(@NotNull @ApiParam(value = "id of amigo to be attached", required = true) @Valid @RequestParam(value = "amigoId", required = true) String amigoId
,@NotNull @ApiParam(value = "single use code", required = true) @Valid @RequestParam(value = "code", required = true) String code
,@NotNull @ApiParam(value = "node to forward request to", required = true) @Valid @RequestParam(value = "node", required = true) String node
);


    @ApiOperation(value = "", nickname = "setAmigoFlag", notes = "Report identity to admin", tags={ "accounts", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation") })
    @RequestMapping(value = "/console/amigos/{amigoId}/flag",
        produces = { "application/json" },
        method = RequestMethod.PUT)

    ResponseEntity<Void> setAmigoFlag(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token
      ,@ApiParam(value = "referenced amigo entry",required=true) @PathVariable("amigoId") String amigoId
);


    @ApiOperation(value = "", nickname = "getIdentityRevision", notes = "request revision of module data", response = Integer.class, tags={ "accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Integer.class),
        @ApiResponse(code = 403, message = "access denied") })
    @RequestMapping(value = "/accounts/revision",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Integer> getIdentityRevision(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token
);


    @ApiOperation(value = "", nickname = "updateHandle", notes = "Update profile with registry message", response = Amigo.class, tags={ "accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "handle updated", response = Amigo.class),
        @ApiResponse(code = 400, message = "invalid message message received"),
        @ApiResponse(code = 401, message = "invalid login token"),
        @ApiResponse(code = 423, message = "account not enabled"),
        @ApiResponse(code = 500, message = "internal server error"),
        @ApiResponse(code = 503, message = "external server error") })
    @RequestMapping(value = "/accounts/registry",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Amigo> updateHandle(@NotNull @ApiParam(value = "login token", required = true) @Valid @RequestParam(value = "token", required = true) String token
,@NotNull @ApiParam(value = "registry base url", required = true) @Valid @RequestParam(value = "registry", required = true) String registry
,@ApiParam(value = "current revision") @Valid @RequestParam(value = "revision", required = false) Integer revision
);

}

