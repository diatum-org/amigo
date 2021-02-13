package org.coredb.emigo.api;

import org.coredb.emigo.model.Contact;
import org.coredb.emigo.model.SearchArea;
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

import java.security.InvalidParameterException;
import javax.ws.rs.NotAcceptableException;
import org.springframework.dao.DataIntegrityViolationException;
import java.nio.file.AccessDeniedException;

import org.coredb.emigo.jpa.entity.Account;
import org.coredb.emigo.service.AuthService;
import org.coredb.emigo.service.SearchService;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@Controller
public class SearchApiController implements SearchApi {

    private static final Logger log = LoggerFactory.getLogger(SearchApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    private AuthService authService;  
  
    @org.springframework.beans.factory.annotation.Autowired
    private SearchService searchService;    

    @org.springframework.beans.factory.annotation.Autowired
    public SearchApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Contact>> scanAccounts(@ApiParam(value = "emigo to insert" ,required=true )  @Valid @RequestBody SearchArea body,@NotNull @ApiParam(value = "login token", required = true) @Valid @RequestParam(value = "token", required = true) String token,@NotNull  @DecimalMax("1024") @ApiParam(value = "return max number of results", required = true) @Valid @RequestParam(value = "limit", required = true) Integer limit,@ApiParam(value = "return results starting at offset") @Valid @RequestParam(value = "offset", required = false) Integer offset) {
       try {
        Account account = authService.loginToken(token);
        List<Contact> emigos = searchService.search(account, null, null, null, null, body, offset, limit);
        return new ResponseEntity<List<Contact>>(emigos, HttpStatus.OK);
      }
      catch(InvalidParameterException e) {
        log.error(e.toString());
        return new ResponseEntity<List<Contact>>(HttpStatus.UNAUTHORIZED); //401
      }
      catch(NotAcceptableException e) {
        log.error(e.toString());
        return new ResponseEntity<List<Contact>>(HttpStatus.NOT_ACCEPTABLE); //423
      }
      catch(Exception e) {
        log.error(e.toString());
        return new ResponseEntity<List<Contact>>(HttpStatus.INTERNAL_SERVER_ERROR); //500
      }
    }

    public ResponseEntity<List<Contact>> searchAccounts(@NotNull @ApiParam(value = "login token", required = true) @Valid @RequestParam(value = "token", required = true) String token,@NotNull  @DecimalMax("1024") @ApiParam(value = "return max number of results", required = true) @Valid @RequestParam(value = "limit", required = true) Integer limit,@ApiParam(value = "or-set of matching name, handle, location, description") @Valid @RequestParam(value = "match", required = false) String match,@ApiParam(value = "filter with name like") @Valid @RequestParam(value = "name", required = false) String name,@ApiParam(value = "filter with handle like") @Valid @RequestParam(value = "handle", required = false) String handle,@ApiParam(value = "filter with description like") @Valid @RequestParam(value = "description", required = false) String description,@ApiParam(value = "filter with location like") @Valid @RequestParam(value = "location", required = false) String location,@ApiParam(value = "return results starting at offset") @Valid @RequestParam(value = "offset", required = false) Integer offset) {
      try {
        Account account = authService.loginToken(token);
        List<Contact> emigos = searchService.search(account, match, name, description, location, null, offset, limit);
        return new ResponseEntity<List<Contact>>(emigos, HttpStatus.OK);
      }
      catch(InvalidParameterException e) {
        log.error(e.toString());
        return new ResponseEntity<List<Contact>>(HttpStatus.UNAUTHORIZED); //401
      }
      catch(NotAcceptableException e) {
        log.error(e.toString());
        return new ResponseEntity<List<Contact>>(HttpStatus.NOT_ACCEPTABLE); //423
      }
      catch(Exception e) {
        log.error(e.toString());
        return new ResponseEntity<List<Contact>>(HttpStatus.INTERNAL_SERVER_ERROR); //500
      }
    }

}
