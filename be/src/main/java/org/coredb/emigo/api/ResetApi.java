package org.coredb.emigo.api;

import org.coredb.emigo.model.Contact;
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
@Api(value = "reset", description = "the reset API")
public interface ResetApi {

    @ApiOperation(value = "", nickname = "resetCheck", notes = "Check if reset token is valid", response = Contact.class, tags={ "password", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = Contact.class),
        @ApiResponse(code = 401, message = "invalid token"),
        @ApiResponse(code = 404, message = "not found"),
        @ApiResponse(code = 500, message = "internal server error") })
    @RequestMapping(value = "/reset/account",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Contact> resetCheck(@NotNull @ApiParam(value = "reset token", required = true) @Valid @RequestParam(value = "token", required = true) String token
);


    @ApiOperation(value = "", nickname = "resetPassword", notes = "Apply new password with token", tags={ "password", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok"),
        @ApiResponse(code = 401, message = "invalid token"),
        @ApiResponse(code = 404, message = "not found"),
        @ApiResponse(code = 500, message = "internal server error") })
    @RequestMapping(value = "/reset/account",
        method = RequestMethod.PUT)
    ResponseEntity<Void> resetPassword(@NotNull @ApiParam(value = "confirmation token", required = true) @Valid @RequestParam(value = "token", required = true) String token
,@NotNull @ApiParam(value = "updated account password", required = true) @Valid @RequestParam(value = "password", required = true) String password
);

}

