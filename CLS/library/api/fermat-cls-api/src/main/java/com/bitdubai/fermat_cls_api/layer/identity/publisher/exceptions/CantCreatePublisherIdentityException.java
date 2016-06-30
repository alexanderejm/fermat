package com.bitdubai.fermat_cls_api.layer.identity.publisher.exceptions;

import com.bitdubai.fermat_cls_api.all_definition.exceptions.CLSException;

/**
 * Created by Alexander Jimenez (alex_jimenez76@hotmail.com) on 6/30/16.
 */
public class CantCreatePublisherIdentityException extends CLSException {

    public static final String DEFAULT_MESSAGE = "CANNOT CREATE PUBLISHER IDENTITY";

    public CantCreatePublisherIdentityException(
            final String message,
            final Exception cause,
            final String context,
            final String possibleReason) {
        super(message, cause, context, possibleReason);
    }

    public CantCreatePublisherIdentityException(
            Exception cause,
            String context,
            String possibleReason) {
        super(DEFAULT_MESSAGE , cause, context, possibleReason);
    }

    public CantCreatePublisherIdentityException(
            final String message,
            String context,
            String possibleReason) {
        this(message, null, context, possibleReason);
    }

    public CantCreatePublisherIdentityException(
            final String message,
            final Exception cause) {
        this(message, cause, "", "");
    }

    public CantCreatePublisherIdentityException(final String message) {
        this(message, null);
    }

    public CantCreatePublisherIdentityException(final Exception exception) {
        this(exception.getMessage());
        setStackTrace(exception.getStackTrace());
    }

    public CantCreatePublisherIdentityException() {
        this(DEFAULT_MESSAGE);
    }
}
