package com.bitdubai.fermat_cls_core;

import com.bitdubai.fermat_api.layer.all_definition.common.system.utils.PlatformReference;
import com.bitdubai.fermat_api.layer.all_definition.enums.Platforms;
import com.bitdubai.fermat_core_api.layer.all_definition.system.abstract_classes.AbstractPlatform;
import com.bitdubai.fermat_core_api.layer.all_definition.system.exceptions.CantStartPlatformException;

/**
 * Created by Alexander Jimenez (alex_jimenez76@hotmail.com) on 6/29/16.
 */
public class CLSPlatform extends AbstractPlatform {

    public CLSPlatform() {
        super(new PlatformReference(Platforms.CLASSIFIED));
    }

    @Override
    public void start() throws CantStartPlatformException {

    }
}
