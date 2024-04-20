package service.personService;

import base.service.BaseServiceImpl;
import model.Person;
import org.hibernate.SessionFactory;
import repository.personRepository.PersonRepository;

public class PersonServiceImpl extends BaseServiceImpl<Person, Long, PersonRepository> implements PersonService{
    public PersonServiceImpl(PersonRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
