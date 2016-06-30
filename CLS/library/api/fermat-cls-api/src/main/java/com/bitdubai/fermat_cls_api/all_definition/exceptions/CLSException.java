package com.bitdubai.fermat_cls_api.all_definition.exceptions;

import com.bitdubai.fermat_api.FermatException;

/**
 * Created by Alexander Jimenez (alex_jimenez76@hotmail.com) on 6/30/16.
 */
public class CLSException extends FermatException {

    public static final String DEFAULT_MESSAGE = "THE CLS PLATFORM HAS DETECTED AN EXCEPTION";

    public CLSException(
            final String message,
            final Exception cause,
            final String context,
            final String possibleReason) {
        super(message, cause, context, possibleReason);
    }

    public CLSException(
            Exception cause,
            String context,
            String possibleReason) {
        super(DEFAULT_MESSAGE , cause, context, possibleReason);
    }

    public CLSException(
            final String message,
            final Exception cause) {
        this(message, cause, "", "");
    }

    public CLSException(final String message) {
        this(message, null);
    }

    public CLSException(final Exception exception) {
        this(exception.getMessage());
        setStackTrace(exception.getStackTrace());
    }

    public CLSException() {
        this(DEFAULT_MESSAGE);
    }
}
