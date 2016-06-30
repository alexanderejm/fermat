package com.bitdubai.fermat_cls_api.all_definition.interfaces;

import java.io.Serializable;

/**
 * Created by Alexander Jimenez (alex_jimenez76@hotmail.com) on 6/30/16.
 */
public interface CLSIdentity extends Serializable {

    /**
     * This method returns the identity Alias.
     * @return
     */
    String getAlias();
    /**
     * This method returns the identity public key.
     * @return
     */
    String getPublicKey();

    /**
     * This method returns the profile image.
     * @return
     */
    byte[] getProfileImage();

    /**
     * This method saves a profile image
     * @param imageBytes
     */
    void setNewProfileImage(final byte[] imageBytes);

}
