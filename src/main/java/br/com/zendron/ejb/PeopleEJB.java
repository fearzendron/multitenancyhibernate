package br.com.zendron.ejb;

import br.com.zendron.model.People;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Stateless
public class PeopleEJB implements PeopleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<People> getAll(String tenant) {
        Collection<People> listOfPeople = null;
        EntityManager em = null;

        try {

            listOfPeople = em.createNamedQuery("People.all", People.class).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOfPeople;
    }
}

/*
    SessionImpl tmpsession = (SessionImpl) entityManager.getDelegate();
    SessionFactory sessionFactory = tmpsession.getSessionFactory();
    Session session = sessionFactory.withOptions().tenantIdentifier( "jboss" ).openSession();
*/