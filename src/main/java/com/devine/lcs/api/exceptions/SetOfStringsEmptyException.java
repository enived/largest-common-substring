package com.devine.lcs.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = SetOfStringsEmptyException.REASON)
public class SetOfStringsEmptyException extends HttpClientErrorException {
    static final String REASON = "setOfStrings must be exist and be populated";

    public SetOfStringsEmptyException() {
        super(HttpStatus.BAD_REQUEST, REASON);
    }
}
