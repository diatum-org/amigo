package org.coredb.emigo.api;

import org.coredb.emigo.model.GpsLocation;
import org.coredb.emigo.model.Profile;
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
import org.coredb.emigo.service.ProfileService;

import java.security.InvalidParameterException;
import javax.ws.rs.NotAcceptableException;
import org.springframework.dao.DataIntegrityViolationException;
import java.nio.file.AccessDeniedException;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@Controller
public class ProfileApiController implements ProfileApi {

    private static final Logger log = LoggerFactory.getLogger(ProfileApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    private AuthService authService;

    @org.springframework.beans.factory.annotation.Autowired
    private ProfileService profileService;

    @org.springframework.beans.factory.annotation.Autowired
    public ProfileApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    public ResponseEntity<Profile> availableProfile(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token,@NotNull @ApiParam(value = "whether account is searchable", required = true) @Valid @RequestParam(value = "flag", required = true) Boolean flag) {
      try {
        Account account = authService.loginToken(token);
        Profile profile = profileService.setAvailable(account, flag);
        return new ResponseEntity<Profile>(profile, HttpStatus.OK);
      }
      catch(InvalidParameterException e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.UNAUTHORIZED); //401
      }
      catch(NotAcceptableException e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.NOT_ACCEPTABLE); //423
      }
      catch(Exception e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.INTERNAL_SERVER_ERROR); //500
      }
    }
        
    public ResponseEntity<Profile> emailProfile(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token,@NotNull @ApiParam(value = "email address to update", required = true) @Valid @RequestParam(value = "emailAddress", required = true) String emailAddress) {
      try {
        Account account = authService.loginToken(token);
        Profile profile = profileService.setEmail(account, emailAddress);
        return new ResponseEntity<Profile>(profile, HttpStatus.OK);
      }
      catch(InvalidParameterException e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.UNAUTHORIZED); //401
      }
      catch(NotAcceptableException e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.NOT_ACCEPTABLE); //423
      }
      catch(Exception e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.INTERNAL_SERVER_ERROR); //500
      }
    }

    public ResponseEntity<Profile> getProfile(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token) {
      try {
        Account account = authService.loginToken(token);
        Profile profile = profileService.getProfile(account);
        return new ResponseEntity<Profile>(profile, HttpStatus.OK);
      }
      catch(InvalidParameterException e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.UNAUTHORIZED); //401
      }
      catch(NotAcceptableException e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.NOT_ACCEPTABLE); //423
      }
      catch(Exception e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.INTERNAL_SERVER_ERROR); //500
      }
    }

    public ResponseEntity<Integer> getProfileRevision(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token) {
      try {
        Account account = authService.loginToken(token);
        Integer rev = profileService.getRevision(account);
        return new ResponseEntity<Integer>(rev, HttpStatus.OK);
      }
      catch(InvalidParameterException e) {
        log.error(e.toString());
        return new ResponseEntity<Integer>(HttpStatus.UNAUTHORIZED); //401
      }
      catch(NotAcceptableException e) {
        log.error(e.toString());
        return new ResponseEntity<Integer>(HttpStatus.NOT_ACCEPTABLE); //423
      }
      catch(Exception e) {
        log.error(e.toString());
        return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR); //500
      }
    }

    public ResponseEntity<Profile> gpsProfile(@ApiParam(value = "emigo to insert" ,required=true )  @Valid @RequestBody GpsLocation body,@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token,@ApiParam(value = "expiration of location") @Valid @RequestParam(value = "expires", required = false) Long expires) {
      try {
        Account account = authService.loginToken(token);
        Profile profile = profileService.setLocation(account, body, expires);
        return new ResponseEntity<Profile>(profile, HttpStatus.OK);
      }
      catch(InvalidParameterException e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.UNAUTHORIZED); //401
      }
      catch(NotAcceptableException e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.NOT_ACCEPTABLE); //423
      }
      catch(Exception e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.INTERNAL_SERVER_ERROR); //500
      }
    }

    public ResponseEntity<Void> passwordProfile(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token,@NotNull @ApiParam(value = "account password", required = true) @Valid @RequestParam(value = "password", required = true) String password,@NotNull @ApiParam(value = "updated account password", required = true) @Valid @RequestParam(value = "update", required = true) String update) {
      try {
        Account account = authService.loginToken(token);
        profileService.setPassword(account, password, update);
        return new ResponseEntity<Void>(HttpStatus.OK);
      }
      catch(InvalidParameterException e) {
        log.error(e.toString());
        return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED); //401
      }
      catch(IllegalArgumentException e) {
        log.error(e.toString());
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST); //400
      }
      catch(NotAcceptableException e) {
        log.error(e.toString());
        return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE); //423
      }
      catch(Exception e) {
        log.error(e.toString());
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR); //500
      }
    }

    public ResponseEntity<Profile> phoneProfile(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token,@NotNull @ApiParam(value = "phone number to update", required = true) @Valid @RequestParam(value = "phoneNumber", required = true) String phoneNumber) {
      try {
        Account account = authService.loginToken(token);
        Profile profile = profileService.setPhone(account, phoneNumber);
        return new ResponseEntity<Profile>(profile, HttpStatus.OK);
      }
      catch(InvalidParameterException e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.UNAUTHORIZED); //401
      }
      catch(NotAcceptableException e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.NOT_ACCEPTABLE); //423
      }
      catch(Exception e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.INTERNAL_SERVER_ERROR); //500
      }
    }

    public ResponseEntity<Profile> searchableProfile(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token,@NotNull @ApiParam(value = "whether account is searchable", required = true) @Valid @RequestParam(value = "flag", required = true) Boolean flag) {
    try {
        Account account = authService.loginToken(token);
        Profile profile = profileService.setSearchable(account, flag);
        return new ResponseEntity<Profile>(profile, HttpStatus.OK);
      }
      catch(InvalidParameterException e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.UNAUTHORIZED); //401
      }
      catch(NotAcceptableException e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.NOT_ACCEPTABLE); //423
      }
      catch(Exception e) {
        log.error(e.toString());
        return new ResponseEntity<Profile>(HttpStatus.INTERNAL_SERVER_ERROR); //500
      }
    }

    public ResponseEntity<Void> confirmTrigger(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token
) {
    try {
      Account account = authService.loginToken(token);
      profileService.confirmContact(account);
      return new ResponseEntity<Void>(HttpStatus.OK);
    }
    catch(InvalidParameterException e) {
      return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED); //401
    }
    catch(NotAcceptableException e) {
      return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE); //423
    }
    catch(Exception e) {
      return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR); //500
    }
  }

    public ResponseEntity<Void> resetTrigger(@ApiParam(value = "recovery phone number") @Valid @RequestParam(value = "phoneNumber", required = false) String phoneNumber
,@ApiParam(value = "recovery email address") @Valid @RequestParam(value = "emailAddress", required = false) String emailAddress
) {
    try {
      profileService.resetPassword(emailAddress, phoneNumber);
      return new ResponseEntity<Void>(HttpStatus.OK);
    }
    catch(NotFoundException e) {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
    catch(Exception e) {
      return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

