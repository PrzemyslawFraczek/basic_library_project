package pl.sda.util;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@Slf4j
public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            StandardServiceRegistry standardRegistry =
                    new StandardServiceRegistryBuilder().configure().build();
            Metadata metaData =
                    new MetadataSources(standardRegistry).getMetadataBuilder().build();
            SESSION_FACTORY = metaData.getSessionFactoryBuilder().build();
        } catch (Exception e) {
            log.error("Enitial SessionFactory creation failed {}", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Session openSession() {
        return SESSION_FACTORY.openSession();
    }
}
