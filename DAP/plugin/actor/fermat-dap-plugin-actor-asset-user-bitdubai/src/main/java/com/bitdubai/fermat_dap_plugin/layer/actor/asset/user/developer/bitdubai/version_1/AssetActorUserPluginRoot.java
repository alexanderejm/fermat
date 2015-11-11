package com.bitdubai.fermat_dap_plugin.layer.actor.asset.user.developer.bitdubai.version_1;

import com.bitdubai.fermat_api.CantStartPluginException;
import com.bitdubai.fermat_api.FermatException;
import com.bitdubai.fermat_api.layer.all_definition.common.system.abstract_classes.AbstractPlugin;
import com.bitdubai.fermat_api.layer.all_definition.common.system.annotations.NeededAddonReference;
import com.bitdubai.fermat_api.layer.all_definition.common.system.annotations.NeededPluginReference;
import com.bitdubai.fermat_api.layer.all_definition.common.system.utils.PluginVersionReference;
import com.bitdubai.fermat_api.layer.all_definition.developer.DatabaseManagerForDevelopers;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperDatabase;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperDatabaseTable;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperDatabaseTableRecord;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperObjectFactory;
import com.bitdubai.fermat_api.layer.all_definition.enums.Actors;
import com.bitdubai.fermat_api.layer.all_definition.enums.Addons;
import com.bitdubai.fermat_api.layer.all_definition.enums.BlockchainNetworkType;
import com.bitdubai.fermat_api.layer.all_definition.enums.ConnectionState;
import com.bitdubai.fermat_api.layer.all_definition.enums.CryptoCurrency;
import com.bitdubai.fermat_api.layer.all_definition.enums.CryptoCurrencyVault;
import com.bitdubai.fermat_api.layer.all_definition.enums.Genders;
import com.bitdubai.fermat_api.layer.all_definition.enums.Layers;
import com.bitdubai.fermat_api.layer.all_definition.enums.Platforms;
import com.bitdubai.fermat_api.layer.all_definition.enums.Plugins;
import com.bitdubai.fermat_api.layer.all_definition.enums.ReferenceWallet;
import com.bitdubai.fermat_api.layer.all_definition.enums.ServiceStatus;
import com.bitdubai.fermat_api.layer.all_definition.enums.VaultType;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEventHandler;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEventListener;
import com.bitdubai.fermat_api.layer.all_definition.money.CryptoAddress;
import com.bitdubai.fermat_api.layer.all_definition.util.Version;
import com.bitdubai.fermat_api.layer.dmp_world.wallet.exceptions.CantStartAgentException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.PluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.file_system.PluginFileSystem;
import com.bitdubai.fermat_bch_api.layer.crypto_vault.asset_vault.interfaces.AssetVaultManager;
import com.bitdubai.fermat_ccp_api.layer.network_service.crypto_addresses.enums.CryptoAddressDealers;
import com.bitdubai.fermat_ccp_api.layer.network_service.crypto_addresses.enums.RequestAction;
import com.bitdubai.fermat_ccp_api.layer.network_service.crypto_addresses.exceptions.CantConfirmAddressExchangeRequestException;
import com.bitdubai.fermat_ccp_api.layer.network_service.crypto_addresses.exceptions.CantListPendingCryptoAddressRequestsException;
import com.bitdubai.fermat_ccp_api.layer.network_service.crypto_addresses.exceptions.CantSendAddressExchangeRequestException;
import com.bitdubai.fermat_ccp_api.layer.network_service.crypto_addresses.exceptions.PendingRequestNotFoundException;
import com.bitdubai.fermat_ccp_api.layer.network_service.crypto_addresses.interfaces.CryptoAddressRequest;
import com.bitdubai.fermat_ccp_api.layer.network_service.crypto_addresses.interfaces.CryptoAddressesManager;
import com.bitdubai.fermat_cry_api.layer.crypto_module.crypto_address_book.exceptions.CantRegisterCryptoAddressBookRecordException;
import com.bitdubai.fermat_cry_api.layer.crypto_module.crypto_address_book.interfaces.CryptoAddressBookManager;
import com.bitdubai.fermat_dap_api.layer.all_definition.enums.EventType;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_issuer.interfaces.ActorAssetIssuer;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.AssetUserActorRecord;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.exceptions.CantAssetUserActorNotFoundException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.exceptions.CantConnectToAssetUserException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.exceptions.CantCreateAssetUserActorException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.exceptions.CantGetAssetUserActorsException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.interfaces.ActorAssetUser;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.interfaces.ActorAssetUserManager;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.interfaces.ActorNetworkServiceAssetUser;
import com.bitdubai.fermat_dap_api.layer.dap_actor_network_service.asset_user.exceptions.CantRegisterActorAssetUserException;
import com.bitdubai.fermat_dap_api.layer.dap_actor_network_service.asset_user.interfaces.AssetUserActorNetworkServiceManager;
import com.bitdubai.fermat_dap_api.layer.dap_actor_network_service.asset_user.interfaces.DealsWithAssetUserActorNetworkServiceManager;
import com.bitdubai.fermat_dap_plugin.layer.actor.asset.user.developer.bitdubai.version_1.Agent.AssetUserActorMonitorAgent;
import com.bitdubai.fermat_dap_plugin.layer.actor.asset.user.developer.bitdubai.version_1.developerUtils.AssetUserActorDeveloperDatabaseFactory;
import com.bitdubai.fermat_dap_plugin.layer.actor.asset.user.developer.bitdubai.version_1.event_handlers.AssetUserActorCompleteRegistrationNotificationEventHandler;
import com.bitdubai.fermat_dap_plugin.layer.actor.asset.user.developer.bitdubai.version_1.event_handlers.CryptoAddressRequestedEventHandler;
import com.bitdubai.fermat_dap_plugin.layer.actor.asset.user.developer.bitdubai.version_1.exceptions.CantAddPendingAssetUserException;
import com.bitdubai.fermat_dap_plugin.layer.actor.asset.user.developer.bitdubai.version_1.exceptions.CantGetAssetUsersListException;
import com.bitdubai.fermat_dap_plugin.layer.actor.asset.user.developer.bitdubai.version_1.exceptions.CantHandleCryptoAddressReceivedActionException;
import com.bitdubai.fermat_dap_plugin.layer.actor.asset.user.developer.bitdubai.version_1.exceptions.CantHandleCryptoAddressesNewsEventException;
import com.bitdubai.fermat_dap_plugin.layer.actor.asset.user.developer.bitdubai.version_1.exceptions.CantInitializeAssetUserActorDatabaseException;
import com.bitdubai.fermat_dap_plugin.layer.actor.asset.user.developer.bitdubai.version_1.exceptions.CantUpdateAssetUserConnectionException;
import com.bitdubai.fermat_dap_plugin.layer.actor.asset.user.developer.bitdubai.version_1.structure.AssetUserActorDao;
import com.bitdubai.fermat_pip_api.layer.pip_user.device_user.exceptions.CantGetLoggedInDeviceUserException;
import com.bitdubai.fermat_pip_api.layer.pip_user.device_user.interfaces.DeviceUserManager;
import com.bitdubai.fermat_pip_api.layer.platform_service.error_manager.ErrorManager;
import com.bitdubai.fermat_pip_api.layer.platform_service.error_manager.UnexpectedPluginExceptionSeverity;
import com.bitdubai.fermat_pip_api.layer.platform_service.event_manager.interfaces.EventManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Nerio on 09/09/15.
 */

public class AssetActorUserPluginRoot extends AbstractPlugin implements
        ActorAssetUserManager,
        ActorNetworkServiceAssetUser,
        DatabaseManagerForDevelopers,
        DealsWithAssetUserActorNetworkServiceManager,
        Serializable {

    @NeededAddonReference(platform = Platforms.PLUG_INS_PLATFORM, layer = Layers.USER, addon = Addons.DEVICE_USER)
    private DeviceUserManager deviceUserManager;

    @NeededAddonReference(platform = Platforms.OPERATIVE_SYSTEM_API, layer = Layers.SYSTEM, addon = Addons.PLUGIN_DATABASE_SYSTEM)
    private PluginDatabaseSystem pluginDatabaseSystem;

    @NeededAddonReference(platform = Platforms.OPERATIVE_SYSTEM_API, layer = Layers.SYSTEM, addon = Addons.PLUGIN_FILE_SYSTEM)
    private PluginFileSystem pluginFileSystem;

    @NeededAddonReference(platform = Platforms.PLUG_INS_PLATFORM   , layer = Layers.PLATFORM_SERVICE, addon = Addons.ERROR_MANAGER         )
    private ErrorManager errorManager;

    @NeededAddonReference(platform = Platforms.PLUG_INS_PLATFORM   , layer = Layers.PLATFORM_SERVICE, addon = Addons.EVENT_MANAGER         )
    private EventManager eventManager;

    @NeededPluginReference(platform = Platforms.CRYPTO_CURRENCY_PLATFORM, layer = Layers.NETWORK_SERVICE, plugin = Plugins.CRYPTO_ADDRESSES)
    private CryptoAddressesManager cryptoAddressesNetworkServiceManager;

    @NeededPluginReference(platform = Platforms.BLOCKCHAINS, layer = Layers.CRYPTO_VAULT, plugin = Plugins.BITCOIN_ASSET_VAULT)
    private AssetVaultManager assetVaultManager;

    @NeededPluginReference(platform = Platforms.BLOCKCHAINS, layer = Layers.CRYPTO_MODULE, plugin = Plugins.CRYPTO_ADDRESS_BOOK)
    private CryptoAddressBookManager cryptoAddressBookManager;


    BlockchainNetworkType blockchainNetworkType;

    AssetUserActorNetworkServiceManager assetUserActorNetworkServiceManager;

    private AssetUserActorDao assetUserActorDao;
    private AssetUserActorMonitorAgent assetUserActorMonitorAgent;

    private final List<FermatEventListener> listenersAdded;

    public AssetActorUserPluginRoot() {
        super(new PluginVersionReference(new Version()));

        listenersAdded = new ArrayList<>();
    }

    /**
     * DealsWithIntraWalletUsersNetworkService Interface implementation.
     */
    @Override
    public void setAssetUserActorNetworkServiceManager(AssetUserActorNetworkServiceManager assetUserActorNetworkServiceManager) {
        this.assetUserActorNetworkServiceManager = assetUserActorNetworkServiceManager;
    }

    @Override
    public void start() throws CantStartPluginException {
        try {
            /**
             * I created instance of AssetUserActorDao
             * and initialize Database
             */
            this.assetUserActorDao = new AssetUserActorDao(this.pluginDatabaseSystem, this.pluginFileSystem, this.pluginId);

            blockchainNetworkType = BlockchainNetworkType.REG_TEST;

            createAndRegisterActorAssetUserTest();

            /**
             * Agent for Search Actor Asset User REGISTERED in Actor Network Service User
             */
            initializeListener();

            startMonitorAgent();

            this.serviceStatus = ServiceStatus.STARTED;
//            testRaiseEvent();

        } catch (Exception e) {
            errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_ACTOR, UnexpectedPluginExceptionSeverity.DISABLES_THIS_PLUGIN, e);
            throw new CantStartPluginException(e, Plugins.BITDUBAI_DAP_ASSET_USER_ACTOR);
        }
    }

    /**
     * This method register the genesis address in crypto address book.
     *
     * @param genesisAddress
     * @throws CantRegisterCryptoAddressBookRecordException
     */
    public void registerGenesisAddressInCryptoAddressBook(CryptoAddress genesisAddress) throws CantRegisterCryptoAddressBookRecordException {
        try {
            this.cryptoAddressBookManager.registerCryptoAddress(genesisAddress,
                    this.assetUserActorDao.getActorAssetUser().getPublicKey(),
                    Actors.DAP_ASSET_USER,
                    this.assetUserActorDao.getActorAssetUser().getPublicKey(),
                    Actors.DAP_ASSET_USER,
                    Platforms.DIGITAL_ASSET_PLATFORM,
                    VaultType.ASSET_VAULT,
                    CryptoCurrencyVault.BITCOIN_VAULT.getCode(),
                    UUID.randomUUID().toString(),//this.walletPublicKey,
                    ReferenceWallet.BASIC_WALLET_BITCOIN_WALLET);
        } catch (CantGetAssetUsersListException e) {
            throw new CantRegisterCryptoAddressBookRecordException(e.getMessage(), e, "Asset User Actor", "Can't Register CryptoAddress Book: Review Actor PublicKey");
        }
    }

    @Override
    public void stop() {
        this.assetUserActorMonitorAgent.stop();
        this.serviceStatus = ServiceStatus.STOPPED;
    }

    @Override
    public List<DeveloperDatabase> getDatabaseList(DeveloperObjectFactory developerObjectFactory) {
        AssetUserActorDeveloperDatabaseFactory dbFactory = new AssetUserActorDeveloperDatabaseFactory(this.pluginDatabaseSystem, this.pluginId);
        return dbFactory.getDatabaseList(developerObjectFactory);
    }

    @Override
    public List<DeveloperDatabaseTable> getDatabaseTableList(DeveloperObjectFactory developerObjectFactory, DeveloperDatabase developerDatabase) {
        AssetUserActorDeveloperDatabaseFactory dbFactory = new AssetUserActorDeveloperDatabaseFactory(this.pluginDatabaseSystem, this.pluginId);
        return dbFactory.getDatabaseTableList(developerObjectFactory);
    }

    @Override
    public List<DeveloperDatabaseTableRecord> getDatabaseTableContent(DeveloperObjectFactory developerObjectFactory, DeveloperDatabase developerDatabase, DeveloperDatabaseTable developerDatabaseTable) {
        try {
            AssetUserActorDeveloperDatabaseFactory dbFactory = new AssetUserActorDeveloperDatabaseFactory(this.pluginDatabaseSystem, this.pluginId);
            dbFactory.initializeDatabase();
            return dbFactory.getDatabaseTableContent(developerObjectFactory, developerDatabaseTable);
        } catch (CantInitializeAssetUserActorDatabaseException e) {
            /**
             * The database exists but cannot be open. I can not handle this situation.
             */
            this.errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_ACTOR, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, e);
        } catch (Exception e) {
            this.errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_DAP_ASSET_USER_ACTOR, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN, e);
        }
        // If we are here the database could not be opened, so we return an empry list
        return new ArrayList<>();
    }

    @Override
    public ActorAssetUser getActorByPublicKey(String actorPublicKey) throws CantGetAssetUserActorsException,
                                                                            CantAssetUserActorNotFoundException {

        try {
            return this.assetUserActorDao.getActorByPublicKey(actorPublicKey);
        } catch (CantGetAssetUserActorsException e) {
            throw new CantGetAssetUserActorsException("", FermatException.wrapException(e), "Cant Get Actor Asset User from Data Base", null);
        }

    }

    @Override
    public void createActorAssetUserFactory(String assetUserActorPublicKey, String assetUserActorName, byte[] assetUserActorprofileImage) throws CantCreateAssetUserActorException {

    }

    @Override
    public ActorAssetUser getActorAssetUser() throws CantGetAssetUserActorsException,
                                                        CantAssetUserActorNotFoundException {

        ActorAssetUser actorAssetUser;
        try {
            actorAssetUser = this.assetUserActorDao.getActorAssetUser();
        } catch (Exception e) {
            throw new CantGetAssetUserActorsException("", FermatException.wrapException(e), "There is a problem I can't identify.", null);
        }

        return actorAssetUser;
    }

    @Override
    public List<ActorAssetUser> getAllAssetUserActorInTableRegistered() throws CantGetAssetUserActorsException {
        List<ActorAssetUser> list; // Asset User Actor list.
        try {
            list = this.assetUserActorDao.getAllAssetUserActorRegistered();
        } catch (CantGetAssetUsersListException e) {
            throw new CantGetAssetUserActorsException("CAN'T GET ASSET USER REGISTERED ACTOR", e, "", "");
        }
        return list;
    }

    /**
     * Method getAllAssetUserActorConnected usado para obtener la lista de ActorAssetUser
     * que tienen CryptoAddress en table REGISTERED
     * y ser usados en Wallet Issuer para poder enviarles BTC del Asset
     *
     * @return List<ActorAssetUser> with CryptoAddress
     * @see #getAllAssetUserActorConnected();
     */
    @Override
    public List<ActorAssetUser> getAllAssetUserActorConnected() throws CantGetAssetUserActorsException {
        List<ActorAssetUser> list; // Asset User Actor list.
        try {
            list = this.assetUserActorDao.getAllAssetUserActorConnected();
        } catch (CantGetAssetUsersListException e) {
            throw new CantGetAssetUserActorsException("CAN'T GET ASSET USER ACTORS CONNECTED WITH CRYPTOADDRESS ", e, "", "");
        }
        return list;
    }

    @Override
    public void registerActorInActorNetowrkSerice() {

        try {
            /*
             * Send the Actor Asset User Local for Register in Actor Network Service
             */
            ActorAssetUser actorAssetUser = this.assetUserActorDao.getActorAssetUser();
//            if (actorAssetUser.getConnectionState() != ConnectionState.CONNECTED) {
            assetUserActorNetworkServiceManager.registerActorAssetUser(this.assetUserActorDao.getActorAssetUser());
//            } else {
//                System.out.println("Actor Asset User YA REGISTRADO - NO Puede volver a registrarse");
//            }
        } catch (CantRegisterActorAssetUserException | CantGetAssetUsersListException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectToActorAssetUser(ActorAssetIssuer requester, List<ActorAssetUser> actorAssetUsers) throws CantConnectToAssetUserException {
        try {
            for (ActorAssetUser actorAssetUser : actorAssetUsers){
                try {
                    cryptoAddressesNetworkServiceManager.sendAddressExchangeRequest(null,
                            CryptoCurrency.BITCOIN,
                            Actors.DAP_ASSET_ISSUER,
                            Actors.DAP_ASSET_USER,
                            requester.getPublicKey(),
                            actorAssetUser.getPublicKey(),
                            CryptoAddressDealers.DAP_ASSET,//TODO Para testing "DAP_ASSET" - VALIDAR QUE USAR para DESPUES ???
                            BlockchainNetworkType.DEFAULT);

                    this.assetUserActorDao.updateAssetUserConnectionStateActorNetworService(actorAssetUser.getPublicKey(), ConnectionState.PENDING_REMOTELY_ACCEPTANCE, actorAssetUser.getCryptoAddress());
                } catch (CantUpdateAssetUserConnectionException e) {
                    e.printStackTrace();
                }
            }
        } catch (CantSendAddressExchangeRequestException e) {
            e.printStackTrace();
        }

        //ademas, debemos al iniciar el plugin del actor, debemos escuchar al evento del requestCryptoAddress del network service, para cuando
        //alguien nos pida la dirección, y llamar a los metodos de getGenesisAddress y registrarla en el address book. para luego enviar
        // la direccion a traves del sendCryptoAddress del network service.
    }

//    public CryptoAddress getGenesisAddress(UUID requestId) throws CantGetGenesisAddressException {
//        try {
//            CryptoAddressRequest request = cryptoAddressesNetworkServiceManager.getPendingRequest(requestId);
//
////            System.out.println("La BlockChain es: " + blockchainNetworkType);
//            CryptoAddress genesisAddress = this.assetVaultManager.getNewAssetVaultCryptoAddress(request.getBlockchainNetworkType());
//
////            System.out.println("========================================================================");
////            System.out.println("Genesis Address Actor Asset User: " + genesisAddress.getAddress() + " Currency: " + genesisAddress.getCryptoCurrency());
//            registerGenesisAddressInCryptoAddressBook(genesisAddress);
//            cryptoAddressesNetworkServiceManager.acceptAddressExchangeRequest(requestId, genesisAddress);
//        } catch (GetNewCryptoAddressException exception) {
//            throw new CantGetGenesisAddressException(exception, "Requesting a genesis address", "Cannot get a new crypto address from asset vault");
//        } catch (PendingRequestNotFoundException e) {
//            e.printStackTrace();
//        } catch (CantGetPendingAddressExchangeRequestException e) {
//            e.printStackTrace();
//        } catch (CantRegisterCryptoAddressBookRecordException e) {
//            e.printStackTrace();
//        } catch (CantAcceptAddressExchangeRequestException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public void handleDeliveredCryptoAddresFromRemoteAssetUserEvent(ActorAssetUser actorAssetUser, CryptoAddress cryptoAddress) {
        try {
            System.out.println("=============Actor Asset Inicia Recepcion Crypto=========");
            //todo actualizar tabla de usuarios registrados con nueva crypto address.
            this.assetUserActorDao.createNewAssetUserRegisterInNetworkService(actorAssetUser, ConnectionState.CONNECTED, cryptoAddress);
            System.out.println("=============Actor Asset User Recibida Crypto================");
            System.out.println("Actor Asset User: " + actorAssetUser.getName());
            System.out.println("Actor Asset Crypto Address: " + actorAssetUser.getCryptoAddress().getAddress());
            System.out.println("==========================================================");
        } catch (CantAddPendingAssetUserException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleCompleteRequestListRegisteredAssetUserActorNetworksNotificationEvent(List<ActorAssetUser> actorAssetUserList) {
        System.out.println("==========================================================");
        System.out.println("Actor Asset User - Lista recibida");
        System.out.println("==========================================================");

    }

    //TODO al confirmarse el registro del Actor cambia su estado local a CONNECTED
    @Override
    public void handleCompleteClientAssetUserActorRegistrationNotificationEvent(ActorAssetUser actorAssetUser) {
        System.out.println("***************************************************************");
        System.out.println("Actor Asset User se Registro " + actorAssetUser.getName());
        try {
            //TODO Cambiar luego por la publicKey Linked proveniente de Identity
            this.assetUserActorDao.updateAssetUserConnectionStateOrCrpytoAddress(actorAssetUser.getPublicKey(),
                    ConnectionState.CONNECTED,
                    actorAssetUser.getCryptoAddress());
        } catch (CantUpdateAssetUserConnectionException e) {
            e.printStackTrace();
        }
        System.out.println("***************************************************************");
    }

    @Override
    public void handleRequestCryptoAddresFromRemoteAssetUserEvent(ActorAssetIssuer actorAssetIssuer, ActorAssetUser actorAssetUser) {
//        try {
//            System.out.println("=============Actor Asset Inicia Peticion Crypto=========");
//            CryptoAddress genesisAddress = getGenesisAddress(UUID.randomUUID());
//            registerGenesisAddressInCryptoAddressBook(genesisAddress);
//            System.out.println("=====Actor Asset Registrando Crypto Local y Enviando======");
//            this.assetUserActorDao.createNewAssetUser(actorAssetUser);
//            this.assetUserActorNetworkServiceManager.sendCryptoAddress(actorAssetUser, actorAssetIssuer, genesisAddress);
//            System.out.println("=============Actor Asset User envio Crypto================");
//            System.out.println("Actor Asset User: " + actorAssetUser.getName());
//            System.out.println("Actor Asset Crypto Address " + actorAssetUser.getCryptoAddress().getAddress());
//            System.out.println("***************************************************************");
//        } catch (CantGetGenesisAddressException e) {
//            e.printStackTrace();
//        } catch (CantRegisterCryptoAddressBookRecordException e) {
//            e.printStackTrace();
//        } catch (CantSendCryptoAddressException e) {
//            e.printStackTrace();
//        } catch (CantAddPendingAssetUserException e) {
//            e.printStackTrace();
//        }
    }

    public void createAndRegisterActorAssetUserTest() throws CantCreateAssetUserActorException {

        try {
            ActorAssetUser actorAssetUser = this.assetUserActorDao.getActorAssetUser();
            if (actorAssetUser == null) {
                String assetUserActorPublicKey = UUID.randomUUID().toString();
                Genders genders = Genders.MALE;
                String age = "25";
                ConnectionState connectionState = ConnectionState.PENDING_REMOTELY_ACCEPTANCE;
                Double locationLatitude = new Random().nextDouble();
                Double locationLongitude = new Random().nextDouble();
                AssetUserActorRecord record = new AssetUserActorRecord(assetUserActorPublicKey,
                                                                       "Thunder User_" + new Random().nextInt(90),
                                                                       age,
                                                                       genders,
                                                                       connectionState,
                                                                       locationLatitude,
                                                                       locationLongitude,
                                                                       System.currentTimeMillis(),
                                                                       System.currentTimeMillis(),
                                                                       new byte[0]);

                this.assetUserActorDao.createNewAssetUser(record);
                actorAssetUser = this.assetUserActorDao.getActorAssetUser();
                System.out.println("*****************Actor Asset User************************");
                System.out.println("Actor Asset PublicKey: " + actorAssetUser.getPublicKey());
                System.out.println("Actor Asset Name: " + actorAssetUser.getName());
//                System.out.println("Actor Asset GenesisAddress in Crypto Address Book: " + actorAssetUser.getCryptoAddress().getAddress());
                System.out.println("**********************************************************");
                this.assetUserActorDao.createNewAssetUserRegisterInNetworkService(record, ConnectionState.CONNECTED,record.getCryptoAddress());
            }

        } catch (CantAddPendingAssetUserException e) {
            throw new CantCreateAssetUserActorException("CAN'T ADD NEW ASSET USER ACTOR", e, "", "");
        } catch (Exception e) {
            throw new CantCreateAssetUserActorException("CAN'T ADD NEW ASSET USER ACTOR", FermatException.wrapException(e), "", "");
        }
    }

    /**
     * Private methods
     */
    private void startMonitorAgent() throws CantGetLoggedInDeviceUserException, CantStartAgentException {
        if (this.assetUserActorMonitorAgent == null) {
//            String userPublicKey = this.deviceUserManager.getLoggedInDeviceUser().getPublicKey();
            this.assetUserActorMonitorAgent = new AssetUserActorMonitorAgent(this.eventManager,
                    this.pluginDatabaseSystem,
                    this.errorManager,
                    this.pluginId,
                    this.assetUserActorNetworkServiceManager,
                    this.assetUserActorDao,
                    this);
//            this.assetUserActorMonitorAgent.setLogManager(this.logManager);
            this.assetUserActorMonitorAgent.start();
        } else {
            this.assetUserActorMonitorAgent.start();
        }
    }

    private void initializeListener() {
        /**
         * I will initialize the handling of com.bitdubai.platform events.
         */
        FermatEventListener fermatEventListener;
        FermatEventHandler fermatEventHandler;

        /**
         * Listener Accepted connection event
         */
        fermatEventListener = eventManager.getNewListener(EventType.COMPLETE_ASSET_USER_REGISTRATION_NOTIFICATION);
        fermatEventListener.setEventHandler(new AssetUserActorCompleteRegistrationNotificationEventHandler(this));
        eventManager.addListener(fermatEventListener);
        listenersAdded.add(fermatEventListener);

        fermatEventListener = eventManager.getNewListener(com.bitdubai.fermat_ccp_api.all_definition.enums.EventType.CRYPTO_ADDRESSES_NEWS);
        fermatEventListener.setEventHandler(new CryptoAddressRequestedEventHandler(this));
        eventManager.addListener(fermatEventListener);
        listenersAdded.add(fermatEventListener);

//        fermatEventListener = eventManager.getNewListener(com.bitdubai.fermat_pip_api.layer.platform_service.event_manager.enums.EventType.NEW_CRYPTO_ADDRESS_REQUEST_ASSET_USER);
//        fermatEventListener.setEventHandler(new NewCryptoAddressRequestAssetUserActorNotificationEventHandler(this));
//        eventManager.addListener(fermatEventListener);
//        listenersAdded.add(fermatEventListener);
//
//        fermatEventListener = eventManager.getNewListener(com.bitdubai.fermat_pip_api.layer.platform_service.event_manager.enums.EventType.NEW_CRYPTO_ADDRESS_RECEIVE_ASSET_USER);
//        fermatEventListener.setEventHandler(new NewCryptoAddressReceiveAssetUserActorNotificationEventHandler(this));
//        eventManager.addListener(fermatEventListener);
//        listenersAdded.add(fermatEventListener);

        /*FermatEventListener cryptoAddressReceivedEventListener = eventManager.getNewListener(com.bitdubai.fermat_ccp_api.all_definition.enums.EventType.CRYPTO_ADDRESS_REQUESTED);
        cryptoAddressReceivedEventListener.setEventHandler(new CryptoAddressRequestedEventHandler(this));
        eventManager.addListener(cryptoAddressReceivedEventListener);
        listenersAdded.add(cryptoAddressReceivedEventListener);*/
    }


    public void handleCryptoAddressesNewsEvent() throws CantHandleCryptoAddressesNewsEventException {
        final List<CryptoAddressRequest> list;
        try {
            list = cryptoAddressesNetworkServiceManager.listAllPendingRequests();

            System.out.println("----------------------------\n" +
                    "Actor Asset User : handleCryptoAddressesNewsEvent " + list.size()
                    + "\n-------------------------------------------------");
            for (final CryptoAddressRequest request : list) {

                if (request.getCryptoAddressDealer().equals(CryptoAddressDealers.DAP_ASSET)) {

                    if (request.getAction().equals(RequestAction.ACCEPT))
                        this.handleCryptoAddressReceivedEvent(request);

//                if (request.getAction().equals(RequestAction.DENY))
//                    this.handleCryptoAddressDeniedEvent(request);
                }
            }
        } catch(CantListPendingCryptoAddressRequestsException |
//                CantHandleCryptoAddressDeniedActionException |
                CantHandleCryptoAddressReceivedActionException e) {

            throw new CantHandleCryptoAddressesNewsEventException(e, "", "Error handling Crypto Addresses News Event.");
        }
    }

    public void handleCryptoAddressReceivedEvent(final CryptoAddressRequest request) throws CantHandleCryptoAddressReceivedActionException {

        try {
            if(request.getCryptoAddress() != null) {
                System.out.println("*****Actor Asset User Recibiendo Crypto Localmente*****");

                this.assetUserActorDao.updateAssetUserConnectionStateActorNetworService(request.getIdentityPublicKeyResponding(), ConnectionState.CONNECTED, request.getCryptoAddress());

                List<ActorAssetUser> actorAssetUser = this.assetUserActorDao.getAllAssetUserActorRegistered();

                int i = 0;
                for (ActorAssetUser actorAssetUser1:actorAssetUser) {
                    i++;
                    System.out.println("Actor Asset User: N° " + i);
                    System.out.println("Actor Asset User: " + actorAssetUser1.getPublicKey());
                    System.out.println("Actor Asset User: " + actorAssetUser1.getName());
                    System.out.println("Actor Asset User: " + actorAssetUser1.getCryptoAddress().getAddress());
                    System.out.println("Actor Asset User: " + actorAssetUser1.getCryptoAddress().getCryptoCurrency());
                    System.out.println("Actor Asset User: " + actorAssetUser1.getConnectionState());

                }

                cryptoAddressesNetworkServiceManager.confirmAddressExchangeRequest(request.getRequestId());
            }
        } catch (CantUpdateAssetUserConnectionException e) {
            e.printStackTrace();
        } catch (CantGetAssetUsersListException e) {
            e.printStackTrace();
        } catch (PendingRequestNotFoundException e) {
            e.printStackTrace();
        } catch (CantConfirmAddressExchangeRequestException e) {
            e.printStackTrace();
        }
    }
}
