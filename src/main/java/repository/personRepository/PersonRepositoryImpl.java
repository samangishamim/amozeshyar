package repository.personRepository;

import base.connection.SessionFactorySingleton;
import base.repository.BaseRepositoryImpl;
import model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class PersonRepositoryImpl extends BaseRepositoryImpl<Person,Long> implements PersonRepository  {
    @Override
    public Class getEntityClass() {
        return null;
    }
}
