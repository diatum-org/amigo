package org.coredb.emigo.api;

import org.coredb.emigo.model.AmigoLogin;
import org.coredb.emigo.model.Result;
import org.coredb.emigo.model.Amigo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;

import org.coredb.emigo.jpa.entity.Account;
import org.coredb.emigo.service.AuthService;
import org.coredb.emigo.service.AccountService;

import java.nio.file.AccessDeniedException;
import org.springframework.web.client.RestClientException;
import org.coredb.emigo.api.NotFoundException;
import java.security.InvalidParameterException;
import javax.ws.rs.NotAcceptableException;
import java.io.IOException;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@Controller
public class AccountsApiController implements AccountsApi {

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    private AuthService authService;

    @org.springframework.beans.factory.annotation.Autowired
    private AccountService accountService;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<AmigoLogin> attachAccount(@NotNull @ApiParam(value = "id of amigo to be attached", required = true) @Valid @RequestParam(value = "amigoId", required = true) String amigoId
,@NotNull @ApiParam(value = "single use code", required = true) @Valid @RequestParam(value = "code", required = true) String code
,@NotNull @ApiParam(value = "node to forward request to", required = true) @Valid @RequestParam(value = "node", required = true) String node
) {
      try {
        AmigoLogin amigo = accountService.attach(amigoId, node, code);
        return new ResponseEntity<AmigoLogin>(amigo, HttpStatus.CREATED);
      }  
      catch(java.security.InvalidParameterException e) {
        log.error(e.toString());
        return new ResponseEntity<AmigoLogin>(HttpStatus.UNAUTHORIZED); //401
      }
      catch(IOException e) {
        log.error(e.toString());
        return new ResponseEntity<AmigoLogin>(HttpStatus.NOT_ACCEPTABLE); //406
      }
      catch(Exception e) {
        log.error(e.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add("details", e.getMessage());
        return new ResponseEntity<AmigoLogin>(headers, HttpStatus.SERVICE_UNAVAILABLE);
      }
    }

    public ResponseEntity<Void> setAmigoFlag(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token
      ,@ApiParam(value = "referenced amigo entry",required=true) @PathVariable("amigoId") String amigoId
)
    {
      return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Integer> getIdentityRevision(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token) {
    try {
        Account act = authService.loginToken(token);
        Integer rev = act.getRevision();
        return new ResponseEntity<Integer>(rev, HttpStatus.OK);
      }
      catch(InvalidParameterException e) {
        log.error(e.toString());
        return new ResponseEntity<Integer>(HttpStatus.UNAUTHORIZED); //401
      }
      catch(IllegalArgumentException e) {
        log.error(e.toString());
        return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST); //400
      }
      catch(NotAcceptableException e) {
        log.error(e.toString());
        return new ResponseEntity<Integer>(HttpStatus.LOCKED); //423
      }
      catch(RestClientException e) {
        log.error(e.toString());
        return new ResponseEntity<Integer>(HttpStatus.SERVICE_UNAVAILABLE); //503
      }
      catch(Exception e) {
        log.error(e.toString());
        return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR); //500
      }
    }

    public ResponseEntity<Amigo> updateHandle(@NotNull @ApiParam(value = "login token", required = true) @Valid @RequestParam(value = "token", required = true) String token,@NotNull @ApiParam(value = "registry base url", required = true) @Valid @RequestParam(value = "registry", required = true) String registry,@ApiParam(value = "current revision") @Valid @RequestParam(value = "revision", required = false) Integer revision) {
      try {
        Account act = authService.loginToken(token);
        Amigo amigo = accountService.update(act, registry, revision);
        return new ResponseEntity<Amigo>(amigo, HttpStatus.OK);
      }
      catch(InvalidParameterException e) {
        log.error(e.toString());
        return new ResponseEntity<Amigo>(HttpStatus.UNAUTHORIZED); //401
      }
      catch(IllegalArgumentException e) {
        log.error(e.toString());
        return new ResponseEntity<Amigo>(HttpStatus.BAD_REQUEST); //400
      }
      catch(NotAcceptableException e) {
        log.error(e.toString());
        return new ResponseEntity<Amigo>(HttpStatus.LOCKED); //423
      }
      catch(RestClientException e) {
        log.error(e.toString());
        return new ResponseEntity<Amigo>(HttpStatus.SERVICE_UNAVAILABLE); //503
      }
      catch(Exception e) {
        log.error(e.toString());
        return new ResponseEntity<Amigo>(HttpStatus.INTERNAL_SERVER_ERROR); //500
      }
    }       
}
