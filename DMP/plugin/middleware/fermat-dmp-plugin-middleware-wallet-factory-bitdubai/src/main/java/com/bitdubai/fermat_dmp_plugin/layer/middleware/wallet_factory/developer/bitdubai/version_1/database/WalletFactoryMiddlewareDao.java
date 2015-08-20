package com.bitdubai.fermat_dmp_plugin.layer.middleware.wallet_factory.developer.bitdubai.version_1.database;

import com.bitdubai.fermat_api.layer.all_definition.enums.WalletType;
import com.bitdubai.fermat_api.layer.all_definition.navigation_structure.WalletNavigationStructure;
import com.bitdubai.fermat_api.layer.all_definition.resources_structure.Language;
import com.bitdubai.fermat_api.layer.all_definition.resources_structure.Skin;
import com.bitdubai.fermat_api.layer.dmp_middleware.wallet_factory.enums.WalletFactoryProjectState;
import com.bitdubai.fermat_api.layer.dmp_middleware.wallet_factory.exceptions.CantGetWalletFactoryProjectLanguageException;
import com.bitdubai.fermat_api.layer.dmp_middleware.wallet_factory.exceptions.CantGetWalletFactoryProjectSkinException;
import com.bitdubai.fermat_api.layer.dmp_middleware.wallet_factory.interfaces.WalletFactoryProject;
import com.bitdubai.fermat_api.layer.osa_android.database_system.Database;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseFilterType;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTable;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTableFilter;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTableRecord;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTransaction;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DealsWithPluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.database_system.PluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantInsertRecordException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantLoadTableToMemoryException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantOpenDatabaseException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.DatabaseNotFoundException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.DatabaseTransactionFailedException;
import com.bitdubai.fermat_dmp_plugin.layer.middleware.wallet_factory.developer.bitdubai.version_1.exceptions.DatabaseOperationException;
import com.bitdubai.fermat_dmp_plugin.layer.middleware.wallet_factory.developer.bitdubai.version_1.exceptions.MissingProjectDataException;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by rodrigo on 8/17/15.
 */
public class WalletFactoryMiddlewareDao implements DealsWithPluginDatabaseSystem {

    UUID pluginId;
    Database database;
    /**
     * DealsWithPluginDatabaseSystem interface variable and implementation
     */
    PluginDatabaseSystem pluginDatabaseSystem;

    @Override
    public void setPluginDatabaseSystem(PluginDatabaseSystem pluginDatabaseSystem) {
        this.pluginDatabaseSystem = pluginDatabaseSystem;
    }

    /**
     * Constructor
     */
    public WalletFactoryMiddlewareDao(PluginDatabaseSystem pluginDatabaseSystem, UUID pluginId) {
        this.pluginDatabaseSystem = pluginDatabaseSystem;
        this.pluginId = pluginId;
    }

    private DatabaseTable getDatabaseTable(String tableName) {
        DatabaseTable databaseTable = database.getTable(tableName);
        return databaseTable;
    }


    private DatabaseTableRecord getWalletFactoryProjectRecord(WalletFactoryProject walletFactoryProject) throws DatabaseOperationException {
        DatabaseTable databaseTable = getDatabaseTable(WalletFactoryMiddlewareDatabaseConstants.PROJECT_TABLE_NAME);
        DatabaseTableRecord record = databaseTable.getEmptyRecord();
        record.setStringValue(WalletFactoryMiddlewareDatabaseConstants.PROJECT_PUBLICKEY_COLUMN_NAME, walletFactoryProject.getProjectPublicKey());
        record.setStringValue(WalletFactoryMiddlewareDatabaseConstants.PROJECT_NAME_COLUMN_NAME, walletFactoryProject.getName());
        record.setStringValue(WalletFactoryMiddlewareDatabaseConstants.PROJECT_STATE_COLUMN_NAME, walletFactoryProject.getProjectState().value());
        record.setStringValue(WalletFactoryMiddlewareDatabaseConstants.PROJECT_WALLETTYPE_COLUMN_NAME, walletFactoryProject.getWalletType().getCode());
        record.setStringValue(WalletFactoryMiddlewareDatabaseConstants.PROJECT_CREATION_TIMESTAMP_COLUMN_NAME, walletFactoryProject.getCreationTimestamp().toString());
        record.setStringValue(WalletFactoryMiddlewareDatabaseConstants.PROJECT_MODIFICATION_TIMESTAMP_COLUMN_NAME, walletFactoryProject.getLastModificationTimestamp().toString());

        return record;
    }

    private DatabaseTableRecord getSkinDataRecord(String projectPublicKey, UUID id, boolean isDefault) throws DatabaseOperationException, MissingProjectDataException {
        DatabaseTable databaseTable = getDatabaseTable(WalletFactoryMiddlewareDatabaseConstants.SKIN_TABLE_NAME);
        DatabaseTableRecord record = databaseTable.getEmptyRecord();
        record.setStringValue(WalletFactoryMiddlewareDatabaseConstants.SKIN_PROJECT_PUBLICKEY_COLUMN_NAME, projectPublicKey);
        record.setUUIDValue(WalletFactoryMiddlewareDatabaseConstants.SKIN_SKIN_ID_COLUMN_NAME, id);
        record.setStringValue(WalletFactoryMiddlewareDatabaseConstants.SKIN_DEFAULT_COLUMN_NAME, String.valueOf(isDefault));
        return record;
    }

    private DatabaseTableRecord getLanguageDataRecord(String projectPublicKey, UUID id, boolean isDefault) throws DatabaseOperationException, MissingProjectDataException{
        DatabaseTable databaseTable = getDatabaseTable(WalletFactoryMiddlewareDatabaseConstants.LANGUAGE_TABLE_NAME);
        DatabaseTableRecord record = databaseTable.getEmptyRecord();
        record.setStringValue(WalletFactoryMiddlewareDatabaseConstants.LANGUAGE_PROJECT_PUBLICKEY_COLUMN_NAME, projectPublicKey);
        record.setUUIDValue(WalletFactoryMiddlewareDatabaseConstants.LANGUAGE_LANGUAGE_ID_COLUMN_NAME, id);
        record.setStringValue(WalletFactoryMiddlewareDatabaseConstants.LANGUAGE_DEFAULT_COLUMN_NAME, String.valueOf(isDefault));
        return record;
    }

    private DatabaseTableRecord getNavigationStructureData(String projectPublicKey, String publicKey) throws DatabaseOperationException, MissingProjectDataException{
        DatabaseTable databaseTable = getDatabaseTable(WalletFactoryMiddlewareDatabaseConstants.NAVIGATION_STRUCTURE_TABLE_NAME);
        DatabaseTableRecord record = databaseTable.getEmptyRecord();
        record.setStringValue(WalletFactoryMiddlewareDatabaseConstants.NAVIGATION_STRUCTURE_PROJECT_PUBLICKEY_COLUMN_NAME, projectPublicKey);
        record.setStringValue(WalletFactoryMiddlewareDatabaseConstants.NAVIGATION_STRUCTURE_PUBLICKEY_COLUMN_NAME, publicKey);
        return record;
    }

    private DatabaseTransaction addSkinRecordsToTransaction(DatabaseTransaction transaction, WalletFactoryProject walletFactoryProject) throws DatabaseOperationException, MissingProjectDataException, CantLoadTableToMemoryException {
        Skin defaultSkin = null;

        defaultSkin = walletFactoryProject.getDefaultSkin();
        if (defaultSkin != null){
            // if a skin was defined in the project, then I will prepare the database record and add it to the transaction
            DatabaseTable table = getDatabaseTable(WalletFactoryMiddlewareDatabaseConstants.SKIN_TABLE_NAME);

            DatabaseTableRecord defaultSkinRecord = getSkinDataRecord(walletFactoryProject.getProjectPublicKey(), defaultSkin.getId(), true);
            DatabaseTableFilter filter = getSkinFilter(defaultSkin.getId().toString());

            if (isNewRecord(table, filter))
                transaction.addRecordToInsert(table, defaultSkinRecord);
            else {
                table.setStringFilter(filter.getColumn(), filter.getValue(), filter.getType());
                transaction.addRecordToUpdate(table, defaultSkinRecord);
            }


            // I will add all the skins defined, if there are more than one.
            if (!walletFactoryProject.getSkins().isEmpty()){
                for (Skin skin : walletFactoryProject.getSkins()){
                    DatabaseTableRecord skinRecord = getSkinDataRecord(walletFactoryProject.getProjectPublicKey(), skin.getId(), false);
                    filter.setValue(skin.getId().toString());
                    if (isNewRecord(table, filter))
                        transaction.addRecordToInsert(table, skinRecord);
                    else {
                        table.setStringFilter(filter.getColumn(), filter.getValue(), filter.getType());
                        transaction.addRecordToUpdate(table, skinRecord);
                    }
                }
            }

        }

        return transaction;
    }

    private DatabaseTableFilter getSkinFilter(String value) {
        DatabaseTableFilter filter = getDatabaseTable(WalletFactoryMiddlewareDatabaseConstants.SKIN_TABLE_NAME).getEmptyTableFilter();
        filter.setColumn(WalletFactoryMiddlewareDatabaseConstants.SKIN_SKIN_ID_COLUMN_NAME);
        filter.setType(DatabaseFilterType.EQUAL);
        filter.setValue(value);

        return filter;
    }

    private DatabaseTransaction addLanguageRecordsToTransaction(DatabaseTransaction transaction, WalletFactoryProject walletFactoryProject) throws DatabaseOperationException, MissingProjectDataException, CantLoadTableToMemoryException {
        Language defaultLanguage = null;

        defaultLanguage = walletFactoryProject.getDefaultLanguage();

        if (defaultLanguage != null){
            DatabaseTable table = getDatabaseTable(WalletFactoryMiddlewareDatabaseConstants.LANGUAGE_TABLE_NAME);
            DatabaseTableRecord defaultLanguageRecord = getLanguageDataRecord(walletFactoryProject.getProjectPublicKey(), defaultLanguage.getId(), true);

            DatabaseTableFilter filter = getLanguageFilter(defaultLanguage.getId().toString());
            if (isNewRecord(table, filter))
                transaction.addRecordToInsert(table, defaultLanguageRecord);
            else {
                table.setStringFilter(filter.getColumn(), filter.getValue(), filter.getType());
                transaction.addRecordToUpdate(table, defaultLanguageRecord);
            }

            //I will add any other language defined
            if (!walletFactoryProject.getLanguages().isEmpty()){
                for (Language language : walletFactoryProject.getLanguages()){
                    DatabaseTableRecord record = getLanguageDataRecord(walletFactoryProject.getProjectPublicKey(), language.getId(), false);
                    filter.setValue(language.getId().toString());
                    if (isNewRecord(table, filter))
                        transaction.addRecordToInsert(table, record);
                    else{
                        table.setStringFilter(filter.getColumn(), filter.getValue(), filter.getType());
                        transaction.addRecordToUpdate(table, record);
                    }

                }
            }

        }
        return transaction;
    }

    private DatabaseTableFilter getLanguageFilter(String value) {
        DatabaseTableFilter filter = getDatabaseTable(WalletFactoryMiddlewareDatabaseConstants.LANGUAGE_TABLE_NAME).getEmptyTableFilter();
        filter.setColumn(WalletFactoryMiddlewareDatabaseConstants.LANGUAGE_LANGUAGE_ID_COLUMN_NAME);
        filter.setType(DatabaseFilterType.EQUAL);
        filter.setValue(value);

        return filter;
    }

    private DatabaseTransaction addNavigationStructureRecordToTransaction(DatabaseTransaction transaction, WalletFactoryProject walletFactoryProject) throws DatabaseOperationException, MissingProjectDataException, CantLoadTableToMemoryException {
        WalletNavigationStructure navigationStructure = null;

        navigationStructure = walletFactoryProject.getNavigationStructure();
        if (navigationStructure != null){
            DatabaseTableRecord record = getNavigationStructureData(walletFactoryProject.getProjectPublicKey(), navigationStructure.getPublicKey());
            DatabaseTable table = getDatabaseTable(WalletFactoryMiddlewareDatabaseConstants.NAVIGATION_STRUCTURE_TABLE_NAME);
            DatabaseTableFilter filter = getNavigationStructureFilter(navigationStructure.getPublicKey());
            if (isNewRecord(table, filter)){
                //If it is a new record, then I ll insert it
                transaction.addRecordToInsert(table, record);
            } else {
                //if it exists, then I will update it.
                table.setStringFilter(filter.getColumn(), filter.getValue(), filter.getType());
                transaction.addRecordToUpdate(table, record);
            }
        }
        return transaction;
    }

    private DatabaseTableFilter getNavigationStructureFilter(String publicKey) {
        DatabaseTableFilter filter = getDatabaseTable(WalletFactoryMiddlewareDatabaseConstants.NAVIGATION_STRUCTURE_TABLE_NAME).getEmptyTableFilter();
        filter.setColumn(WalletFactoryMiddlewareDatabaseConstants.NAVIGATION_STRUCTURE_PUBLICKEY_COLUMN_NAME);
        filter.setType(DatabaseFilterType.EQUAL);
        filter.setValue(publicKey);

        return filter;
    }

    private boolean isNewRecord(DatabaseTable table, DatabaseTableFilter filter) throws CantLoadTableToMemoryException {
        table.setStringFilter(filter.getColumn(), filter.getValue(), filter.getType());
        table.loadToMemory();
        if (table.getRecords().isEmpty())
            return true;
        else
            return false;
    }

    /**
     * Saves the Wallet Factory Project in the database.
     * If it doesn't exists, it insert, if they already exists, everything is updated.
     * If it has skins and languages, it persists all of them.
     * @param walletFactoryProject
     * @throws DatabaseOperationException
     * @throws MissingProjectDataException
     */
    public void saveWalletFactoryProjectData(WalletFactoryProject walletFactoryProject) throws DatabaseOperationException, MissingProjectDataException{
        DatabaseTransaction transaction = database.newTransaction();

        try {
            // add the Wallet factory project database record to a transaction
            DatabaseTable table = getDatabaseTable(WalletFactoryMiddlewareDatabaseConstants.PROJECT_TABLE_NAME);
            DatabaseTableRecord walletFactoryRecord = getWalletFactoryProjectRecord(walletFactoryProject);
            DatabaseTableFilter filter = table.getEmptyTableFilter();
            filter.setType(DatabaseFilterType.EQUAL);
            filter.setValue(walletFactoryProject.getProjectPublicKey());
            filter.setColumn(WalletFactoryMiddlewareDatabaseConstants.PROJECT_PUBLICKEY_COLUMN_NAME);
            if (isNewRecord(table, filter))
                transaction.addRecordToInsert(table, walletFactoryRecord);
            else {
                table.setStringFilter(filter.getColumn(), filter.getValue(), filter.getType());
                transaction.addRecordToUpdate(table, walletFactoryRecord);
            }


            // I wil add the skins to the transaction if there are any
            transaction = addSkinRecordsToTransaction(transaction, walletFactoryProject);

            // I wil add the Languages to the transaction if there are any
            transaction = addLanguageRecordsToTransaction(transaction, walletFactoryProject);

            //I will add the navigation structure inside the transaction if there is any
            transaction = addNavigationStructureRecordToTransaction(transaction, walletFactoryProject);

            //I execute the transaction and persist the database side of the project.
            database.executeTransaction(transaction);
        } catch (Exception e) {
            throw new DatabaseOperationException(DatabaseOperationException.DEFAULT_MESSAGE, e, "Error trying to save the Factory project in the database.", null);
        }
    }




}
