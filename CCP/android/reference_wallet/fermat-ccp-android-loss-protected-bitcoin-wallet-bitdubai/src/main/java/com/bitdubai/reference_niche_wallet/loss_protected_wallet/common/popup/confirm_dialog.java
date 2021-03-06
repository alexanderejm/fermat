package com.bitdubai.reference_niche_wallet.loss_protected_wallet.common.popup;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bitdubai.android_fermat_ccp_loss_protected_wallet_bitcoin.R;
import com.bitdubai.fermat_api.layer.all_definition.enums.Actors;
import com.bitdubai.fermat_api.layer.all_definition.enums.BlockchainNetworkType;
import com.bitdubai.fermat_api.layer.all_definition.enums.ReferenceWallet;
import com.bitdubai.fermat_api.layer.all_definition.money.CryptoAddress;
import com.bitdubai.fermat_api.layer.all_definition.settings.structure.SettingsManager;
import com.bitdubai.fermat_ccp_api.layer.wallet_module.loss_protected_wallet.LossProtectedWalletSettings;
import com.bitdubai.fermat_ccp_api.layer.wallet_module.loss_protected_wallet.exceptions.CantSendLossProtectedCryptoException;
import com.bitdubai.fermat_ccp_api.layer.wallet_module.loss_protected_wallet.exceptions.LossProtectedInsufficientFundsException;
import com.bitdubai.fermat_ccp_api.layer.wallet_module.loss_protected_wallet.interfaces.LossProtectedWallet;
import com.bitdubai.fermat_api.layer.all_definition.common.system.interfaces.ErrorManager;

/**
 * Created by Joaquin Carrasuquero on 11/04/16.
 */
public class confirm_dialog extends Dialog implements
        View.OnClickListener {



    public Activity activity;
    public Dialog d;


    private SettingsManager<LossProtectedWalletSettings> settingsManager;
    private BlockchainNetworkType blockchainNetworkType;
    private long cryptoAmount;
    private CryptoAddress destinationAddress;
    private String notes;
    private String walletPublicKey;
    private String deliveredByActorPublicKey;
    private Actors deliveredByActorType;
    private String deliveredToActorPublicKey;
    private Actors deliveredToActorType;
    private ReferenceWallet referenceWallet;

    /**
     *  Deals with crypto wallet interface
     */

    private LossProtectedWallet cryptoWallet;

    /**
     * Deals with error manager interface
     */

    private ErrorManager errorManager;

    /**
     *  UI components
     */
    Button cancel_btn;
    Button accept_btn;
    TextView confirm_text;


    /**
     * Allow the zxing engine use the default argument for the margin variable
     */
    static public int MARGIN_AUTOMATIC = -1;

    private Typeface tf;
    /**
     *
     * @param a
     * @param cryptoWallet
     */


    public confirm_dialog(Activity a,
                          LossProtectedWallet  cryptoWallet,
                          long cryptoAmount,
                          CryptoAddress destinationAddress,
                          String notes, String walletPublicKey,
                          String deliveredByActorPublicKey,
                          Actors deliveredByActorType,
                          String deliveredToActorPublicKey,
                          Actors deliveredToActorType,
                          ReferenceWallet referenceWallet,
                          BlockchainNetworkType blockchainNetworkType) {
        super(a);
        this.activity = a;
        this.cryptoWallet=cryptoWallet;
        this.cryptoAmount = cryptoAmount;
        this.destinationAddress = destinationAddress;
        this.notes = notes;
        this.walletPublicKey = walletPublicKey;
        this.deliveredByActorPublicKey = deliveredByActorPublicKey;
        this.deliveredByActorType = deliveredByActorType;
        this.deliveredToActorPublicKey = deliveredToActorPublicKey;
        this.deliveredToActorType = deliveredToActorType;
        this.referenceWallet = referenceWallet;
        this.blockchainNetworkType = blockchainNetworkType;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpScreenComponents();


    }

    private void setUpScreenComponents(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.confirmation_window);

        accept_btn = (Button) findViewById(R.id.accept_btn);
        cancel_btn = (Button) findViewById(R.id.cancel_btn);

        accept_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);

        getWindow().setBackgroundDrawable(new ColorDrawable(0));

    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.cancel_btn) {
            //activity.finish();
            dismiss();
        }else if( i == R.id.accept_btn){
            try {
                cryptoWallet.send(
                       this.cryptoAmount,
                        this.destinationAddress,
                        notes,
                        this.walletPublicKey,
                        this.deliveredByActorPublicKey,
                        this.deliveredByActorType,
                        this.deliveredToActorPublicKey,
                        this.deliveredToActorType,
                        this.referenceWallet,
                        blockchainNetworkType
                );
            } catch (CantSendLossProtectedCryptoException e) {
                e.printStackTrace();
                Toast.makeText(this.activity, "Unexpected error", Toast.LENGTH_SHORT).show();
            } catch (LossProtectedInsufficientFundsException e) {
                e.printStackTrace();
                Toast.makeText(this.activity, "Not enough funds to perform action", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this.activity, "Sending...", Toast.LENGTH_SHORT).show();
            dismiss();
        }
    }



}