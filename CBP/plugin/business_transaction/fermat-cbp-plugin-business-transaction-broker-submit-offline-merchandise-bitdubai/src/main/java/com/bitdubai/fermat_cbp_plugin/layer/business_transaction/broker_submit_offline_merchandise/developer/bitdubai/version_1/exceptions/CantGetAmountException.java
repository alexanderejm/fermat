package com.bitdubai.fermat_cbp_plugin.layer.business_transaction.broker_submit_offline_merchandise.developer.bitdubai.version_1.exceptions;

import com.bitdubai.fermat_cbp_api.all_definition.exceptions.CBPException;

/**
 * Created by Manuel Perez (darkpriestrelative@gmail.com) on 22/12/15.
 */
public class CantGetAmountException extends CBPException {
    /**
     * Represent the default message
     */
    public static final String DEFAULT_MESSAGE = "CANNOT GET A CRYPTO ADDRESS";

    /**
     * Constructor with parameters
     *
     * @param message
     * @param cause
     * @param context
     * @param possibleReason
     */
    public CantGetAmountException(final String message, final Exception cause, final String context, final String possibleReason) {
        super(message, cause, context, possibleReason);
    }

    /**
     * Constructor with parameters
     *
     * @param cause
     * @param context
     * @param possibleReason
     */
    public CantGetAmountException(Exception cause, String context, String possibleReason) {
        super(DEFAULT_MESSAGE , cause, context, possibleReason);
    }

    /**
     * Constructor with parameters
     *
     * @param message
     * @param cause
     */
    public CantGetAmountException(final String message, final Exception cause) {
        this(message, cause, "", "");
    }

    /**
     * Constructor with parameter
     *
     * @param message
     */
    public CantGetAmountException(final String message) {
        this(message, null);
    }

    /**
     * Constructor with parameter
     *
     * @param exception
     */
    public CantGetAmountException(final Exception exception) {
        this(exception.getMessage());
        setStackTrace(exception.getStackTrace());
    }

    /**
     * Constructor
     */
    public CantGetAmountException() {
        this(DEFAULT_MESSAGE);
    }

}
