package com.bitdubai.smartwallet.core.system.wallet.store;

import com.bitdubai.smartwallet.core.system.review.Dislikeable;
import com.bitdubai.smartwallet.core.system.review.Likeable;
import com.bitdubai.smartwallet.core.system.review.Reviewable;
import com.bitdubai.smartwallet.core.system.wallet.license.License;

import java.util.List;

/**
 * Created by ciencias on 28.12.14.
 */
public class CommercialInstalledWallet implements  CommercialWallet, Reviewable, Likeable, Dislikeable {


    private List<License> mLicense;
}

