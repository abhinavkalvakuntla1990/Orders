package org.egen.ecommerce.web.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.egen.ecommerce.web.exception.custom.PreconditionFailedException;
import org.springframework.dao.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
@Slf4j
class GlobalControllerExceptionHandler {


  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(DataRetrievalFailureException.class)
  @ResponseBody
  public ErrorInfo handleItemNotFound(final HttpServletRequest req, final Exception ex) {
    log.debug(req.getRequestURL().toString(), ex);
    return new ErrorInfo(req.getRequestURL().toString(), ex);
  }

  @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
  @ExceptionHandler({ DataIntegrityViolationException.class, PreconditionFailedException.class,
        IllegalArgumentException.class })
  @ResponseBody
  public ErrorInfo handleDataIntegrity(final HttpServletRequest req, final Exception ex) {
    log.debug(req.getRequestURL().toString(), ex);
    return new ErrorInfo(req.getRequestURL().toString(), ex);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseBody
  public ErrorInfo handleBadRequestException(final HttpServletRequest req, final Exception e) {
    log.debug(e.getMessage());
    return new ErrorInfo(req.getRequestURL().toString(), e);
  }

}
