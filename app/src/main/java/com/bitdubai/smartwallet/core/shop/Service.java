package com.bitdubai.smartwallet.core.shop;

import com.bitdubai.smartwallet.core.system.review.Dislikeable;
import com.bitdubai.smartwallet.core.system.review.Likeable;
import com.bitdubai.smartwallet.core.system.review.Reviewable;

/**
 * Created by ciencias on 20.12.14.
 */
public class Service implements Sellable, Likeable, Dislikeable, Reviewable {
    private double mPrice;
    private String mName;
    private String mDescription;
    private String mPicture;

}
