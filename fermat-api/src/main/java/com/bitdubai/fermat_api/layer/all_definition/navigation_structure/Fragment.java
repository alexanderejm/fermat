package com.bitdubai.fermat_api.layer.all_definition.navigation_structure;

import com.bitdubai.fermat_api.layer.dmp_engine.sub_app_runtime.enums.Fragments;

/**
 * Created by rodrigo on 2015.07.17..
 */
public class Fragment implements com.bitdubai.fermat_api.layer.dmp_engine.sub_app_runtime.Fragment {

    Fragments type;
    Fragments fragmentBack;
    Object[] context;

    public void setBack(Fragments fragmentBack)
    {
        this.fragmentBack = fragmentBack;
    }

    public void setType(Fragments type) {
        this.type = type;
    }

    /**
     * Fragment interface implementation.
     */
    @Override
    public Fragments getType() {
        return type;
    }

    public Fragments getBack(){
        return this.fragmentBack;
    }



    public void setContext(Object[] context){
        this.context = context;
    }


    public Object[] getContext(){
        return this.context;

    }
}
