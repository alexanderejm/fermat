package com.bitdubai.fermat_cls_api.all_definition.exceptions;

/**
 * Created by Alexander Jimenez (alex_jimenez76@hotmail.com) on 6/30/16.
 */
public class CantHideIdentityException extends CLSException {

    public static final String DEFAULT_MESSAGE = "CANNOT HIDE IDENTITY";

    public CantHideIdentityException(
            final String message,
            final Exception cause,
            final String context,
            final String possibleReason) {
        super(message, cause, context, possibleReason);
    }

    public CantHideIdentityException(
            Exception cause,
            String context,
            String possibleReason) {
        super(DEFAULT_MESSAGE , cause, context, possibleReason);
    }

    public CantHideIdentityException(
            final String message,
            final Exception cause) {
        this(message, cause, "", "");
    }

    public CantHideIdentityException(final String message) {
        this(message, null);
    }

    public CantHideIdentityException(final Exception exception) {
        this(exception.getMessage());
        setStackTrace(exception.getStackTrace());
    }

    public CantHideIdentityException() {
        this(DEFAULT_MESSAGE);
    }
}
