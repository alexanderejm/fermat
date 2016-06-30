package com.bitdubai.fermat_cls_api.layer.identity.publisher.exceptions;

import com.bitdubai.fermat_cls_api.all_definition.exceptions.CLSException;

/**
 * Created by Alexander Jimenez (alex_jimenez76@hotmail.com) on 6/30/16.
 */
public class CantListPublisherIdentitiesException extends CLSException {

    public static final String DEFAULT_MESSAGE = "CANNOT LIST PUBLISHER IDENTITIES";

    public CantListPublisherIdentitiesException(
            final String message,
            final Exception cause,
            final String context,
            final String possibleReason) {
        super(message, cause, context, possibleReason);
    }

    public CantListPublisherIdentitiesException(
            Exception cause,
            String context,
            String possibleReason) {
        super(DEFAULT_MESSAGE , cause, context, possibleReason);
    }

    public CantListPublisherIdentitiesException(
            final String message,
            final Exception cause) {
        this(message, cause, "", "");
    }

    public CantListPublisherIdentitiesException(final String message) {
        this(message, null);
    }

    public CantListPublisherIdentitiesException(final Exception exception) {
        this(exception.getMessage());
        setStackTrace(exception.getStackTrace());
    }

    public CantListPublisherIdentitiesException() {
        this(DEFAULT_MESSAGE);
    }
}
