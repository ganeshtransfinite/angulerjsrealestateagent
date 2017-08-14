/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.backup;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.hibernate.HibernateException;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
 
import org.springframework.util.Assert;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;

/**
 * An implementation of DabaseBackuper that approaches the database through
 * Hibernate and the backup file through xstream.
 * <p/>
 * An example that loads test data upon initialization:
 * <p/>
 * <
 * pre>
 * &lt;bean id="databaseBackuper"
 *     class="be.kahosl.mammoet.serviceimpl.app.database.Hibernate3ToXstreamXmlDatabaseBackuper"&gt;
 *   &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *   &lt;property name="databaseBackupLocation"
 *       value="classpath:/org/springframework/petclinic/testDatabaseBackup.xml"/&gt;
 *   &lt;property name=xstreamMode"&gt;
 *     &lt;bean id="com.thoughtworks.xstream.XStream.ID_REFERENCES"
 *         class="org.springframework.beans.factory.config.FieldRetrievingFactoryBean"/&gt;
 *   &lt;/property&gt;
 * &lt;property name="restoreDatabaseOnInitialization" value="true"/&gt;
 * &lt;/bean&gt;
 * </pre>
 * <p/>
 * Requires Xstream to be in the classpath.
 *
 * @author Geoffrey De Smet
 * @see DatabaseBackuper
 */
public class Hibernate3ToXstreamXmlDatabaseBackuper extends AbstractDatabaseBackuper {

    private HibernateTemplate hibernateTemplate;

    private Class[] databaseClasses = new Class[]{Object.class};
    private String databaseBackupEncoding = "UTF-8";
    private boolean useSaveOrUpdateToRestore = false;
    private ReplicationMode restoreReplicationMode = ReplicationMode.OVERWRITE;
    private int xstreamMode = XStream.XPATH_REFERENCES;

    /**
     * Set the Hibernate SessionFactory to be used by this DAO. Will
     * automatically create a HibernateTemplate for the given SessionFactory.
     *
     * @see #createHibernateTemplate
     * @see #setHibernateTemplate
     */
    public final void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = createHibernateTemplate(sessionFactory);
    }

    /**
     * Create a HibernateTemplate for the given SessionFactory. Only invoked if
     * populating the DAO with a SessionFactory reference!
     * <p>
     * Can be overridden in subclasses to provide a HibernateTemplate instance
     * with different configuration, or a custom HibernateTemplate subclass.
     *
     * @param sessionFactory the Hibernate SessionFactory to create a
     * HibernateTemplate for
     * @return the new HibernateTemplate instance
     * @see #setSessionFactory
     */
    protected HibernateTemplate createHibernateTemplate(SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }

    /**
     * Set the HibernateTemplate for this DAO explicitly, as an alternative to
     * specifying a SessionFactory.
     *
     * @see #setSessionFactory
     */
    public final void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /**
     * Set the list of Class objects which will be backuped. Note that
     * {@link #restoreDatabase()} fails if any element directly under the root
     * element (of the database backup file) is not an instance of any of these
     * database classes. Defaults to a list with a single element: the class of
     * Object.
     *
     * @param databaseClasses a list of classes to clean, dump and restore
     */
    public void setDatabaseClasses(Class[] databaseClasses) {
        this.databaseClasses = databaseClasses;
    }

    /**
     * Set the encoding of the database backup resource. Defaults to UTF-8.
     *
     * @param databaseBackupEncoding the encoding of the database backup
     * resource
     */
    public void setDatabaseBackupEncoding(String databaseBackupEncoding) {
        this.databaseBackupEncoding = databaseBackupEncoding;
    }

    /**
     * Set wheter to use replicate or saveOrUpdate to restore the database.
     * Defaults to false, which means that replicate is used.
     *
     * @param useSaveOrUpdateToRestore true when saveOrUpdate should be used to
     * restore
     */
    public void setUseSaveOrUpdateToRestore(boolean useSaveOrUpdateToRestore) {
        this.useSaveOrUpdateToRestore = useSaveOrUpdateToRestore;
    }

    /**
     * Set the hibernate replication mode used when restoring the database.
     * Defaults to OVERWRITE, which means that any existing records will be
     * overwritten.
     *
     * @param restoreReplicationMode the hibernate replication mode
     */
    public void setRestoreReplicationMode(ReplicationMode restoreReplicationMode) {
        this.restoreReplicationMode = restoreReplicationMode;
    }

    /**
     * Set the xstream mode of the database backup resource. Defaults to
     * XStream.XPATH_REFERENCES. This is typically injected by use of
     * FieldRetrievingFactoryBean bean.
     *
     * @param xstreamMode can be XStream.XPATH_REFERENCES,
     * XStream.ID_REFERENCES, XStream.NO_REFERENCES
     */
    public void setXstreamMode(int xstreamMode) {
        this.xstreamMode = xstreamMode;
    }

    protected void checkConfig() {
        super.checkConfig();
        Assert.notNull(hibernateTemplate, "sessionFactory or hibernateTemplate is required");
    }

    public void cleanDatabase() {
        // Rollback to java 1.4 compatible
//        for (Class databaseClass : databaseClasses) {
        for (int i = 0; i < databaseClasses.length; i++) {
            Class databaseClass = databaseClasses[i];
            List recordList = hibernateTemplate.loadAll(databaseClass);
            hibernateTemplate.deleteAll(recordList);
        }
        logger.debug("Database cleaned");
    }

    public void dumpDatabase() {
        List recordList = new LinkedList();
        // Rollback to java 1.4 compatible
//        for (Class databaseClass : databaseClasses) {
        for (int i = 0; i < databaseClasses.length; i++) {
            Class databaseClass = databaseClasses[i];
            recordList.addAll(hibernateTemplate.loadAll(databaseClass));
        }
        XStream xStream = new XStream(new DomDriver());
        xStream.setMode(xstreamMode);
        try {
            OutputStream out = new FileOutputStream(databaseBackupLocation.getFile());
            try {
                xStream.toXML(recordList, new OutputStreamWriter(out, databaseBackupEncoding));
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed dumping the database to "
                    + databaseBackupLocation, e);
        }
        logger.debug("Database dumped");
    }

    public void restoreDatabase() {
        if (cleanDatabaseBeforeRestore) {
            cleanDatabase();
        }
        final List recordList;
        XStream xStream = new XStream(new DomDriver());
        xStream.setMode(xstreamMode);
        try {
            InputStream in = databaseBackupLocation.getInputStream();
            try {
                Object o = xStream.fromXML(new InputStreamReader(in, databaseBackupEncoding));
                if (o instanceof List) {
                    recordList = (List) o;
                } else {
                    throw new IllegalArgumentException("Root element of the database backup resource should be a list");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed restoring the database from "
                    + databaseBackupLocation, e);
        }
        checkRecordList(recordList);
//        if (useSaveOrUpdateToRestore) {
//            hibernateTemplate.saveOrUpdateAll(recordList);
//        } else 
        {
            // hibernateTemplate.replicateAll() is not supported in HibernateTemplate
//            hibernateTemplate.execute(new HibernateCallback<Object>() {
//                public Object doInHibernate(Session session) throws HibernateException {
//                    // hibernateTemplate.checkWriteOperationAllowed(session); has non-public access on the template
//                    // Rollback to java 1.4 compatible
                    //                for (Object record : recordList) {
                    for (Iterator it = recordList.iterator(); it.hasNext();) {
                        Object record = it.next();
                        hibernateTemplate.replicate(record, restoreReplicationMode);
                        //session.replicate(record, restoreReplicationMode);
                    }
//                    return null;
//                }
//            }, true);
        }
        logger.debug("Database restored");
    }

    /**
     * check if all records are indeed an instance of one of the databaseClasses
     *
     * @param recordList list of objects to check
     */
    private void checkRecordList(List recordList) {
        // Rollback to java 1.4 compatible
//        for (Object record : recordList) {
        for (Iterator it = recordList.iterator(); it.hasNext();) {
            Object record = it.next();
            boolean instanceOfDatabaseClasses = false;
            // Rollback to java 1.4 compatible
//            for (Class databaseClass : databaseClasses) {
            for (int i = 0; i < databaseClasses.length; i++) {
                Class databaseClass = databaseClasses[i];
                if (databaseClass.isInstance(record)) {
                    instanceOfDatabaseClasses = true;
                    break;
                }
            }
            if (!instanceOfDatabaseClasses) {
                throw new IllegalArgumentException(
                        "The object found to restore is not an instance of one of the database classes:" + record);
            }
        }
    }
}
