package repository.courseRepository;

import base.repository.BaseRepositoryImpl;
import model.Person;
import repository.personRepository.PersonRepository;

public class CourseRepositoryImpl extends BaseRepositoryImpl<Person,Long> implements PersonRepository {

    @Override
    public Class<Person> getEntityClass() {
        return null;
    }
}
