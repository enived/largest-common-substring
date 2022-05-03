package com.devine.lcs.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = InvalidFormDataException.REASON)
public class InvalidFormDataException extends HttpClientErrorException {
    static final String REASON = "Form data was not a valid JSON set";
    public InvalidFormDataException() {
        super(HttpStatus.BAD_REQUEST, REASON);
    }
}
