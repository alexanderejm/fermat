package com.bitdubai.fermat_cls_api.layer.identity.publisher.interfaces;

import com.bitdubai.fermat_api.layer.all_definition.common.system.interfaces.FermatManager;
import com.bitdubai.fermat_cls_api.all_definition.exceptions.CantHideIdentityException;
import com.bitdubai.fermat_cls_api.all_definition.exceptions.CantPublishIdentityException;
import com.bitdubai.fermat_cls_api.all_definition.exceptions.IdentityNotFoundException;
import com.bitdubai.fermat_cls_api.all_definition.interfaces.CLSIdentity;
import com.bitdubai.fermat_cls_api.layer.identity.publisher.exceptions.CantCreatePublisherIdentityException;
import com.bitdubai.fermat_cls_api.layer.identity.publisher.exceptions.CantGetPublisherIdentityException;
import com.bitdubai.fermat_cls_api.layer.identity.publisher.exceptions.CantListPublisherIdentitiesException;
import com.bitdubai.fermat_cls_api.layer.identity.publisher.exceptions.CantUpdatePublisherIdentityException;
import com.bitdubai.fermat_cls_api.layer.identity.publisher.exceptions.PublisherIdentityAlreadyExistsException;

import java.util.List;

/**
 * Created by Alexander Jimenez (alex_jimenez76@hotmail.com) on 6/30/16.
 */
public interface PublisherIdentityManager extends FermatManager {

    /**
     * Through the method <code>listIdentitiesFromCurrentDeviceUser</code> we can get all the Publisher
     * identities linked to the current logged device user.
     * @return
     * @throws CantListPublisherIdentitiesException
     */
    List<Publisher> listIdentitiesFromCurrentDeviceUser() throws CantListPublisherIdentitiesException;

    /**
     * Return an Object with the basic data from the linked identity and its respectible
     * @param publicKey
     * @return
     */
    CLSIdentity getLinkedIdentity(String publicKey);

    /**
     * Through the method <code>createPublisherIdentity</code> you can create a new Publisher identity.
     * @param alias
     * @param imageBytes
     * @param email
     * @return
     * @throws CantCreatePublisherIdentityException
     * @throws PublisherIdentityAlreadyExistsException
     */

    Publisher createPublisherIdentity(
            final String alias,
            final byte[] imageBytes,
            final String email) throws CantCreatePublisherIdentityException,
            PublisherIdentityAlreadyExistsException;
    /**
     *
     * @param alias
     * @param publicKey
     * @param profileImage
     * @param email
     * @throws CantUpdatePublisherIdentityException
     */
    void updatePublisherIdentity(
            String alias,
            String publicKey,
            byte[] profileImage,
            String email) throws
            CantUpdatePublisherIdentityException;

    /**
     * This method returns a Publisher identity
     * @param publicKey
     * @return
     * @throws CantGetPublisherIdentityException
     * @throws IdentityNotFoundException
     */
    Publisher getPublisherIdentity(String publicKey) throws
            CantGetPublisherIdentityException,
            IdentityNotFoundException;

    /**
     * The method <code>publishIdentity</code> is used to publish a Publisher identity.
     * @param publicKey
     *
     * @throws CantPublishIdentityException
     * @throws IdentityNotFoundException
     */
    void publishIdentity(String publicKey) throws
            CantPublishIdentityException,
            IdentityNotFoundException;

    /**
     * The method <code>hideIdentity</code> is used to publish a Publisher identity
     * @param publicKey
     *
     * @throws CantHideIdentityException
     * @throws IdentityNotFoundException
     */
    void hideIdentity(String publicKey) throws
            CantHideIdentityException,
            IdentityNotFoundException;
}
