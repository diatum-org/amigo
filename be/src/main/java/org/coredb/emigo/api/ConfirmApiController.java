package org.coredb.emigo.api;

import org.coredb.emigo.model.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.AccessDeniedException;
import org.coredb.emigo.api.NotFoundException;

import org.coredb.emigo.service.ConfirmService;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@Controller
public class ConfirmApiController implements ConfirmApi {

    private static final Logger log = LoggerFactory.getLogger(ConfirmApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    ConfirmService confirmService;

    @org.springframework.beans.factory.annotation.Autowired
    public ConfirmApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> confirmAccount(@NotNull @ApiParam(value = "confirmation token", required = true) @Valid @RequestParam(value = "token", required = true) String token
) {
      try {
        confirmService.setConfirmation(token);
        return new ResponseEntity<Void>(HttpStatus.OK);
      }
      catch(AccessDeniedException e) {
        return new ResponseEntity<Void>(HttpStatus.GONE);
      }
      catch(NotFoundException e) {
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
      }
      catch(Exception e) {
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

    public ResponseEntity<Contact> confirmCheck(@NotNull @ApiParam(value = "confirmation token", required = true) @Valid @RequestParam(value = "token", required = true) String token
) {
      try {
        Contact contact = confirmService.getConfirmation(token);
        return new ResponseEntity<Contact>(contact, HttpStatus.OK);
      }
      catch(AccessDeniedException e) {
        return new ResponseEntity<Contact>(HttpStatus.GONE);
      }
      catch(NotFoundException e) {
        return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
      }
      catch(Exception e) {
        return new ResponseEntity<Contact>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

}

