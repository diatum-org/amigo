package org.coredb.emigo.api;

import org.coredb.emigo.model.EmigoLogin;
import org.coredb.emigo.model.Result;
import org.coredb.emigo.model.Emigo;
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

    public ResponseEntity<EmigoLogin> attachAccount(@NotNull @ApiParam(value = "password to use for login", required = true) @Valid @RequestParam(value = "password", required = true) String password
,@NotNull @ApiParam(value = "id of emigo to be attached", required = true) @Valid @RequestParam(value = "emigoId", required = true) String emigoId
,@NotNull @ApiParam(value = "single use code", required = true) @Valid @RequestParam(value = "code", required = true) String code
,@NotNull @ApiParam(value = "node to forward request to", required = true) @Valid @RequestParam(value = "node", required = true) String node
) {
      try {
        EmigoLogin emigo = accountService.attach(emigoId, node, code);
        return new ResponseEntity<EmigoLogin>(emigo, HttpStatus.CREATED);
      }  
      catch(java.security.InvalidParameterException e) {
        log.error(e.toString());
        return new ResponseEntity<EmigoLogin>(HttpStatus.UNAUTHORIZED); //401
      }
      catch(IOException e) {
        log.error(e.toString());
        return new ResponseEntity<EmigoLogin>(HttpStatus.NOT_ACCEPTABLE); //406
      }
      catch(RestClientException e) {
        log.error(e.toString());
        return new ResponseEntity<EmigoLogin>(HttpStatus.SERVICE_UNAVAILABLE); //503
      }
      catch(Exception e) {
        log.error(e.toString());
        return new ResponseEntity<EmigoLogin>(HttpStatus.INTERNAL_SERVER_ERROR); //500
      }
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

    public ResponseEntity<Emigo> updateHandle(@NotNull @ApiParam(value = "login token", required = true) @Valid @RequestParam(value = "token", required = true) String token,@NotNull @ApiParam(value = "registry base url", required = true) @Valid @RequestParam(value = "registry", required = true) String registry,@ApiParam(value = "current revision") @Valid @RequestParam(value = "revision", required = false) Integer revision) {
      try {
        Account act = authService.loginToken(token);
        Emigo emigo = accountService.update(act, registry, revision);
        return new ResponseEntity<Emigo>(emigo, HttpStatus.OK);
      }
      catch(InvalidParameterException e) {
        log.error(e.toString());
        return new ResponseEntity<Emigo>(HttpStatus.UNAUTHORIZED); //401
      }
      catch(IllegalArgumentException e) {
        log.error(e.toString());
        return new ResponseEntity<Emigo>(HttpStatus.BAD_REQUEST); //400
      }
      catch(NotAcceptableException e) {
        log.error(e.toString());
        return new ResponseEntity<Emigo>(HttpStatus.LOCKED); //423
      }
      catch(RestClientException e) {
        log.error(e.toString());
        return new ResponseEntity<Emigo>(HttpStatus.SERVICE_UNAVAILABLE); //503
      }
      catch(Exception e) {
        log.error(e.toString());
        return new ResponseEntity<Emigo>(HttpStatus.INTERNAL_SERVER_ERROR); //500
      }
    }       
}
