package com.bitdubai.fermat_api.layer.all_definition.enums;

import com.bitdubai.fermat_api.layer.all_definition.enums.interfaces.FermatEnum;
import com.bitdubai.fermat_api.layer.all_definition.exceptions.InvalidParameterException;

import java.util.Objects;

/**
 * Created by ciencias on 2/13/15.
 */
public enum Plugins implements FermatEnum {

    //Modified by Manuel Perez on 03/08/2015
    BITDUBAI_LICENSE_MANAGER("BLICM"),
    BITDUBAI_BLOCKCHAIN_INFO_WORLD("BBLOCKIW"),
    BITDUBAI_SHAPE_SHIFT_WORLD("BSHAPESW"),
    BITDUBAI_COINAPULT_WORLD("BCOINAW"),
    BITDUBAI_CRYPTO_INDEX("BCRYPTOINW"),
    BITDUBAI_BITCOIN_CRYPTO_NETWORK("BBTCCNET"),
    BITDUBAI_BITCOIN_CRYPTO_NETWORK2("BBTCCNET2"),
    BITDUBAI_CLOUD_CHANNEL("BCLOUDC"),

    BITDUBAI_WS_COMMUNICATION_CLOUD_SERVER("BWSCCLSERVER"),
    BITDUBAI_WS_COMMUNICATION_CLIENT_CHANNEL("BWSCCLIENTCH"),

    BITDUBAI_CLOUD_SERVER_COMMUNICATION("BCLOUSC"),
    BITDUBAI_USER_NETWORK_SERVICE("BUSERNETS"),
    BITDUBAI_TEMPLATE_NETWORK_SERVICE("BTEMNETS"),
    BITDUBAI_INTRAUSER_NETWORK_SERVICE("BINUSERNS"),
    BITDUBAI_APP_RUNTIME_MIDDLEWARE("BAPPRUNM"),
    BITDUBAI_DISCOUNT_WALLET_BASIC_WALLET("BDWALLBW"),
    BITDUBAI_WALLET_RUNTIME_MODULE("BWALLRUNM"),
    BITDUBAI_BITCOIN_CRYPTO_VAULT("BBTCCRYV"),
    BITDUBAI_ASSETS_CRYPTO_VAULT("BASSTCRYV"),
    BITDUBAI_INTRA_USER_FACTORY_MODULE("BINUSFACM"),
    BITDUBAI_BANK_NOTES_WALLET_WALLET_MODULE("BBNWWM"),
    BITDUBAI_CRYPTO_LOSS_PROTECTED_WALLET_WALLET_MODULE("BCLPWWM"),
    BITDUBAI_CRYPTO_WALLET_WALLET_MODULE("BCWWM"),
    BITDUBAI_DISCOUNT_WALLET_WALLET_MODULE("BDWWM"),
    BITDUBAI_FIAT_OVER_CRYPTO_LOSS_PROTECTED_WALLET_WALLET_MODULE("BFOCLPWWM"),
    BITDUBAI_FIAT_OVER_CRYPTO_WALLET_WALLET_MODULE("BFOCWWM"),
    BITDUBAI_MULTI_ACCOUNT_WALLET_WALLET_MODULE("BMAWWM"),
    BITDUBAI_INCOMING_INTRA_USER_TRANSACTION("BININUST"),
    BITDUBAI_INCOMING_DEVICE_USER_TRANSACTION("BINDEVUT"),
    BITDUBAI_OUTGOING_DEVICE_USER_TRANSACTION("BODEVUST"),
    BITDUBAI_INTER_WALLET_TRANSACTION("BINWALLT"),
    BITDUBAI_BANK_NOTES_MIDDLEWARE("BBNMIDD"),
    BITDUBAI_BANK_NOTES_NETWORK_SERVICE("BBNNETSER"),
    BITDUBAI_WALLET_RESOURCES_NETWORK_SERVICE("BWRNETSER"),
    BITDUBAI_WALLET_STORE_NETWORK_SERVICE("BWSTONETSER"),
    BITDUBAI_WALLET_COMMUNITY_NETWORK_SERVICE("BWCNETSER"),
    BITDUBAI_CRYPTO_ADDRESS_BOOK("BCADDB"),
    BITDUBAI_OUTGOING_EXTRA_USER_TRANSACTION("BOUEXUT"),
    BITDUBAI_INCOMING_EXTRA_USER_TRANSACTION("BINEXUT"),
    BITDUBAI_INCOMING_CRYPTO_TRANSACTION("BINCRYT"),
    BITDUBAI_USER_DEVICE_USER("BUDEVU"),
    BITDUBAI_ACTOR_EXTRA_USER("BAEXU"),
    BITDUBAI_USER_INTRA_USER("BUINU"),
    BITDUBAI_COINBASE_WORLD("BCOINW"),
    BITDUBAI_BITCOIN_WALLET_BASIC_WALLET("BBTCWBW"),
    BITDUBAI_DEVICE_CONNECTIVITY("BBTCDEVC"),
    BITDUBAI_LOCATION_WORLD("BLOCW"),
    BITDUBAI_ACTOR_DEVELOPER("BACTORD"),
    BITDUBAI_WALLET_LANGUAGE_MIDDLEWARE("BWLMIDD"),
    BITDUBAI_WALLET_SKIN_MIDDLEWARE("BWSMIDD"),
    BITDUBAI_WALLET_NAVIGATION_STRUCTURE_MIDDLEWARE("BWNSMIDD"),
    BITDUBAI_SUB_APP_SETTINGS_MIDDLEWARE("BSASEMIDD"),
    BITDUBAI_WALLET_STATISTICS_NETWORK_SERVICE("BWSTANETSER"),
    BITDUBAI_SUBAPP_RESOURCES_NETWORK_SERVICE("BSRNETSER"),
    BITDUBAI_CCP_CRYPTO_TRANSMISSION_NETWORK_SERVICE("BCTNSER"),
    BITDUBAI_REQUEST_MONEY_REQUEST("BRMR"),
    BITDUBAI_DEVELOPER_IDENTITY("BDEVID"),
    BITDUBAI_TRANSLATOR_IDENTITY("BDTRAID"),
    BITDUBAI_IDENTITY_MANAGER("BIDMAN"),
    BITDUBAI_DESIGNER_IDENTITY("BDDESID"),
    BITDUBAI_DEVELOPER_MODULE("BDEVMOD"),
    BITDUBAI_MIDDLEWARE_NOTIFICATION("BDNOTMID"),
    BITDUBAI_DESKTOP_RUNTIME("BDR"),

    // Init CCP Plugins
    BITDUBAI_CCP_INTRA_WALLET_USER_ACTOR("BCCPIWUA"),
    BITDUBAI_CCP_CRYPTO_ADDRESSES_NETWORK_SERVICE("BCCPCANS"),
    BITDUBAI_CCP_CRYPTO_CRYPTO_TRANSMISSION_NETWORK_SERVICE("BCCPCTNS"),
    BITDUBAI_CCP_CRYPTO_PAYMENT_REQUEST_NETWORK_SERVICE("BCCPCPRNS"),
    BITDUBAI_CCP_INTRA_WALLET_USER_IDENTITY("BCCPIUI"),
    BITDUBAI_CCP_WALLET_CONTACTS_MIDDLEWARE("BCCPWCM"),
    BITDUBAI_CCP_CRYPTO_PAYMENT_REQUEST("BCCPCPR"),
    BITDUBAI_CCP_OUTGOING_INTRA_ACTOR_TRANSACTION("BCCPOIAT"),

    BITDUBAI_CRYPTO_TRANSMISSION_NETWORK_SERVICE("BCTNS"),


    // End  CCP Plugins

    //CCM Plugins

    BITDUBAI_CCM_INTRA_WALLET_USER_ACTOR("BCCMIWUA"),

    // Init DAP Plugins
    BITDUBAI_DAP_ASSET_ISSUER_ACTOR("BDAPAIA"),
    BITDUBAI_DAP_ASSET_USER_ACTOR("BDAPAUA"),
    BITDUBAI_DAP_REDEEM_POINT_ACTOR("BDAPRPA"),
    BITDUBAI_DAP_ASSET_ISSUER_IDENTITY("BDAPAII"),
    BITDUBAI_DAP_ASSET_USER_IDENTITY("BDAPAUI"),
    BITDUBAI_DAP_REDEEM_POINT_IDENTITY("BDAPRPI"),
    BITDUBAI_ASSET_ISSUING_TRANSACTION("BAIT"),
    BITDUBAI_ASSET_DISTRIBUTION_TRANSACTION("BADT"),
    BITDUBAI_ASSET_RECEPTION_TRANSACTION("BADR"),
    BITDUBAI_ASSET_APPROPRIATION_TRANSACTION("BAAT"),
    BITDUBAI_ASSET_REDEEM_POINT_REDEMPTION_TRANSACTION("BRPRT"),
    BITDUBAI_ASSET_WALLET_ISSUER("BASWI"),
    BITDUBAI_ASSET_FACTORY("BASF"),
    BITDUBAI_ASSET_FACTORY_MODULE("BASFM"),
    BITDUBAI_DAP_ASSET_USER_WALLET_MODULE("BDAUWMO"),
    BITDUBAI_DAP_ASSET_ISSUER_WALLET_MODULE("BDAIWMO"),
    BITDUBAI_DAP_ASSET_REDEEM_POINT_WALLET_MODULE("BDARWMO"),
    BITDUBAI_DAP_ASSET_ISSUER_COMMUNITY_SUB_APP_MODULE("BDAPAICSAM"),
    BITDUBAI_DAP_ASSET_USER_COMMUNITY_SUB_APP_MODULE("BDAPAUCSAM"),
    BITDUBAI_DAP_REDEEM_POINT_COMMUNITY_SUB_APP_MODULE("BDAPRPCSAM"),
    BITDUBAI_DAP_ASSET_REDEEM_POINT_WALLET("BDAPAWRD"),
    BITDUBAI_DAP_ASSET_USER_WALLET("BDAPAWU"),
    BITDUBAI_DAP_ASSET_USER_ACTOR_NETWORK_SERVICE("BDAPAUANS"),
    BITDUBAI_DAP_ASSET_ISSUER_ACTOR_NETWORK_SERVICE("BDAPAIANS"),
    BITDUBAI_DAP_ASSET_REDEEM_POINT_ACTOR_NETWORK_SERVICE("BDAPARANS"),
    BITDUBAI_DAP_ASSET_TRANSMISSION_NETWORK_SERVICE("BDAPATNS"),
    // End  DAP Plugins

    // Init WPD Plugins
    BITDUBAI_WPD_WALLET_MANAGER_DESKTOP_MODULE("BWPDWMDM"),
    BITDUBAI_WPD_WALLET_FACTORY_MIDDLEWARE("BWPDWFM"),
    BITDUBAI_WPD_WALLET_MANAGER_MIDDLEWARE("BWPDWMM"),
    BITDUBAI_WPD_WALLET_PUBLISHER_MIDDLEWARE("BWPDWPM"),
    BITDUBAI_WPD_WALLET_SETTINGS_MIDDLEWARE("BWPDWSEM"),
    BITDUBAI_WPD_WALLET_STORE_MIDDLEWARE("BWPDWSTM"),
    BITDUBAI_WPD_PUBLISHER_IDENTITY("BWPDPI"),
    BITDUBAI_WPD_WALLET_COMMUNITY_NETWORK_SERVICE("BWPDWCNS"),
    BITDUBAI_WPD_WALLET_RESOURCES_NETWORK_SERVICE("BWPDWRNS"),
    BITDUBAI_WPD_WALLET_STATISTICS_NETWORK_SERVICE("BWPDWSNS"),
    BITDUBAI_WPD_WALLET_STORE_NETWORK_SERVICE("BWPDSNS"),
    BITDUBAI_WPD_WALLET_FACTORY_SUB_APP_MODULE("BWPDWFSAM"),
    BITDUBAI_WPD_WALLET_PUBLISHER_SUB_APP_MODULE("BWPDWPSAM"),
    BITDUBAI_WPD_WALLET_STORE_SUB_APP_MODULE("BWPDWSSAM"),
    // End  WPD Plugins

    //Init CBP Plugins
    BITDUBAI_CBP_CRYPTO_BROKER_IDENTITY("BCBPCBI"),
    BITDUBAI_CBP_CRYPTO_CUSTOMER_IDENTITY("BCBPCCI"),
    BITDUBAI_CBP_CRYPTO_BROKER_IDENTITY_SUB_APP_MODULE("BCBPCBISAM"),
    BITDUBAI_CBP_CRYPTO_CUSTOMER_IDENTITY_SUB_APP_MODULE("BCBPCCISAM"),
    //End CBP Plugins

    // Init new Plugins

    BITCOIN_WALLET("BW"),
    CRYPTO_ADDRESSES("CA"),
    CRYPTO_PAYMENT_REQUEST("CPR"),
    CRYPTO_TRANSMISSION("CT"),
    CRYPTO_WALLET("CW"),
    EXTRA_WALLET_USER("EWU"),
    INCOMING_EXTRA_USER("IEU"),
    INCOMING_INTRA_USER("IIU"),
    INTRA_WALLET_USER("IWU"),
    OUTGOING_EXTRA_USER("OEU"),
    OUTGOING_INTRA_ACTOR("OIA"),
    WALLET_CONTACTS("WC");

    // End  new Plugins

    private final String code;

    Plugins(final String code) {
        this.code = code;
    }

    public String getKey() {
        return this.code;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    public static Plugins getByKey(String code) throws InvalidParameterException {
        for (Plugins plugin : values()) {
            if (plugin.getCode().equals(code)) return plugin;
        }
        throw new InvalidParameterException(InvalidParameterException.DEFAULT_MESSAGE, null, "Code Received: " + code, "This Code Is Not Valid for the Plugins enum");
    }
}
