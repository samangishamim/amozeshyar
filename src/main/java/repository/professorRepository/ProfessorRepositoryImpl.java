package repository.professorRepository;

import base.repository.BaseRepositoryImpl;
import model.Person;
import repository.personRepository.PersonRepository;

public class ProfessorRepositoryImpl extends BaseRepositoryImpl<Person,Long> implements PersonRepository {
    @Override
    public Class<Person> getEntityClass() {
        return null;
    }
}
