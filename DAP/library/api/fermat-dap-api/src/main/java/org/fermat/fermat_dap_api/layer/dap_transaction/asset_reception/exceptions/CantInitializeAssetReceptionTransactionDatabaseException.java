package org.fermat.fermat_dap_api.layer.dap_transaction.asset_reception.exceptions;

/**
 * Created by Manuel Perez (darkpriestrelative@gmail.com) on 12/10/15.
 */
public class CantInitializeAssetReceptionTransactionDatabaseException extends org.fermat.fermat_dap_api.layer.all_definition.exceptions.DAPException {
    public static final String DEFAULT_MESSAGE = "CAN'T INITIALIZE ASSET DISTRIBUTION DIGITAL ASSET TRANSACTION DATABASE EXCEPTION";

    public CantInitializeAssetReceptionTransactionDatabaseException(final String message, final Exception cause, final String context, final String possibleReason) {
        super(message, cause, context, possibleReason);
    }

    public CantInitializeAssetReceptionTransactionDatabaseException(final String message, final Exception cause) {
        this(message, cause, "", "");
    }

    public CantInitializeAssetReceptionTransactionDatabaseException(final String message) {
        this(message, null);
    }

    public CantInitializeAssetReceptionTransactionDatabaseException() {
        this(DEFAULT_MESSAGE);
    }
}
