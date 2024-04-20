package repository;

import base.connection.SessionFactorySingleton;
import model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class PersonRepository {
    private final SessionFactory factory = SessionFactorySingleton.getInstance();

    public void save(Person person) {
        Session session = factory.openSession();
        session.getTransaction().begin();

        session.persist(person);

        session.getTransaction().commit();
        session.close();
    }

    public Person findById(Long id) {
        Session currentSession = factory.openSession();
        Person person = currentSession.get(Person.class, id);
        return person;
    }

    public List<Person> findAll() {
        Session currentSession = factory.openSession();
        String sql = "from Person";
        Query<Person> fromFilm = currentSession.createQuery(sql, Person.class);
        List<Person> listOfFilm = fromFilm.list();
        return listOfFilm;
    }


    public void remove(Person person) {
        Session session = factory.openSession();
        session.getTransaction().begin();

        session.remove(person);

        session.getTransaction().commit();
        session.close();
    }

    public void update(Person person) {
        Session session = factory.openSession();
        session.getTransaction().begin();

        session.merge(person);

        session.getTransaction().commit();
        session.close();
    }

    public boolean contain(Long id) {
        Session session = factory.openSession();

        Person person = session.find(Person.class,id);

        boolean resualt = session.contains(person);
        session.close();
        return resualt;
    }

}
