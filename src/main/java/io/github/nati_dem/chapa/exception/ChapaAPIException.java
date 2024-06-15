package io.github.nati_dem.chapa.exception;

import io.github.nati_dem.chapa.model.ResponseData;

/**
 * A ChapaConnectionException is thrown to signal a problem during SDK execution.
 */
public class ChapaAPIException extends RuntimeException {

    public ChapaAPIException(String message) {
        super(message);
    }

    public ChapaAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}
