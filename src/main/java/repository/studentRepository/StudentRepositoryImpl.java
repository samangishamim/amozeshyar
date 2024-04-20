package repository.studentRepository;

import base.repository.BaseRepositoryImpl;
import model.Person;
import repository.personRepository.PersonRepository;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Person,Long> implements PersonRepository {
    @Override
    public Class<Person> getEntityClass() {
        return null;
    }
}
