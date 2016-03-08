package com.bitdubai.fermat_ccp_plugin.layer.crypto_transaction.outgoing_draft.developer.bitdubai.varsion_1.util;

import com.bitdubai.fermat_api.layer.all_definition.enums.Actors;
import com.bitdubai.fermat_api.layer.all_definition.enums.BlockchainNetworkType;
import com.bitdubai.fermat_api.layer.all_definition.enums.ReferenceWallet;
import com.bitdubai.fermat_api.layer.all_definition.money.CryptoAddress;

import java.util.UUID;

/**
 * Created by eze on 2015.09.21..
 */
public class OutgoingDraftTransactionWrapper {

    private UUID requestId;
//    private DraftTransaction bitcoinTransaction;
//    private CryptoAddress sellerCryptoAddress;
//    private CryptoAddress buyerCryptoAddress;
    private String walletPublicKey;
    private long valueToSend;
    private CryptoAddress addressTo;
    private ReferenceWallet referenceWallet;
    private BlockchainNetworkType blockchainNetworkType;
    private String actorFromPublicKey;
    private String actorToPublicKey;
    private Actors actorFromType;
    private Actors actorToType;
    private String memo;
    private long timestamp;
    private String txHash;

    public OutgoingDraftTransactionWrapper() {
    }

    public OutgoingDraftTransactionWrapper(
            UUID requestId,
            String txHash,
            long valueToSend,
            CryptoAddress addressTo,
            String walletPublicKey,
            ReferenceWallet referenceWallet) {
        this.requestId = requestId;
//        this.bitcoinTransaction = bitcoinTransaction;
//        this.sellerCryptoAddress = sellerCryptoAddress;
//        this.buyerCryptoAddress = buyerCryptoAddress;
        this.valueToSend = valueToSend;
        this.addressTo = addressTo;
        this.walletPublicKey = walletPublicKey;
        this.referenceWallet = referenceWallet;
        this.txHash = txHash;
    }

    public OutgoingDraftTransactionWrapper(UUID requestId,String txHash, String walletPublicKey, long valueToSend, CryptoAddress addressTo, ReferenceWallet referenceWallet, BlockchainNetworkType blockchainNetworkType, String actorFromPublicKey, String actorToPublicKey, Actors actorFromType, Actors actorToType, String memo, long timestamp) {
        this.requestId = requestId;
//        this.bitcoinTransaction = bitcoinTransaction;
//        this.sellerCryptoAddress = sellerCryptoAddress;
//        this.buyerCryptoAddress = buyerCryptoAddress;
        this.walletPublicKey = walletPublicKey;
        this.valueToSend = valueToSend;
        this.addressTo = addressTo;
        this.referenceWallet = referenceWallet;
        this.blockchainNetworkType = blockchainNetworkType;
        this.actorFromPublicKey = actorFromPublicKey;
        this.actorToPublicKey = actorToPublicKey;
        this.actorFromType = actorFromType;
        this.actorToType = actorToType;
        this.memo = memo;
        this.timestamp = timestamp;
        this.txHash = txHash;
    }

//    public CryptoAddress getBuyerCryptoAddress() {
//        return buyerCryptoAddress;
//    }
//
//    public DraftTransaction getBitcoinTransaction() {
//        return bitcoinTransaction;
//    }
//
//    public CryptoAddress getSellerCryptoAddress() {
//        return sellerCryptoAddress;
//    }

    public long getValueToSend() {
        return valueToSend;
    }

    public CryptoAddress getAddressTo() {
        return addressTo;
    }

    public String getWalletPublicKey() {
        return walletPublicKey;
    }

    public ReferenceWallet getReferenceWallet() {
        return referenceWallet;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public BlockchainNetworkType getBlockchainNetworkType() {
        return blockchainNetworkType;
    }

    public String getActorFromPublicKey() {
        return actorFromPublicKey;
    }

    public String getActorToPublicKey() {
        return actorToPublicKey;
    }

    public Actors getActorFromType() {
        return actorFromType;
    }

    public Actors getActorToType() {
        return actorToType;
    }

    public String getMemo() {
        return memo;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getTxHash() {
        return txHash;
    }
}
