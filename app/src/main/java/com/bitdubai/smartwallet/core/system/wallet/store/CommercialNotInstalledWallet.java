package com.bitdubai.smartwallet.core.system.wallet.store;

import com.bitdubai.smartwallet.core.system.review.Dislikeable;
import com.bitdubai.smartwallet.core.system.review.Likeable;
import com.bitdubai.smartwallet.core.system.wallet.license.License;

import java.util.List;

/**
 * Created by ciencias on 28.12.14.
 */
public class CommercialNotInstalledWallet implements  CommercialWallet,  Likeable, Dislikeable {


    private List<License> mLicense;
}