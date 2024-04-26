package repository.personRepository;

import connection.SessionFactorySingleton;
import base.repository.BaseRepositoryImpl;
import model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class PersonRepositoryImpl extends BaseRepositoryImpl<Person,Long> implements PersonRepository  {
    public PersonRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class getEntityClass() {
        return Person.class;
    }

    @Override
    public String getMyClass() {
        return null;
    }

    @Override
    public boolean signIn(String username, String password) {
        SessionFactory factory = SessionFactorySingleton.getInstance();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Person person = session.get(Person.class, 1L);
            if (person.getUsername().equals(username) && person.getPassword().equals(password)) {
                return true;
            }
            return false;
        }
    }
}
