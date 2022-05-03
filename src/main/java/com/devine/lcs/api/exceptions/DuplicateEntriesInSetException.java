package com.devine.lcs.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = DuplicateEntriesInSetException.REASON)
public class DuplicateEntriesInSetException extends HttpClientErrorException {
    static final String REASON = "setOfStrings contains duplicate entries, it is not a valid set";
    public DuplicateEntriesInSetException() {
        super(HttpStatus.BAD_REQUEST, REASON);
    }
}
