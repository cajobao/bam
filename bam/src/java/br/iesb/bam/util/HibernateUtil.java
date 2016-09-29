package br.iesb.bam.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateUtil {

    private static HibernateUtil hibernateUtil;
    EntityManagerFactory fabrica;
    EntityManager em;

    private HibernateUtil() {
        fabrica = Persistence.createEntityManagerFactory(Constantes.PERSISTENCE_UNIT_NAME);
        em = fabrica.createEntityManager();
    }

    public static HibernateUtil getInstance() {
        if (HibernateUtil.hibernateUtil == null) {
            HibernateUtil.hibernateUtil = new HibernateUtil();
        }
        return HibernateUtil.hibernateUtil;
    }

    public Session getSessao() {
        return (Session) em.getDelegate();
    }

    public Transaction getTransaction() {
        return getSessao().getTransaction();
    }
}
