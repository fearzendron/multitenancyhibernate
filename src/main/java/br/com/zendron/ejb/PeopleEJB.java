package br.com.zendron.ejb;

import br.com.zendron.connection.MultiTenantSession;
import br.com.zendron.model.People;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.ejb.Stateless;
import java.util.Collection;

@Stateless
public class PeopleEJB implements PeopleRepository {

    @Override
    public Collection<People> getAll(String tenant) {
        Collection<People> listOfPeople = null;

        try {

            Session session = MultiTenantSession.getMultiTenantSession(tenant);

            Query query = session.getNamedQuery("People.all");
            listOfPeople = query.list();

            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOfPeople;

    }


}
