package ru.roman.api;


import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.roman.errors.BadRequest;
import ru.roman.errors.Forbidden;
import ru.roman.errors.InternalError;

public abstract class ApiBase {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Error handleInternal(InternalError internalError) {
        log.error("InternalError", internalError);
        return new Error(Strings.isNullOrEmpty(internalError.getMessage()) ? "Internal server error" : internalError.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleBadRequest(BadRequest badRequest) {
        log.debug("BadRequest", badRequest);
        return new Error(Strings.isNullOrEmpty(badRequest.getMessage()) ? "Bad search" : badRequest.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Error handleForbidden(Forbidden forbidden) {
        log.trace("Forbidden", forbidden);
        return new Error(Strings.isNullOrEmpty(forbidden.getMessage()) ? "Forbidden" : forbidden.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleException(Throwable ex) {
        log.error("Exception", ex);
        return new Error(Strings.isNullOrEmpty(ex.getMessage()) ? "Internal server error" : ex.getMessage());
    }
}
