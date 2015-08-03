package com.bitdubai.fermat_core.layer.dmp_network_service.template;

import com.bitdubai.fermat_api.Plugin;
import com.bitdubai.fermat_api.layer.dmp_network_service.CantStartSubsystemException;
import com.bitdubai.fermat_api.layer.dmp_network_service.NetworkSubsystem;
import com.bitdubai.fermat_dmp_plugin.layer.network_service.template.developer.bitdubai.DeveloperBitDubai;


/**
 * Created by ciencias on 20.01.15.
 */
public class TemplateSubsystem implements NetworkSubsystem{

    Plugin plugin;

    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void start() throws CantStartSubsystemException {
        /**
         * I will choose from the different Developers available which implementation to use. Right now there is only
         * one, so it is not difficult to choose.
         */

        try {

            DeveloperBitDubai developerBitDubai = new DeveloperBitDubai();
            plugin = developerBitDubai.getPlugin();

        }
        catch (Exception e)
        {
            System.err.println("Exception: " + e.getMessage());
            throw new CantStartSubsystemException();
        }
    }

}