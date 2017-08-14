/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.backup;
 
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Geoffrey De Smet
 */
public abstract class AbstractDatabaseBackuper implements DatabaseBackuper, InitializingBean {

    /** Logger available to subclasses */
    protected final transient Log logger = LogFactory.getLog(getClass());

    protected Resource databaseBackupLocation;
    protected boolean restoreDatabaseOnInitialization = false;
    protected boolean cleanDatabaseBeforeRestore = false;

    public void setDatabaseBackupLocation(Resource databaseBackupLocation) {
        this.databaseBackupLocation = databaseBackupLocation;
    }

    public void setRestoreDatabaseOnInitialization(boolean restoreDatabaseOnInitialization) {
        this.restoreDatabaseOnInitialization = restoreDatabaseOnInitialization;
    }

    public void setCleanDatabaseBeforeRestore(boolean cleanDatabaseBeforeRestore) {
        this.cleanDatabaseBeforeRestore = cleanDatabaseBeforeRestore;
    }

    public void afterPropertiesSet() {
        checkConfig();
        if (restoreDatabaseOnInitialization) {
            restoreDatabase();
        }
    }

    protected void checkConfig() {
        Assert.notNull(databaseBackupLocation, "databaseBackupLocation is required");
    }

    public abstract void cleanDatabase();

    public abstract void dumpDatabase();

    public abstract void restoreDatabase();
}