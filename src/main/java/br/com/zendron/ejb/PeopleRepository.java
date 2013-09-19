package br.com.zendron.ejb;

import br.com.zendron.model.People;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA. User: Zendron Date: 17/09/13 Time: 19:35 To change this template use File | Settings |
 * File Templates.
 */
@Local
public interface PeopleRepository {

    Collection<People> getAll(String tenant);

}
