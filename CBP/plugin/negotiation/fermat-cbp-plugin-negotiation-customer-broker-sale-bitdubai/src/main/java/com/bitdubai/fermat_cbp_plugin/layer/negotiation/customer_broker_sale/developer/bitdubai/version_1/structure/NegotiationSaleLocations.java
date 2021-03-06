package com.bitdubai.fermat_cbp_plugin.layer.negotiation.customer_broker_sale.developer.bitdubai.version_1.structure;

import com.bitdubai.fermat_cbp_api.all_definition.negotiation.NegotiationLocations;

import java.util.UUID;

/**
 * Created by angel on 07/12/15.
 */
public class NegotiationSaleLocations implements NegotiationLocations {

    private final UUID locationId;
    private final String location;
    private final String uri;

    public NegotiationSaleLocations(UUID locationId, String location, String uri){
        this.locationId = locationId;
        this.location = location;
        this.uri = uri;
    }

    @Override
    public UUID getLocationId() {
        return this.locationId;
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public String getURI() {
        return this.uri;
    }
}
