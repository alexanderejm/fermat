package org.fermat.fermat_dap_core.layer.funds_transaction.asset_buyer;

import com.bitdubai.fermat_api.layer.all_definition.common.system.utils.PluginReference;
import com.bitdubai.fermat_api.layer.all_definition.enums.Plugins;
import com.bitdubai.fermat_core_api.layer.all_definition.system.abstract_classes.AbstractPluginSubsystem;
import com.bitdubai.fermat_core_api.layer.all_definition.system.exceptions.CantStartSubsystemException;

import org.fermat.fermat_dap_plugin.layer.digital_asset_transaction.asset_buyer.developer.DeveloperBitDubai;

/**
 * Created by Víctor A. Mars M. (marsvicam@gmail.com) on 10/02/16.
 */
public class AssetBuyerPluginSubsystem extends AbstractPluginSubsystem {

    //VARIABLE DECLARATION

    //CONSTRUCTORS
    public AssetBuyerPluginSubsystem() {
        super(new PluginReference(Plugins.ASSET_BUYER));
    }

    //PUBLIC METHODS
    @Override
    public void start() throws CantStartSubsystemException {
        try {
            registerDeveloper(new DeveloperBitDubai());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            throw new CantStartSubsystemException(e, null, null);
        }
    }
    //PRIVATE METHODS

    //GETTER AND SETTERS

    //INNER CLASSES
}
