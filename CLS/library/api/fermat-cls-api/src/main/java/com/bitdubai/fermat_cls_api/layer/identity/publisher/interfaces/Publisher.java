package com.bitdubai.fermat_cls_api.layer.identity.publisher.interfaces;

import com.bitdubai.fermat_cls_api.all_definition.interfaces.CLSIdentity;

import java.io.Serializable;

/**
 * Created by Alexander Jimenez (alex_jimenez76@hotmail.com) on 6/30/16.
 */
public interface Publisher extends CLSIdentity, Serializable {

    /**
     * This method returns the identity email.
     * @return
     */
    String getEmail();

}
