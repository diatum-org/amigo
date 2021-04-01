package org.coredb.emigo.api;

import org.coredb.emigo.model.AppConfig;
import org.coredb.emigo.model.AmigoEntry;
import org.coredb.emigo.model.SystemStat;
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

import org.coredb.emigo.service.ConsoleService;
import org.coredb.emigo.service.ServerStatService;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@Controller
public class ConsoleApiController implements ConsoleApi {

    private static final Logger log = LoggerFactory.getLogger(ConsoleApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    private ServerStatService statService;

    @org.springframework.beans.factory.annotation.Autowired
    private ConsoleService consoleService;

    @org.springframework.beans.factory.annotation.Autowired
    public ConsoleApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> checkToken(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token
) {
      try {
        if(consoleService.checkAccess(token)) {
          return new ResponseEntity<Void>(HttpStatus.OK);
        }
        else {
          return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
        }
      }
      catch(Exception e) {
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    public ResponseEntity<List<AmigoEntry>> getAmigos(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token
) {
      return new ResponseEntity<List<AmigoEntry>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<AmigoEntry> setAmigo(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token
      ,@ApiParam(value = "referenced amigo entry",required=true) @PathVariable("amigoId") String amigoId
      ,@NotNull @ApiParam(value = "blocked flag", required = true) @Valid @RequestParam(value = "blocked", required = true) Boolean blocked
) {
      return new ResponseEntity<AmigoEntry>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<SystemStat>> getStats(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token
) {
      try {
        if(consoleService.checkAccess(token)) {
          List<SystemStat> stats = statService.getStats(null, null);
          return new ResponseEntity<List<SystemStat>>(stats, HttpStatus.OK);
        }
        else {
          return new ResponseEntity<List<SystemStat>>(HttpStatus.FORBIDDEN);
        }
      }
      catch(Exception e) {
        return new ResponseEntity<List<SystemStat>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }


    public ResponseEntity<AppConfig> getConfig(@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token
) {
    try {
      if(consoleService.checkAccess(token)) {
        AppConfig config = consoleService.getConfig();
        return new ResponseEntity<AppConfig>(config, HttpStatus.OK);
      }
      else {
        return new ResponseEntity<AppConfig>(HttpStatus.FORBIDDEN);
      }
    }
    catch(Exception e) {
      return new ResponseEntity<AppConfig>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


    public ResponseEntity<Void> setConfig(@ApiParam(value = "updated configuration" ,required=true )  @Valid @RequestBody AppConfig body
,@NotNull @ApiParam(value = "access token", required = true) @Valid @RequestParam(value = "token", required = true) String token
) {
    try {
      if(consoleService.checkAccess(token)) {
        consoleService.setConfig(body);
        return new ResponseEntity<Void>(HttpStatus.OK);
      }
      else {
        return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
      }
    }
    catch(Exception e) {
      return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

