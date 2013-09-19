package br.com.zendron.connection;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created with IntelliJ IDEA. User: Zendron Date: 19/09/13 Time: 11:24 To change this template use File | Settings |
 * File Templates.
 */
public class MultiTenantSession {

    private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;

    private MultiTenantSession() {
    }

    public static Session getMultiTenantSession(String tenant) {

        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }

        return sessionFactory.withOptions().tenantIdentifier(tenant).openSession();
    }

    private static SessionFactory buildSessionFactory() {
        Configuration cfg = new Configuration().configure();
        cfg.getProperties().put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);

        serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();

        return cfg.buildSessionFactory(serviceRegistry);
    }


}
